package kiosk;

import java.util.Scanner;
import java.util.StringTokenizer;

import schedule.appointment.date.Date;
import schedule.appointment.patient.Patient;

@SuppressWarnings("unused")
public class Kiosk {
	//Will determine if the while loop runs or not
	private boolean isRunning;
	//Scanner to read userInput
	private Scanner scanInput;
	//Deliminaters for string tokenizer
	private String delim;
	//String tokenizer for whole input
	
	
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
				if(intendingCommand == -1) {
					System.out.println("Invalid command!");
					break;
				}
				Date patientDob = readDate(tokens[i++]);
				if(!patientDob.isValid()) {
					System.out.println("Invalid date of birth!");
					break;
				}else if(patientDob.compareTo(new Date()) == 1) {
					System.out.println("Date of birth invalid -> it is a future date.");	
					break;
				}
				Patient patient = new Patient(tokens[i++],tokens[i++],patientDob);
				System.out.println(intendingCommand);
				System.out.println(patient.toString());
				break;
			}
			
			
			
		}
		sc.close();
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
	/*
	 * Parse input string and just take the date
	 */
	private Date readDate(String input) {
		return new Date(input);
	}
	
}
