package org.glamey.training.codes.loadbalance.random;

import org.glamey.training.codes.loadbalance.LoadBalance;
import org.glamey.training.codes.loadbalance.domian.ServerIp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.ToIntFunction;

/**
 * 加权随机算法
 * 两种实现
 * 1、根据权重值新增同量级的IP至列表中，然后通过随机数取出即可。缺点：如果权重很大的话，导致列表增大。
 * 2、根据坐标概念来算。根据权重大小均放置在X轴上面
 * A:5
 * B:3
 * C:2
 * <p>
 * 形成的坐标类似：0-----5---3--2
 * 执行步骤：
 * 1、拿到随机数 random。
 * 2、遍历列表，看 random 小于等于当前权重，如果是，返回当前IP，反之 random -= 当前权重，继续循环。
 */
public class WeightRandomLB implements LoadBalance {

    /**
     * 我们按照坐标来算
     * 0-----A-----B----C
     *
     * @return
     */
    @Override
    public String getServer() {
        Map<String, Integer> weightIpMap = ServerIp.IP_WEIGHT_MAP;
        int total = ServerIp.IP_WEIGHT_MAP.values().stream().mapToInt(Integer::intValue).sum();
        int offset = ThreadLocalRandom.current().nextInt(total);

        for (Map.Entry<String, Integer> entry : weightIpMap.entrySet()) {
            if (offset <= entry.getValue()) {
                return entry.getKey();
            }
            offset -= entry.getValue();
        }
        return null;
    }

    public static void main(String[] args) {
        int count = 100000;
        HashMap<String, Integer> analyzerMap = new HashMap<>(count);
        WeightRandomLB loadBalance = new WeightRandomLB();
        String ip;
        for (int i = 0; i < count; i++) {
            ip = loadBalance.getServer();
            analyzerMap.put(ip, analyzerMap.getOrDefault(ip, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : analyzerMap.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());
    }
}
