package cleancode.concurrency;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {
    @RepeatedTest(20)
    public void twoThreadsShouldNotFail() throws Exception {
        final IdGenerator idGenerator
                = new IdGenerator();

        Runnable runnable = new Runnable() {
            public void run() {
                idGenerator.incrementId();
            }
        };

        for (int i = 0; i < 50000; ++i) {
            int startingId = idGenerator.getLastId();
            int expectedResult = 2 + startingId;

            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            int endingId = idGenerator.getLastId();

            if (endingId != expectedResult)
                fail("예상치 않은 스레드 실행");
        }
    }
}