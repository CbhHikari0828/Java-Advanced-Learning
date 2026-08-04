package chapter05_atomic_and_volatile;

public class VolatileVisibilityDemo {
    static volatile boolean running = true;
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            System.out.println("Worker thread started.");
            while (running) {
                // busy-wait
            }
            System.out.println("Worker thread finished.");
        });
        worker.start();
        Thread.sleep(1000);
        running = false;
        System.out.println("Main thread set running to false.");
        worker.join();
        System.out.println("Main thread finished.");
    }
}