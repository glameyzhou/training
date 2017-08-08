package org.glamey.training.algorithm.leetcode;

/**
 *
 * print the rhombus
 *
 *       *
 *      ***
 *     *****
 *      ***
 *       *
 *
 * @author zhouyang.zhou. 2017.08.08.11.
 */
public class RhombusDemo {


  /**
   * print the rhombus for special high
   * @param high
   */
  public static void process(int high) {
    System.out.println("<--------------[" + high + "]-------------->");
    if(high < 3 || high % 2 == 0) {
      System.out.println("the high must greater than 3,or high % 2 != 0");
      return;
    }


    int middle = (high >> 1) + 1;
    int maxStar = high;

    int blankCount, starCount;

    for (int i = 1; i <= high; i++) {
      //middle
      if(i == middle) {
        starCount = maxStar;
        blankCount = 0;
      }

      //up
      else if(i < middle) {
        starCount = (i << 1) - 1;
        blankCount = maxStar - starCount;
      }

      //down
      else {
        int upLineNum = (high - i) + 1;
        starCount = (upLineNum << 1) - 1;
        blankCount = maxStar - starCount;
      }
      printStar(starCount, blankCount);
    }
  }

  private static void printStar(int starCount, int blankCount) {
    int halfBlankCount = blankCount >> 1;
    int lastStar = halfBlankCount + starCount;
    int max = starCount + blankCount;

    for (int i = 1; i <= max ; i++) {
      if(i <= halfBlankCount || i > lastStar) {
        System.out.print(" ");
      } else {
        System.out.print("*");
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    process(13);
    process(10);
    process(2);
    process(5);
  }

}
