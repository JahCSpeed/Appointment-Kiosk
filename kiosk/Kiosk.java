package kiosk;

import java.util.Scanner;

import schedule.Schedule;
import schedule.appointment.Appointment;
import schedule.appointment.date.Date;
import schedule.appointment.location.Location;
import schedule.appointment.patient.Patient;
import schedule.appointment.timeSlot.Timeslot;
import schedule.appointment.timeSlot.time.Time;

public class Kiosk {
	//Will determine if the while loop runs or not
	private boolean isRunning;
	//Scanner to read userInput
	private Scanner scanInput;
	private Schedule mainSchedule;
	
	
	
	public Kiosk() {
		//Initialize instance variables
		this.isRunning = false;
		this.scanInput = new Scanner(System.in);
		this.mainSchedule = new Schedule();
				

	}
	public void run(){
		//Start of while loop to get user commands
		System.out.println("Kiosk running. Ready to process transactions.");
		this.isRunning = !this.isRunning; 
		while(isRunning) {
			captureInput(scanInput);
		}
	}
	/*
	 * Will capture user input, currently only does single line
	 * NEEDS TO CAPTURE MULTIPLE LINES OF INPUT
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
					
					if(!this.checkValidBDate(patientDob)) {break;}
					
					patient = new Patient(tokens[i++],tokens[i++],patientDob);
					
					apptDate = readDate(tokens[i++]);
					
					if(!this.checkValidAppDate(apptDate)) {break;}
					
					apptTime = readTime(tokens[i++]);
					
					if(!this.checkTime(apptTime)) {break;}
					
					apptTimeslot = new Timeslot(apptDate, apptTime);
					
					appLocal = this.readLocation(tokens[i++]);
					
					if(!this.checkLocation(appLocal)) {break;}
				}catch(ArrayIndexOutOfBoundsException e) {}
				doCommand(intendingCommand, patient, apptTimeslot, appLocal);
				break;
			}
			
			
			
		}
		sc.close();
	}
	
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
	/*
	 * Will return all possible commands as int values
	 * 
	 * -1 means not a valid command
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
	
	/*
	 * Parse input string and just take the date
	 */
	private Date readDate(String input) {
		return new Date(input);
	}
	
	/*
	 * Parse input string and just take the date
	 */
	private Time readTime(String input) {
		return new Time(input);
	}
	
	
	private void endKiosk() {
		System.out.println("Kiosk session ended.");
		this.isRunning = !this.isRunning;
	}
	
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
	
	private boolean checkTime(Time time) {
		if(!time.isValid()){
			System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
			return false;
		}
		return true;
	}
	
	private boolean checkLocation(Location local){
		if(local == null) {
			System.out.println("Invalid location!");
			return false;
		}
		return true;
	}
	
	private boolean checksBeforeAdd(Appointment appt) {
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
