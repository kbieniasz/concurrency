package lab10;
import org.jcsp.lang.*;
//import jcsp.lang.*


public class DriverProgram
{
	public static void main(String[] args) {

	    One2OneChannelInt chan = Channel.one2oneInt();
	    One2OneChannelInt chan1 = Channel.one2oneInt();

	    AltingChannelInputInt[] q = Channel.getInputArray(new One2OneChannelInt[] {chan,chan1});
	   
	    //;;One2AnyChannelInt c1 = Channel.one2anyInt();
	    
	    Process1 process3 = new Process1(chan1.out());
	    Process1 process1 = new Process1(chan.out());
	   // Process2 process2 = new Process2(chan.in(),chan1.in());
	    Process3 process2 = new Process3(q);
	    Parallel parallel = new Parallel();

	    parallel.addProcess(process1);
	    parallel.addProcess(process2);
	    parallel.addProcess(process3);
	    parallel.run();
	}
}
