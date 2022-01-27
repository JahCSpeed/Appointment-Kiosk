package schedule.appointment.date;

public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;
	
	public Date(String date) {//take “mm/dd/yyyy” and create a Date object
		
	} 
	
	public Date() {//create an object with today’s date (see Calendar class)
		
	}

	public boolean isValid() {
		return true;
	}

	@Override
	public int compareTo(Date date) {
		return -1;
	}
	
}