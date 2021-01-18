package view;

import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controller.TimerController;
import model.CsvFileManager;
import observer.TimerObserver;

public class RecordTable extends JScrollPane implements TimerObserver {
	private static final long serialVersionUID = 1L;
	private final int minimumMunutesForRecord = 1;
	private CsvFileManager fileManager;
	private DefaultTableModel model;
	private String[] content;
	private boolean flag;
	private int count;

	public RecordTable() {
		String header[] = {"No", "Start", "Stop", "Minutes"};
		model = new DefaultTableModel(header, 0) {
			private static final long serialVersionUID = 1L;
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		fileManager = new CsvFileManager();
		String fileName = getFileName(new Date());
		List<String[]> contents = fileManager.read(fileName);
		if(contents != null) {
			for(String[] content : contents) {
				model.addRow(content);
			}
		}
		JTable table = new JTable(model);
		setTableCellAlignment(table, JLabel.CENTER);
        this.getViewport().add(table);
        this.count = 0;
        this.flag = false;
	}

	private void setTableCellAlignment(JTable table, int alignment) {
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(alignment);
		
		TableColumnModel model = table.getColumnModel();
		for(int i=0; i<model.getColumnCount(); i++) {
			model.getColumn(i).setCellRenderer(cellRenderer);
		}
	}

	@Override
	public void update(Object subject) {
		TimerController tc = (TimerController) subject;
		if(tc.isRunning()) {
			flag = true;
		} else {
			if(flag==true) {
				Date start = tc.getStartTime();
				Date end = tc.getEndTime();
				this.record(start, end);
				flag=false;
			}
		}
	}

	private void record(Date start, Date end) {
		long elapsedTime = end.getTime()-start.getTime();
		int minutes = (int) (elapsedTime/60000);
		if(minutes >= minimumMunutesForRecord) {
			count++;
			content = new String[] {count+"", castDateToString(start), castDateToString(end), minutes+""};
			model.addRow(content);
			String fileName = getFileName(start);
			fileManager.write(fileName, content);
			start = null;
			end = null;
		}
	}
	
	private String castDateToString(Date date) {
		int hour = date.getHours();
		int minute = date.getMinutes();
		String str = getDoubleDigitString(hour) + ":" + getDoubleDigitString(minute);
		return str;
	}
	
	private String getDoubleDigitString(int n) {
		return n<10 ? "0"+n : ""+n;
	}
	
	private String getFileName(Date date) {
		int y = date.getYear()+1900;
		int m = date.getMonth()+1;
		int d = date.getDate();
		return y+"."+m+"."+d+".csv";
	}
}
