package z2.podejscie2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;

public class SingleBuffer implements CSProcess{

	ChannelInputInt channelInputInt;
	ChannelOutputInt channelOutputInt;
	
	public SingleBuffer(ChannelInputInt channelInputInt, ChannelOutputInt channelOutputInt) {
		this.channelInputInt = channelInputInt;
		this.channelOutputInt = channelOutputInt;
	}

	@Override
	public void run() {
		while(true){
			channelOutputInt.write(channelInputInt.read());
		}
	}

}
