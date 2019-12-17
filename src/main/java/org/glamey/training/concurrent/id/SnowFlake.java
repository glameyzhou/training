package org.glamey.training.concurrent.id;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 简单的 SnowFlake 算法，性能是有很大问题的。需要做优化
 *
 * @author yang.zhou 2019.12.17.10
 */
public class SnowFlake {

    //起始时间戳
    private static final long START_TIMESTAMP = 1576548921000L;

    //每一部分的占位
    private static final long BIT_SEQ = 12;
    private static final long BIT_NODE = 5;
    private static final long BIT_DATA_CENTER = 5;
    private static final long BIT_TIME = 41;

    //每一部分的最大值
    private static final long MAX_DATA_CENTER_NUM = 1 << BIT_DATA_CENTER;
    private static final long MAX_NODE_NUM = 1 << BIT_NODE;
    private static final long MAX_SEQ_NUM = 1 << BIT_SEQ;

    //每一部分向左的位移
    private static final long LEFT_NODE = BIT_SEQ;
    private static final long LEFT_DATA_CENTER = BIT_SEQ + BIT_NODE;
    private static final long LEFT_TIMESTAMP = LEFT_DATA_CENTER + BIT_DATA_CENTER;

    private long dataCenterId;
    private long nodeId;
    private long seq = 0L;
    private long lastTimeStamp = -1L;

    public SnowFlake(long dataCenterId, long nodeId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("data center Id can not great than %d or less then zero",
                    MAX_DATA_CENTER_NUM));
        }

        if (nodeId > MAX_NODE_NUM || nodeId < 0) {
            throw new IllegalArgumentException(String.format("machine Id can not great than %d or less then zero",
                    MAX_NODE_NUM));
        }

        this.dataCenterId = dataCenterId;
        this.nodeId = nodeId;
    }


    public synchronized long nextId() {
        long currentTimeStamp = getNowTimeStamp();
        if (currentTimeStamp < lastTimeStamp) {
            throw new RuntimeException(String.format("the Clock moves back....[currentTimeStamp=%d]", currentTimeStamp));
        }
        //相同毫秒内递增
        if (currentTimeStamp == lastTimeStamp) {
            seq = (seq + 1) & MAX_SEQ_NUM;
            if (seq == 0L) {
                currentTimeStamp = getNextMill();
            }
        }
        //不同毫秒内，设置0
        else {
            seq = 0L;
        }
        lastTimeStamp = currentTimeStamp;
        return (currentTimeStamp - START_TIMESTAMP) << LEFT_TIMESTAMP
                | dataCenterId << LEFT_DATA_CENTER
                | nodeId << LEFT_NODE
                | seq;
    }

    private long getNextMill() {
        long mill = getNowTimeStamp();
        while (mill <= lastTimeStamp) {
            mill = getNowTimeStamp();
        }
        return mill;
    }


    private long getNowTimeStamp() {
        return System.currentTimeMillis();
    }


    public static final long idToTimeStamp(long id) {
        return (id >> 22) + START_TIMESTAMP;
    }


    public static void main(String[] args) {
        SnowFlake snowFlake = new SnowFlake(10, 10);
        long id;
        for (int i = 0; i < 100; i++) {
            id = snowFlake.nextId();
            String format = DateFormatUtils.format(idToTimeStamp(id), "yyyy-MM-dd HH:mm:ss:SSS");
            System.out.println(id + "  " + format);
        }
    }
}
