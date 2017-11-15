/**
 * Function for light listener 
 * Elodie Boudes 10171818, Grace Ferguson 30004869, 
 * Tae Chyung 10139101, Karndeep Dhami 10031989, 
 * Andrew Garcia-Corley 10015169 & Michael de Grood 10134884
 */

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
	private String prevMessage = "";
	private String currMessage = "";
	
	public Timestamp getCurrentTime() {	
			
			Date date= new Date();
		   	long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			return ts;
		
		}
	
	public void messageChange(String oldMessage, String newMessage) {
		// call event log here 
		Timestamp currentTime = getCurrentTime();
		String classType  = this.getClass().getName();
		newMessage = currentTime+ classType + "\t" + newMessage;
//		try {
//			LogFile.writeLog(newMessage);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} 
	}
	
	/**
	 * 
	 * @return
	 */
	public Boolean getisActive() {
			return isActive;
	}
	
	/**
	 * 
	 * @param light
	 */
	private void setMessage (IndicatorLight light) {
		prevMessage  = "Light isActive value was" + isActive;
		isActive = light.isActive();
		currMessage = "Light isActive value is now " + isActive; 
		messageChange(currMessage, prevMessage);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void activated(IndicatorLight light) {
		setMessage(light);
		
	}
	
	/**
	 * 
	 */
	@Override
	public void deactivated(IndicatorLight light) {
		setMessage(light);
	
	}
	
	/**
	 * 
	 */
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		// TODO Auto-generated method stub
		
	}


}
