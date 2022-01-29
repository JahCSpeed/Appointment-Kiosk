package kiosk;

import java.util.Scanner;
import java.util.StringTokenizer;

@SuppressWarnings("unused")
public class Kiosk {
	//Will determine if the while loop runs or not
	private boolean isRunning;
	//Scanner to read userInput
	private Scanner scanInput;
	//Deliminaters for string tokenizer
	private String delim;
	
	public Kiosk() {
		//Initialize global variables
		this.isRunning = false;
		this.delim = "\n";
		this.scanInput = new Scanner(System.in).useDelimiter(delim);

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
			int intendingCommand = getCommand(readCommand(input));
			System.out.println(intendingCommand);
			
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
	 * Parse input string and just take the command
	 */
	private String readCommand(String input) {
		String intendingCommand = "";
		try {
			String[] splitString = input.split("\\s+");
			intendingCommand = splitString[0];
			
		}catch(NullPointerException e) {
			intendingCommand = "";
		}catch(IndexOutOfBoundsException e) {
			intendingCommand = "";
		}
		return intendingCommand;
	}
	
	
}
