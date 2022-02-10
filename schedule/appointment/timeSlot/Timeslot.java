package schedule.appointment.timeSlot;

import schedule.appointment.date.Date;
import schedule.appointment.timeSlot.time.Time;
/**
 The Timeslot class creates valid timeslots for vaccination appointments.
 Uses both the date and time class to create the timeslot and enables
 these timeslots to be compared to one another in both date and time.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Timeslot implements Comparable<Timeslot> {
	
	private Date date;
	private Time time;
	
	/**
	 Constructs the timeslot object by taking in a date and time and creating this.
	 @param date The mm/dd/yyyy date object for this given time slot.
	        time The hh:mm time object in 24-hour formate for this given time slot.
	 */
	public Timeslot(Date date, Time time) {
		this.date = date;
		this.time = time;
	}
	/**
	 Takes the timeslot object and prints it out into a readable format.
	 @return The timeslot in "Date, Time" format.
	 */
	@Override
	public String toString() {
		return this.date.toString() + ", " + this.time.toString() + ",";
	}
	
	/**
	 Takes this timeslot and another timeslot in question and compares the two of them
	 in terms of the dates written on them as well as the time stamp on them.
	 @param slot The timeslot used to compare to this one.
	 @return -1 If this timeslot is earlier than the param slot.
	         1 If this timeslot is later than the param slot.
	         0 If the two timeslots are equal.
	 */
	@Override
	public int compareTo(Timeslot slot) {
		if( this.date.compareTo(slot.date) == 0){
			return this.time.compareTo(slot.getTime());
		}else {
			return this.date.compareTo(slot.getDate());
		}
	}
	
	/**
	 Gets the time stamp of this specific timeslot object. 
	 @return The time of the timeslot.
	 */
	public Time getTime() {
		return this.time;
	}
	
	/**
	 Gets the date of this specific timeslot object.
	 @return The date of the timeslot.
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 Testbed main, used for testing this specific class to ensure all functions and methodologies,
	 mainly compareTo, work. See test case number before each print statement.
	 */
	public static void main(String[] args){
		Timeslot timeslot1 = new Timeslot(new Date("10/09/2000"), new Time("10:00"));
		Timeslot timeslot2 = new Timeslot(new Date("10/09/2000"), new Time("10:15"));
		Timeslot timeslot3 = new Timeslot(new Date("10/08/2000"), new Time("10:00"));
		Timeslot timeslot4 = new Timeslot(new Date("10/10/2000"), new Time("10:00"));
		Timeslot timeslot5 = new Timeslot(new Date("10/09/2000"), new Time("09:45"));
		int same_slot = timeslot1.compareTo(timeslot1);
		int later_date = timeslot1.compareTo(timeslot4);
		int early_date = timeslot1.compareTo(timeslot3);
		int early_time = timeslot1.compareTo(timeslot5);
		int later_time = timeslot1.compareTo(timeslot2);
		System.out.println("Test Case 1: Same Slot\n\tExpected Output: 0. Result: "
							+ same_slot + (same_slot == 0? ". True":". False"));
		System.out.println("Test Case 2: Later Date\n\tExpected Output: -1. Result: "
				+ later_date + (later_date == -1? ". True":". False"));
		System.out.println("Test Case 3: Early Date\n\tExpected Output: 1. Result: "
				+ early_date + (early_date == 1? ". True":". False"));
		System.out.println("Test Case 4: Later Time\n\tExpected Output: -1. Result: "
				+ later_time + (later_time == -1? ". True":". False"));
		System.out.println("Test Case 5: Early Time\n\tExpected Output: 1. Result: "
				+ early_time + (early_time == 1? ". True":". False"));
	}
}