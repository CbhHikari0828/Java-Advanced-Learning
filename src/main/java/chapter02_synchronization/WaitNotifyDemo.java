package chapter02_synchronization;

public class WaitNotifyDemo {
    private static Object LOCK = new Object();
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = new Thread(() -> {
            synchronized (LOCK) {
                System.out.println("waiter get lock");

                while (!ready) {
                    try {
                        System.out.println("waiter wait");
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("waiter continue");
            }
        }, "waiter");
        waiter.start();
        Thread.sleep(2000);
        System.out.println("waiter status:" + waiter.getState());
        Thread notifier = new Thread(() -> {
            synchronized (LOCK) {
                System.out.println("notifier get lock");
                ready = true;
                LOCK.notify();
                System.out.println("notifier notify");
            }
        }, "notifier");
        notifier.start();
        waiter.join();
        notifier.join();
    }
}