package ca.ucalgary.seng300.a2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DisplayListeningTest {
	
	private DisplayListening displayListeningTest;
	private Display displayTest;
	//private LogFile log;

	@Before
	public void setUp() throws Exception {
		displayListeningTest = new DisplayListening();
		displayTest = new Display();
		LogFile.createLogFile();
	}

	@Test
	public void testIsOn() {
		displayListeningTest.enabled(displayTest);
		assertEquals(true, displayListeningTest.isOn());
	}
	
	@Test
	public void testNotOn() {
		displayListeningTest.disabled(displayTest);
		assertEquals(false, displayListeningTest.isOn());
	}

	@Test
	public void testMessageChange() {
		String prevMessage = "";
		displayListeningTest.setPrevMessage(prevMessage);
		
		String currMessage = "";
		displayListeningTest.setCurrMessage(currMessage);
		
		for(int i = 0; i<5; i++) {
			String message = "Test";
			displayListeningTest.messageChange(displayTest, prevMessage, message);
			assertEquals(message, displayListeningTest.getCurrMessage());
			assertEquals(prevMessage, displayListeningTest.getPrevMessage());
		}
	}
	
}
