package kiosk;

/**
 The commands class holds all the valid command information for the kiosk to use.
 It lists all the valid commands as ids for use in the kiosk, where further
 actions will be done depending on the command.
 @author Jah C. Speed
 */
public enum Commands {
	B(0),
	C(1),
	CP(2),
	P(3),
	PZ(4),
	PP(5),
	Q(6);
	public final int id;
	
	/**
	 Constructs the identification code for each command.
	 @param id The identification code for the given command.
	 */
	private Commands(int id) {
		this.id = id;
	}
	
}
