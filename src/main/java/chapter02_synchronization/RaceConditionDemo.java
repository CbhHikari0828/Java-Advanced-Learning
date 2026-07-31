package chapter02_synchronization;

public class RaceConditionDemo {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i=0; i < 10000; i++) {
                increment();
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("thread1 status: " + thread1.getState());
        System.out.println("thread2 status: " + thread2.getState());
        System.out.println("counter= " + counter);
        counter ++;
        System.out.println("counter= " + counter);
    }

    private static void increment() {
        synchronized (RaceConditionDemo.class) {
            counter++;
        }
    }
}