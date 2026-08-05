package chapter06_thread_pool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleAtFixedRateDemo {


    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        AtomicInteger count = new AtomicInteger(0);
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {
            int current = count.incrementAndGet();
            System.out.println("task executed: " + current);
        }, 0, 1, TimeUnit.SECONDS);
        Thread.sleep(3500);
        future.cancel(false);
        scheduler.shutdown();
        scheduler.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("scheduler terminated: " + scheduler.isTerminated());
    }

}
