package lab10.z2.podejscie2;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;




public class Main {
	private BufferUnit[] buf;
	private Consumer consumer;
	private Producer producer;
	private One2OneChannelInt[] channels;
	
	Main(int n){
	   channels = Channel.one2oneIntArray(n);
	   
	   consumer= new Consumer(channels[n-1].in());
	   producer = new Producer(channels[0].out());
	   buf = new BufferUnit[n];
	   for(int i=0;i<buf.length-1;i++){
		   buf[i]=new BufferUnit(channels[i].in(), channels[i+1].out());
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
		makeItRun(100);
	}
}