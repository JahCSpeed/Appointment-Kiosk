package schedule.appointment.date;

import java.util.Calendar;

public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;

	public static final int QUADRENNIAL = 4;
	public static final int CENTENNIAL = 100;
	public static final int QUATERCENTENNIAL = 400;
	
	public Date(String date) {//take mm/dd/yyyy and create a Date object
		parseDate(date);
	} 
	
	public Date() {//create an object with todays date (see Calendar class)
		Calendar cal = Calendar.getInstance();
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH);
		this.day = cal.get(Calendar.DATE);
	}

	public boolean isValid() {
		
		if(this.month == -1 || this.day == -1 || this.year == -1) {
			return false;
		}
		
		if(!checkRange(this.month,0,11) || !checkRange(this.year,1,9999)) {
			return false;
		}
		
		try {
			if(isLeapYear(this.year)) {
				if(!checkRange(this.day,1,getMonth(this.month).leapYearDays)) {
					return false;
				}
			}else {
				if(!checkRange(this.day,1,getMonth(this.month).days)) {
					return false;
				}
			}
		}catch(NullPointerException e) {
			
			return false;
		}
		
		return true;
	}

	/*
	 * Compare the two dates, 0 means they are equal, -1 means they are not equal, 1 means future date
	 */
	@Override
	public int compareTo(Date date) {
		if((this.month == date.month) && (this.day == date.day) && (this.year == date.year)) {
			return 0;
		}
		if(this.year > date.year) {
			return 1;	
		}else {
			if(this.month > date.month && this.year == date.year) {
				return 1;
			}else {
				if((this.day > date.day) && (this.month == date.month) && (this.year == date.year)) {
					return 1;
				}
			}
		}
		return -1;
	}
	
	/*
	 * Returns if a year is a leap year or not
	 */
	private boolean isLeapYear(int year) {
		if(year % QUADRENNIAL != 0) {
			return false;
		}else if(year % CENTENNIAL != 0) {
			return true;
		}else if(year % CENTENNIAL == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	/*
	 * Checks if an int is in between a specific range of numbers
	 */
	private boolean checkRange(int input, int lowerBound, int upperBound) {
		if(input > upperBound || input < lowerBound) {
			return false;
		}
		return true;
	}
	
	/*
	 * Parse the date by month/day/year
	 */
	public void parseDate(String date) {
		String[] tokens = date.split("/");
		try {
			this.month = Integer.parseInt(tokens[0]) -1;
			this.day = Integer.parseInt(tokens[1]);
			this.year = Integer.parseInt(tokens[2]);
		}catch(Exception e) {}
		
		
	}
	
	/*
	 * Get the correct month from Months class for days in said month
	 */
	public Months getMonth(int id) {
		for(Months month : Months.values()) {
			if(month.id == id) {
				return month;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return (this.month + 1) + "/" + this.day + "/" + this.year + "\n";
	}
	
	
}