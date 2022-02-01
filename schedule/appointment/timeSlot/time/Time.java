package schedule.appointment.timeSlot.time;

public class Time implements Comparable<Time> {
	private int hour;
	private int minute;
	
	private final int OPENINGHOUR = 9;
	private final int OPENINGMIN = 00;
	private final int CLOSINGHOUR = 16;
	private final int CLOSINGMIN = 45;
	private final int INTERVAL = 15;
	private final int MAXHOUR = 23;
	private final int MAXMINUTE = 59;
	public Time(String time) {
		parseTime(time);
	}
	public boolean isValid() {
		if(this.hour < 0 || this.hour > MAXHOUR) {
			return false;
		}
		
		if(this.minute < 0 || this.minute > MAXMINUTE) {
			return false;
		}
		if((this.minute % INTERVAL) != 0) {
			return false;
		}
		boolean openingValue = this.hour >= OPENINGHOUR && this.minute >= OPENINGMIN;
		boolean closingValue = this.hour <= CLOSINGHOUR && this.minute <= CLOSINGMIN;
		if(openingValue && closingValue) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		return this.hour + ":" + (this.minute == 0? "00":this.minute);
	}
	
	/*
	 * Will return 1 if this.obj > time, 0 if equal, -1 if less than
	 */
	@Override
	public int compareTo(Time time) {
		if(this.hour > time.hour) {
			return 1;
		}else if(this.hour < time.hour) {
			return -1;
		}else {
			if(this.minute > time.minute) {
				return 1;
			}else if(this.minute < time.minute) {
				return -1;
			}else{
				return 0;
			}
		}
	}
	/*
	 * Will parse the string time in between hours and min
	 */
	public void parseTime(String time) {
		String[] tokens = time.split(":");
		try {
			this.hour = Integer.parseInt(tokens[0]);
			this.minute = Integer.parseInt(tokens[1]);
		}catch(Exception e) {}
		
		
	}
}