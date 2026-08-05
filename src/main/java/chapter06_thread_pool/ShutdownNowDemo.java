package chapter06_thread_pool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ShutdownNowDemo {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(1);
        pool.execute(() -> {
            try {
                System.out.println("Task started");
                Thread.sleep(5000);
                System.out.println("Task completed");
            } catch (InterruptedException e) {
                System.out.println("Task interrupted");
                Thread.currentThread().interrupt();
            }
        });

        pool.execute(() -> {
            System.out.println("Second task started");
        });
        Thread.sleep(500);
        List<Runnable> waitingTasks = pool.shutdownNow();
        System.out.println("Waiting tasks: " + waitingTasks.size());
        System.out.println("is shutdown: " + pool.isShutdown());

        pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("is terminated: " + pool.isTerminated());

    }
}
