package org.glamey.training.algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class MaxMultiTest {

    @Test
    public void maxMultiOfContinue() {
        int[] arrays = {2, 3, 0, -1, 2, 5};
        int maxMulti = MaxMulti.maxMultiOfContinue(arrays);
        System.out.println(maxMulti);
        Assert.assertTrue(maxMulti == 10);
    }


    @Test
    public void maxMultiOfContinue_slow() {
        int[] arrays = {2, 3, 0, -1, 2, 5};
        int maxMulti = MaxMulti.maxMultiOfContinue_slow(arrays);
        System.out.println(maxMulti);
        Assert.assertTrue(maxMulti == 10);
    }
}