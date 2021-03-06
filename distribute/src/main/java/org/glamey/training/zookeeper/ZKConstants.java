package org.glamey.training.zookeeper;

import com.google.common.base.Charsets;

import java.nio.charset.Charset;

/**
 * 测试使用的全局变量
 *
 * @author zhouyang.zhou. 2017.11.07.18.
 */
public class ZKConstants {

  public static final String ZK_ADDRESS = "glamey.local:3301,glamey.local:3302,glamey.local:3303";
  public static final String ZK_ROOT = "/zk_demo";
  public static final String LEADER_ROOT = ZK_ROOT + "/leader";

  public static final Charset UTF_8 = Charsets.UTF_8;
}
