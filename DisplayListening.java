package package ca.ucalgary.seng300.a2;

import java.io.IOException;

public class DisplayListening implements DisplayListener {
	private boolean isOn;
	private String prevMessage = "";
	private String currMessage = "";
	
	public DisplayListening() {
		isOn = true;
	
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isOn = true;
		
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
		isOn = false;
		
	}

	@Override
	public void messageChange(Display display, String oldMessage, String newMessage) {
		prevMessage = oldMessage;
		currMessage = newMessage;
		try {
			LogFile.writeLog(this.getClass().getName() + "\t" + currMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public boolean isOn() {
		return isOn;
		
	}

}
