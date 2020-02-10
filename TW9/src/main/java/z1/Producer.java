package z1;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;

import java.util.Random;

/** Producer class: produces one random integer and sends on
 * output channel, then terminates.
 */


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


