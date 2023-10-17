package cleancode.concurrency.dependenciesBetweenMethods;

public class IntegerIteratorClient {

    public void process(){
        IntegerIterator iterator = new IntegerIterator();

        while(iterator.hasNext()){
            int nextValue = iterator.next();
            doSomething(nextValue);
        }
    }

    public void processWithClientLocked(){
        IntegerIterator iterator = new IntegerIterator();

        while(true){

            int nextValue;
            synchronized (iterator){
                if(!iterator.hasNext()) break;
                nextValue = iterator.next();
            }
            doSomething(nextValue);
        }
    }

    private void doSomething(int nextValue) {
    }
}
