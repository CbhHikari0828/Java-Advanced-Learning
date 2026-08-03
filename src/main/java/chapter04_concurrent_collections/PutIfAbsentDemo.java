package chapter04_concurrent_collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PutIfAbsentDemo {
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        Runnable task1 = () -> {
            String oldValue = map.putIfAbsent("name", "thread-1 value");
            System.out.println(Thread.currentThread().getName() + "oldValue=" + oldValue);
        };

        Runnable task2 = () -> {
            String oldValue = map.putIfAbsent("name", "thread-2 value");
            System.out.println(Thread.currentThread().getName() + "oldValue=" + oldValue);
        };

        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("final value = " + map.get("name"));

    }
}