/**
 * Function for exact change light listener 
 * Elodie Boudes 10171818, Grace Ferguson 30004869, 
 * Tae Chyung 10139101, Karndeep Dhami 10031989, 
 * Andrew Garcia-Corley 10015169 & Michael de Grood 10134884
 */

package ca.ucalgary.seng300.a2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.IndicatorLight;
import org.lsmr.vending.hardware.IndicatorLightListener;


public class exactChangeLightListening implements IndicatorLightListener {


	private Boolean isActive ;
	static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    static Date dateobj = new Date();

	/**
	 * exact change light 
	 */
	public exactChangeLightListening () {
		isActive = false;
	}
	
	/**
	 * messageDisplay 
	 * @param display
	 * @param oldMessage
	 * @param newMessage
	 */
//	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		// call event log here 
		if (!oldMessage.equals(newMessage)){
			String classType  = this.getClass().getName();
			try {
				LogFile.writeLog("\n"+df.format(dateobj) + "\t" + getClass().getName() + "\t" + newMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		}		
	}

	/**
	 * activated indicator light 
	 */
	@Override
	public void activated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
//		messageChange(display, oldMessage, newMessage);
		
	}

	/**
	 * deactivated indicator light 
	 */
	@Override
	public void deactivated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
//		messageChange(display, oldMessage, newMessage);
		
	}

	/**
	 * light indicator active
	 * @return
	 */
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


