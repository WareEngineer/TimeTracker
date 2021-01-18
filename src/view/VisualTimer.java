package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import controller.TimerController;
import observer.TimerObserver;

public class VisualTimer extends JPanel implements TimerObserver{
	private static final long serialVersionUID = 1L;
	private double start;
	private double ms;
	private boolean isPositive;
	private boolean flag;
	
	public VisualTimer() {
		isPositive = true;
		flag = false;
	}

	@Override
	public void update(Object subject) {
		TimerController tc = (TimerController) subject;
		ms = tc.getRemainingTime();
		if(tc.isRunning()) {
			if(flag==false) {
				flag = true;
				start = tc.getRemainingTime();
			}
		} else {
			flag = false;
			if(ms==0) {
				start=0;
			}
		}
		
		if(ms>=0) {
			isPositive = true;
		} else if (ms<0) {
			isPositive = false;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		int width = this.getWidth();
		int height = this.getHeight();
		int centerX = width/2;
		int centerY = height/2;
		int d = (int) (width<height ? width*0.8 : height*0.8);
		int r = d/2;
		
		for(int i=0; i<60; i++) {
			double degree = (Math.PI/30) * i;
			int line[];
			if(i%5==0) {
				g2d.setStroke(new BasicStroke(2));
				line = createIndex(centerX, centerY, r, degree, 5);
				String index = Integer.toString(i==0?0:60-i);
				int xy[] = getXY(centerX, centerY, r+20, degree);
				xy = optimizeStringXY(g2d.getFontMetrics(), xy[0], xy[1], index);
				g2d.drawString(index, xy[0], xy[1]);
			} else {
				g2d.setStroke(new BasicStroke());
				line = createIndex(centerX, centerY, r, degree, 2);
			}
			g2d.drawLine(line[0], line[1], line[2], line[3]);
		}
		
		// draw remaining-time
		if(isPositive) {
			g2d.setColor(new Color(0xADD8E6));
		} else {
			g2d.setColor(new Color(0xFFDAB9));
		}
		g2d.fill(new Arc2D.Double(centerX-(d/2), centerY-(d/2), d, d, 90.0, ms/10000, Arc2D.PIE));
		
		// draw start-line
		double degree = -(Math.PI/30)*(start/60000);
		int xy[] = getXY(centerX, centerY, r, degree);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawLine(centerX, centerY, xy[0], xy[1]);
		g2d.setStroke(new BasicStroke());
	}
	
	private int[] optimizeStringXY(FontMetrics fm, int x, int y, String str) {
		int[] xy = new int[2];
		xy[0] = x-(fm.stringWidth(str)/2);
		xy[1] = y+(fm.getHeight()/3);
		return xy;
	}
	
	private int[] getXY(int x, int y, int r, double degree) {
		int xy[] = new int[] {x, y-r};
		int rotatedXY[] = new int[2];
		rotatedXY[0] = (int) (x + (xy[0]-x)*Math.cos(degree) - (xy[1]-y)*Math.sin(degree));
		rotatedXY[1] = (int) (y + (xy[0]-x)*Math.sin(degree) + (xy[1]-y)*Math.cos(degree));
		return rotatedXY;
	}
	
	private int[] createIndex(int x, int y, int r, double degree, int len) {
		int line[] = new int[] {x, y-r+len, x, y-r-len};
		int rotatedLine[] = new int[4];
		rotatedLine[0] = (int) (x + (line[0]-x)*Math.cos(degree) - (line[1]-y)*Math.sin(degree));
		rotatedLine[1] = (int) (y + (line[0]-x)*Math.sin(degree) + (line[1]-y)*Math.cos(degree));
		rotatedLine[2] = (int) (x + (line[2]-x)*Math.cos(degree) - (line[3]-y)*Math.sin(degree));
		rotatedLine[3] = (int) (y + (line[2]-x)*Math.sin(degree) + (line[3]-y)*Math.cos(degree));
		return rotatedLine;
	}
}
