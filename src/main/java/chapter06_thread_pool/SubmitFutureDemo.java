package chapter06_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitFutureDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> future = pool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " executing task");
            Thread.sleep(2000);
            return "Task completed";
        });
        System.out.println("before get");
        String result = future.get();

        System.out.println("after get");
        System.out.println("Result: " + result);
        pool.shutdown();
    }
}