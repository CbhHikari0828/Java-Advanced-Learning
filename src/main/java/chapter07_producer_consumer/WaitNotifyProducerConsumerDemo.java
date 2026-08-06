package chapter07_producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;

public class WaitNotifyProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException{
        BoundedBuffer buffer = new BoundedBuffer(1);

        Thread consumer = new Thread(() -> {
            try {
                for (int i=1; i<=5; i++) {
                    int value = buffer.take();
                    System.out.println("consumed: "+ value);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }, "consumer");

        Thread producer = new Thread(() -> {
            try {
                for (int i=1; i<=5; i++) {
                    buffer.put(i);
                    System.out.println("produced: "+ i);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }, "producer");

        consumer.start();
        Thread.sleep(100);
        producer.start();

        producer.join();
        consumer.join();
        System.out.println("main finished");
    }

    static class BoundedBuffer {
        private final Queue<Integer> queue = new ArrayDeque<>();
        private final int capacity;

        BoundedBuffer(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void put(int value) throws InterruptedException {
            while (queue.size() == capacity) {
                System.out.println("buffer full,"+ Thread.currentThread().getName()+" waits");
                wait();
            }

            queue.offer(value);
            notifyAll();
        }

        public synchronized int take() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("buffer empty, "+ Thread.currentThread().getName()+" waits");
                wait();
            }

            int value = queue.poll();
            notifyAll();
            return value;
        }
    }


}
