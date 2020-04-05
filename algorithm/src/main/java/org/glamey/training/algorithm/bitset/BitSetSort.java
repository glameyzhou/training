package org.glamey.training.algorithm.bitset;

import java.util.BitSet;

/**
 * @author zhouyang.zhou. 2017.05.15.16.
 */
public class BitSetSort {
  public static void main(String[] args) {
    int[] array = new int[]{423, 700, 9999, 2323, 356, 6400, 1};
    int len = array.length;

    BitSet bitSet = new BitSet(2 << 13);
    System.out.println("BitSet size: " + bitSet.size());

    for (int i = 0; i < array.length; i++) {
      bitSet.set(array[i]);
    }
    System.out.println("cardinality: " + bitSet.cardinality());

    int[] orderedArray = new int[len];
    int k = 0;
    for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
      orderedArray[k++] = i;
    }

    System.out.println("After ordering: ");
    for (int i = 0; i < len; i++) {
      System.out.print(orderedArray[i] + "\t");
    }
  }
}
