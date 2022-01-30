package schedule.appointment.patient;

import schedule.appointment.date.Date;

public class Patient implements Comparable<Patient> {
	
	private String fname;

	private String lname;

	private Date dob;

	public Patient(String fname, String lname,Date dob) {
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		
	}
	@Override
	public String toString() {
		return "First Name: " + fname + "\nLast Name: " + lname + "\nD.O.B: "
				+ dob.toString();
	}

	@Override
	public int compareTo(Patient patient) {
		if(this.fname.equals(patient.fname) && this.lname.equals(lname) 
				&& (this.dob.compareTo(patient.dob)== 0) ){
			return 0;
		}
		return -1;
	}

}