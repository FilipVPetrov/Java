package Core;

import java.util.Date;

public class Spending {
	private int id;
	private int year;
	private int month;
	private int day;
	private float spend;

	

	public Spending() {

	}

	public Spending(int id, int year, int month, int day, float spend) {
		setId(id);
		setYear(year);
		setMonth(month);
		setDay(day);
		setSpend(spend);
	}
	
	public Spending(int year, int month, int day, float spend) {
		setId(1);
		setYear(year);
		setMonth(month);
		setDay(day);
		setSpend(spend);
	}
	
	public Spending(int id, float spend) {
		setId(id);
		Date date = new Date();
		setYear(date.getYear()+1900);
		setMonth(date.getMonth()+1);
		setDay(date.getDate());
		setSpend(spend);
	}
	
	@SuppressWarnings("deprecation")
	public Spending(float spend) {
		setId(1);
		Date date = new Date();
		setYear(date.getYear()+1900);
		setMonth(date.getMonth()+1);
		setDay(date.getDate());
		setSpend(spend);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public float getSpend() {
		return spend;
	}

	public void setSpend(float spend) {
		this.spend = spend;
	}

	public String toString() {
		return "Spending ("+day+"/"+month+"/"+year+" \n spending:"+spend+")";
	}
}
