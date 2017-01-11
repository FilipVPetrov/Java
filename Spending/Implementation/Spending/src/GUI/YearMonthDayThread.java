package GUI;

import java.util.Date;

import javax.swing.JLabel;

public class YearMonthDayThread extends Thread {
	private JLabel year;
	private JLabel month;
	private JLabel day;
	
	public YearMonthDayThread(JLabel year, JLabel month,JLabel day){
		this.year=year;
		this.month=month;
		this.day=day;
		this.start();
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		Date date= new Date();
		while(true){
			int year=date.getYear()+1900;
			this.year.setText(""+year);
			int month=date.getMonth()+1;
			this.month.setText(""+month);
			int day=date.getDate();
			this.day.setText(""+day);
		}
	}
}
