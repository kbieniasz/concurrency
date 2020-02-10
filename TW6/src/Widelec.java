import java.util.concurrent.Semaphore;

class Widelec {
    private Semaphore semaphore = new Semaphore(1);


    private int id;

    public Widelec(int id)
    {
        this.id = id;
    }

    public void podnies() throws InterruptedException {
        semaphore.acquire();
    }

    public void odloz()  {
        semaphore.release();
    }

    public boolean isAvailable() {
        if(semaphore.availablePermits() == 1)
        {
            return true;
        }
        return false;
    }


    public int getId() {
        return id;
    }
    /*
    private boolean available = true;
    private int num;


    public Widelec(int num) {
        this.num = num;
    }

    public synchronized void podnies() {
        this.available = false;
    }

    public synchronized void odloz() {
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getNum() {
        return num;
    }

     */
}