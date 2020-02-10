import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ListThread extends Thread {
    // dodawanie na koniec listy
    public Object val;
    public ListThread next;
    public Lock lock;
    //private ListThread head;
    private Object operationVal;
    private int operationType; // 1 -add  2 contains 3 remove



    public ListThread(Object o, ListThread next)
    {
        this.val = o;
        lock = new ReentrantLock();
        next = null;
    }

    public ListThread(ListThread head, int operationType, Object operationVal)
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

    public boolean add(Object operationVal) throws InterruptedException {
        ListThread tmp = this;
        //next = this.next;
        lock.lock();
        try {
            while (next != null) {
                try {
                    next.lock.lock();
                } finally {
                    tmp.lock.unlock();
                }
                tmp = next;
                next = next.next;
            }
            tmp.next = new ListThread(operationVal, null);
            return true;
        } finally {
            tmp.lock.unlock();
            if (next != tmp && next != null) {
                next.lock.unlock();
            }
        }
    }

    public boolean contains(Object operationVal) throws InterruptedException {
        ListThread previous = null;
        ListThread tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == operationVal) {
                    return true;
                }
                previous = tmp;
                tmp = tmp.next;
                try {
                    if (tmp != null) {
                        tmp.lock.lock();
                    }
                } finally {
                    previous.lock.unlock();
                }
            }
        } finally {
            if (tmp != null) {
                tmp.lock.unlock();
            }
        }
        return false;
    }

    public boolean remove(Object operationVal) throws InterruptedException {
        ListThread beforePrevious = null;
        ListThread previous = null;
        ListThread tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == operationVal) {
                    if (previous != null) {
                        previous.next = tmp.next;
                        tmp.next = null;
                    }
                    return true;
                }
                beforePrevious = previous;
                previous = tmp;
                tmp = tmp.next;
                try {
                    if (tmp != null) {
                        tmp.lock.lock();
                    }
                } finally {
                    if (beforePrevious != null) {
                        beforePrevious.lock.unlock();
                    }
                }
            }
        } finally {
            if (previous != beforePrevious) {
                previous.lock.unlock();
            }
            if (tmp != null) {
                tmp.lock.unlock();
            }
        }
        return false;
    }


}
