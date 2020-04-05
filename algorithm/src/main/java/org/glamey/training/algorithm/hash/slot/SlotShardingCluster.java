package org.glamey.training.algorithm.hash.slot;

import org.glamey.training.algorithm.hash.consistent.ShardInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * hash slot
 *
 * @author yang.zhou 2019.11.04.18
 */
public class SlotShardingCluster<R, S extends ShardInfo<R>> {
    private Map<String, R> nodes = new HashMap<>(1000);
    private Map<Long, R> slots = new HashMap<>(16384);
}
