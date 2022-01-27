package schedule.appointment.timeSlot;

import schedule.appointment.date.Date;
import schedule.appointment.timeSlot.time.Time;

public class Timeslot implements Comparable<Timeslot> {
	
	private Date date;
	private Time time;

	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public int compareTo(Timeslot slot) {
		return -1;
	}
	
}