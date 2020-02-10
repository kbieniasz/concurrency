package z1.podejscie2;

import org.jcsp.lang.*;


public class Main {
	private BufferUnit[] buf;
	private Consumer consumer;
	private Producer producer;
	private One2OneChannelInt[] producerChannels,consumerChannels;
	
	Main(int n){
		//Tworzymy tablice kanałów - każdy element bufora ma kanał do komunikacji z konsumentem i producentem
	   producerChannels = Channel.one2oneIntArray(n);
	   consumerChannels = Channel.one2oneIntArray(n);
	   
	   //wymagane przez API:
	   AltingChannelInputInt[] producerIn = Channel.getInputArray(producerChannels);
	   AltingChannelInputInt[] consumerIn = Channel.getInputArray(consumerChannels);
	   ChannelOutputInt[] producerOut = Channel.getOutputArray(producerChannels);
	   ChannelOutputInt[] consumerOut = Channel.getOutputArray(consumerChannels);
	  
	   consumer= new Consumer(consumerIn);
	   producer = new Producer(producerOut, producerIn);
	   buf = new BufferUnit[n];
	   for(int i=0;i<buf.length;i++){
		   buf[i]=new BufferUnit(producerChannels[i], consumerOut[i]);
	   }  
	}
	
	static void makeItRun(int n){
		Main main = new Main(n);
		Parallel parallel = new Parallel();
	    for(BufferUnit b: main.buf){
	    	parallel.addProcess(b);
	    }
	    parallel.addProcess(main.consumer);
	    parallel.addProcess(main.producer);
	    parallel.run();
	}

	public static void main(String[] args){
		makeItRun(15);
	}
}