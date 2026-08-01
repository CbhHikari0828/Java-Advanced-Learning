package chapter03_lock_and_aqs;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final  Condition CONDITION = LOCK.newCondition();
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = new Thread(ConditionDemo::waitForReady, "Waiter-Thread");
        Thread signaler = new Thread(() -> {
            sleep(500);
            makeReady();
        },"Signaler-Thread");
        waiter.start();
        signaler.start();
        waiter.join();
        signaler.join();
        System.out.println("Main thread finished execution.");
    }


    private static void waitForReady() {
        LOCK.lock();
        try {
            while (!ready) {
                System.out.println(Thread.currentThread().getName() + " is waiting for ready signal.");
                CONDITION.await();
            }
            System.out.println(Thread.currentThread().getName() + " detected ready = true.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            LOCK.unlock();
        }

    }

    private static void makeReady() {
        LOCK.lock();
        try {
            ready = true;
            System.out.println(Thread.currentThread().getName() + " set ready = true and is signaling all waiting threads.");
            CONDITION.signal();
        }finally {
            LOCK.unlock();
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

