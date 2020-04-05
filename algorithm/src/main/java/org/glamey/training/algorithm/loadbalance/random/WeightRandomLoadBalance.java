package org.glamey.training.algorithm.loadbalance.random;

import org.glamey.training.algorithm.loadbalance.LoadBalance;
import org.glamey.training.algorithm.loadbalance.ServerIp;

import java.util.Map;
import java.util.Random;

public class WeightRandomLoadBalance implements LoadBalance {

    /**
     * 我们按照坐标来算
     * 0-----A-----B----C
     * @return
     */
    @Override
    public String getServer() {
        Map<String, Integer> weightIpMap = ServerIp.WEIGHT_IP_MAP;
        int total = ServerIp.WEIGHT_IP_MAP.values().stream().mapToInt(value -> value).sum();
        int offset = new Random().nextInt(total);

        for (Map.Entry<String, Integer> entry : weightIpMap.entrySet()) {
            if (offset <= entry.getValue()) {
                return entry.getKey();
            }
            offset = offset - entry.getValue();
        }
        return null;
    }

    public static void main(String[] args) {
        WeightRandomLoadBalance loadBalance = new WeightRandomLoadBalance();
        for (int i = 0; i < 10; i++) {
            System.out.println(loadBalance.getServer());
        }
    }
}
