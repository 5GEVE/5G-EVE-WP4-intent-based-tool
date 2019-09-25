package blueprints;

public class Blueprint {

	private String name;
	private String description;
	private double version;
	private String identity;
	private String sector;
	private String country;
	private String fromCountry;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int duration;
	private int latency;
	private int dataRate;
	private double reliability;
	private double availability;
	private int mobility;
	private double broadband;
	private double capacity;
	private int density;
	private String slicing;
	private String security;
	
	//constructors
	public Blueprint() {
		
	}
	
	public Blueprint(String name, String description, String identity, String sector, String country, String fromCountry, int day, int month, int year, int hour, int minutes, int duration, int latency, int dataRate, double reliability, double availability, int mobility, double broadband, double capacity, int density, String slicing, String security) {
		this.name = name;
		this.description = description;
		this.version = (float) 1.0;
		this.identity = identity;
		this.sector = sector;
		this.country = country;
		this.fromCountry = fromCountry;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.latency = latency;
		this.dataRate = dataRate;
		this.reliability = reliability;
		this.availability = availability;
		this.mobility = mobility;
		this.broadband = broadband;
		this.capacity = capacity;
		this.density = density;
		this.slicing = slicing;
		this.security = security;
	}
	
	//getters - setters
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getSlicing() {
		return slicing;
	}

	public void setSlicing(String slicing) {
		this.slicing = slicing;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}

	public int getDataRate() {
		return dataRate;
	}

	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}

	public double getReliability() {
		return reliability;
	}

	public void setReliability(double reliability) {
		this.reliability = reliability;
	}

	public double getAvailability() {
		return availability;
	}

	public void setAvailability(double availability) {
		this.availability = availability;
	}

	public int getMobility() {
		return mobility;
	}

	public void setMobility(int mobility) {
		this.mobility = mobility;
	}

	public double getBroadband() {
		return broadband;
	}

	public void setBroadband(double broadband) {
		this.broadband = broadband;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getFromCountry() {
		return fromCountry;
	}

	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getVersion() {
		return version;
	}

	public void setVersion(double d) {
		this.version = d;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
