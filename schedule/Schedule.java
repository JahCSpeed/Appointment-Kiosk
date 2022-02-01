package schedule;

import schedule.appointment.Appointment;

public class Schedule {
	
	private Appointment [] appointments;

	private int numAppts;
	private final int INCREASESIZE = 4;
	public Schedule() {
		this.appointments = new Appointment[0];
		this.numAppts = 0;
		
	}
	private int find(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = 0; i < numAppts; i++) {
			if(appointments[i].equals(appt)) {
				return i;
			}
		}
		return -1;
	} 

	private void grow() { //increase the capacity of the container by 4
		Appointment [] prevList = this.appointments;
		this.appointments = new Appointment[this.numAppts + INCREASESIZE];
		for(int i = 0; i < prevList.length; i++) {
			this.appointments[i] = prevList[i];
		}
	}

	public boolean add(Appointment appt) {
		if(this.find(appt) != -1) {
			System.out.println("Same appointment exists in the schedule.");
			return false;
		}
		if(this.findTimeSlot(appt) != -1) {
			System.out.println("Time slot has been taken at this location.");
			return false;
		}
		if(numAppts == this.appointments.length) {
			this.grow();
		}
		appointments[this.numAppts++] = appt;
		System.out.println("Appointment booked and added to the schedule.");
		return true;
	}

	public boolean remove(Appointment appt) {
		int index = this.find(appt);
		if(index == -1) {
			return false;
		}
		for(int i = index; i < numAppts - 1; i++) {
			appointments[i] = appointments[i + 1];
		}
		appointments[numAppts] = null;
		numAppts--;
		return true;
	}

	public void print() { //print all the appointments in current order
		for(int i = 0; i < numAppts; i++) {
			System.out.println(appointments[i].toString());
		}
	}
	
	private int findTimeSlot(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = 0; i < numAppts; i++) {
			if(appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == 0) {
				return i;
			}
		}
		return -1;
	}

	public void printByZip() { //sort by zip codes and print
	
	} 

	public void printByPatient() { //sort by patient and print
	
	} 



}