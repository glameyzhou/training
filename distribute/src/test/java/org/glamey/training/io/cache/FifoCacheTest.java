package org.glamey.training.io.cache;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author zhouyang.zhou. 2017.06.05.16.
 */
public class FifoCacheTest {

    private final FifoCache fifoCache = new FifoCache(100);

    @BeforeMethod
    public void setUp() throws Exception {
        for (int i = 0; i < 101; i++) {
            fifoCache.put(i, "v_" + i);
        }
    }

    @Test
    public void testCache() throws InterruptedException {

        assertTrue(fifoCache.get(0) == null);
        assertEquals("v_1", fifoCache.get(1));
        assertEquals("v_100", fifoCache.get(100));

        fifoCache.remove(99);
        assertTrue(fifoCache.get(99) == null);
    }

    @AfterMethod
    public void setDown() {
        fifoCache.clear();
    }
}