package org.glamey.training.codes.hash.consistent;

/**
 * 节点相关信息
 *
 * @author yang.zhou 2019.11.04.17
 */
public abstract class ShardInfo<R> {
    private final int weight;

    public ShardInfo(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public abstract String getName();

    public abstract R createResource();
}
