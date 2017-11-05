//SENG300 Group Assignment 1
//Tae Chyung (10139101), Cameron Davies (30003456) & Grace Ferguson (30004869)

package ca.ucalgary.seng300.a1;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;

/**
 * Listener class for coin slot
 */
public class CoinSlotListening implements CoinSlotListener {
	private boolean isOn;

	public CoinSlotListening() {
		isOn = true;
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

	public void validCoinInserted(CoinSlot slot, Coin coin) {
	}

	public void coinRejected(CoinSlot slot, Coin coin) {
	}

	/**
	 * method for checking state of listener
	 * 
	 * @return boolean state of listener
	 */
	public boolean isOn() {
		return isOn;
	}

}
