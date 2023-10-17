package cleancode.concurrency;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class ClassWithThreadingProblemTest {
    @RepeatedTest(20)
    public void twoThreadsShouldFailEventually() throws Exception {
        final ClassWithThreadingProblem classWithThreadingProblem
                = new ClassWithThreadingProblem();

        Runnable runnable = new Runnable() {
            public void run() {
                classWithThreadingProblem.takeNextId();
            }
        };

        for (int i = 0; i < 50000; ++i) {
            int startingId = classWithThreadingProblem.nextId;
            int expectedResult = 2 + startingId;

            Thread t1 = new Thread(runnable);
            Thread t2 = new Thread(runnable);
            t1.start();
            t2.start();
            t1.join();
            t2.join();

            int endingId = classWithThreadingProblem.nextId;

            if (endingId != expectedResult)
                fail("예상치 않은 스레드 실행");
        }
    }
}