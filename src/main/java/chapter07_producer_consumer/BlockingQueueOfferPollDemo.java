package chapter07_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueOfferPollDemo{
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
        boolean firstOffer = queue.offer(1);
        System.out.println("First offer (1): " + firstOffer); // Should print true
        boolean secondOffer = queue.offer(2);
        System.out.println("Second offer (2): " + secondOffer);
        Integer firstPoll = queue.poll();
        System.out.println("First poll: " + firstPoll); // Should print 1
        Integer secondPoll = queue.poll();
        System.out.println("Second poll: " + secondPoll); // Should print 2
        Integer timeoutValue = queue.poll(1, TimeUnit.SECONDS);
        System.out.println("Poll with timeout (should be null): " + timeoutValue); //
    }
}