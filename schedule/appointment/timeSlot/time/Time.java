package schedule.appointment.timeSlot.time;

public class Time implements Comparable<Time> {
	private int hour;
	private int minute;
	
	public boolean isValid() {
		return true;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public int compareTo(Time time) {
		return -1;
	} 
}