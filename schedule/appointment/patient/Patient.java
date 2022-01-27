package schedule.appointment.patient;

import schedule.appointment.date.Date;

public class Patient implements Comparable<Patient> {
	
	private String fname;

	private String lname;

	private Date dob;

	@Override
	public String toString() {
		return "";
	}

	@Override
	public int compareTo(Patient patient) {
		return -1;
	}

}