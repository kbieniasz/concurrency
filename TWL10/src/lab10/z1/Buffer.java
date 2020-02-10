package lab10.z1;

import org.jcsp.lang.*;

public class Buffer implements CSProcess {
	AltingChannelInputInt[] fromProducer;
	ChannelOutputInt[] toConsumer;
	
	Buffer( AltingChannelInputInt[] fromProducer, ChannelOutputInt[] toConsumer){
		this.toConsumer=toConsumer;
		this.fromProducer=fromProducer;
	}
	
	@Override
	public void run() {
		final Alternative alt = new Alternative (fromProducer);
        int x,i;
        
		while(true){
       	 	i=alt.fairSelect();
	       	x=fromProducer[i].read();
	       	toConsumer[i].write(x);	
		}		
	}
	
}
