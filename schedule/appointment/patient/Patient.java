package schedule.appointment.patient;

import schedule.appointment.date.Date;

/**
 The Patient class creates necessary information for a patient object.
 Gives the patient object their first and last name as well as their date-of-birth.
 Patient objects can compare to one another to see if they are the same patient or not.
 @Author Jah C. Speed, Abe Vitangcol
 */
public class Patient implements Comparable<Patient> {
	
	private String fname;

	private String lname;

	private Date dob;
	/**
	 Constructs a person object by taking in their first and last names
	 as well as their date of birth.
	 @param fname The first name of the patient in string format.
	  		lname The last name of the patient in string format.
	 		dob The date in which the person was born in mm/dd/yyyy format.
	 */
	public Patient(String fname, String lname,Date dob) {
		this.fname = fname;
		this.lname = lname;
		this.dob = dob;
		
	}
	
	/**
	 Takes this patient and returns their information as a string.
	 @return A string in "fname lname, DOB: dob" format
	 */
	@Override
	public String toString() {
		return fname + " " + lname + ", DOB: " + dob.toString();
	}
	
	/**
	 Compares this patient to another patient of interest and sees if
	 they are the same person (same fname, lname, and dob).
	 @param patient The patient of interest to compare to with this one.
	 @return 0 if they are the same person, -1 otherwise
	 */
	@Override
	public int compareTo(Patient patient) {
		if(this.fname.equals(patient.fname) && this.lname.equals(lname) 
				&& (this.dob.compareTo(patient.dob)== 0) ){
			return 0;
		}
		return -1;
	}
	
	/**
	 Takes the patient and gives their first name in a String format.
	 @return The first name of this patient in a String format.
	 */
	public String getFirstName() {
		return this.fname;
	}
	
	/**
	 Takes the patient and gives their last name in a String format.
	 @return The last name of this patient in a String format.
	 */
	public String getLastName() {
		return this.lname;
	}
	
	/**
	 Takes the patient and gives their date of birth in mm/dd/yyyy format.
	 @return The birth date of this patient in mm/dd/yyyy format.
	 */
	public Date getDOB() {
		return this.dob;
	}
	/**
	 Testbed main, used for testing this specific class to ensure all functions and methodologies,
	 mainly compareTo, work. See test case number before each print statement.
	 */
	public static void main(String[] args){
		Patient patient1 = new Patient("Jah","Caffie-Speed",new Date("5/6/2001"));
		Patient patient2 = new Patient("Lia","Shekaran",new Date("5/17/2000"));
		int same_person = patient1.compareTo(patient1); //Test Case 1
		System.out.println("Test Case 1: Same Person \n\tExpected output: 0. Result: "
		                   + same_person + ". " + (same_person == 0? "True":"False"));
		int dif_person = patient2.compareTo(patient1); //Test Case 2
		System.out.println("Test Case 2: Different Person \n\tExpected output: -1. Result: "
		                   + dif_person + (dif_person == 1? ". True": ". False"));	
	}

}