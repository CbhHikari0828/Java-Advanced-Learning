package chapter04_concurrent_collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ComputeIfAbsentDemo {
    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            String value = map.computeIfAbsent("name", key ->{
                System.out.println(Thread.currentThread().getName() + " computing value for " + key);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "value for " + key;
            });
        };

        Thread thread1 = new Thread(task, "thread-1");
        Thread thread2 = new Thread(task, "thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("map = " + map);

    }
}