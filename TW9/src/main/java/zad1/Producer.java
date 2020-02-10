package zad1;


import org.jcsp.lang.*;

/** Producer class: produces one random integer and sends on
 * output channel, then terminates.
 */

public class Producer implements CSProcess
{
    private One2OneChannelInt channel;

    public Producer (final One2OneChannelInt out)
    {
        channel = out;
    }

    public void run ()
    { int item = (int)(Math.random()*100)+1;
        //channel.write(item);
    } // run

} // class Producer
