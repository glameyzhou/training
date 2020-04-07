package org.glamey.training.algorithm.loadbalance.random;


import org.glamey.training.algorithm.loadbalance.LoadBalance;
import org.glamey.training.algorithm.loadbalance.domian.ServerIp;

import java.util.Random;

/**
 * 随机算法。
 * <p>
 * 根据随机数算法找到任意的一个主机节点
 */
public class RandomLB implements LoadBalance {

    @Override
    public String getServer() {
        int size = ServerIp.IPS.size();
        return ServerIp.IPS.get(new Random().nextInt(size));
    }


    public static void main(String[] args) {
        RandomLB randomLoadBalance = new RandomLB();
        for (int i = 0; i < 100; i++) {
            System.out.println(randomLoadBalance.getServer());
        }
    }
}
