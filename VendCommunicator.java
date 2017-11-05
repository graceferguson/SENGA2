//SENG300 Group Assignment 1
//Tae Chyung (10139101), Cameron Davies (30003456) & Grace Ferguson (30004869)

package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.*;

/**
 * Class that holds a reference to the parts of the machine necessary for
 * communication between parts of the vending machine.
 */
public class VendCommunicator {

	private CoinReceptacleListening receptacle;
	private PopCanRackListening[] pRacks;
	private VendingMachine machine;
	private IndicatorLighListening lightListener;
	private DisplayLightListening displayLightListener;

	public VendCommunicator() {
	}

	// Links the appropriate parts to their corresponding variables
	public void linkVending(CoinReceptacleListening receptacle, PopCanRackListening[] pRacks, IndicatorLighListening indicator, DisplayLightListening display, VendingMachine machine) {
		this.receptacle = receptacle;
		this.pRacks = pRacks;
		this.machine = machine;
		this.lightListener = indicator;
		this.displayLightListener = display;
	}

	/**
	 * Function that is called by SelectionButtonListening
	 * 
	 * index - index of the selectionButton calling the function
	 * 
	 * Checks if the requested pop is available. If it is, checks to see if the
	 * machine has enough credit to purchase the soda. If enough credit is
	 * available, deducts the price of the appropriate pop and calls for the
	 * machine to dispense said pop. Prints an appropriate message in each
	 * instance.
	 */
	public void purchasePop(int index) {
		if (pRacks[index].isEmpty()) {
			System.out.println("Out of " + machine.getPopKindName(index));
		} else if (receptacle.getValue() >= machine.getPopKindCost(index)) {
			try {
				receptacle.Purchase(machine.getPopKindCost(index));
				machine.getPopCanRack(index).dispensePopCan();
			} catch (DisabledException e) {
			} catch (EmptyException e) {
			} catch (CapacityExceededException e) {
			}
		} else {
			System.out.println("Insufficient Funds");
		}
	}
}
