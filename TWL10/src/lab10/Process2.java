package lab10;

import org.jcsp.lang.*
;

public  class Process2 implements CSProcess {

    private ChannelInputInt input,input2;

    AltingChannelInputInt[] q;
    public Process2(ChannelInputInt in,ChannelInputInt in2) {
        input = in;
        input2=in2;
	    //q = Channel.get
    }

    @Override
    public void run() {
    	
    	
        //final Guard[] altChans = {input, input1};
        //final Alternative alt = new Alternative (altChans);
        
        int x = 0;
        while ((x = input.read()) >= 0) {
            System.out.println(x);
        }
    }

}