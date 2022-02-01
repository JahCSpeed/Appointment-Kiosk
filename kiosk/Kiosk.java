package kiosk;

import java.util.Scanner;
import java.util.StringTokenizer;

import schedule.Schedule;
import schedule.appointment.Appointment;
import schedule.appointment.date.Date;
import schedule.appointment.location.Location;
import schedule.appointment.patient.Patient;
import schedule.appointment.timeSlot.Timeslot;
import schedule.appointment.timeSlot.time.Time;

@SuppressWarnings("unused")
public class Kiosk {
	//Will determine if the while loop runs or not
	private boolean isRunning;
	//Scanner to read userInput
	private Scanner scanInput;
	private String delim;
	
	
	
	public Kiosk() {
		//Initialize instance variables
		this.isRunning = false;
		this.delim = "\n";
		this.scanInput = new Scanner(System.in);
				

	}
	public void run(){
		//Start of while loop to get user commands
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
			
			for(int i = 0; i < tokens.length; i++) {
				int intendingCommand = getCommand(tokens[i++]);
				if(this.doNoNameCommand(intendingCommand) == 0) {
					break;
				}
				Date patientDob = readDate(tokens[i++]);
				if(!this.checkValidBDate(patientDob)) {
					break;
				}
				
				Patient patient = new Patient(tokens[i++],tokens[i++],patientDob);
				
				Date apptDate = readDate(tokens[i++]);
				if(!this.checkValidAppDate(apptDate)) {
					break;
				}
				
				Time apptTime = readTime(tokens[i++]);
				if(!this.checkTime(apptTime)) {
					break;
				}
				
				Timeslot apptTimeslot = new Timeslot(apptDate, apptTime);
				
				Location appLocal = this.readLocation(tokens[i++]);
				if(!this.checkLocation(appLocal)) {
					break;
				}
				doCommand(intendingCommand, patient, apptTimeslot, appLocal);
				break;
			}
			
			
			
		}
		sc.close();
	}
	
	private void doCommand(int command, Patient patient, Timeslot time, Location local) {
		switch(command) {
			case 0:
				local.add(new Appointment(patient, time, local));
				break;
			case 1:
				local.remove(new Appointment(patient, time, local));
				break;
			case 2:
				//while(this.mainSchedule.remove(new Appointment(patient, time, local))) {}
				break;
			default:
				break;
		}
	}
	private int doNoNameCommand(int command) {
		switch(command) {
			case 3:
				printLocationsSchedule();
				return 0;
			case 4:
				return 0;
			case 5:
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
	
	private void printLocationsSchedule() {
		Location[] allLocations = Location.values();
		for(Location l : allLocations) {
			l.getSchedule().print();
		}
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
		System.out.println(" Kiosk session ended.");
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
}
