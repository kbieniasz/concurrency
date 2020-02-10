package z1.podejscie2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;

public class BufferUnit implements CSProcess{
	static final int JESZCZE = 0;
	One2OneChannelInt producerC;
	ChannelOutputInt toConsumer;

	public BufferUnit(One2OneChannelInt producerC, ChannelOutputInt toConsumer) {
		this.producerC = producerC;
		this.toConsumer = toConsumer;
	}

	@Override
	public void run() {
		while(true){
			producerC.out().write(JESZCZE); //sygnał do producenta (JESZCZE())
			int x =producerC.in().read(); //odbiór
			toConsumer.write(x);
		}
	}

	
}
