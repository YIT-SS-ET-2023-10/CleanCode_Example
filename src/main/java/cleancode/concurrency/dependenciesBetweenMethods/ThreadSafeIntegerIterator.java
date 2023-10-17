package cleancode.concurrency.dependenciesBetweenMethods;

//Adapter 패턴을 사용하여 Server Lock 예 - 서버 코드를 수정할 수 없는 경우 구현 방법
public class ThreadSafeIntegerIterator {
    private IntegerIterator iterator = new IntegerIterator();

    public synchronized Integer getNextOrNull(){
        if(iterator.hasNext())
            return iterator.next();
        return null;
    }
}
