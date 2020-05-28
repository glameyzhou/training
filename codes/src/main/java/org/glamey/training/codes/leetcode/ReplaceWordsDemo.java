package org.glamey.training.codes.leetcode;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/replace-words/description/
 *
 * @author zhouyang.zhou. 2017.09.04.15.
 */
public class ReplaceWordsDemo {

  public static void main(String[] args) {
    List<String> dict = Lists.newArrayList("cat", "bat", "rat");
    String sentence = "the cattle was rattled by the battery";
    String result = replaceWords(dict, sentence);
    System.out.println(result);
  }

  private static String replaceWords(List<String> dict, String sentence) {
    if(dict == null || dict.size() == 0) {
      return sentence;
    }

    if(sentence == null || "".equals(sentence)) {
      return sentence;
    }

    StringBuilder builder = new StringBuilder(sentence.length());
    Set<String> dictSet = new HashSet<>(dict);
    String[] strings = sentence.split("\\s+");
    for (String string : strings) {
      String prefix = "";
      for (int i = 1; i <= string.length(); i++) {
        prefix = string.substring(0, i);
        if(dictSet.contains(prefix)) {
          break;
        }
      }
      builder.append(" ").append(prefix);
    }
    return builder.deleteCharAt(0).toString();
  }
}
