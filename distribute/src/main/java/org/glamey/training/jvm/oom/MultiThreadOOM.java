package org.glamey.training.jvm.oom;

import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * -Xmx100M -Xms100M
 * <p>
 * 其中一个线程OOM，另外一个线程可以正常运行
 * <p>
 * 可以通过JVisualVM查看，heap占用量到100M的时候，出现一个断崖式的下跌，说明thread_1 OOM后，释放掉其中在HEAP中的所有内存。
 * <p>
 * 同时thread_1 OOM死掉，thread_2正常运行
 *
 * @author yang.zhou 2019.11.15.12
 */
public class MultiThreadOOM {

    public static void main(String[] args) {

        new Thread(() -> {
            List<Object> list = Lists.newArrayList();
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + LocalDateTime.now().toLocalTime());
                byte[] bytes = new byte[1024 * 1024 * 1];
                list.add(bytes);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " " + LocalDateTime.now().toLocalTime());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
