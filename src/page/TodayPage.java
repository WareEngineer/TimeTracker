package page;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.util.Calendar;

import javax.swing.JLabel;

import button.MinuteButton;
import button.ResetButton;
import button.StartButton;
import button.StopButton;
import controller.TimerController;
import observer.TimerObserver;
import view.DigitalTimer;
import view.RecordTable;
import view.TotalTime;
import view.VisualTimer;

public class TodayPage extends Page {
	private static final long serialVersionUID = 1L;
	
/*      [ 0][ 1][ 2][ 3][ 4][ 5][ 6][ 7][ 8][ 9][10][11][12][13][14][15][16][17][18][19]
 * [ 0]   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X
 * [ 1]   X   a   a   a   a   a   X   f   f   f   f   f   f   f   f   f   f   j   j   X
 * [ 2]   X   a   a   a   a   a   X   f   f   f   f   f   f   f   f   f   f   X   X   X
 * [ 3]   X   b   b   b   b   b   X   f   f   f   f   f   f   f   f   f   f   k   k   X
 * [ 4]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   l   l   X
 * [ 5]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   m   m   X
 * [ 6]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   n   n   X
 * [ 7]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   o   o   X
 * [ 8]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   p   p   X
 * [ 9]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   q   q   X
 * [10]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   r   r   X
 * [11]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   X   X   X
 * [12]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   X   X   X
 * [13]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   X   X   X
 * [14]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   X   X   X
 * [15]   X   c   c   c   c   c   X   f   f   f   f   f   f   f   f   f   f   X   X   X
 * [16]   X   c   c   c   c   c   X   g   g   g   g   g   g   g   g   g   g   h   h   X
 * [17]   X   c   c   c   c   c   X   g   g   g   g   g   g   g   g   g   g   h   h   X
 * [18]   X   e   e   e   e   e   X   g   g   g   g   g   g   g   g   g   g   i   i   X
 * [19]   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X   X
*/

	public TodayPage() {
        Component date 		= new JLabel("Date : "+getYYMMDD());
        Component record 	= new JLabel("Racords");
        Component total 	= new TotalTime();
        Component table 	= new RecordTable();
        Component clock 	= new VisualTimer();
        Component timer 	= new DigitalTimer();
        Component start 	= new StartButton("Start");
        Component stop 		= new StopButton("Stop");
        Component reset 	= new ResetButton("Reset");
        Component m60 		= new MinuteButton("60m");
        Component m50 		= new MinuteButton("50m");
        Component m40 		= new MinuteButton("40m");
        Component m30 		= new MinuteButton("30m");
        Component m20 		= new MinuteButton("20m");
        Component m15 		= new MinuteButton("15m");
        Component m10 		= new MinuteButton("10m");
        Component m5 		= new MinuteButton( "5m");
        
        this.add(date, 		createConstrains(1, 1, 5, 2));  	//a
      	this.add(record, 	createConstrains(1, 3, 5, 1));  	//b
        this.add(table, 	createConstrains(1, 4, 5, 14)); 	//c
      	this.add(total, 	createConstrains(1, 18, 5, 1));  	//e
        this.add(clock, 	createConstrains(7, 1, 9, 14)); 	//f
        this.add(timer, 	createConstrains(7, 15, 9, 3)); 	//g
        this.add(start, 	createConstrains(17, 16, 2, 1));	//h
        this.add(stop, 		createConstrains(17, 17, 2, 1));	//i
        this.add(reset, 	createConstrains(17, 2, 2, 1)); 	//j
        this.add(m60, 		createConstrains(17, 4, 2, 1)); 	//k
        this.add(m50, 		createConstrains(17, 5, 2, 1)); 	//l
        this.add(m40, 		createConstrains(17, 6, 2, 1)); 	//m
        this.add(m30, 		createConstrains(17, 7, 2, 1)); 	//n
        this.add(m20, 		createConstrains(17, 8, 2, 1)); 	//o
        this.add(m15, 		createConstrains(17, 9, 2, 1)); 	//p
        this.add(m10, 		createConstrains(17, 10, 2, 1));	//q
        this.add(m5, 		createConstrains(17, 11, 2, 1));	//r
        
        TimerController controller = new TimerController(this);
        controller.register((TimerObserver) total);
        controller.register((TimerObserver) table);
        controller.register((TimerObserver) clock);
        controller.register((TimerObserver) timer);
        controller.register((TimerObserver) start);
        controller.register((TimerObserver) stop);
        controller.register((TimerObserver) reset);
        controller.register((TimerObserver) m60);
        controller.register((TimerObserver) m50);
        controller.register((TimerObserver) m40);
        controller.register((TimerObserver) m30);
        controller.register((TimerObserver) m20);
        controller.register((TimerObserver) m15);
        controller.register((TimerObserver) m10);
        controller.register((TimerObserver) m5);
	}
	
	protected GridBagConstraints createConstrains(int gridx, int gridy, int gridwidth, int gridheight) {
		GridBagConstraints c = new GridBagConstraints();
        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = gridwidth;
        c.gridheight = gridheight;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 0;
        c.ipady = 0;
        return c;
	}
	
	private String getYYMMDD() {
		int y = Calendar.getInstance().getTime().getYear()+1900;
		int m = Calendar.getInstance().getTime().getMonth()+1;
		int d = Calendar.getInstance().getTime().getDate();
		return y+"."+m+"."+d;
	}
}
