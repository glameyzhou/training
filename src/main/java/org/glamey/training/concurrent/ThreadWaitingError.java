package org.glamey.training.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * http://ifeve.com/%e4%b8%80%e4%b8%aa%e7%ba%bf%e7%a8%8b%e7%bd%a2%e5%b7%a5%e7%9a%84%e8%af%a1%e5%bc%82%e4%ba%8b%e4%bb%b6/
 *
 * @author yang.zhou 2019.11.21.14
 */
@Slf4j
public class ThreadWaitingError {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());


        executorService.execute(() -> log.info("-----> {}", 1));


        TimeUnit.SECONDS.sleep(1);


        executorService.execute(new CustomRunnable());


    }


    private static class CustomRunnable implements Runnable {
        @Override
        public void run() {
            int count = 0;
            while (true) {
                count++;
                log.info("---> {}", count);
                if (count == 2) {
                    try {
                        log.info("---> {} ", count / 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (count == 3) {
                    break;
                }
            }
        }
    }
}
