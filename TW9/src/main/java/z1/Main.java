package z1;

import org.jcsp.lang.*;

public class Main {
	private Buffer buf;
	private Consumer consumer;
	private Producer producer;
	private One2OneChannelInt[] producerChannels,consumerChannels;
	
	Main(int n){
		//Tworzymy tablice kanałów - każdy element bufora ma kanał do komunikacji z konsumentem i producentem
	   producerChannels = Channel.one2oneIntArray(n);
	   consumerChannels = Channel.one2oneIntArray(n);

	   
	   //Dziadostwo wymagane przez API:
	   AltingChannelInputInt[] producerIn = Channel.getInputArray(producerChannels);
	   AltingChannelInputInt[] consumerIn = Channel.getInputArray(consumerChannels);
	   ChannelOutputInt[] producerOut = Channel.getOutputArray(producerChannels);
	   ChannelOutputInt[] consumerOut = Channel.getOutputArray(consumerChannels);
	  
	   consumer= new Consumer(consumerIn);
	   producer = new Producer(producerOut);
	   buf = new Buffer(producerIn, consumerOut);
	}
	
	static void makeItRun(int n){
		Main main = new Main(n);
		Parallel parallel = new Parallel();
	    parallel.addProcess(main.buf);
	    parallel.addProcess(main.consumer);
	    parallel.addProcess(main.producer);
	    parallel.run();
	}

	public static void main(String[] args){
		makeItRun(100);
	}
}
