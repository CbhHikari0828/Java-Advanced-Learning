package chapter02_synchronization;

public class InstanceLockDemo {

    public static void main(String[] args) throws InterruptedException {
        // InstanceLockDemo demo = new InstanceLockDemo();

        // Thread thread1 = new Thread(() -> demo.instanceMethod(), "Thread-1");

        // Thread thread2 = new Thread(() -> demo.instanceMethod(), "Thread-2");

        // thread1.start();
        // thread2.start();
        // thread1.join();
        // thread2.join();   
        InstanceLockDemo demo1 = new InstanceLockDemo();
        InstanceLockDemo demo2 = new InstanceLockDemo();
        Thread thread1 = new Thread(() -> demo1.instanceMethod(), "Thread-1");
        Thread thread2 = new Thread(() -> demo2.instanceMethod(), "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join(); 
    }

    public synchronized void instanceMethod() {
        System.out.println(Thread.currentThread().getName() + " enter instance method");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " exit instance method");
    }

    public static synchronized void staticMethod() {
        System.out.println(Thread.currentThread().getName() + " enter static method");

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " leave static method");
    }


}