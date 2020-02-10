package z2.podejscie2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;

public class Consumer implements CSProcess{

	ChannelInputInt channelInputInt;
	
	public Consumer(ChannelInputInt channelInputInt) {
		this.channelInputInt = channelInputInt;
	}

	@Override
	public void run() {
		while(true){
			System.out.println(channelInputInt.read());
		}
	}

}
