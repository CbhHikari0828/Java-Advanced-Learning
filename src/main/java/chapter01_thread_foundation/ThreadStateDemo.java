package chapter01_thread_foundation;

public class ThreadStateDemo {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " is running");
        });

        System.out.println("after create: " + thread.getState());

        thread.start();
        System.out.println("after start: " + thread.getState());

        thread.sleep(500);
        System.out.println("after sleep: " + thread.getState());

        thread.join();
        System.out.println("after join: " + thread.getState());
    }
}