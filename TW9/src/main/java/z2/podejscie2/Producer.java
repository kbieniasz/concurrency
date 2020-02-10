package z2.podejscie2;



import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

public class Producer implements CSProcess{
	ChannelOutputInt channelOutputInt;

	public Producer(ChannelOutputInt channelOutputInt) {
		this.channelOutputInt = channelOutputInt;
	}

	@Override
	public void run() {
		int counter=0;
		while(true){
			channelOutputInt.write(++counter);
		}
	}
	
}
