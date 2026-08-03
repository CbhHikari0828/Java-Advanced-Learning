package chapter04_concurrent_collections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedMapDemo {
    private static final Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                map.put(i, i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put(i, i);
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("map size = " + map.size());
    }
}