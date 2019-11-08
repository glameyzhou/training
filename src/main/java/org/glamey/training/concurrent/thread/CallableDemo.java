package org.glamey.training.concurrent.thread;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.*;

/**
 * @author zhouyang.zhou. 2017.09.25.17.
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(new ComputeCallable(50967.234));
        while (!future.isDone()) {
            System.out.println("*");
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println(future.get());
        executor.shutdown();
    }

    private static final class ComputeCallable implements Callable<Double> {

        private double money;

        public ComputeCallable(double money) {
            this.money = money;
        }

        @Override
        public Double call() throws Exception {
            TimeUnit.SECONDS.sleep(5);
            return new BigDecimal(money).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        }
    }
}
