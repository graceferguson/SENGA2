package ca.ucalgary.seng300.a2;

import java.sql.Timestamp;

import org.lsmr.vending.hardware.Display;

public class IndicatorLighListening extends SharedMethodsLightListening {
	
	public IndicatorLighListening() {
		 this.isActive = false;
	}

	// signature can change just used the DisplayLister as a template same message function in both for now
	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		// call event log here 
		Timestamp currentTime = getCurrentTime();
		String classType  = this.getClass().getName();
		newMessage = currentTime + classType + "\t" + newMessage;
//		writeLog(newMessage)
		
	}


	
	

}
