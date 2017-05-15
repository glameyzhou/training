package org.glamey.training.algorithm;

import java.util.Stack;

/**
 * @author zhouyang.zhou. 2017.05.09.15.
 */
public class ReverseWord {

  public static void main(String[] args) {
    String string = "hello world java";
    Stack<String> stack = new Stack<>();
    char[] chars = string.toCharArray();
    StringBuilder builder = new StringBuilder(20);
    for (char aChar : chars) {
      boolean whitespace = Character.isWhitespace(aChar);
      if (whitespace) {
        if (builder.length() > 0) {
          stack.push(builder.toString());
          builder.setLength(0);
        }
      } else {
        builder.append(aChar);
      }
    }

    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }
}
