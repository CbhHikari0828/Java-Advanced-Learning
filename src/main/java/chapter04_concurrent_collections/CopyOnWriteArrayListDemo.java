package chapter04_concurrent_collections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }

        Thread reader = new Thread(() -> {
            for (Integer value : list) {
                if (value % 200 == 0) {
                    System.out.println("reading value = " + value);
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "reader");

        Thread writer = new Thread(() -> {
            for (int i = 1000; i < 2000; i++) {
                list.add(i);
            }
        }, "writer");
        reader.start();
        writer.start();
        reader.join();
        writer.join();
        System.out.println("list size = " + list.size());
    }
}