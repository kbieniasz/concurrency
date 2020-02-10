package lab10;


import org.jcsp.lang.*
;

public  class Process3 implements CSProcess {

    private AltingChannelInputInt[] q;

    //AltingChannelInputInt[] q;
    public Process3(AltingChannelInputInt[] q) {
        this.q=q;
	    //q = Channel.get
    }

    @Override
    public void run() {
        final Guard[] altChans =q;
        final Alternative alt = new Alternative (altChans);
        boolean last=false;
        int x = 0;
        while (true) {
        	int i=alt.fairSelect();
        	x=q[i].read();
        	System.out.println(x);
        	if(x<0){
        		if(last){
        			break;
        		}else{
        			last=true;
        		}
        	}
        }
    }

}
