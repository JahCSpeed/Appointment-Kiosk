package kiosk;

/*
 * Enum class for all valid commands
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

	private Commands(int id) {
		this.id = id;
	}
	
}
