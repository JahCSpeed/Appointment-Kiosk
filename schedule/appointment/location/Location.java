package schedule.appointment.location;

public enum Location {
	Bridgewater("Bridgewater","08807","Somerset"),
	Piscataway("Piscataway","08854", "Middlesex"),
	Princeton("Princeton","08542", "Mercer"),
	Morristown("Morristown","07960", "Morris"),
	Union("Union","07083", "Union");
	
	private final String zipCode;
	private final String county;
	private final String name;
	private Location(String name, String zipCode,String county) {
		this.zipCode = zipCode;
		this.county = county;
		this.name = name;
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
	
	public String getCounty() {
		return this.county;
	}

}
