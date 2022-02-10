package schedule.appointment;

import schedule.appointment.location.Location;
import schedule.appointment.patient.Patient;
import schedule.appointment.timeSlot.Timeslot;

/**
 The appointment class creates all the necessary information regarding an appointment.
 It holds information regarding the patient, the timeslot of the appointment made, as
 well as the location the appointment will be held.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Appointment {
	private Patient patient;
	private Timeslot slot;
	private Location location;
	
	/**
	 Constructs the appointment object by taking in patient information, the timeslot they
	 want the appointment, and the location of the vaccination.
	 @param patient The information about the patient with params specified by the patient class.
	        slot The time slot for the appointment with params specified by the timeslot class.
	        location The location of the appointment with params specified by the location class.
	 */
	public Appointment(Patient patient, Timeslot slot, Location location) {
		this.patient = patient;
		this.slot = slot;
		this.location = location;
	}
	
	/**
	 Checks one appointment and compares it to this one to see if the appointments are exactly
	 the same, meaning same patient, same time slot, and the same location.
	 @param obj The appointment of interest to compare to this one.
	 @return true if the two appointments are the same, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean patientValue = (this.patient.compareTo(((Appointment)obj).patient)  == 0);
		boolean timeValue = this.slot.compareTo(((Appointment)obj).slot) == 0;
		boolean locationValue = this.location.equals(((Appointment)obj).location) == 0;
		
		if(patientValue && timeValue && locationValue) {
			return true;
		}
		return false;
	}
	
	/**
	 Take this appointment and turns it into a readable string.
	 @return This appointment in the form of "Patient, Appointment detail: Timeslot Location" string.
	 */
	@Override
	public String toString() { 
		return patient.toString() + ", Appointment detail: " + slot.toString() + " "
			+ this.location.toString();
	}
	
	/**
	 Gets the specific timeslot of this appointment.
	 @return The timeslot of this appointment.
	 */
	public Timeslot getTimeslot() {
		return this.slot;
	}
	
	/**
	 Gets the specific location of this appointment.
	 @return The location of this appointment.
	 */
	public Location getLocation() {
		return this.location;
	}
	
	/**
	 Gets the patient information of this specific appointment.
	 @return The patient of this appointment.
	 */
	public Patient getPatient() {
		return this.patient;
	}
}
