package lab10.z2;

import java.util.Random;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

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
		