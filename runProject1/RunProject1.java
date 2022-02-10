package runProject1;

import kiosk.Kiosk;

/**
 This is a driver class to simply run all of Project 1.
 It runs the Kiosk, which enables commands to be entered via the command prompt.
 @author Jah C. Speed, Abe Vitangcol
 */
public class RunProject1 {
	/**
	 The main functionality of this class: Running the Kiosk.
	 @param args Additional arguments added to the call. Note this is not used.
	 */
	public static void main(String[] args) {
			new Kiosk().run();
	}

}
