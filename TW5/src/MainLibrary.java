import java.io.Reader;
import java.io.Writer;

/*
public class MainLibrary {
    private int readersAmount;
    private int writersAmount;
    public long readerWaitingTime = 0L;
    public long writerWaitingTime = 0L;

    public MainLibrary(int readersAmount, int writersAmount) {
        this.readersAmount = readersAmount;
        this.writersAmount = writersAmount;
    }

    public static void main(String[] args) {
        MainLibrary tmp;
        for (int writersAmount = 1; writersAmount <= 10; ++writersAmount) {
            for (int readersAmount = 10; readersAmount <= 100; readersAmount += 5) {
                tmp = new MainLibrary(readersAmount, writersAmount);
                tmp.run();
            }
        }
    }

    public void run() {
        int i;
        final Library library = new Library(this);
        Reader[] reader = new Reader[readersAmount];
        Writer[] writer = new Writer[writersAmount];
        for (i = 0; i < readersAmount; ++i) {
            reader[i] = new Reader(i, library);
            reader[i].start();
        }
        for (i = 0; i < writersAmount; ++i) {
            writer[i] = new Writer(i, library);
            writer[i].start();
        }
        for (i = 0; i < readersAmount; ++i) {
            try {
                reader[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (i = 0; i < writersAmount; ++i) {
            try {
                writer[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("%d %d %d %d",
                readersAmount, writersAmount, readerWaitingTime/readersAmount, writerWaitingTime/writersAmount));
    }

    public long getReaderWaitingTime() {
        return readerWaitingTime;
    }

    public void setReaderWaitingTime(long readerWaitingTime) {
        this.readerWaitingTime = readerWaitingTime;
    }

    public long getWriterWaitingTime() {
        return writerWaitingTime;
    }

    public void setWriterWaitingTime(long writerWaitingTime) {
        this.writerWaitingTime = writerWaitingTime;
    }
}
*/