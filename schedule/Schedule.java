package schedule;
import schedule.appointment.Appointment;
import schedule.appointment.date.Date;
import schedule.appointment.patient.Patient;

/**
 The schedule class holds all appointments and keeps track of them.
 Holds the number of appointments and all appointments in an array.
 Can find, add, remove, and print out appointments when prompted.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Schedule {
	
	private Appointment [] appointments;
	private int numAppts;
	private final int INCREASESIZE = 4;
	private final int NOT_FOUND = -1;
	private final int ZERO = 0;
	
	/**
	 Initializes the array of scheduled appointments.
	 Sets the current number of appointments to zero
	 as well as the size of the array of appointments.
	 */
	public Schedule() {
		this.appointments = new Appointment[ZERO];
		this.numAppts = ZERO;
		
	}
	
	/**
	 Finds a specific appointment in this array of appointments and returns the index.
	 @param appt The specific appointment to be found.
	 @return The index of the appointment found, NOT_FOUND (which is -1) otherwise.
	 */
	private int find(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = ZERO;i < this.numAppts; i++) {
			if(this.appointments[i].equals(appt)) {
				return i;
			}
		}
		return NOT_FOUND;
	} 
	
	/**
	 Grows the capacity of the appointment array by 4.
	 Helpful especially when the old array is out of space.
	 */
	private void grow() {
		Appointment [] prevList = this.appointments;
		this.appointments = new Appointment[this.numAppts + INCREASESIZE];
		for(int i = ZERO; i < prevList.length; i++) {
			this.appointments[i] = prevList[i];
		}
	}
	
	/**
	 Adds an appointment into the array if there is room, and grows the array if there is no room.
	 @param appt The specific appointment to be added to this array.
	 @return true if this operation was successful.
	 */
	public boolean add(Appointment appt) {
		if(this.numAppts == this.appointments.length) {
			this.grow();
		}
		this.appointments[this.numAppts++] = appt;
		return true;
	}

	/**
	 Removes a specific appointment in this array and updates the array accordingly.
	 Finds the appointment, shifts all appointments after it left one index,
	 then makes the last index empty. The number of appointments goes down by 1.
	 @param appt The specific appointment to be removed from this array.
	 @return NOT_FOUND if the appointment wasn't found, true otherwise.
	 */
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
	
	/**
	 Removes all patients of given specifications and updates the array accordingly.
	 @param patient The specific patient to be removed (includes fname, lname, dob)
	 @return true if this operation was successful.
	 */
	public boolean removeAll(Patient patient) {
		for(int i = ZERO;  i < this.numAppts; i++) {
			if(this.appointments[i].getPatient().compareTo(patient) == ZERO) {
				if(this.remove(this.appointments[i])) {
					i--;
				}
			}
		}
		return true;
	}
	
	/**
	 Takes the current array and prints out all the appointments in it and in order.
	 No other specifications have been done to the array.
	 */
	public void print() { //print all the appointments in current order
		System.out.println("\n" + "*list of appointments in the schedule*");
		for(int i = ZERO; i < this.numAppts; i++) {
			//if(this.appointments[i] == null) {
				
			//}
			System.out.println(this.appointments[i].toString());
		}
		System.out.println("*end of schedule*" + "\n");
	}
	
	/**
	 Finds the index of an appointment with a specific time slot at a specific location.
	 @param appt The specific appointment of interest.
	 @return the index of the appointment if it was found in the array, NOT_FOUND otherwise.
	 */
	public int findTimeSlotAtLocation(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = ZERO; i < this.numAppts; i++) {
			if(this.appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == ZERO && 
					(this.appointments[i].getLocation().equals(appt.getLocation()) == ZERO) ){
				return i;
			}
			
		}
		return NOT_FOUND;
	}
	
	/**
	 Finds the index of an appointment with a specific time slot for a specific person.
	 @param appt The specific appointment of interest.
	 @return the index of the appointment if it was found in the array, NOT_FOUND otherwise.
	 */
	public int findTimeSlotForPatient(Appointment appt) { //return the index, or NOT_FOUND
		for(int i = ZERO; i < this.numAppts; i++) {
			if(this.appointments[i].getTimeslot().compareTo(appt.getTimeslot()) == ZERO &&
					this.appointments[i].getPatient().compareTo(appt.getPatient()) == ZERO) {
				return i;
			}
			
		}
		return NOT_FOUND;
	}
	
	/**
	 Finds a specific appointment in this array of appointments.
	 @param appt The specific appointment to be found.
	 @return The specific appointment if found, NOT_FOUND otherwise.
	 */
	public int findAppointment(Appointment appt) {
		return this.find(appt);
	}
	
	/**
	 Sorts the appointment array by zip code, then prints this sorted array.
	 Takes the current appointment array and builds an empty array of equal size.
	 Then it goes through the array multiple times to sort. It prints this sorted array afterwards.
	 */
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
		        	if(sortAppt[j].getTimeslot().compareTo(sortAppt[min_idx].getTimeslot()) == -1) {
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
		System.out.println("*end of schedule." + "\n");
		  
	} 

	/**
	 Sorts the appointment array by patient, then prints this sorted array.
	 Takes the current appointment array and builds an empty array of equal size.
	 Then it goes through the array multiple times to sort. It prints this sorted array afterwards.
	 */
	public void printByPatient() { //sort by patient and print
		Appointment[] sortAppt = this.appointments;
		int size = sortAppt.length;
		for (int i = 0; i < size - 1; i++) {
		      int min_idx = i;
		      for (int j = i + 1; j < size; j++) {
		    	  if(sortAppt[j] == null) {
		    		  break;
		    	  }
		    	  String lname1 = sortAppt[j].getPatient().getLastName();
		    	  String lname2 = sortAppt[min_idx].getPatient().getLastName();
		    	  if (lname1.compareTo(lname2) < 0) {
		    		  min_idx = j;
		    	  }
		    	  if(lname1.compareTo(lname2) == 0) {
		    		  String fname1 = sortAppt[j].getPatient().getFirstName();
		    		  String fname2 = sortAppt[min_idx].getPatient().getFirstName();
		    		  if (fname1.compareTo(fname2) < 0) {
			    		  min_idx = j;
			    	  }
		    		  if(fname1.compareTo(fname2) == 0) {
			    		  Date date1 = sortAppt[j].getPatient().getDOB();
			    		  Date date2 = sortAppt[min_idx].getPatient().getDOB();
			    		  if (date1.compareTo(date2) < 0) {
				    		  min_idx = j;
				    	  }
			    	  }
		    	  }
		      }
		      Appointment temp = sortAppt[i];
		      sortAppt[i] = sortAppt[min_idx];
		      sortAppt[min_idx] = temp;
		}
		System.out.println("\n" + "*list of appointments by patient.");
		for(int i = ZERO; i < this.numAppts; i++) {
			System.out.println(sortAppt[i].toString());
		}
		System.out.println("*end of list" + "\n");
		  
	} 



}