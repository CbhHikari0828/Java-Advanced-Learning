package chapter03_lock_and_aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TimedTryLockDemo {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                tryIncrementWithTimeout();
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Final counter value: " + counter);

    }

    public static void tryIncrementWithTimeout() {
        boolean locked = false;

        try {
            locked = LOCK.tryLock(200, TimeUnit.MILLISECONDS);
            if (locked) {
                counter++;
                System.out.println(Thread.currentThread().getName() + " acquired the lock and incremented the counter to: " + counter);
                sleep(100); // Simulate some work with the lock held
            }else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock within the timeout and did not increment the counter.");
            }

        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (locked) {
                LOCK.unlock();
            }
        }
    }

    private static void sleep(long millis) {
    try {
        Thread.sleep(millis);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
}