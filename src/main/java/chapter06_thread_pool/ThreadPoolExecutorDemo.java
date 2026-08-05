package chapter06_thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 10,
        TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
        new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 1; i <= 7; i++) {
            int taskId = i;

            try {
                pool.execute(() -> {
                    System.out.println("task-" + taskId + "started");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

                System.out.println("task-" + taskId + "finished");
            } catch (RejectedExecutionException e) {
                System.out.println("task-" + taskId + "rejected");
            }
        }

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("pool terminated: " + pool.isTerminated());
    }
}
