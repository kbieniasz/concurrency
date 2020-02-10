package solution3;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Filozof extends Thread {
    private int _licznik = 0;
    private Widelec lewyWidelec;
    private Widelec prawyWidelec;
    private int id;
    private Semaphore podejscieDoStołu;

    public Filozof(int id, Widelec lewyWidelec, Widelec prawyWidelec, Semaphore semafor)
    {
        this.id = id;
        this.lewyWidelec = lewyWidelec;
        this.prawyWidelec = prawyWidelec;
        this.podejscieDoStołu = semafor;
    }


    public void run() {
        while (true) {

            myślenie();



            try {
                podejscieDoStołu.acquire();
                //System.out.println("Filozof o id = "+ id + " podchodzi do stolu");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prawyWidelec.podnies();
            lewyWidelec.podnies();



           // System.out.println("Filozof o id = "+ id + " podniósł oba widelce");
            jedzenie();
            // jedzenie
            ++_licznik;
            if (_licznik % 100000 == 0) {
                /*
                System.out.println("Filozof: " + id +
                        " jadlem " + _licznik + " razy, czas: " + System.currentTimeMillis());

                 */

                System.out.println( id +";" + System.currentTimeMillis());


            }
            prawyWidelec.odloz();
           // System.out.println("Filozof o id = "+ id + " odłożył prawy widelec");
            lewyWidelec.odloz();
          //  System.out.println("Filozof o id = "+ id + " odłożył lewy widelec");
            // koniec jedzenia
            podejscieDoStołu.release();

        }
    }


    public void myślenie()
    {
        //System.out.println("Filozof o id = "+ id + " śpi");
        Random rand = new Random();

        /*
        try {
            sleep(rand.nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         */
    }


    public void jedzenie()
    {
        //System.out.println("Filozof o id = "+ id + " je");
        Random rand = new Random();

        /*
        try {
            sleep(rand.nextInt(10)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         */
    }
}
