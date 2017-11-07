package ca.ucalgary.seng300.a2.test;

import static org.junit.Assert.*;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;
import ca.ucalgary.seng300.a2.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class EmptyMsgLoopTest {

	private VendingMachine machine;
	private emptyMsgLoop msgLoop;
	
	@Before 
	public void setup() {
		int[] coinTypes = { 5, 10, 25, 100, 200 };
		int numButtons = 6;
		int coinCap = 200;
		int popCap = 10;
		int reCap = 50;
		CoinSlotListening slot = new CoinSlotListening();
		CoinRackListening[] racks = new CoinRackListening[coinTypes.length];
		ArrayList<String> popNames = new ArrayList<String>(6);
		String[] names = { "Pop1", "Pop2", "Pop3", "Pop4", "Pop5", "Pop6" };
		for (String name : names) {
			popNames.add(name);
		}
		ArrayList<Integer> prices = new ArrayList<Integer>(6);
		int[] costs = { 250, 250, 250, 250, 250, 250 };
		for (int cost : costs) {
			prices.add(cost);
		}

		machine = new VendingMachine(coinTypes, numButtons, coinCap, popCap, reCap);

		// communicator needs to be created before selection buttons, since
		// selection button takes in a reference to the communicator
		VendCommunicator communicator = new VendCommunicator();
		
		msgLoop = new emptyMsgLoop("Hi there!", communicator);

		SelectionButtonListening[] buttons = new SelectionButtonListening[numButtons];
		CoinReceptacleListening receptacle = new CoinReceptacleListening(reCap, communicator, msgLoop);
		PopCanRackListening[] canRacks = new PopCanRackListening[6];
		DeliveryChuteListening chute = new DeliveryChuteListening();
		DisplayListening display = new DisplayListening();

		machine.configure(popNames, prices);
		machine.disableSafety();
		machine.getCoinSlot().register(slot);
		machine.getCoinReceptacle().register(receptacle);
		machine.getDeliveryChute().register(chute);
		machine.getDisplay().register(display);
		for (int i = 0; i < coinTypes.length; i++) {
			racks[i] = new CoinRackListening(coinTypes[i]);
			machine.getCoinRack(i).register(racks[i]);
		}
		for (int i = 0; i < numButtons; i++) {
			buttons[i] = new SelectionButtonListening(i, communicator);
			machine.getSelectionButton(i).register(buttons[i]);
		}
		for (int i = 0; i < 6; i++) {
			canRacks[i] = new PopCanRackListening();
			machine.getPopCanRack(i).register(canRacks[i]);
			machine.getPopCanRack(i).load(new PopCan(machine.getPopKindName(i)));
		}

		communicator.linkVending(receptacle, canRacks, machine);

	}
	
	@Test
	public void test() throws DisabledException {
		msgLoop.startThread();
		try
		{
			TimeUnit.SECONDS.sleep(14);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
		
		machine.getCoinSlot().addCoin(new Coin(100));
		
		try
		{
			TimeUnit.SECONDS.sleep(14);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
		
		machine.getCoinSlot().addCoin(new Coin(100));
/**
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getCoinSlot().addCoin(new Coin(25));
		machine.getSelectionButton(3).press();
		
		try
		{
			TimeUnit.SECONDS.sleep(15);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
*/		
		msgLoop.reactivateMsg();
		
		try
		{
			TimeUnit.SECONDS.sleep(14);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
		
		msgLoop.interruptThread();
		
		try
		{
			TimeUnit.SECONDS.sleep(20);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
	}
}
