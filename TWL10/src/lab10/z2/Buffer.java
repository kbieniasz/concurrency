package lab10.z2;

import org.jcsp.lang.Alternative;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;

public class Buffer implements CSProcess{

	private ChannelInputInt fromProd;
	private ChannelOutputInt toCons;
	private One2OneChannelInt[] channels;
	
	Buffer(ChannelInputInt fromProd, One2OneChannelInt[] channels, ChannelOutputInt toCons){
		this.fromProd=fromProd;
		this.toCons=toCons;
		this.channels=channels;
	}
	
	@Override
	public void run() {
        int x;
		while(true){
			x=fromProd.read();
			
			
		}		
	}
}
