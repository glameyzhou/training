package org.glamey.training.algorithm.search.blomfilter;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** -Xms64m -Xmx64m -XX:+PrintHeapAtGC
 * @author yang.zhou 2019.12.03.17
 */
public class CustomBloomFilters {

    private int[] array;

    private int size;

    private List<HashAlgorithm> hashAlgorithms;

    public CustomBloomFilters(int size) {
        this.size = size;
        array = new int[size];
        Arrays.fill(array, 0);
        this.hashAlgorithms = Lists.newArrayList(
                source -> {
                    int hash = Hashing.md5().newHasher().putString(source, Charset.forName("UTF-8")).hash().hashCode();
                    return hash > 0 ? hash : -hash;
                },
                source -> {
                    int hash = Hashing.murmur3_32().newHasher().putString(source, Charset.forName("UTF-8")).hash().hashCode();
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
        int size = 10000000;
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
