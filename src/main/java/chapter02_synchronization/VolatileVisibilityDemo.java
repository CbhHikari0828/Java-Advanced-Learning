package chapter02_synchronization;

public class VolatileVisibilityDemo {

    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {
            while (running){

            }
            System.out.println("worker thread stopped.");
        }, "worker-thread");
        worker.start();
        Thread.sleep(1000);
        running = false;
        System.out.println("main changed running to false");
        worker.join();
    }
}