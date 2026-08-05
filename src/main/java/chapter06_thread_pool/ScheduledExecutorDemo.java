package chapter06_thread_pool;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println("submit task");

        scheduler.schedule(() -> {
            System.out.println("delayed task executed");
        }, 2, TimeUnit.SECONDS);
        scheduler.shutdown();
        scheduler.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("scheduler terminated: " + scheduler.isTerminated());
    }
}