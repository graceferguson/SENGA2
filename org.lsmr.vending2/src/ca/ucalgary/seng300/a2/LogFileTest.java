package ca.ucalgary.seng300.a2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import ca.ucalgary.seng300.a2.LogFile;

class LogFileTest {

	
	private LogFile logfile; 
	private boolean filecreated; 
	
	@Test
	void CreateFileTest() throws IOException {
		filecreated = LogFile.createLogFile();
		assertEquals(true,filecreated); 
		
	}
	
	@Test 
	void CreateFileTest2() throws IOException {
		filecreated = LogFile.createLogFile();
//	    assertTrue(.exists());
	}
	
	
	@Test
	void CloseFileTest() throws IOException {
		logfile.closeLogFile();
	}
	
	@Test 
	void WriteFileTest() throws IOException {
		logfile.writeLog ("new line"); 
		
	}
	

	// testing only 
    /*public static void main(String args[]) throws IOException {
		filecreated = createLogFile(); 
		writeLog("this is new"); 
		writeLog("new new");
				
		closeLogFile(); 
		
	} */
	
	
}
