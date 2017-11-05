//SENG300 Group Assignment 1
//Tae Chyung (10139101), Cameron Davies (30003456) & Grace Ferguson (30004869)

package ca.ucalgary.seng300.a2;

import org.lsmr.vending.hardware.*;
import java.util.*;

/**
 * Class that holds a reference to the parts of the machine necessary for
 * communication between parts of the vending machine.
 */
public class VendCommunicator {

	private CoinReceptacleListening receptacle;
	private PopCanRackListening[] pRacks;
	private VendingMachine machine;
	private HashMap<CoinRack, CoinRackListening> cRacks;

	public VendCommunicator() {
	}

	// Links the appropriate parts to their corresponding variables
	public void linkVending(CoinReceptacleListening receptacle, PopCanRackListening[] pRacks, VendingMachine machine, HashMap<CoinRack, CoinRackListening> cRacks) {
		this.receptacle = receptacle;
		this.pRacks = pRacks;
		this.machine = machine;
		this.cRacks = cRacks;
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
	
	/**
	 * 
	 * @return True if the machine can return exact change, false otherwise
	 */
	public boolean hasChange() {
		return false;
	}
	
	/**
	 * Removes a specified amount of money from the coin racks and delivers them to the coin racks' sink
	 * This method is intended for giving change.
	 * This method does not guarantee that exact change will be given, instead, as close to exact change will be given
	 * 
	 * the correctness of this method for change giving is based on the assumption that
	 * all coins from the receptacle were added to the coin racks before change was given.
	 * @param change The amount of change that needs to be given
	 * @return the amount of change that was not given (returned 0 means all change was given)
	 */
	public int giveChange(int change){
		if(change == 0) {return 0;}
		
		int[] coinKinds = new int[machine.getNumberOfCoinRacks()]; //get the coin kinds used in the machine
		for(int i=0; i<machine.getNumberOfCoinRacks(); i++) {
			coinKinds[i] = machine.getCoinKindForCoinRack(i);
		}
		Arrays.sort(coinKinds); //sort in ascending order
		for(int i=0; i<machine.getNumberOfCoinRacks()/2; i++) { //reverse sorted array
			int temp = coinKinds[i];
			coinKinds[i] = coinKinds[coinKinds.length - 1 - i];
			coinKinds[coinKinds.length - 1 - i] = temp;
		}
		
		
		//gives change
		for(int coin : coinKinds) {
			CoinRackListening rack = cRacks.get(machine.getCoinRackForCoinKind(coin));
			//keeps emptying coins from the rack until either the rack is empty 
			//or the value of the coin that this rack holds is greater than the amount of change that is still to be given 
			while(rack.hasCoins()) { 
				if(change < coin) {
					break;
				}
				
				try {
					machine.getCoinRackForCoinKind(coin).releaseCoin();
					change -= coin;
				} catch (CapacityExceededException e) {
					e.printStackTrace();
				} catch (EmptyException e) {
					System.out.println("This shouldn't have happened.");
					e.printStackTrace();
				} catch (DisabledException e) {//do not dispense coins
					break;
				}
			}
		}
		
		return change;
		
		
	}
	
	
	//internal function for giveChange
	//only public for testing purposes, will become private later
	public HashMap<Integer, Integer> makeChange(int[] coinsIn, int[] coinKinds, int change){
		int[][] bestChange = new int[change+1][coinKinds.length];
		
		for(int i = 1; i <= change; i++) {
			int[] best = new int[coinKinds.length];
			int bestLength = change+1;
			for(int j = 0; j < coinKinds.length; j++) {
				if(coinKinds[j] > i) { //coin denomination is more than change that we have to make
					continue;
				}
				if(coinsIn[j] == 0) { //there are no coins of this type
					continue;
				}
				
				int changeLeft = i-coinKinds[j];
				System.out.println(i + " | " + coinKinds[j] + " | " + changeLeft);//------------------------------------------
				int[] changePossible = new int[best.length];
				
				for(int k=0; k<best.length; k++) { //
					changePossible[k] = bestChange[changeLeft][k];
				}
				
				if(changeLeft == 0) { //returning only this coin will give the amount of change necessary
					best[j] = 1;
					bestLength = 1;
					break;
				}
				if(bestChange[changeLeft][j] == coinsIn[j]) { //we've already used up every type of this coin
					continue;
				}
				changePossible[j] += 1;
				
				if(sumArray(changePossible) <= bestLength) {
					for(int k=0; k<changePossible.length; k++) {
						best[k] = changePossible[k];
					}
					bestLength = sumArray(changePossible);
				}
				
			}
			
			for(int k=0; k<best.length; k++) {
				bestChange[i][k] = best[k];
			}
		}
		
		for(int k=0; k<change+1; k++) {
			System.out.println(Arrays.toString(bestChange[k]) + " " + k);
		}
		
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		map.put(0, 0);
		
		for(int i=change-1; i>=0; i--) {
			if(sumArray(bestChange[i]) > 0) {
				for(int j = 0; j<coinKinds.length; j++) {
					map.put(coinKinds[j], bestChange[i][j]);
				}
				break;
			}
		}
		return map;
	}
	
	private int sumArray(int[] a) {
		int out = 0;
		for(int i:a) {
			out += i;
		}
		return out;
	}
	
}
