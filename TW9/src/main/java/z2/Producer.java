package z2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

import java.util.Random;

public class Producer implements CSProcess {

	ChannelOutputInt toBuffer;
	
	Producer(ChannelOutputInt toBuffer){
		this.toBuffer=toBuffer;
	}
	
	@Override
	public void run() {
		Random r = new Random();
		while(true){
		toBuffer.write(r.nextInt(1337));
		}
	}
}
		