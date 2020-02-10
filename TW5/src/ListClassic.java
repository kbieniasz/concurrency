import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListClassic extends Thread{
    public Object val;
    public ListClassic next;
    public Lock lock;
    //private ListThread head;
    private Object operationVal;
    private int operationType;



    public ListClassic(Object val, ListClassic next, Lock lock) {
        this.val = val;
        this.next = next;
        this.lock = lock;
    }

    public ListClassic(ListClassic head, int operationType, Object operationVal)
    {
        next = head;
        this.operationType = operationType;
        this.operationVal = operationVal;
    }

    @Override
    public void run() {
        switch(operationType)
        {
            case 1:
                try {
                    add(operationVal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                try {
                    contains(operationVal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    remove(operationVal);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }
    }

    public boolean contains(Object o) throws InterruptedException {
        ListClassic tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    return true;
                }
                tmp = tmp.next;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean remove(Object o) throws InterruptedException {
        ListClassic previous = null;
        ListClassic tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    if (previous != null) {
                        previous.next = tmp.next;
                        tmp.next = null;
                    }

                    return true;
                }
                previous = tmp;
                tmp = tmp.next;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public boolean add(Object o) throws InterruptedException {
        if (o == null) {
            return false;
        }
        ListClassic tmp = this;
        lock.lock();
        try {
            while (next != null) {
                tmp = next;
                next = next.next;
            }
            tmp.next = new ListClassic(o, null, lock);
            return true;
        } finally {
            lock.unlock();
        }
    }
}
