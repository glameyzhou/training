package org.glamey.training.algorithm.search;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 最长连续的数字字符串
 *
 * @author zhouyang.zhou. 2017.10.10.14.
 */
public class ContinueString {
  public static void main(String[] args) {
    List<String> list = Lists.newArrayList("ab1235cd56", "ab1235cd56e", "we1we", "12456");
    for (String s : list) {
      Pair continueNumbers = getContinueNumbers(s);
      System.out.printf("%s --> %s\n", s, continueNumbers);
    }
  }

  private static Pair getContinueNumbers(String source) {
    if(StringUtils.isBlank(source)) {
      return new Pair(source, 0);
    }
    Pair pair = new Pair(null, 0);
    char[] chars = source.toCharArray();
    int len = chars.length;
    int maxCount = 0;
    StringBuilder builder = new StringBuilder(source.length());
    char c;
    char last = '0';
    for (int i = 0; i < len; i++) {
      last = c = chars[i];
      if(Character.isDigit(c)) {
        builder.append(c);
        maxCount += 1;
      } else if(maxCount > 0) {
        if(pair.getCount() == 0 || pair.getCount() <= maxCount) {
          pair = new Pair(builder.toString(), maxCount);
          builder.setLength(0);
          maxCount = 0;
        }
      }
    }
    //处理最后一位(如果非数字的话，上层已经处理过，如果是数组，需要做比较)
    if(Character.isDigit(last) && maxCount > 0) {
      if(pair.getCount() == 0 || pair.getCount() <= maxCount) {
        pair = new Pair(builder.toString(), maxCount);
      }
    }
    return pair;
  }


  @Setter
  @Getter
  @AllArgsConstructor
  @ToString
  static class Pair {
    private String val;
    private int count;
  }
}
