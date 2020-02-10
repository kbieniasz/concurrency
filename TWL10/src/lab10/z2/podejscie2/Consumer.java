package lab10.z2.podejscie2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;

public class Consumer implements CSProcess{

	ChannelInputInt c;
	
	public Consumer(ChannelInputInt c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		while(true){
			System.out.println(c.read());
		}
	}

}
