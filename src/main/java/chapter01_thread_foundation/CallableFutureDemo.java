package chapter01_thread_foundation;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableFutureDemo {
    public static void main(String[] args) throws Exception {

        Callable<String> task = () -> {
            System.out.println(Thread.currentThread().getName() + " is calculating");
            Thread.sleep(2000);
            return "task result";
            
        };

        FutureTask<String> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("before get");
        String result = futureTask.get();
        System.out.println("after get");
        System.out.println(result);
    }
}