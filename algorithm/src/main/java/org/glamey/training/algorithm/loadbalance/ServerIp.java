package org.glamey.training.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerIp {
    public static final Map<String, Integer> WEIGHT_IP_MAP = new LinkedHashMap<>();
    public static final List<String> IPS = new ArrayList<>();

    static {
        WEIGHT_IP_MAP.put("192.168.0.1", 5);
        WEIGHT_IP_MAP.put("192.168.0.2", 3);
        WEIGHT_IP_MAP.put("192.168.0.3", 2);

        IPS.addAll(WEIGHT_IP_MAP.keySet());
    }


}
