package EX1;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

public class Producer implements CSProcess {
	ChannelOutputInt[] channelOutputInts;
	AltingChannelInputInt[] altingChannelInputInts;
	
	public Producer(ChannelOutputInt[] channelOutputInts, AltingChannelInputInt[] altingChannelInputInts) {
		this.channelOutputInts = channelOutputInts;
		this.altingChannelInputInts = altingChannelInputInts;
	}

	public void run(){
		final Alternative alternative = new Alternative (altingChannelInputInts);
        int counter = 0;
		while(true){
       	 	int i=alternative.fairSelect();
	       	int x=altingChannelInputInts[i].read();
			System.out.println(x);
			channelOutputInts[i].write(++counter);
		}		
	}
}
