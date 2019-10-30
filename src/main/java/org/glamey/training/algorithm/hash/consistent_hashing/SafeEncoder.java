package org.glamey.training.algorithm.hash.consistent_hashing;

import com.google.common.base.Charsets;

import java.io.UnsupportedEncodingException;

/**
 * @author zhouyang.zhou. 2017.05.15.15.
 */
public class SafeEncoder {

    public static byte[] encode(String str) {
        if (str == null) {
            throw new IllegalArgumentException(" the string is null");
        }

        try {
            return str.getBytes(Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(" the string encode error", e);
        }
    }

    public static String encode(final byte[] data) {
        try {
            return new String(data, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("the string encode error", e);
        }
    }
}
