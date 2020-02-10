package z2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;

public class Consumer implements CSProcess {
	private ChannelInputInt receiveEnd;
	
	Consumer(ChannelInputInt receiveEnd){
		this.receiveEnd=receiveEnd;
	}
	
	public void run(){
		int x;
		while(true){
	       	x=receiveEnd.read();
	       	System.out.println(x);	
		}
	}
}