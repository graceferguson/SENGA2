package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.IndicatorLight;
import org.lsmr.vending.hardware.IndicatorLightListener;


public class exactChangeLightListening implements IndicatorLightListener {


	private Boolean isActive ;
	
	
	public exactChangeLightListening () {
		isActive = false;
	}
	
//	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		// call event log here 
		if (!oldMessage.equals(newMessage)){
			String classType  = this.getClass().getName();
			newMessage = classType + "\t" + newMessage;
//			writeLog(newMessage)
	
		}
		
		 
	}

	@Override
	public void activated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
//		messageChange(display, oldMessage, newMessage);
		
	}

	@Override
	public void deactivated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
//		messageChange(display, oldMessage, newMessage);
		
	}

	
	public Boolean getIsActive() {
		return isActive;
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}
	

}


