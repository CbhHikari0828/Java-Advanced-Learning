package chapter06_thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduleWithFixedDelayDemo {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        AtomicInteger count = new AtomicInteger(0);

        ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(() -> {
            int current = count.incrementAndGet();

            System.out.println("task-: " + current+ "start");

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(5000);

        future.cancel(false);
        scheduler.shutdown();
        scheduler.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("scheduler terminated: " + scheduler.isTerminated());
    }
}
