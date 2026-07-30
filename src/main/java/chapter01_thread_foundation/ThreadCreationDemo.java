package chapter01_thread_foundation;

public class ThreadCreationDemo {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " is running");
        MyThread myThread = new MyThread();
        myThread.start();
        MyTask myTask = new MyTask();
        Thread thread = new Thread(myTask); // Runnable创建的是任务而非线程，线程和任务真正解耦了
        new Thread(myTask).start();
        thread.start();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    
}