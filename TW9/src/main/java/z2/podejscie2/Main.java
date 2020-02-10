package z2.podejscie2;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;


public class Main {
	private SingleBuffer[] buffers;
	private Consumer consumer;
	private Producer producer;
	private One2OneChannelInt[] channels;
	
	Main(int n){
	   channels = Channel.one2oneIntArray(n);
	   
	   consumer= new Consumer(channels[n-1].in());
	   producer = new Producer(channels[0].out());
	   buffers = new SingleBuffer[n];
	   for(int i=0;i<buffers.length-1;i++){
		   buffers[i]=new SingleBuffer(channels[i].in(), channels[i+1].out());
	   }  
	}


	public static void main(String[] args){
		Main main = new Main(15);
		Parallel parallel = new Parallel();
		for(SingleBuffer singleBuffer: main.buffers){
			parallel.addProcess(singleBuffer);
		}
		parallel.addProcess(main.consumer);
		parallel.addProcess(main.producer);
		parallel.run();
	}
}