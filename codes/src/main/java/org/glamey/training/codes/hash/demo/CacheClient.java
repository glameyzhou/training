package org.glamey.training.codes.hash.demo;

import java.util.List;

import org.glamey.training.codes.hash.consistent.ConsistentShardingCluster;

/**
 * @author yang.zhou 2019.11.04.18
 */
public class CacheClient extends ConsistentShardingCluster<CacheResource, CacheNode> {
    public CacheClient(List<CacheNode> shards) {
        super(shards);
    }


    public Object get(String key) {
        CacheResource shard = super.getShard(key);
        return shard.get(key);
    }

    public void set(String key, Object value) {
        CacheResource shard = super.getShard(key);
        shard.set(key, value);
    }
}
