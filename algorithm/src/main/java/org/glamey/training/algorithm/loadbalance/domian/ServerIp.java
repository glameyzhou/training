package org.glamey.training.algorithm.loadbalance.domian;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServerIp {
    public static final Map<String, Integer> IP_WEIGHT_MAP = new LinkedHashMap<>();
    public static final List<String> IPS = new ArrayList<>();

    static {
        IP_WEIGHT_MAP.put("192.168.0.1", 5);
        IP_WEIGHT_MAP.put("192.168.0.2", 3);
        IP_WEIGHT_MAP.put("192.168.0.3", 2);

        IPS.addAll(IP_WEIGHT_MAP.keySet());

    }
}
