package org.glamey.training.zk;

import com.google.common.base.Charsets;

/**
 * @author zhouyang.zhou. 2017.09.18.15.
 */
public class ZKSerializer {

  public static final byte[] stringToByte(String source) {
    if(source == null) {
      return null;
    }

    return source.getBytes(Charsets.UTF_8);
  }

  public static final String byteToString(byte[] source) {
    if(source == null) {
      return null;
    }

    return new String(source, Charsets.UTF_8);
  }
}
