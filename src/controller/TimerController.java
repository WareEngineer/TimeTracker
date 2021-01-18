package controller;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import button.CustomButton;
import observer.TimerObserver;

public class TimerController extends Thread implements MouseListener{
	private Set<TimerObserver> observers;
	private Component component;
	private boolean isRunning;
	private boolean isReseted;
	private long remainingMs;
	private Date start;
	private Date current;
	private Date end;
	
	public TimerController(Component component) {
		this.component = component;
		this.observers = new HashSet<TimerObserver>();
		this.isRunning = false;
		this.isReseted = true;
		this.start();
	}

	public void register(TimerObserver observer) {
		observers.add(observer);
		if(observer instanceof Component) {
			((Component) observer).addMouseListener(this);
		}
	}
	
	public void unregister(TimerObserver observer) {
		this.observers.remove(observer);
	}
	
	public void inform() {
		for(TimerObserver o : observers) { 
			o.update(this); 
		}
		this.component.repaint();
	}
	
	public void setMilliSeconds(long ms) {
		this.remainingMs = ms;
		this.inform();
	}
	
	private void resetTimer() {
		this.isReseted = true;
		this.isRunning = false;
		setMilliSeconds(0);
		this.inform();
	}
	
	public void startTimer() {
		this.start = new Date();
		this.end = start;
		this.isRunning = true;
		this.isReseted = false;
		this.inform();
	}
	
	public void stopTimer() {
		this.end = new Date();
		this.isRunning = false;
		this.remainingMs -= end.getTime()-start.getTime();
		this.inform();
	}
	
	public long getRemainingTime() {
		long elapsedTime = getElapsedTime();
		return remainingMs-elapsedTime;
	}
	
	public long getElapsedTime() {
		long elapsedTime = 0;
		if(isRunning) {
			current = new Date();
			elapsedTime = current.getTime()-start.getTime();
		}
		return elapsedTime;
	}
	
	public Date getStartTime() {
		return start;
	}
	
	public Date getCurrentTime() {
		return current;
	}
	
	public Date getEndTime() {
		return end;
	}

	public boolean isReseted() {
		return isReseted;
	}

	public boolean isRunning() {
		return isRunning;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		while(true) {
			try {
				this.sleep(500);
				this.inform();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() instanceof CustomButton) {
			CustomButton btn = (CustomButton) e.getSource();
			if(btn.isActive()) {
				switch(btn.getText()) {
				case "Start" : startTimer(); 			    break;
				case "Reset" : resetTimer(); 			    break;
				case  "Stop" : stopTimer();  				break;
				case   "60m" : setMilliSeconds(60*60*1000); break;
				case   "50m" : setMilliSeconds(50*60*1000); break;
				case   "40m" : setMilliSeconds(40*60*1000); break;
				case   "30m" : setMilliSeconds(30*60*1000); break;
				case   "20m" : setMilliSeconds(20*60*1000); break;
				case   "15m" : setMilliSeconds(15*60*1000); break;
				case   "10m" : setMilliSeconds(10*60*1000); break;
				case    "5m" : setMilliSeconds( 5*60*1000); break;
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
