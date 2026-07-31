package chapter02_synchronization;

public class LockObjectDemo {
    private static final Object Lock_A = new Object();
    private static final Object Lock_B = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable taskA = () -> {
            synchronized (Lock_A) {
                System.out.println(Thread.currentThread().getName() + " enter lock_A");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " leave Lock_A");
            }
        };

        Runnable taskB = () -> {
            synchronized (Lock_B) {
                System.out.println(Thread.currentThread().getName() + " enter Lock_B");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " leave Lock_B");
            }
        };
        Thread threadA = new Thread(taskA, "Thread-A");
        Thread threadB = new Thread(taskA, "Thread-B");
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }

}
