package org.glamey.training.codes.loadbalance.roundrobbin;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.glamey.training.codes.loadbalance.LoadBalance;
import org.glamey.training.codes.loadbalance.domian.ServerIp;

/**
 * 基于权重的轮询算法
 * <p>
 * A:5
 * B:3
 * C:2
 */
public class WeightRoundRobbinLB implements LoadBalance {

    private static final AtomicInteger COUNT = new AtomicInteger(0);

    private int getId() {
        return COUNT.incrementAndGet();
    }


    @Override
    public String getServer() {
        int total = ServerIp.IP_WEIGHT_MAP.values().stream().mapToInt(e -> e).sum();
        int offset = getId() % total;
        for (Map.Entry<String, Integer> entry : ServerIp.IP_WEIGHT_MAP.entrySet()) {
            if (offset <= entry.getValue()) {
                return entry.getKey();
            }
            offset = offset - entry.getValue();
        }
        return null;
    }


    public static void main(String[] args) {
        WeightRoundRobbinLB loadBalance = new WeightRoundRobbinLB();
        for (int i = 0; i < 10; i++) {
            System.out.println(loadBalance.getServer());
        }
    }
}
