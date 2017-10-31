/**
* SENG300 Group Assignment 1
* @author Tae Chyung (10139101), Cameron Davies (30003456) & Grace Ferguson (30004869)
* 
* A delivery chute listener class
*/

package ca.ucalgary.seng300.a1;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;

public class DeliveryChuteListening implements DeliveryChuteListener {

	boolean active;
	Deliverable[] itemsReturned;

	/**
	 * method for enabling listener
	 */
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		active = true;
	}

	/**
	 * method for disabling listener
	 */
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		active = false;
	}

	/**
	 * When an item is sent to the delivery chute, empties the chute, and checks
	 * if the item was a popcan and outputs it
	 */

	public void itemDelivered(DeliveryChute chute) {
		itemsReturned = chute.removeItems();
		for (int i = 0; i < itemsReturned.length; i++) {
			if (itemsReturned[i] instanceof PopCan) {
				System.out.println(itemsReturned[i].toString() + " has been dispensed");
			}
		}
	}

	public void doorOpened(DeliveryChute chute) {

	}

	public void doorClosed(DeliveryChute chute) {

	}

	public void chuteFull(DeliveryChute chute) {

	}

}
