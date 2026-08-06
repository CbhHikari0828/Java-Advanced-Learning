package chapter07_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MultipleConsumersPoisonPillDemo {

    private static final int POISON_PILL = -1;
    private static final int CONSUMER_COUNT = 2;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 6; i++) {
                    queue.put(i);
                    System.out.println("produced: " + i);
                }

                for (int i = 0; i < CONSUMER_COUNT; i++) {
                    queue.put(POISON_PILL);
                }

                System.out.println("producer sent poison pills");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "producer");

        Runnable consumerTask = () -> {
            try {
                while (true) {
                    int value = queue.take();

                    if (value == POISON_PILL) {
                        System.out.println(
                                Thread.currentThread().getName() + " stopped"
                        );
                        break;
                    }

                    System.out.println(
                            Thread.currentThread().getName()
                                    + " consumed: " + value
                    );
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread consumer1 = new Thread(consumerTask, "consumer-1");
        Thread consumer2 = new Thread(consumerTask, "consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();

        producer.join();
        consumer1.join();
        consumer2.join();

        System.out.println("main finished");
    }
}