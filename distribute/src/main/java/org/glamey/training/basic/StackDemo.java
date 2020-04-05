package org.glamey.training.basic;

import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 数据太大，放入LONG中可能出现溢出，因此将他们变成单向链表。
 *
 * 将两个链表相加，求结果值
 *
 * Number 1 = "1,2,3,4,5,6,7,8,9";
 * Number 2 = "4,5,6,7,8,9";
 *
 * 计算方式：分别将两个数字放入栈中，依次出栈相加，结果大于9进1即可。
 *
 * @author zhouyang.zhou. 2017.05.17.10.
 */
public class StackDemo {

  public static void main(String[] args) {
    LinkedHashMap<String, String> kvs = generateKvs();
    for (Map.Entry<String, String> entry : kvs.entrySet()) {
      Stack<Integer> result = plus(generateStack(entry.getKey()), generateStack(entry.getValue()));
      String expected = generateStr(result);
      String actual = generateByBigDecimal(entry.getKey(), entry.getValue());
      System.out.printf("%s \t %s \t %s \r\n", expected.equals(actual), expected, actual);
    }
  }



  public static Stack<Integer> plus(Stack<Integer> a, Stack<Integer> b) {
    Stack<Integer> result = new Stack<>();
    Stack<Integer> big, small;
    int aLen = a.size();
    int bLen = b.size();
    if (aLen >= bLen) {
      big = a;
      small = b;
    } else {
      big = b;
      small = a;
    }
    doPlus(big, small, result);
    return result;
  }

  private static void doPlus(Stack<Integer> big, Stack<Integer> small, Stack<Integer> result) {
    boolean overflow = false;
    while (!big.isEmpty()) {
      int bigValue = big.pop();
      int smallValue = small.isEmpty() ? 0 : small.pop();
      int value = bigValue + smallValue;
      if (overflow) {
        if (value >= 9) {
          result.push(value - 10 + 1);
          overflow = true;
        } else {
          result.push(value + 1);
          overflow = false;
        }
      } else {
        if (value >= 10) {
          result.push(value - 10);
          overflow = true;
        } else {
          result.push(value);
          overflow = false;
        }
      }
    }

    //避免第一位相加的时候，出现溢出现象。
    if (overflow) {
      result.push(1);
    }
  }

  private static String generateStr(Stack<Integer> result) {
    StringBuilder builder = new StringBuilder(result.size());
    Iterator<Integer> iterator = result.iterator();
    while (iterator.hasNext()) {
      builder.append(iterator.next());
    }
    return builder.reverse().toString();
  }

  private static Stack<Integer> generateStack(String str) {
    char[] chars = str.toCharArray();
    Stack<Integer> stack = new Stack<>();
    for (char aChar : chars) {
      stack.push(Integer.parseInt(String.valueOf(aChar)));
    }
    return stack;
  }

  private static String generateByBigDecimal(String a, String b) {
    BigDecimal b1 = new BigDecimal(a);
    BigDecimal b2 = new BigDecimal(b);
    return b1.add(b2).toString();
  }

  private static LinkedHashMap<String, String> generateKvs() {
    LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
    map.put("45678", "12345678");
    map.put("12345678", "45678");
    map.put("1111", "1111");
    map.put("1111", "0");
    map.put("5555", "5555");
    map.put("9999", "9999");
    return map;
  }
}
