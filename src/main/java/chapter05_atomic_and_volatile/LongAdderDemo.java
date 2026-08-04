package chapter05_atomic_and_volatile;

import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    private static final LongAdder counter = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Final counter: " + counter.sum());
    }
}