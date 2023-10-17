package cleancode.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class ObjectWithValue {
    private AtomicInteger value = new AtomicInteger(0);

    public void incrementValue(){
        value.incrementAndGet();
    }

    public int getValue(){
        return value.get();
    }
}
