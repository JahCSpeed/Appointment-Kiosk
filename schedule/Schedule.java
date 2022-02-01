package schedule;

import schedule.appointment.Appointment;
import schedule.appointment.patient.Patient;

public class Schedule {
	
	private Appointment [] appointments;
	private int numAppts;
	private final int INCREASESIZE = 4;
	private final int NOT_FOUND = -1;
	private final int ZERO = 0;
	public Schedule() {
		this.appointments = new Appointment[ZERO];
		this.numAppts = ZERO;
		
	}
	private int find(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = ZERO; i < this.numAppts; i++) {
			if(this.appointments[i].equals(appt)) {
				return i;
			}
		}
		return NOT_FOUND;
	} 

	private void grow() { //increase the capacity of the container by 4
		Appointment [] prevList = this.appointments;
		this.appointments = new Appointment[this.numAppts + INCREASESIZE];
		for(int i = ZERO; i < prevList.length; i++) {
			this.appointments[i] = prevList[i];
		}
	}

	public boolean add(Appointment appt) {
		if(this.numAppts == this.appointments.length) {
			this.grow();
		}
		this.appointments[this.numAppts++] = appt;
		return true;
	}

	public boolean remove(Appointment appt) {
		int index = this.find(appt);
		if(index == NOT_FOUND) {
			return false;
		}
		for(int i = index; i < this.numAppts - 1; i++) {
			this.appointments[i] = this.appointments[i + 1];
		}
		this.appointments[this.numAppts] = null;
		this.numAppts--;
		return true;
	}
	
	/*
	 * Removes all patients by a given name
	 */
	public boolean removeAll(Patient patient) {
		for(int i = ZERO; i < this.numAppts; i++) {
			if(this.appointments[i].getPatient().compareTo(patient) == ZERO) {
				this.remove(this.appointments[i]);
			}
		}
		return true;
	}

	public void print() { //print all the appointments in current order
		System.out.println("\n" + "*list of appointments in the schedule*");
		for(int i = ZERO; i < this.numAppts; i++) {
			System.out.println(this.appointments[i].toString());
		}
		System.out.println("*end of schedule*" + "\n");
	}
	
	public int findTimeSlotAtLocation(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = ZERO; i < this.numAppts; i++) {
			if(this.appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == ZERO && 
					(this.appointments[i].getLocation().equals(appt.getLocation()) == ZERO) ){
				return i;
			}
			
		}
		return NOT_FOUND;
	}
	
	public int findTimeSlotForPatient(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = ZERO; i < this.numAppts; i++) {
			if(this.appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == ZERO &&
					this.appointments[i].getPatient().compareTo(appt.getPatient()) == ZERO) {
				return i;
			}
			
		}
		return NOT_FOUND;
	}
	
	public int findAppointment(Appointment appt) {
		return this.find(appt);
	}
	public void printByZip() { //sort by zip codes and print
		
	} 

	public void printByPatient() { //sort by patient and print
	
	} 



}