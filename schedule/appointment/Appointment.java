package schedule.appointment;

import schedule.appointment.location.Location;
import schedule.appointment.patient.Patient;
import schedule.appointment.timeSlot.Timeslot;

public class Appointment {
	private Patient patient;
	private Timeslot slot;
	private Location location;
	
	public Appointment(Patient patient, Timeslot slot, Location location) {
		this.patient = patient;
		this.slot = slot;
		this.location = location;
	}
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
	
	@Override
	public String toString() { 
		return patient.toString() + ", Appointment detail: " + slot.toString() + " "
			+ this.location.toString();
	}
	
	public Timeslot getTimeslot() {
		return this.slot;
	}
	public Location getLocation() {
		return this.location;
	}
	public Patient getPatient() {
		return this.patient;
	}
}
