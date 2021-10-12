package org.glamey.training.codes.loadbalance.random;


import java.util.concurrent.ThreadLocalRandom;

import org.glamey.training.codes.loadbalance.LoadBalance;
import org.glamey.training.codes.loadbalance.domian.ServerIp;

/**
 * 随机算法。
 * <p>
 * 根据随机数算法找到任意的一个主机节点
 */
public class RandomLB implements LoadBalance {

    @Override
    public String getServer() {
        int size = ServerIp.IPS.size();
        return ServerIp.IPS.get(ThreadLocalRandom.current().nextInt(size));
    }


    public static void main(String[] args) {
        RandomLB randomLoadBalance = new RandomLB();
        for (int i = 0; i < 100; i++) {
            System.out.println(randomLoadBalance.getServer());
        }
    }
}
