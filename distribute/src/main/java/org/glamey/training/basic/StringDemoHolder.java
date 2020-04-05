package org.glamey.training.basic;

/**
 * @author zhouyang.zhou.
 * @date 2017.12.13.11.
 */
public class StringDemoHolder {

  private StringDemoHolder() {
  }

  public static class Holder {
    private static final StringDemoHolder STRING_DEMO_HOLDER = new StringDemoHolder();
  }

  public static StringDemoHolder getInstance() {
    return Holder.STRING_DEMO_HOLDER;
  }
}


