package org.glamey.training;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yang.zhou 2019.11.01.10
 */
public class Demo {
    public static final int total = 1000;
    public static final int resourceCount = 8;

    @Data
    @AllArgsConstructor
    static class Location {
        private int resourceIndex;
        private int entryIndex;
    }

    public static Location findLocation(int source) {
        if (source <= 0) {
            throw new IllegalArgumentException(String.format("source must greater zero"));
        }
//        int entryIndex = source % total;
//        int resourceIndex = entryIndex % resourceCount;

        int entryIndex = source % total;
        int resourceIndex = entryIndex & (resourceCount - 1);

        return new Location(resourceIndex, entryIndex);
    }

    public static void main(String[] args) {
        System.out.println(findLocation(1231312312));
        System.out.println(findLocation(12311112));
        System.out.println(findLocation(5486874));
        System.out.println(findLocation(234534571));
        System.out.println(findLocation(123746));

        /*
        Demo.Location(resourceIndex=0, entryIndex=312)
        Demo.Location(resourceIndex=0, entryIndex=112)
        Demo.Location(resourceIndex=2, entryIndex=874)
        Demo.Location(resourceIndex=3, entryIndex=571)
        Demo.Location(resourceIndex=2, entryIndex=746)
       */
    }
}
