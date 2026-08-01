package chapter03_lock_and_aqs;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {

    private static int counter = 0;
    private static final ReentrantLock LOCK = new ReentrantLock(); 

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i< 5; i++) tryIncrement();
        };
            Thread threadA = new Thread(task, "Thread-A");
            Thread threadB = new Thread(task, "Thread-B");
            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            System.out.println("Final counter value: " + counter);
    }

    private static void tryIncrement() {
        if (LOCK.tryLock()) {
            try {
                counter++;
                System.out.println(Thread.currentThread().getName() + " acquired the lock and incremented the counter to: " + counter);
                sleep(100); // Simulate some work with the lock held
            } finally {
                LOCK.unlock();
            }
        }else System.out.println(Thread.currentThread().getName() + " could not acquire the lock and did not increment the counter.");
    }

    private static void sleep (long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}