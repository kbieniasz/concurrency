package EX1;

import org.jcsp.lang.*;


public class Main {
	private SingleBuffer[] buffers;
	private One2OneChannelInt[] channelForConsumer;
	private One2OneChannelInt[] channelForProducer;
	private Consumer consumer;
	private Producer producer;


	Main(int n){
		channelForProducer = Channel.one2oneIntArray(n);
		channelForConsumer = Channel.one2oneIntArray(n);

		ChannelOutputInt[] consumerOut = Channel.getOutputArray(channelForConsumer);
		ChannelOutputInt[] producerOut = Channel.getOutputArray(channelForProducer);
		AltingChannelInputInt[] consumerIn = Channel.getInputArray(channelForConsumer);
		AltingChannelInputInt[] producerIn = Channel.getInputArray(channelForProducer);

		consumer= new Consumer(consumerIn);
		producer = new Producer(producerOut, producerIn);
		buffers = new SingleBuffer[n];
		for(int i=0;i<buffers.length;i++){
			buffers[i]=new SingleBuffer(channelForProducer[i], consumerOut[i]);
		}
	}


	public static void main(String[] args){
		Main main = new Main(15);
		Parallel parallel = new Parallel();

		for(SingleBuffer b: main.buffers){
			parallel.addProcess(b);
		}
		parallel.addProcess(main.consumer);
		parallel.addProcess(main.producer);
		parallel.run();
	}
}