package blueprints;

public class SmrtNrgBp extends Blueprint {

	private String location;
	private String service;
	private int dataRate;
	private int density;
	
	//constructors
	public SmrtNrgBp() {
		
	}
	
	public SmrtNrgBp(String location, String service, int dataRate, int density) {
		this.location = location;
		this.service = service;
		this.dataRate = dataRate;
		this.density = density;
	}

	//getters - setters
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getDataRate() {
		return dataRate;
	}

	public void setDataRate(int dataRate) {
		this.dataRate = dataRate;
	}

	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}
			
}
