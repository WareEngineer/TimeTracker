
import javax.swing.JFrame;

import page.TodayPage;

public class Main {
	public static void main(String[] args) {
        JFrame fr = new JFrame("TimeTracker");
        TodayPage page = new TodayPage();
        fr.setContentPane(page);
        fr.setSize(800,450);
        fr.setVisible(true);
	}
}
