import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class MandelbrotCallable implements Callable {
    private int MAX_ITER;
    private final double ZOOM = 150;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;

    private int height;
    private int width;
    private int threadNumber;
    private int numberOfThreads;


    MandelbrotCallable(int height, int width, BufferedImage I, int numberOfIter, int threadNumber, int numberOfThreads)
    {
        this.height = height;
        this.width = width;
        this.I = I;
        this.MAX_ITER = numberOfIter;
        this.threadNumber = threadNumber;
        this.numberOfThreads = numberOfThreads;
    }

    public Integer call() {
        for (int y = (height/numberOfThreads)*threadNumber;  y <(height/numberOfThreads)*(threadNumber+1); y++) {
            for (int x = 0; x < width; x++) {
                zx = zy = 0;
                cX = (x - 400) / ZOOM;
                cY = (y - 300) / ZOOM;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                I.setRGB(x, y, iter | (iter << 8));
            }
        }
        return 0;
    }
}
