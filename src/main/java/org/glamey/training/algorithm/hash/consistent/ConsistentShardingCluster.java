package org.glamey.training.algorithm.hash.consistent;

import org.glamey.training.algorithm.hash.Hashing;
import org.glamey.training.algorithm.hash.SafeEncoder;

import java.util.*;

/**
 * 一致性hash
 *
 * @author yang.zhou 2019.11.04.17
 */
public class ConsistentShardingCluster<R, S extends ShardInfo<R>> {

    public static final int DEFAULT_WEIGHT = 1;
    public static final int FACTOR = 160;
    private TreeMap<Long, S> nodes;
    private final Hashing algorithm;
    private final Map<ShardInfo<R>, R> resources = new LinkedHashMap<>();


    public ConsistentShardingCluster(List<S> shards) {
        this(shards, Hashing.MD5);
    }

    public ConsistentShardingCluster(List<S> shards, Hashing algorithm) {
        this.algorithm = algorithm;
        initialize(shards);
    }

    private void initialize(List<S> shards) {
        nodes = new TreeMap<>();
        for (int i = 0; i < shards.size(); i++) {
            S shardInfo = shards.get(i);
            String name = shardInfo.getName();
            int weight = shardInfo.getWeight() <= 0 ? DEFAULT_WEIGHT : shardInfo.getWeight();
            String key;
            for (int j = 0; j < FACTOR * weight; j++) {
                key = null == name || "".equals(name) ?
                        "SHARD-" + i + "-NODE-" + j : name + "-NODE-" + j;
                nodes.put(algorithm.hash(SafeEncoder.encode(key)), shardInfo);
            }
            resources.put(shardInfo, shardInfo.createResource());
        }
    }

    public final R getShard(byte[] key) {
        S shardInfo = getShardInfo(key);
        return resources.get(shardInfo);
    }

    public final R getShard(String key) {
        return getShard(SafeEncoder.encode(key));
    }


    public final S getShardInfo(byte[] key) {
        SortedMap<Long, S> tailMap = nodes.tailMap(algorithm.hash(key));
        if (tailMap.isEmpty()) {
            return nodes.get(nodes.firstKey());
        }
        return tailMap.get(tailMap.firstKey());
    }

    public final S getShardInfo(String key) {
        return getShardInfo(SafeEncoder.encode(key));
    }
}
