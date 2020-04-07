package org.glamey.training.algorithm.loadbalance.roundrobbin;

import org.glamey.training.algorithm.loadbalance.LoadBalance;
import org.glamey.training.algorithm.loadbalance.domian.ServerIp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询算法
 */
public class RoundRobbinLB implements LoadBalance {

    private static final AtomicInteger count = new AtomicInteger(0);

    @Override
    public String getServer() {
        if (count.get() >= ServerIp.IPS.size()) {
            count.set(0);
        }
        String server = ServerIp.IPS.get(count.get());
        count.incrementAndGet();
        return server;
    }


    public static void main(String[] args) {
        RoundRobbinLB loadBalance = new RoundRobbinLB();
        for (int i = 0; i < 10; i++) {
            System.out.println(loadBalance.getServer());
        }
    }
}
