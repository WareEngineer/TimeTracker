package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import controller.TimerController;
import observer.TimerObserver;

public class DigitalTimer extends JPanel implements TimerObserver {
	private static final long serialVersionUID = 1L;
	private boolean isPositive = true;
	private long seconds;
	
	@Override
	public void update(Object subject) {
		TimerController tc = (TimerController) subject;
		seconds = tc.getRemainingTime()/1000;
		if(seconds>0) 	   isPositive = true;
		else if(seconds<0) isPositive = false;
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		String min = this.getDobleDigitString(Math.abs(seconds)/60);
		String sec = this.getDobleDigitString(Math.abs(seconds)%60);
		String str = min + " : " + sec;
		if(isPositive) {
			g2d.setColor(new Color(0x4682B4));
		} else {
			g2d.setColor(new Color(0xFF7F50));
			str = "+ " + str;
		}
		
		int[] xy = optimizeStringXY(g2d, str);
		g2d.drawString(str, xy[0], xy[1]);
	}

	private int[] optimizeStringXY(Graphics2D g2d, String str) {
		Font font = new Font("/res/CALIBRI.TTF", Font.BOLD, 20);
		g2d.setFont(font);
		
		int strWidth = g2d.getFontMetrics().stringWidth(str);
		
		int[] xy = new int[2];
		xy[0] = (this.getWidth()-strWidth)/2;
		xy[1] = (int) (this.getHeight()*0.8);
		
		return xy;
	}

	private String getDobleDigitString(long n) {
		return n<10 ? "0"+n : ""+n;
	}
}
