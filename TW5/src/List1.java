
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class List1 {
    private Object val;
    private List1 next;
    private Lock lock;
    private static long sleepTime;

    public List1(Object val, List1 next) {
        this.val = val;
        this.next = next;
        lock = new ReentrantLock();
    }

    public boolean contains(Object o) throws InterruptedException {
        List1 prev = null, tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    Thread.sleep(sleepTime / 10);
                    return true;
                }
                prev = tmp;
                tmp = tmp.next;
                try {
                    if (tmp != null) {
                        tmp.lock.lock();
                    }
                } finally {
                    prev.lock.unlock();
                }
            }
        } finally {
            if (tmp != null) {
                tmp.lock.unlock();
            }
        }
        return false;
    }

    public boolean remove(Object o) throws InterruptedException {
        List1 prevprev = null, prev = null, tmp = this;
        lock.lock();
        try {
            while (tmp != null) {
                if (val == o) {
                    if (prev != null) {
                        prev.next = tmp.next;
                        tmp.next = null;
                    }
                    Thread.sleep(sleepTime / 3);
                    return true;
                }
                prevprev = prev;
                prev = tmp;
                tmp = tmp.next;
                try {
                    if (tmp != null) {
                        tmp.lock.lock();
                    }
                } finally {
                    if (prevprev != null) {
                        prevprev.lock.unlock();
                    }
                }
            }
        } finally {
            if (prev != prevprev) {
                prev.lock.unlock();
            }
            if (tmp != null) {
                tmp.lock.unlock();
            }
        }
        return false;
    }

    public boolean add(Object o) throws InterruptedException {
        if (o == null) {
            return false;
        }
        List1 tmp = this, next = this.next;
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
            tmp.next = new List1(o, null);
            Thread.sleep(sleepTime);
            return true;
        } finally {
            tmp.lock.unlock();
            if (next != tmp && next != null) {
                next.lock.unlock();
            }
        }
    }

    public static void setSleepTime(long sleepTime) {
        List1.sleepTime = sleepTime;
    }
}
