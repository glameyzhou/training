package org.glamey.training.codes.hash.demo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * @author yang.zhou 2019.11.04.18
 */
public class Main {

    public static void main(String[] args) {
        List<CacheNode> nodes = buildCacheNodes();
        CacheClient client = new CacheClient(nodes);

        for (int i = 0; i < 100; i++) {
            client.set("c_" + i, "b_" + i);
        }

        for (int i = 0; i < 100; i++) {
            Object obj = client.get("c_" + new Random().nextInt(100));
            System.out.println(obj);
        }
    }

    private static List<CacheNode> buildCacheNodes() {
        CacheNode node1 = new CacheNode(2);
        node1.setIp("192.168.1.1");
        node1.setPort(6371);

        CacheNode node2 = new CacheNode(3);
        node2.setIp("192.168.1.2");
        node2.setPort(6371);

        CacheNode node3 = new CacheNode(2);
        node3.setIp("192.168.1.3");
        node3.setPort(6371);

        return Lists.newArrayList(node1, node2, node3);
    }
}
