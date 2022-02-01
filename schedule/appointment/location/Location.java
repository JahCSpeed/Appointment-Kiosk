package schedule.appointment.location;

import schedule.Schedule;
import schedule.appointment.Appointment;

public enum Location {
	Bridgewater("Bridgewater","08807","Somerset", new Schedule()),
	Piscataway("Piscataway","08854", "Middlesex",new Schedule()),
	Princeton("Princeton","08542", "Mercer",new Schedule()),
	Morristown("Morristown","07960", "Morris",new Schedule()),
	Union("Union","07083", "Union",new Schedule());
	
	private final String zipCode;
	private final String county;
	private final String name;
	public final Schedule schedule;
	private Location(String name, String zipCode,String county,Schedule schedule) {
		this.zipCode = zipCode;
		this.county = county;
		this.name = name;
		this.schedule = schedule;
	}
	
	public String toString() {
		return this.name + " " + this.zipCode + ", " + this.county.toUpperCase();
	}
	
	public int equals(Location location) {
		boolean zip = this.zipCode.equals(location.zipCode);
		boolean count = this.county.equals(location.county);
		boolean nm = this.name.equals(location.name);
		
		if(zip && count && nm) {
			return 0;
		}
		return -1;
	}
	public void add(Appointment appt) {
		this.schedule.add(appt);
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}

	public void remove(Appointment appointment) {
		this.schedule.remove(appointment);
	}
	
	public String getCounty() {
		return this.county;
	}

}
