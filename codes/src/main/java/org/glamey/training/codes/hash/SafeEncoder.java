package org.glamey.training.codes.hash;

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
    // Long类型的数组，数组中100条记录，共10W数据，占内存共76兆。
    // 8*100*100000/1024/1024=76M
}
