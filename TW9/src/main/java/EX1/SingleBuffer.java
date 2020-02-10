package EX1;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;

public class SingleBuffer implements CSProcess{

	One2OneChannelInt channelForProducer;
	ChannelOutputInt channelOutputIntConsumer;
	private int number = 1;

	public SingleBuffer(One2OneChannelInt channelForProducer, ChannelOutputInt channelOutputIntConsumer) {
		this.channelForProducer = channelForProducer;
		this.channelOutputIntConsumer = channelOutputIntConsumer;
	}

	@Override
	public void run() {
		while (true) {
			channelForProducer.out().write(number);
			int x = channelForProducer.in().read();
			channelOutputIntConsumer.write(x);
		}
	}
}
