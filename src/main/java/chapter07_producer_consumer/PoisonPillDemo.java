package chapter07_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PoisonPillDemo {
    private static final int POISON_PILL = -1;
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i=1; i<=5; i++) {
                    queue.put(i);
                    System.out.println("produced: "+i);
                }
                queue.put(POISON_PILL);
                System.out.println("productor sent poison pill");
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() ->{
            try {
                while (true) {
                    int value = queue.take();
                    if (value == POISON_PILL) {
                        System.out.println("consumer stopped");
                        break;
                    }
                    System.out.println("consumed: "+value);
                }
            }catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("main finished");

    }
}
