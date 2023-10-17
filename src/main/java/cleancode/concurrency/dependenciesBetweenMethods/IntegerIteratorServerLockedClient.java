package cleancode.concurrency.dependenciesBetweenMethods;

public class IntegerIteratorServerLockedClient {

    public void processWithServerLocked(){
        IntegerIteratorServerLocked iterator = new IntegerIteratorServerLocked();

        while(true){
            Integer nextValue = iterator.getNextOrNull();
            if(nextValue == null) break;
            doSomething(nextValue);
        }
    }

    private void doSomething(int nextValue) {
    }
}
