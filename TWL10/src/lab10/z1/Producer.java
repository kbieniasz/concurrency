package lab10.z1;

import java.util.Random;

import org.jcsp.lang.*;

public class Producer implements CSProcess {

	ChannelOutputInt[] toBuffer;
	
	Producer(ChannelOutputInt[] toBuffer){
		this.toBuffer=toBuffer;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		while(true){
			for(ChannelOutputInt c: toBuffer){
				c.write(r.nextInt(1337));
			}
		}	
		
	}
	
}
