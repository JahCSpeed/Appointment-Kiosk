package schedule.appointment.location;

/**
 Creates a Location based on where the appointment is scheduled.
 The place includes the name, county, and zip code, which is used for finding or cancelling appointments.
 @author Jah C. Speed,
 */
public enum Location {
	Bridgewater("Bridgewater","08807","Somerset"),
	Piscataway("Piscataway","08854", "Middlesex"),
	Princeton("Princeton","08542", "Mercer"),
	Morristown("Morristown","07960", "Morris"),
	Union("Union","07083", "Union");
	
	private final String zipCode;
	private final String county;
	private final String name;
	/**
	 
	 @param name
	 		zipCode
	 		county
	 */
	private Location(String name, String zipCode,String county) {
		this.zipCode = zipCode;
		this.county = county;
		this.name = name;
	}

	/**
	 Takes the format of the Location object and prints it out into a readable format.
	 @return The location in "Name Zip, County" format, all in a string.
	 */
	public String toString() {
		return this.name + " " + this.zipCode + ", " + this.county.toUpperCase();
	}

	/**
	 Checks if a location in question matches this object's location parameters.
	 @param location the object to check if it matches this object.
	 @return 0 if they are the same location, -1 otherwise.
	 */
	public int equals(Location location) {
		boolean zip = this.zipCode.equals(location.zipCode);
		boolean count = this.county.equals(location.county);
		boolean nm = this.name.equals(location.name);
		
		if(zip && count && nm) {
			return 0;
		}
		return -1;
	}
	/**
	 Takes this Location and has it return the county it is from.
	 @return The county of this Location
	 */
	public String getCounty() {
		return this.county;
	}
	/**
	 Takes this Location and has it return the zip code it has.
	 @return The zip code of this Location
	 */
	public String getZipCode() {
		return this.zipCode;
	}

}
