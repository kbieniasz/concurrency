package solution1;

import java.util.concurrent.Semaphore;

public class Widelec {
    private Semaphore semaphore = new Semaphore(1);
    private int id;

    public Widelec(int id) {
        this.id = id;
    }

    public void podnies() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void odloz() {
        semaphore.release();
    }
}
