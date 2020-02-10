import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import javax.swing.JFrame;

import static java.lang.Thread.sleep;

public class Mandelbrot extends JFrame {

    private int MAX_ITER;
    private final double ZOOM = 150;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;


    public Mandelbrot(int numberOfThreads, int MAX_ITER, int implementation) {
        super("Mandelbrot Set");
        this.MAX_ITER = MAX_ITER;
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        ExecutorService pool = Executors.newSingleThreadExecutor();
        switch (implementation)
        {
            case 1:
                pool = Executors.newSingleThreadExecutor();
                break;
            case 2:
                pool = Executors.newFixedThreadPool(numberOfThreads);
                break;
            case 3:
                pool = Executors.newCachedThreadPool();
                break;
            case 4:
                pool = Executors.newWorkStealingPool(numberOfThreads);
                break;



        }
        List<Future<Integer>> futureList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i<numberOfThreads; i++) {
            MandelbrotCallable callable = new MandelbrotCallable(getHeight(), getWidth(), I, MAX_ITER, i, numberOfThreads);
            Future<Integer> future = pool.submit(callable);
            futureList.add(future);
        }

        for(Future<Integer> future : futureList) {
            try {
                future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        long time = System.currentTimeMillis() - start;

        System.out.println("Threads;" + numberOfThreads + ";MAX_ITER;" + MAX_ITER
                + ";Implementation;" + implementation + ";Time;" + time );
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) throws InterruptedException {
        if(args.length > 2) {
            int numberOfThreads = Integer.parseInt(args[0]);
            int max_iter = Integer.parseInt(args[1]);
            int implementation = Integer.parseInt(args[2]);
            Mandelbrot mandelbrot = new Mandelbrot(numberOfThreads, max_iter, implementation);
        }
        else
        {
            Mandelbrot mandelbrot;
            if(args.length == 1)
            {
                for(int k = 570; k<=570*4; k=k+570)
                    for(int j=1; j<=4; j++)
                        for(int i=1; i<=8; i=i*2)
                           mandelbrot = new Mandelbrot(i, k, j);
            }

            mandelbrot = new Mandelbrot(1, 570, 4);
            mandelbrot.setVisible(true);
        }
    }
}