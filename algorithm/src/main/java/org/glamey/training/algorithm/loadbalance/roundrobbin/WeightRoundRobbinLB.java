package org.glamey.training.algorithm.loadbalance.roundrobbin;

import org.glamey.training.algorithm.loadbalance.LoadBalance;
import org.glamey.training.algorithm.loadbalance.domian.ServerIp;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于权重的轮询算法
 * <p>
 * A:5
 * B:3
 * C:2
 */
public class WeightRoundRobbinLB implements LoadBalance {

    private AtomicInteger count = new AtomicInteger(0);

    private int getId() {
        return count.incrementAndGet();
    }


    @Override
    public String getServer() {
        int total = ServerIp.WEIGHT_IP_MAP.values().stream().mapToInt(e -> e).sum();

        int offset = getId() % total;

        for (Map.Entry<String, Integer> entry : ServerIp.WEIGHT_IP_MAP.entrySet()) {
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
