package chapter03_lock_and_aqs;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private static int counter = 0;
    private static ReentrantLock LOCK =  new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        // Runnable task = () -> {
        //     for (int i = 0; i < 100000; i++) {
        //         outer();
        //     }
        // };

        outer(); // 测试可重入锁，主线程调用 outer() 方法，outer() 方法中又调用 inner() 方法，inner() 方法中又加锁了 LOCK，测试是否会死锁。

        // Thread thread1 = new Thread(task, "Thread-A");
        // Thread thread2 = new Thread(task, "Thread-B");

        // thread1.start();
        // thread2.start();

        // thread1.join();
        // thread2.join();

        System.out.println("Final counter value: " + counter);
    }

    public static void increment() {
        LOCK.lock();
        try {
            counter++;
            // int i = 1/0; // 故意制造异常，测试锁是否会被释放
        } finally {
            LOCK.unlock(); // ReentrantLock 是显式锁，必须手动释放；unlock 放在 finally 中可以保证异常时也释放锁。
        }
    }

    public static void inner () {
        LOCK.lock();
        try {
            counter++;
        } finally {
            LOCK.unlock();
        }
    }

    public static void outer () {
        LOCK.lock();
        try {
            inner();
        } finally {
            LOCK.unlock();
        }
    }
}
