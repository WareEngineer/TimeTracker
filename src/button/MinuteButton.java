package button;

import java.awt.Color;

import controller.TimerController;
import observer.TimerObserver;

public class MinuteButton extends CustomButton implements TimerObserver {
	private static final long serialVersionUID = 1L;
	
	public MinuteButton(String text) {
		super(text);
	}
	
	@Override
	public void update(Object subject) {
		TimerController tc = (TimerController) subject;
		if(tc.isRunning()) {
			this.disable();
		} else {
			if(tc.isReseted() || tc.getRemainingTime()<0) {
				this.enable();
			} else {
				this.disable();
			}
		}
	}
	
	@Override
	public void enable() {
		super.enable();
		this.setTextColor(Color.BLACK);
	}

	@Override
	public void disable() {
		super.disable();
		this.setTextColor(Color.LIGHT_GRAY);
	}
}
