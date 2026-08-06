package chapter07_producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;

public class NotifyAllMultiProducerConsumerDemo {

    public static void main(String[] args) throws InterruptedException {

        BoundedBuffer buffer = new BoundedBuffer(1);

        Thread producer1 = new Thread(() -> produce(buffer,1), "producer-1");
        Thread producer2 = new Thread(() -> produce(buffer,100), "producer-2");

        Thread consumer1 = new Thread(() -> consume(buffer),"consumer-1");
        Thread consumer2 = new Thread(() -> consume(buffer),"consumer-2");

        consumer1.start();
        consumer2.start();

        Thread.sleep(100);

        producer1.start();
        producer2.start();

        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();

        System.out.println("main finished");


    }

    private static void produce(BoundedBuffer buffer, int start) {
        try {
            for (int i=start; i< start+3; i++) {
                buffer.put(i);
                System.out.println(Thread.currentThread().getName()+"produced:"+i);
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void consume(BoundedBuffer buffer) {
        try {
            for (int i=0; i<3; i++) {
                int value = buffer.take();
                System.out.println(Thread.currentThread().getName() + " consumed:"+value);
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
