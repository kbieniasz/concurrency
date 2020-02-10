package lab10.z1.podejscie2;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;

public class Consumer implements CSProcess{

	private AltingChannelInputInt[] receiveEnds;
	
	Consumer(AltingChannelInputInt[] receiveEnds){
		this.receiveEnds=receiveEnds;
	}
	
	public void run(){
        final Alternative alt = new Alternative (receiveEnds);
        
		while(true){
       	 	int i=alt.fairSelect();
	       	int x=receiveEnds[i].read();
	       	System.out.println(x);	
		}
	}
}
