package schedule.appointment.patient;

import schedule.appointment.date.Date;

/**
 The Patient class creates necessary information for a patient object.
 Gives the patient object their first and last name as well as their date-of-birth.
 Patient objects can compare to one another to see if they are the same patient or not.
 @Author Jah C. Speed,
 */
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
		return fname + " " + lname + ", DOB: " + dob.toString();
	}

	@Override
	public int compareTo(Patient patient) {
		if(this.fname.equals(patient.fname) && this.lname.equals(lname) 
				&& (this.dob.compareTo(patient.dob)== 0) ){
			return 0;
		}
		return -1;
	}
	
	public String getFirstName() {
		return this.fname;
	}
	
	public String getLastName() {
		return this.lname;
	}
	
	public Date getDOB() {
		return this.dob;
	}

}