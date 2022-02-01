package schedule.appointment.timeSlot;

import schedule.appointment.date.Date;
import schedule.appointment.timeSlot.time.Time;

public class Timeslot implements Comparable<Timeslot> {
	
	private Date date;
	private Time time;

	public Timeslot(Date date, Time time) {
		this.date = date;
		this.time = time;
	}
	@Override
	public String toString() {
		return this.date.toString() + ", " + this.time.toString();
	}
	
	@Override
	public int compareTo(Timeslot slot) {
		if( (this.date.compareTo(slot.date) == 0) && (this.time.compareTo(time) == 0)) {
			return 0;
		}
		return -1;
	}
	
}