//SENG300 Group Assignment 1
//Tae Chyung (10139101), Cameron Davies (30003456) & Grace Ferguson (30004869)

package ca.ucalgary.seng300.a2;

import java.io.IOException;
import java.text.*;
import java.util.Date; 
import org.lsmr.vending.hardware.*;

/**
 * Class for selection button listener
 */
public class SelectionButtonListening implements PushButtonListener {
	private boolean isOn;
	private int index;
	private VendCommunicator communicator;
	
	static DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    static Date dateobj = new Date();

	/**
	 * listener object for selection button
	 * @param num numerical representation of button pressed
	 * @param com communicator 
	 */
	public SelectionButtonListening(int num, VendCommunicator com) {
		isOn = true;
		index = num;
		communicator = com;
	}

	/**
	 * method for enabling listener
	 */
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isOn = true;
	}

	/**
	 * method for disabling listener
	 */
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isOn = false;
	}

	/**
	 * method for dealing with button pressed
	 */
	public void pressed(PushButton button) {
		communicator.purchasePop(index);
		try {
			LogFile.writeLog("\n"+df.format(dateobj) + "\t" + getClass().getName() + "\t" + "button pressed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method for retrieving state of listener
	 * @return boolean state of listener
	 */
	public boolean isOn() {
		return isOn;
	}


}
