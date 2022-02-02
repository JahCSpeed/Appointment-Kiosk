package schedule;
import schedule.appointment.Appointment;
import schedule.appointment.patient.Patient;
import schedule.appointment.timeSlot.time.Time;
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
			if(this.appointments[i] == null) {
				
			}
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
		Appointment[] sortAppt = this.appointments;
		int size = sortAppt.length;
		for (int i = 0; i < size - 1; i++) {
		      int min_idx = i;
		      for (int j = i + 1; j < size; j++) {
		    	  if(sortAppt[j] == null) {
		    		  break;
		    	  }
		    	  String zip1 = sortAppt[j].getLocation().getZipCode();
		    	  String zip2 = sortAppt[min_idx].getLocation().getZipCode();
		        if (zip1.compareTo(zip2) < 0) {
		          min_idx = j;
		        }
		        if(zip1.compareTo(zip2) == 0) {
		        	Time apptTime1 =  sortAppt[j].getTimeslot().getTime();
		        	Time apptTime2 =  sortAppt[min_idx].getTimeslot().getTime();
		        	if(apptTime1.compareTo(apptTime2) == -1) {
		        		min_idx = j;
		        	}
		        }
		      }
		      Appointment temp = sortAppt[i];
		      sortAppt[i] = sortAppt[min_idx];
		      sortAppt[min_idx] = temp;
		}
		System.out.println("\n" + "*list of appointments by zip and time slot.");
		for(int i = ZERO; i < this.numAppts; i++) {
			System.out.println(sortAppt[i].toString());
		}
		System.out.println("*end of schedule*" + "\n");
		  
	} 

	public void printByPatient() { //sort by patient and print
	
	} 



}