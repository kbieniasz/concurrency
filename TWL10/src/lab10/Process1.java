package lab10;
import org.jcsp.lang.*;

public  class Process1 implements CSProcess {

    private ChannelOutputInt output;

    public Process1(ChannelOutputInt out) {
        output = out;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println("Written...");
            output.write(i);
        }
        output.write(-1);
    }

}