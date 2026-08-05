package chapter06_thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallerRunsPolicyDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0,
        TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 1; i <= 7; i++) {
            int taskId = i;

            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " executing task-" + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            System.out.println("task-" + taskId + " submitted");

        }
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("pool terminated: " + pool.isTerminated());
    }
}