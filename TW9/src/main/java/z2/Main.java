package z2;

import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;

public class Main {
	private Buffer buf;
	private Consumer consumer;
	private Producer producer;
	private One2OneChannelInt producerC,consumerC;
	private One2OneChannelInt[] bufferChannels;
	
	Main(int n){
		bufferChannels=Channel.one2oneIntArray(n-2);
		producerC=Channel.one2oneInt();
		consumerC=Channel.one2oneInt();
		
		producer=new Producer(producerC.out());
		consumer = new Consumer(consumerC.in());
		buf = new Buffer(producerC.in(),bufferChannels,consumerC.out());
		
	}
}
