package solution2;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Filozof extends Thread {
    private int _licznik = 0;
    private Widelec lewyWidelec;
    private Widelec prawyWidelec;
    private int id;
    private Semaphore semaforProbujDwaWidelce;

    public Filozof(int id, Widelec lewyWidelec, Widelec prawyWidelec, Semaphore semafor)
    {
        this.id = id;
        this.lewyWidelec = lewyWidelec;
        this.prawyWidelec = prawyWidelec;
        this.semaforProbujDwaWidelce = semafor;
    }


    public void run() {
        while (true) {

            myślenie();
            /*
            while(true)
            {

                try {
                    semaforProbujDwaWidelce.acquire();
                    System.out.println("Filozof o id = "+ id + " próbuje podnieść oba widelce");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(lewyWidelec.czyDostępny() && prawyWidelec.czyDostępny())
                {
                    prawyWidelec.podnies();
                    lewyWidelec.podnies();
                    semaforProbujDwaWidelce.release();
                    break;
                }
                semaforProbujDwaWidelce.release();
            }

                 */
            while(true) {

              //  System.out.println("Filozof o id = "+ id + " próbuje podnieść oba widelce");
                if (lewyWidelec.czyDostępny() && prawyWidelec.czyDostępny()) {
                    prawyWidelec.podnies();
                    lewyWidelec.podnies();
                    semaforProbujDwaWidelce.release();
                    break;
                }
                Random rand = new Random();
                try {
                    sleep(rand.nextInt(10)+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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
