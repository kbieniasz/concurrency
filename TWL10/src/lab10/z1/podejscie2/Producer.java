package lab10.z1.podejscie2;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

public class Producer implements CSProcess {
	ChannelOutputInt[] toBuffer;
	AltingChannelInputInt[] fromBuffer;
	
	public Producer(ChannelOutputInt[] toBuffer, AltingChannelInputInt[] fromBuffer) {
		this.toBuffer = toBuffer;
		this.fromBuffer = fromBuffer;
	}

	public void run(){
		final Alternative alt = new Alternative (fromBuffer);
        
		while(true){
       	 	int i=alt.fairSelect();
	       	int x=fromBuffer[i].read(); //x to stała JESZCZE
	       	toBuffer[i].write(x+i);	
		}		
	}
}
