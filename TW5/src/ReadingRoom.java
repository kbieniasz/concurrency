import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadingRoom {
    public Queue<Person> personQueue;
    public Lock queueLock = new ReentrantLock();
    public Lock readingRoomLock = new ReentrantLock();
    public Condition writers;

    private Lock lock = new ReentrantLock();
    private Condition readers = lock.newCondition();
    //private Condition writers = lock.newCondition();


}
