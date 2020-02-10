package z1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;

public class Consumer implements CSProcess {
	private AltingChannelInputInt[] receiveEnds;
	
	Consumer(AltingChannelInputInt[] receiveEnds){
		this.receiveEnds=receiveEnds;
	}
	
	public void run(){
        final Alternative alt = new Alternative (receiveEnds);
        int x,i;
        
		while(true){
       	 	i=alt.fairSelect();
	       	x=receiveEnds[i].read();
	       	System.out.println(x);	
		}
	}
}
