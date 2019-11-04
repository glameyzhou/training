package org.glamey.training.algorithm.hash.consistent;

import com.google.common.base.Charsets;

/**
 * @author yang.zhou 2019.11.04.17
 */
public class SafeEncoder {

    public static final byte[] encode(String key) {
        return key.getBytes(Charsets.UTF_8);
    }

    public static final String decode(byte[] bytes) {
        return new String(bytes, Charsets.UTF_8);
    }
}
