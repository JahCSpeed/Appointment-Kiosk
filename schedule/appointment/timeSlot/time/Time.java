package schedule.appointment.timeSlot.time;


/**
 The time class defines the available and valid times to schedule.
 Creates a time object in a 24-hour time with the constraints and
 time intervals as specified.
 @Author Jah C. Speed, Abe Vitangcol
 */
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
	
	/**
	 Constructs a time object by calling the parse method to parse the time.
	 @param time The time given in hh:mm format. AM/PM not necessary, 24-hour format.
	 */
	public Time(String time) {
		parseTime(time);
	}
	
	/**
	 Checks to see if this time has the valid time for a vaccination appointment.
	 Makes sure the time is within the open hours as well as being on a 15-minute interval.
	 @return False if the time is not within open hours or an invalid time, true otherwise.
	 */
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
	
	/**
	 Takes this object and returns it in a readable string format.
	 @return The time of this object in hh:mm format.
	 */
	@Override
	public String toString() {
		return this.hour + ":" + (this.minute == 0? "00":this.minute);
	}
	
	/**
	 Takes a time object of interest and compares it to this time.
	 @param time The time in question and used to compare to this one.
	 @return -1 If this time is earlier than the param time.
	 		 1 If this time is later than the param time.
	 		 0 If the times are the same.
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
	
	/**
	 Takes the time string (written in hh:mm format), parse it, and create a time object with
	 the proper hours and minutes.
	 @param time The time given in hh:mm format. 24-hour format.
	 */
	public void parseTime(String time) {
		String[] tokens = time.split(":");
		try {
			this.hour = Integer.parseInt(tokens[0]);
			this.minute = Integer.parseInt(tokens[1]);
		}catch(Exception e) {}
		
		
	}
	
	/**
	 Testbed main, used for testing this specific class to ensure all functions and methodologies,
	 mainly compareTo, work. See test case number before each print statement.
	 */
	public static void main(String[] args){
		Time time1 = new Time("9:00");
		int same_time = time1.compareTo(time1); //Test Case 1
		System.out.println("Test Case 1: Same Time \n\tExpected output: 0. Result: "
		                   + same_time + ". " + (same_time == 0? "True":"False"));
		int one_hr_later = new Time("10:00").compareTo(time1); //Test Case 2
		System.out.println("Test Case 2: Future Time by 1 Hour \n\tExpected output: 1. Result: "
		                   + one_hr_later + (one_hr_later == 1? ". True": ". False"));
		int one_min_later = new Time("9:01").compareTo(time1); //Test Case 3
		System.out.println("Test Case 3: Future Date by 1 Minute \n\tExpected output: 1. Result: "
		                   + one_min_later + (one_min_later == 1? ". True": ". False"));
		int past_time = new Time("8:59").compareTo(time1);
		System.out.println("Test Case 4: Past Time \n\tExpected output: -1. Result: "
		                   + past_time + (past_time == -1? ". True": ". False"));
	}
}