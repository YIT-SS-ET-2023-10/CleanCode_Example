package cleancode.concurrency.dependenciesBetweenMethods;

import java.util.Iterator;

public class IntegerIteratorServerLocked implements Iterator<Integer> {
    private Integer nextValue = 0;

    @Override
    public synchronized boolean hasNext() {
        return nextValue < 100000;
    }

    @Override
    public synchronized Integer next() {
        if(nextValue == 100000) {
            try {
                throw new IteratorPastEndException();
            } catch (IteratorPastEndException e) {
                e.printStackTrace();
            }
        }
        return nextValue++;
    }

    public synchronized Integer getNextOrNull()
    {
        if(nextValue < 100000)
            return nextValue++;
        else
            return null;
    }

}
