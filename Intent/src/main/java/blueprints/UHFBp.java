package blueprints;

public class UHFBp extends Blueprint {
	
	private String location;
	private String service;
	private int cellBand;
	
	//constructors
	public UHFBp() {
		
	}
	
	public UHFBp(String location, String service, int cellBand) {
		this.location = location;
		this.service = service;
		this.cellBand = cellBand;
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

	public int getCellBand() {
		return cellBand;
	}

	public void setCellBand(int cellBand) {
		this.cellBand = cellBand;
	}
	
}
