import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.concurrent.*;


public class Main extends JFrame {
    private BufferedImage I;
    private static final int THREADS = 2;

    public static void main(String[] args) {
        Main main =new Main(THREADS);
        main.setVisible(true);
    }

    public Main(int procs){
        super("Mandelbrot Set");
        setBounds(100, 100, 800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        ExecutorService pool = Executors.newWorkStealingPool();
        LinkedList<Future<Integer>> listOfFutures = new LinkedList<>();
        long start = System.currentTimeMillis();
        int maxY = getHeight();

        for (int i = 0; i<maxY; i++) {
            MandelbrotTask callable = new MandelbrotTask(i, getWidth(), I);
            Future<Integer> future = pool.submit(callable);
            listOfFutures.add(future);
        }

        for(Future<Integer> f : listOfFutures) {
            try {
                f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
}