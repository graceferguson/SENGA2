package ca.ucalgary.seng300.a2;

import java.sql.Timestamp;
import java.util.Date;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.IndicatorLight;
import org.lsmr.vending.hardware.IndicatorLightListener;

public class SharedMethodsLightListening  implements IndicatorLightListener{
	
	
	Boolean isActive; 
	
	public Timestamp getCurrentTime() {	
			
			Date date= new Date();
		   	long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			return ts;
		
		}
	
	public void messageChange(Display display, String oldMessage, String newMessage) {
		// call event log here 
		Timestamp currentTime = getCurrentTime();
		String classType  = this.getClass().getName();
		newMessage = currentTime+ classType + "\t" + newMessage;
//		writeLog(newMessage)	
	}
	
	public Boolean getisActive() {
			return isActive;
	}
	
	@Override
	public void activated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
	//	messageChange(display, oldMessage, newMessage);		
	}
	
	@Override
	public void deactivated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
	//	messageChange(display, oldMessage, newMessage);		
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
