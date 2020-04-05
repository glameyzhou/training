package org.glamey.training.algorithm.leetcode;

import org.springframework.util.StringUtils;

/**
 * 给定字符串 abc\bd\bghi ,删除字符\b前边的一个字符，如果\b出现多次，统一按照此逻辑执行 <p>
 *
 * 解决方法：<p>
 *
 * 1、将字符串转化为char来操作，声明结果char[]、非removeStr的指针；非removeStr放入char[]，指针+1,removeStr不放入char[]中，指针-1，通过后续的值进行覆盖操作即可。<p>
 * 2、利用StringBuffer的append,deleteCharAt。<p>
 * 3、通过递归的方式实现。
 *
 * @author zhouyang.zhou. 2017.08.30.17.
 */
public class RemoveSpecialCharacter {
  private static final char removeStr = '\b';

  public static String remove_1(String target) {
    if (StringUtils.isEmpty(target)) {
      return target;
    }

    int position = 0;
    char result[] = new char[target.length()];
    for (int i = 0; i < target.length(); i++) {
      char current = target.charAt(i);
      if (current != removeStr) {
        result[position++] = current;
      } else {
        if (position > 0) {
          position--;
        }
      }
    }
    return String.valueOf(result, 0, position);
  }

  public static String remove_2(String target) {
    if (StringUtils.isEmpty(target)) {
      return target;
    }

    StringBuilder builder = new StringBuilder(target.length());
    int count = 0;
    for (int i = 0; i < target.length(); i++) {
      char current = target.charAt(i);
      if (current != removeStr) {
        builder.append(current);
        count++;
      } else {
        if (count > 0) {
          builder.deleteCharAt(count - 1);
          count--;
        }
      }
    }
    return builder.toString();
  }

  public static String remove_3(String target) {
    if (StringUtils.isEmpty(target)) {
      return target;
    }
    return null;
  }

  public static void main(String[] args) {
    //String target = "abc\b\bd\b\bghi";
    String target = "abc\bd\bghi";
    System.out.println(remove_1(target));
    System.out.println(remove_2(target));
  }
}
