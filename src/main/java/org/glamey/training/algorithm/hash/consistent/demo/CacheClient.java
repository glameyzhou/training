package org.glamey.training.algorithm.hash.consistent.demo;

import org.glamey.training.algorithm.hash.consistent.ShardingCluster;

import java.util.List;

/**
 * @author yang.zhou 2019.11.04.18
 */
public class CacheClient extends ShardingCluster<CacheResource, CacheNode> {
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
