package chapter05_atomic_and_volatile;

import java.util.concurrent.atomic.AtomicInteger;
public class CompareAndSetDemo {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        boolean first = counter.compareAndSet(0, 10);
        System.out.println(first);
        System.out.println(counter.get());
        boolean second = counter.compareAndSet(0, 20);
        System.out.println(second);
        System.out.println(counter.get());
    }
}
    