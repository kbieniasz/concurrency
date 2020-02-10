package solution1;

import java.util.Random;

class Filozof extends Thread {
    private int _licznik = 0;
    private Widelec lewyWidelec;
    private Widelec prawyWidelec;
    private int id;

    public Filozof(int id, Widelec lewyWidelec, Widelec prawyWidelec)
    {
        this.id = id;
        this.lewyWidelec = lewyWidelec;
        this.prawyWidelec = prawyWidelec;
    }


    public void run() {
        while (true) {

            myślenie();
            prawyWidelec.podnies();
            System.out.println("Filozof o id = "+ id + " podniósł prawy widelec");
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lewyWidelec.podnies();
            System.out.println("Filozof o id = "+ id + " podniósł lewy widelec");
            jedzenie();
            // jedzenie
            ++_licznik;
            if (_licznik % 100 == 0) {
                System.out.println("Filozof: " + Thread.currentThread() +
                        "jadlem " + _licznik + " razy");
            }
            prawyWidelec.odloz();
            System.out.println("Filozof o id = "+ id + " odłożył prawy widelec");
            lewyWidelec.odloz();
            System.out.println("Filozof o id = "+ id + " odłożył lewy widelec");
            // koniec jedzenia

        }
    }


    public void myślenie()
    {
        System.out.println("Filozof o id = "+ id + " śpi");
        Random rand = new Random();

        try {
            sleep(rand.nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void jedzenie()
    {
        System.out.println("Filozof o id = "+ id + " je");
        Random rand = new Random();

        try {
            sleep(rand.nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
