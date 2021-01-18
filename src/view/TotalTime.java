package view;

import java.awt.FlowLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TimerController;
import model.CsvFileManager;
import observer.TimerObserver;

public class TotalTime extends JPanel implements TimerObserver {
	private static final long serialVersionUID = 1L;
	private static int total;
	private JLabel content;
	private boolean flag;
	
	public TotalTime() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel head = new JLabel("Total: ");
		this.add(head);

		total = 0;
		CsvFileManager fileManager = new CsvFileManager();
		String fileName = getFileName(new Date());
		List<String[]> contents = fileManager.read(fileName);
		if(contents != null) {
			for(String[] content : contents) {
				total += Integer.parseInt(content[3]);
			}
		}
		content = new JLabel(Integer.toString(total));
		this.add(content);
	}

	private String getFileName(Date date) {
		int y = date.getYear()+1900;
		int m = date.getMonth()+1;
		int d = date.getDate();
		return y+"."+m+"."+d+".csv";
	}

	@Override
	public void update(Object subject) {
		TimerController tc = (TimerController) subject;
		if(tc.isRunning()) {
			flag = true;
		} else {
			if(flag==true) {
				long elapsedMs = tc.getEndTime().getTime() - tc.getStartTime().getTime();
				total += elapsedMs/60000;
				content.setText(Integer.toString(total));
				flag=false;
			}
		}
	}
}
