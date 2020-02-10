package lab10.z2.podejscie2;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;

public class BufferUnit implements CSProcess{

	ChannelInputInt cIn;
	ChannelOutputInt cOut;
	
	public BufferUnit(ChannelInputInt cIn, ChannelOutputInt cOut) {
		super();
		this.cIn = cIn;
		this.cOut = cOut;
	}

	@Override
	public void run() {
		while(true){
			cOut.write(cIn.read());
		}
	}

}
