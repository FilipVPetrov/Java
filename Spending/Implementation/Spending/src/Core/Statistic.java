package Core;

public class Statistic {
	private int year;
	private int month;
	private float total;
	private float average;
	
	public Statistic(){
		
	}
	
	public Statistic(int year, int month, float total, float average){
		setYear(year);
		setMonth(month);
		setTotal(total);
		setAverage(average);
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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	@Override
	public String toString() {
		return "Statistic (year=" + year + ", month=" + month + ", total="
				+ total + ", average=" + average + ")";
	}
	
	
}
