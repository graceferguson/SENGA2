package ca.ucalgary.seng300.a2;

import org.lsmr.vending.*;
import org.lsmr.vending.hardware.AbstractHardware;
import org.lsmr.vending.hardware.AbstractHardwareListener;
import org.lsmr.vending.hardware.IndicatorLight;
import org.lsmr.vending.hardware.IndicatorLightListener;

public class IndicatorLighListening  implements IndicatorLightListener {
	
	
	private Boolean isActive;
	
	
	public IndicatorLighListening() {
		 this.isActive = true;
	}

	@Override
	public void enabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
//		isActive = !hardware.isDisabled();
	}

	@Override
	public void disabled(AbstractHardware<? extends AbstractHardwareListener> hardware) {
//		isActive = !hardware.isDisabled();
	}

	@Override
	public void activated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
		
	}

	@Override
	public void deactivated(IndicatorLight light) {
		// TODO Auto-generated method stub
		isActive = light.isActive();
		
	}

}
