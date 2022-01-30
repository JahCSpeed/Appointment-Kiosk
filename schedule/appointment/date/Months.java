package schedule.appointment.date;
/*
 * Enum class to store the total days in a month on a regular year and leap year
 */
public enum Months {
	January(0,31,31),
	Febuary(1,28,29),
	March(2,31,31),
	April(3,30,30),
	May(4,31,31),
	June(5,30,30),
	July(6,31,31),
	August(7,31,31),
	September(8,30,30),
	October(9,31,31),
	November(10,30,30),
	December(11,31,31);
	
	public final int id;
	public final int days;
	public final int leapYearDays;
	
	private Months(int id,int days, int leapYearDays) {
		this.id = id;
		this.days = days;
		this.leapYearDays = leapYearDays;
	}
	
}
