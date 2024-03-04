package org.glamey.training.concurrent.scheduler;

import com.google.common.util.concurrent.Uninterruptibles;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 时间轮测试，基于netty
 *
 * @author yang.zhou 2019.11.21.16
 */
public class TimeWheelDemo {

    public static void main(String[] args) {
        Timer timer = new HashedWheelTimer(r -> {
            Thread thread = new Thread(r);
            thread.setName("thread_demo_");
            thread.setDaemon(true);
            return thread;
        });
        timer.newTimeout(timeout -> {
            int count = 0;
            while (true) {
                count++;
                System.out.println(Thread.currentThread().getName() + "-->" + count);
                if (count == 10) {
                    break;
                }
            }
        }, 5, TimeUnit.SECONDS);

        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);

        Set<Timeout> stop = timer.stop();
        for (Timeout timeout : stop) {
            System.out.println(timeout.getClass());
        }
    }
}
