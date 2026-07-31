package chapter02_synchronization;

public class ReentrantSynchronizedDemo {

    public static void main(String[] args) {
        ReentrantSynchronizedDemo demo = new ReentrantSynchronizedDemo();
        demo.outer();
    }

    public synchronized void inner() {
        System.out.println(Thread.currentThread().getName() + " enter inner method");
        System.out.println(Thread.currentThread().getName() + " leave inner method");
    }

    public synchronized void outer() {
        System.out.println(Thread.currentThread().getName() + " enter outer method");
        inner();
        System.out.println(Thread.currentThread().getName() + " leave outer method");
    }
}