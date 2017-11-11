package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.DisplayListener;

public class DisplayLightListening  implements DisplayListener{
	
	private Boolean isActive;

	
	public DisplayLightListening() {
		isActive = false;
	}
	
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isActive = !hardware.isDisabled();		
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isActive = !hardware.isDisabled();
		
		
	}

	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		// call event log here 
		String classType  = this.getClass().getName();
		newMessage = classType + "\t" + newMessage;
//		writeLog(newMessage)
		
	}
	

}
