package org.glamey.training.algorithm.hash.consistent_hashing;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouyang.zhou. 2017.05.15.15.
 */
public class Sharing<R, S extends ShardInfo<R>> {
  public static final int DEFAULT_WEIGHT = 1;
  public static final int FACTOR = 160;
  private TreeMap<Long, S> nodes;
  private final Hashing algo;
  private final Map<ShardInfo<R>, R> resources = new LinkedHashMap<ShardInfo<R>, R>();
  private Pattern tagPattern = null;
  // the tag is anything between {}
  public static final Pattern DEFAULT_KEY_TAG_PATTERN = Pattern
      .compile("\\{(.+?)\\}");

  public Sharing(List<S> shards) {
    this(shards, Hashing.MURMUR_HASH);
  }

  public Sharing(List<S> shards, Hashing murmurHash) {
    this.algo = murmurHash;
    initialize(shards);
  }

  private void initialize(List<S> shards) {
    nodes = new TreeMap<Long, S>();
    for (int i = 0; i < shards.size(); i++) {
      S shardInfo = shards.get(i);
      if (shardInfo.getName() == null) {
        for (int n = 0; n < FACTOR * shardInfo.getWeight(); n++) {
          nodes.put(
              this.algo.hash("SHARD-" + i + "-NODE-" + n),
              shardInfo
          );
        }
      } else {
        for (int n = 0; n < FACTOR * shardInfo.getWeight(); n++) {
          nodes.put(
              this.algo.hash(shardInfo.getName() + "*" + shardInfo.getWeight() + n),
              shardInfo
          );
        }
      }
      resources.put(shardInfo, shardInfo.createResource());
    }
  }

  public R getShard(byte[] key) {
    return resources.get(getShardInfo(key));
  }

  public R getShard(String key) {
    return resources.get(getShardInfo(key));
  }

  private S getShardInfo(byte[] key) {
    SortedMap<Long, S> tailMap = nodes.tailMap(this.algo.hash(key));
    if (tailMap.isEmpty()) {
      return nodes.get(nodes.firstKey());
    }
    return tailMap.get(tailMap.firstKey());
  }

  public S getShardInfo(String key) {
    return getShardInfo(SafeEncoder.encode(getKeyTag(key)));
  }

  public String getKeyTag(String key) {
    if (tagPattern != null) {
      Matcher m = tagPattern.matcher(key);
      if (m.find()) {
        return m.group(1);
      }
    }
    return key;
  }

  public Collection<S> getAllShardInfo() {
    return Collections.unmodifiableCollection(nodes.values());
  }

  public Collection<R> getAllShards() {
    return Collections.unmodifiableCollection(resources.values());
  }
}
