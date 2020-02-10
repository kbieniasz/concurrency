package solution3;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Main {
    public static void main (String args[])
    {
        List<Filozof> listaFilozofów = new ArrayList<>();
        List<Widelec> listaWidelców = new ArrayList<>();
        Semaphore lokaj = new Semaphore(4);
        for(int i=0; i<5; i++)
        {
            Widelec widelec = new Widelec(i);
            listaWidelców.add(widelec);
        }

        for(int i=0; i<5; i++)
        {
            Filozof filozof = new Filozof(i, listaWidelców.get(i), listaWidelców.get((i+4)%5), lokaj);
            listaFilozofów.add(filozof);
        }

        for(Filozof filozof : listaFilozofów)
        {
            filozof.start();
        }

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
