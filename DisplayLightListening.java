/**
 * Function for display light listener
 * Elodie Boudes 10171818, Grace Ferguson 30004869, 
 * Tae Chyung 10139101, Karndeep Dhami 10031989, 
 * Andrew Garcia-Corley 10015169 & Michael de Grood 10134884
 */

package ca.ucalgary.seng300.a2;

import java.io.IOException;

import org.lsmr.vending.hardware.AbstractHardware;
import ca.ucalgary.seng300.a2.LogFile;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.Display;
import org.lsmr.vending.hardware.DisplayListener;

public class DisplayLightListening  implements DisplayListener{
	
	private Boolean isActive;
	private LogFile logfile;
	

	/**
	 * method for determining if the display light is active 
	 */
	public DisplayLightListening() {
		isActive = false;
	}
	
	/**
	 * enabled display light 
	 */
	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isActive = !hardware.isDisabled();		
	}

	/**
	 * disabled display light 
	 */
	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isActive = !hardware.isDisabled();
		
		
	}

	/**
	 * create message for display 
	 */
	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		String classType  = this.getClass().getName();
		newMessage = classType + "\t" + newMessage;
		try {
			LogFile.writeLog(LogFile.df.format(LogFile.dateobj) + "\t" + this.getClass().getName() + "\t" + "Display");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}


	

}
