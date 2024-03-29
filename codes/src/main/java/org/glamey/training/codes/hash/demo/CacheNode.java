package org.glamey.training.codes.hash.demo;

import org.glamey.training.codes.hash.consistent.ShardInfo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yang.zhou 2019.11.04.18
 */
public class CacheNode extends ShardInfo<CacheResource> {
    @Getter
    @Setter
    private String ip;
    @Getter
    @Setter
    private int port;

    public CacheNode(int weight) {
        super(weight);
    }

    @Override
    public String getName() {
        return ip + ":" + port;
    }

    @Override
    public CacheResource createResource() {
        return new CacheResource(this);
    }
}
