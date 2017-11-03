package org.lsmr.vending.hardware;

public class DisplayListening implements DisplayListener {
	private boolean isOn;
	
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
		
		
	}
	
	public boolean isOn() {
		return isOn;
		
	}

}
