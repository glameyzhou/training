package org.glamey.training.codes.search.blomfilter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

/**
 * -Xms64m -Xmx64m -XX:+PrintHeapAtGC
 *
 * @author yang.zhou 2019.12.03.17
 */
public class CustomBloomFilters {

    private final int[] array;

    private final int size;

    private final List<HashAlgorithm> hashAlgorithms;

    public CustomBloomFilters(int size) {
        this.size = size;
        array = new int[size];
        Arrays.fill(array, 0);
        this.hashAlgorithms = Lists.newArrayList(
                source -> {
                    int hash = Hashing.md5().newHasher().putString(source, StandardCharsets.UTF_8).hash().hashCode();
                    return hash > 0 ? hash : -hash;
                },
                source -> {
                    int hash = Hashing.murmur3_32().newHasher().putString(source, StandardCharsets.UTF_8).hash()
                            .hashCode();
                    return hash > 0 ? hash : -hash;
                }
        );
    }


    public void add(int source) {
        for (HashAlgorithm algorithm : hashAlgorithms) {
            int hash = algorithm.hash(source + "");
            int slot = hash % size;
            array[slot] = 1;
        }
    }

    public boolean mightContain(int source) {
        for (HashAlgorithm algorithm : hashAlgorithms) {
            int hash = algorithm.hash(source + "");
            int slot = hash % size;
            if (array[slot] == 0) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        int size = 10000000; //1KW
        CustomBloomFilters filters = new CustomBloomFilters(size);
        for (int i = 0; i < size; i++) {
            filters.add(i);
        }

        System.out.println(filters.mightContain(1));
        System.out.println(filters.mightContain(2));
        System.out.println(filters.mightContain(3));
        System.out.println(filters.mightContain(999999));
        System.out.println(filters.mightContain(400230340));

        System.out.println("耗时 : " + started.elapsed(TimeUnit.MILLISECONDS) + " ms");

    }
}
