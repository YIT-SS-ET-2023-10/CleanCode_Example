package cleancode.concurrency;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class ObjectWithValueTest {
    @RepeatedTest(20)
    public void twoThreadsShouldNotFail() throws Exception {
        final ObjectWithValue objectWithValue
                = new ObjectWithValue();

        Runnable runnable = new Runnable() {
            public void run() {
                objectWithValue.incrementValue();
            }
        };

        for (int i = 0; i < 50000; ++i) {
            int startingValue = objectWithValue.getValue();
            int expectedResult = 2 + startingValue;

            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            int endingValue = objectWithValue.getValue();

            if (endingValue != expectedResult)
                fail("예상치 않은 스레드 실행");
        }
    }
}