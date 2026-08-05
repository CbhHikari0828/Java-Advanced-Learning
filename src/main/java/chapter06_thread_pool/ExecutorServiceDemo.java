package chapter06_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;

            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task " + taskId);
            });
        }

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("All tasks completed.");
    }

}
