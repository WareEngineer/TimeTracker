package button;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public abstract class CustomButton extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean isActive;
	private String text;
	private Color textColor;
	private Color backgroundColor;
	
	public CustomButton(String text) {
		this.isActive = false;
		this.text = text;
		this.disable();
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = (int) (this.getWidth()*0.95);
		int height = (int) (this.getHeight()*0.9);
		g2d.setColor(backgroundColor);
		g2d.fillRoundRect(0, 0, width, height, 20, 20);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(0, 0, width, height, 20, 20);
		
		int centerX = width/2;
		int centerY = height/2;
		int[] xy = optimizeStringXY(g2d.getFontMetrics(), centerX, centerY, text);
		g2d.setColor(textColor);
		g2d.drawString(text, xy[0], xy[1]);
	}
	
	public void setTextColor(Color color) {
		this.textColor = color;
	}
	
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}
	
	private int[] optimizeStringXY(FontMetrics fm, int x, int y, String str) {
		int[] xy = new int[2];
		xy[0] = x-(fm.stringWidth(str)/2);
		xy[1] = y+(fm.getHeight()/3);
		return xy;
	}
	
	public boolean isActive() {
		return this.isActive;
	}

	public String getText() {
		return text;
	}
	
	public void enable() {
		this.isActive = true;
		this.textColor = Color.BLACK;
		this.backgroundColor = Color.WHITE;
	}
	
	public void disable() {
		this.isActive = false;
		this.textColor = Color.LIGHT_GRAY;
		this.backgroundColor = Color.DARK_GRAY;
	}
}
