package button;

import java.awt.Color;

import controller.TimerController;
import observer.TimerObserver;

public class StopButton extends CustomButton implements TimerObserver {
	private static final long serialVersionUID = 1L;
	
	public StopButton(String text) {
		super(text);
	}

	@Override
	public void update(Object subject) {
		TimerController tc = (TimerController) subject;
		if(tc.isRunning()) {
			this.enable();
		} else {
			this.disable();
		}
	}
	
	@Override
	public void enable() {
		super.enable();
		this.setTextColor(Color.RED);
	}
}
