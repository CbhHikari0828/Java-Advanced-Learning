package chapter05_atomic_and_volatile;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class CounterPerformanceDemo {

    private static final int THREAD_COUNT = 16;
    private static final int INCREMENTS_PER_THREAD = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        long atomicLongCost = testAtomicLong();
        long longAdderCost = testLongAdder();

        System.out.printf("AtomicLong cost: %.2f ms%n", atomicLongCost / 1_000_000.0);
        System.out.printf("LongAdder cost: %.2f ms%n", longAdderCost / 1_000_000.0);
    }

    private static long testAtomicLong() throws InterruptedException {
        AtomicLong counter = new AtomicLong();
        Thread[] threads = new Thread[THREAD_COUNT];

        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    counter.incrementAndGet();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long expected = (long) THREAD_COUNT * INCREMENTS_PER_THREAD;
        System.out.println("AtomicLong result: " + counter.get());

        if (counter.get() != expected) {
            throw new IllegalStateException("AtomicLong result is incorrect");
        }

        return System.nanoTime() - startTime;
    }

    private static long testLongAdder() throws InterruptedException {
        LongAdder counter = new LongAdder();
        Thread[] threads = new Thread[THREAD_COUNT];

        long startTime = System.nanoTime();

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long expected = (long) THREAD_COUNT * INCREMENTS_PER_THREAD;
        System.out.println("LongAdder result: " + counter.sum());

        if (counter.sum() != expected) {
            throw new IllegalStateException("LongAdder result is incorrect");
        }

        return System.nanoTime() - startTime;
    }
}