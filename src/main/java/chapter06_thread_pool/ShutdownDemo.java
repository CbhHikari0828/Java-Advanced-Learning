package chapter06_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.execute(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("task completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        pool.shutdown();
        System.out.println("is shutdown: " + pool.isShutdown());

        System.out.println("is terminated: " + pool.isTerminated());

        pool.awaitTermination(2, TimeUnit.SECONDS);

        System.out.println("is terminated: " + pool.isTerminated());
    }
}