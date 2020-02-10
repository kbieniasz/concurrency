package lab10;
import org.jcsp.lang.*;


public class ReadEvenIntsProcess implements CSProcess
{
    private ChannelInput in;
    public ReadEvenIntsProcess(ChannelInput in)
    {
      this.in = in;
    }

    public void run()
    {
      while (true)
      {
        Integer d = (Integer)in.read();
        System.out.println("Read: " + d.intValue());
      }
    }
}