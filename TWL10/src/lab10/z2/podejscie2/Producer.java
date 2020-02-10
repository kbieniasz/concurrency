package lab10.z2.podejscie2;



import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

public class Producer implements CSProcess{
	ChannelOutputInt c;

	public Producer(ChannelOutputInt c) {
		super();
		this.c = c;
	}

	@Override
	public void run() {
		int i=0;
		while(true){
			c.write(i);
			i++;
			i%=1337;
		}
	}
	
}
