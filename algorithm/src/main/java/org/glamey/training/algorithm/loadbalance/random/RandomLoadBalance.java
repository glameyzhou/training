package org.glamey.training.algorithm.loadbalance.random;


import org.glamey.training.algorithm.loadbalance.LoadBalance;
import org.glamey.training.algorithm.loadbalance.ServerIp;

import java.util.Random;

public class RandomLoadBalance implements LoadBalance {
    
    @Override
    public String getServer() {
        int size = ServerIp.IPS.size();
        return ServerIp.IPS.get(new Random().nextInt(size ));
    }


    public static void main(String[] args) {
        RandomLoadBalance randomLoadBalance = new RandomLoadBalance();
        for (int i = 0; i < 100; i++) {
            System.out.println(randomLoadBalance.getServer());
        }
    }
}
