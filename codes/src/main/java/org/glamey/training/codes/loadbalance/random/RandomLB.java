package org.glamey.training.codes.loadbalance.random;


import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

import com.google.common.collect.Maps;
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
        Map<String, Integer> map = Maps.newConcurrentMap();
        for (int i = 0; i < 100; i++) {
            String server = randomLoadBalance.getServer();
            map.compute(server, (key, oldVal) -> oldVal == null ? 1 : oldVal + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }
}
