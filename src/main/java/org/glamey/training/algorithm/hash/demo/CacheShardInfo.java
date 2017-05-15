package org.glamey.training.algorithm.hash.demo;

import java.net.URI;
import org.glamey.training.algorithm.hash.consistent_hashing.ShardInfo;
import org.glamey.training.algorithm.hash.consistent_hashing.Sharing;

/**
 * @author zhouyang.zhou. 2017.05.15.16.
 */
public class CacheShardInfo extends ShardInfo<Cache> {

  private String host;
  private int port;
  private int timeout;
  private String password = null;
  private String name = null;

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CacheShardInfo(String host) {
    super(Sharing.DEFAULT_WEIGHT);
    URI uri = URI.create(host);
    if (uri.getScheme() != null && uri.getScheme().equals("redis")) {
      this.host = uri.getHost();
      this.port = uri.getPort();
      this.password = uri.getUserInfo().split(":", 2)[1];
    } else {
      this.host = host;
      this.port = 3679;
    }
  }

  public CacheShardInfo(URI uri) {
    super(Sharing.DEFAULT_WEIGHT);
    this.host = uri.getHost();
    this.port = uri.getPort();
    this.password = uri.getUserInfo().split(":", 2)[1];
  }

  public CacheShardInfo(int weight) {
    super(Sharing.DEFAULT_WEIGHT);
  }

  @Override protected Cache createResource() {
    return new Cache(this);
  }

  @Override public String getName() {
    return name;
  }
}
