package org.glamey.training.codes.hash.slot;

import java.util.HashMap;
import java.util.Map;

import org.glamey.training.codes.hash.consistent.ShardInfo;

/**
 * hash slot
 *
 * @author yang.zhou 2019.11.04.18
 */
public class SlotShardingCluster<R, S extends ShardInfo<R>> {
    private final Map<String, R> nodes = new HashMap<>(1000);
    private final Map<Long, R> slots = new HashMap<>(16384);
}
