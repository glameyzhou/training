package org.glamey.training.algorithm.hash.consistent_hashing;

/**
 * @author zhouyang.zhou. 2017.05.15.15.
 */
public abstract class ShardInfo<R> {

    private int weight;

    public ShardInfo(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    protected abstract R createResource();

    public abstract String getName();
}
