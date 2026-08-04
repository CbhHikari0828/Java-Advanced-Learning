package chapter04_concurrent_collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapIterationDemo {
    private static final Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            map.put(i, i);
        }

        Thread reader = new Thread(() -> {
            for (Integer key : map.keySet()) {
                if (key % 200 == 0) {
                    System.out.println("reading key = " + key);
                }
            }
        },"reader");
        Thread writer = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                map.put(i, i);
            }
        }, "writer");

        reader.start();
        writer.start();
        reader.join();
        writer.join();

        System.out.println("map size = " + map.size());
    }
}