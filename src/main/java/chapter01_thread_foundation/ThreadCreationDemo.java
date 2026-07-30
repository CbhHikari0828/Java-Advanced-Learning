package chapter01_thread_foundation;

public final class ThreadCreationDemo {
    private ThreadCreationDemo() {
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadByInheritance = new GreetingThread("extends Thread");

        Runnable runnableTask = () -> printCurrentThread("implements Runnable");
        Thread threadByRunnable = new Thread(runnableTask, "runnable-worker");

        threadByInheritance.start();
        threadByRunnable.start();

        threadByInheritance.join();
        threadByRunnable.join();

        printCurrentThread("main method");
    }

    private static final class GreetingThread extends Thread {
        private final String taskName;

        private GreetingThread(String taskName) {
            super("thread-subclass-worker");
            this.taskName = taskName;
        }

        @Override
        public void run() {
            printCurrentThread(taskName);
        }
    }

    private static void printCurrentThread(String taskName) {
        System.out.printf("%s -> running in %s%n", taskName, Thread.currentThread().getName());
    }
}
