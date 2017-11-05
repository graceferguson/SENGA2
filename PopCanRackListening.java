//SENG300 Group Assignment 1
//Tae Chyung (10139101), Cameron Davies (30003456) & Grace Ferguson (30004869)

package ca.ucalgary.seng300.a1;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;

/**
 * listener for pop can rack
 */
public class PopCanRackListening implements PopCanRackListener {
	private boolean isOn;
	private boolean isEmpty;

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
	 * method for adding pop cans
	 */
	public void popCanAdded(PopCanRack popCanRack, PopCan popCan) {
		if (isEmpty) {
			isEmpty = false;
		}
	}

	/**
	 * method for removing pop cans
	 */
	public void popCanRemoved(PopCanRack popCanRack, PopCan popCan) {
	}

	/**
	 * method for checking if rack is full
	 */
	public void popCansFull(PopCanRack popCanRack) {
	}

	/**
	 * method for testing if rack is empty
	 */
	public void popCansEmpty(PopCanRack popCanRack) {
		isEmpty = true;
	}

	/**
	 * method for loading pop cans
	 */
	public void popCansLoaded(PopCanRack rack, PopCan... popCans) {
		if (isEmpty) {
			isEmpty = false;
		}
	}

	/**
	 * method for unloading pop cans
	 */
	public void popCansUnloaded(PopCanRack rack, PopCan... popCans) {
		isEmpty = true;
	}

	/**
	 * check if pop can rack is empty
	 * 
	 * @return boolean state of pop can rack
	 */
	public boolean isEmpty() {
		return isEmpty;
	}

	/**
	 * check if listener is enabled
	 * 
	 * @return boolean state of listener
	 */
	public boolean isOn() {
		return isOn;
	}

}
