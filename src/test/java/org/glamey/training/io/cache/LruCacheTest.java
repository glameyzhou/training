package org.glamey.training.io.cache;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author zhouyang.zhou. 2017.06.05.15.
 */

public class LruCacheTest {
    private final LruCache cache = new LruCache(100);

    @BeforeClass
    public void before() {
        for (int i = 0; i < 101; i++) {
            cache.put(i, "v_" + i);
        }
    }

    @Test
    public void testCache() {
        assertTrue(cache.get(0) == null);
        assertEquals("v_1", cache.get(1));
        assertEquals("v_100", cache.get(100));

        cache.remove(98);
        assertTrue(cache.get(98) == null);
    }

    @AfterClass
    public void setDown() {
        cache.clear();
        assertTrue(cache.get(11) == null);
    }
}