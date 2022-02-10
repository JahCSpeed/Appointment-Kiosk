package kiosk;

import java.util.Scanner;

import schedule.Schedule;
import schedule.appointment.Appointment;
import schedule.appointment.date.Date;
import schedule.appointment.location.Location;
import schedule.appointment.patient.Patient;
import schedule.appointment.timeSlot.Timeslot;
import schedule.appointment.timeSlot.time.Time;

/**
 The Kiosk class is what enables users to schedule their appointment by writing in a specific format.
 It knows what are errors or bad commands given to it as well as it knows all the valid commands
 it needs to know to run. Capable to run, add, remove, print, and stop the scheduling process.
 @author Jah C. Speed, Abe Vitangcol
 */
public class Kiosk {
	private boolean isRunning;
	private Scanner scanInput;
	private Schedule mainSchedule;
	
	
	/**
	 Initializes the Kiosk and sets the variables to values before the run() function is called.
	 */
	public Kiosk() {
		this.isRunning = false;
		this.scanInput = new Scanner(System.in);
		this.mainSchedule = new Schedule();
				

	}
	
	/**
	 Runs the Kiosk and sets the status to Running. It captures inputs continuously until it stops running.
	 */
	public void run(){
		System.out.println("Kiosk running. Ready to process transactions.");
		this.isRunning = !this.isRunning; 
		while(isRunning) {
			captureInput(scanInput);
		}
	}
	
	/**
	 Scans the user input(s) and does command(s) based on the inputs given.
	 Able to capture single or multiple lines of input.
	 @param sc The scanner that scans the user inputs and parses them.
	 */
	private void captureInput(Scanner sc) {
		while(sc.hasNextLine()) {
			String input = sc.nextLine();
			String[] tokens = input.split("\\s+");
			Date patientDob = null;
			Patient patient = null;
			Date apptDate = null;
			Time apptTime = null;
			Timeslot apptTimeslot = null;
			Location appLocal = null;
			int intendingCommand = -1;
			for(int i = 0; i < tokens.length;) {
				try {
					intendingCommand = getCommand(tokens[i++]);
					
					if(this.doNoNameCommand(intendingCommand) == 0) {return;}
					
					patientDob = readDate(tokens[i++]);
					patient = new Patient(tokens[i++],tokens[i++],patientDob);
					apptDate = readDate(tokens[i++]);
					apptTime = readTime(tokens[i++]);
					apptTimeslot = new Timeslot(apptDate, apptTime);
					appLocal = this.readLocation(tokens[i++]);
				}catch(ArrayIndexOutOfBoundsException e) {}
					doCommand(intendingCommand, patient, apptTimeslot, appLocal);
				break;
			}
			
			
			
		}
		sc.close();
	}
	
	/**
	 Performs a command that requires parameters to execute.
	 Will add or remove appointments based on the command given.
	 @param command The identification code of the command based on the enum class.
	        patient The patient looking for an appointment.
	        time The timeslot of the appointment wanted.
	        local The location of the appointment booked.
	 */
	private void doCommand(int command, Patient patient, Timeslot time, Location local) {
		Appointment appt = new Appointment(patient, time, local);
		switch(command) {
			case 0:
				if(this.checksBeforeAdd(appt)) {
					this.mainSchedule.add(appt);
					System.out.println("Appointment booked and added to the schedule.");
				}
				break;
			case 1:
				if(this.mainSchedule.remove(appt)) {
					System.out.println("Appointment cancelled.");
				}else {
					System.out.println("Not cancelled, appointment does not exist.");
				}
				break;
			case 2:
				this.mainSchedule.removeAll(patient);
				System.out.println("All appointments for " + patient.toString() +" have been cancelled.");
				break;
			default:
				break;
		}
	}
	
	/**
	 Performs a command that requires no further parameters.
	 Will print the array with specifications based on the command, end the kiosk,
	 or state the invalidity of the command.
	 @param command The identification code of the command based on the enum class.
	 @return 0 if a print or invalid branch has been reached successfully, -1 otherwise.
	 */
	private int doNoNameCommand(int command) {
		switch(command) {
			case 3:
				this.mainSchedule.print();
				return 0;
			case 4:
				this.mainSchedule.printByZip();
				return 0;
			case 5:
				this.mainSchedule.printByPatient();
				return 0;
			case 6:
				this.endKiosk();
				return 0;
			case -1:
				System.out.println("Invalid command!");
				return 0;
			default:
				return -1;
		}
	}
	
	/**
	 Take a command and returns the identification code of that command for the switch cases.
	 @param input The command input for the kiosk to execute.
	 @return -1 if it is not a valid command, the id of the command otherwise.
	 */
	private int getCommand(String input) {
		switch(input) {
			case "B":
				return Commands.B.id;
			case "C":
				return Commands.C.id;
			case "CP":
				return Commands.CP.id;
			case "P":
				return Commands.P.id;
			case "PZ":
				return Commands.PZ.id;
			case "PP":
				return Commands.PP.id;
			case "Q":
				return Commands.Q.id;
			default:
				break;
			
		}
		return -1;
		
	}
	
	/**
	 Reads an input and finds the proper location based on its county.
	 @param input The location in a string format to be read.
	 @return The proper location if found, null otherwise.
	 */
	private Location readLocation(String input) {
		Location[] allLocations = Location.values();
		for(Location l : allLocations) {
			//System.out.println("Comparing: " +  l.name().toLowerCase() + "==" + input.toLowerCase());
			if(l.getCounty().toLowerCase().equals(input.toLowerCase())) {
				return l;
			}
		}
		return null;
	}
	
	/**
	 Parse an input string and just take the date part of it.
	 @param input The input of the time to parse.
	 @return The appropriate date object.
	 */
	private Date readDate(String input) {
		return new Date(input);
	}
	
	/**
	 Parse an input string takes only the time part of it.
	 @param input The input of the time to parse.
	 @return The appropriate time object.
	 */
	private Time readTime(String input) {
		return new Time(input);
	}
	
	/**
	 Ends the Kiosk session, causing it to stop running.
	 */
	private void endKiosk() {
		System.out.println("Kiosk session ended");
		this.isRunning = !this.isRunning;
	}
	
	/**
	 Checks to see if the dob of the patient is a valid date for a dob.
	 @param date The date of birth of the patient to be checked.
	 @return false if the dob is not a valid dob (in the future / not a date), true otherwise.
	 */
	private boolean checkValidBDate(Date date) {
		if(!date.isValid()) {
			System.out.println("Invalid date of birth!");
			return false;
		}else if(date.compareTo(new Date()) == 1) {
			System.out.println("Date of birth invalid -> it is a future date.");	
			return false;
		}
		return true;
	}
	
	/**
	 Checks to see if the date of the appointment is a valid date for an appointment.
	 @param date The date of the appointment to be checked.
	 @return false if the date is an invalid appointment date, true otherwise.
	 */
	private boolean checkValidAppDate(Date date) {
		if(!date.isValid() || date.compareTo(new Date(1)) == 1) {
			System.out.println("Invalid appointment date!");
			return false;
		}else if(date.compareTo(new Date()) == -1 || date.compareTo(new Date()) == 0) {
			System.out.println("Appointment date invalid -> must be a future date.");	
			return false;
		}
		return true;
	}
	
	/**
	 Checks to see if the time provided is a valid time and in a 15-min interval.
	 @param time The time of the appointment to be checked.
	 @return false if the time is an invalid appointment time, true otherwise.
	 */
	private boolean checkTime(Time time) {
		if(!time.isValid()){
			System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
			return false;
		}
		return true;
	}
	
	/**
	 Checks to see if the location given is one of the locations providing the appointment.
	 @param local The location being checked.
	 @return false if the location is not a valid one, true otherwise.
	 */
	private boolean checkLocation(Location local){
		if(local == null) {
			System.out.println("Invalid location!");
			return false;
		}
		return true;
	}
	
	/**
	 Checks to see if the appointment information is valid and can be added to the array of appointments.
	 @param appt The appointment being checked before before being added.
	 @return false if there is any conflict, true otherwise.
	 */
	private boolean checksBeforeAdd(Appointment appt) {
		if(!this.checkValidBDate(appt.getPatient().getDOB())) {return false;}
		if(!this.checkValidAppDate(appt.getTimeslot().getDate())) {return false;}
		if(!this.checkTime(appt.getTimeslot().getTime())) {return false;}
		if(!this.checkLocation(appt.getLocation())) {return false;}
		if(this.mainSchedule.findAppointment(appt) != -1) {
			System.out.println("Same appointment exists in the schedule.");
			return false;
		}
		if(this.mainSchedule.findTimeSlotAtLocation(appt) != -1) {
			System.out.println("Time slot has been taken at this location.");
			return false;
		}
		if(this.mainSchedule.findTimeSlotForPatient(appt) != -1) {
			System.out.println("Same patient cannot book an appointment with the same date.");
			return false;
		}
		return true;
	}
}
