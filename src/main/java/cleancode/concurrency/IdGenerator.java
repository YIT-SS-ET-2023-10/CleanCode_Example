package cleancode.concurrency;

public class IdGenerator {
    private int lastIdUsed;

    public synchronized void incrementId(){++lastIdUsed;}

    public int getLastId() {
        return lastIdUsed;
    }
}
