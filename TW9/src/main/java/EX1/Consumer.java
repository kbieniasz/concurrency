package EX1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;

public class Consumer implements CSProcess{

	private AltingChannelInputInt[] channelEnd;
	
	Consumer(AltingChannelInputInt[] channelEnd){
		this.channelEnd=channelEnd;
	}
	
	public void run(){
        final Alternative alt = new Alternative (channelEnd);
        
		while(true){
       	 	int i=alt.fairSelect();
	       	int x=channelEnd[i].read();
	       	System.out.println(x);	
		}
	}
}
