package ca.ucalgary.seng300.a2.tests;

import static org.junit.Assert.*;
import org.lsmr.vending.*;
import org.lsmr.vending.hardware.*;
import ca.ucalgary.seng300.a2.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class EmptyMsgLoopTest {

	//private VendingMachine machine;
	private emptyMsgLoop msgLoop;
	private displayListening myDisplay;
	
	@Before 
	public void setup() {
		VendCommunicator communicator = new VendCommunicator();
		msgLoop = new emptyMsgLoop("Hi there!", communicator);
		myDisplay = new displayListening();
		CoinReceptacleListening receptacle = new CoinReceptacleListening(200, communicator, msgLoop);
		VendingMachine machine = new VendingMachine(new int[] {1,5,10,25,100,200}, 6, 200,10,200, 200, 200);
		machine.getDisplay().register(myDisplay);
		communicator.linkVending(receptacle, null, null, null, machine, null);
		}
	
	class displayListening implements DisplayListener
	{
	private int numMessages = 0;

		@Override
		public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		}

		@Override
		public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		}

		@Override
		public void messageChange(Display display, String oldMessage, String newMessage) {
			numMessages++;		
		}
		
		public int getNum()
		{
			return numMessages;
		}
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
		assertEquals(myDisplay.getNum(), 2);
		
		msgLoop.interruptThread();
		
		try
		{
			TimeUnit.SECONDS.sleep(14);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
		
		assertEquals(myDisplay.getNum(), 2);
		
		msgLoop.reactivateMsg();
		
		try
		{
			TimeUnit.SECONDS.sleep(14);
		}
		catch(InterruptedException e){
			System.out.println("should not print");
		}
		
		assertEquals(myDisplay.getNum(), 4);
	}
}
