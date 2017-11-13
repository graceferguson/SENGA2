package ca.ucalgary.seng300.a2;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lsmr.vending.hardware.IndicatorLight;

public class OutOfOrderLightListeningTest {

	
	OutOfOrderLightListening ofOrderLightListening = new OutOfOrderLightListening();
//	String MessageTest = "test message";
	IndicatorLight light = new IndicatorLight();

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		OutOfOrderLightListening ofOrderLightListening = new OutOfOrderLightListening();
		IndicatorLight light = new IndicatorLight();
		light.register(ofOrderLightListening);
		LogFile.createLogFile();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWriteLog() throws Exception {
//		Boolean  t = LogFile.createLogFile();
//		System.out.println(t);
		LogFile.createLogFile();
		ofOrderLightListening.messageChange("old message", "newMessage");
//		LogFile.closeLogFile();
		
	}
	
	@Test
	public void testActivated() {
		light.activate();
		ofOrderLightListening.activated(light);
		System.out.println(ofOrderLightListening.getisActive());
		
		
	}
	
	@Test
	public void testDeactive() {
		light.deactivate();
		ofOrderLightListening.deactivated(light);
		System.out.println(ofOrderLightListening.getisActive());

		
	}
	
	@Test
	public void testGet() {
//		testDeactive();
		Boolean current = ofOrderLightListening.getisActive();
		assertEquals(false, current);
		light.activate();
		ofOrderLightListening.activated(light);
		Boolean current1 = ofOrderLightListening.getisActive();
		assertEquals(true, current1);
		
	}
	
	
	
	
	
	
	
}
