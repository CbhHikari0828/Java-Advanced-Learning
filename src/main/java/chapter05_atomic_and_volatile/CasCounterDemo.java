package chapter05_atomic_and_volatile;

import java.util.concurrent.atomic.AtomicInteger;

public class CasCounterDemo {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i=0; i<100000; i++) {
                while (true) {
                    int oldValue = counter.get();
                    int newValue = oldValue + 1;
                    if (counter.compareAndSet(oldValue, newValue)) {
                        break;
                    }
                }
            }
        };

        Thread thread1 = new Thread(task,"Thread-1");
        Thread thread2 = new Thread(task,"Thread-2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Final counter: " + counter.get());
    }
}