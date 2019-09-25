package blueprints;

public class ImMediaBp extends Blueprint{
	
	private String location;
	private String service;
	private int cellBand;
	private int upLink;
	
	//constructors
	public ImMediaBp() {
		
	}
	
	public ImMediaBp(String location, String service, int cellBand, int upLink) {
		this.location = location;
		this.service = service;
		this.cellBand = cellBand;
		this.upLink = upLink;
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

	public int getUpLink() {
		return upLink;
	}

	public void setUpLink(int upLink) {
		this.upLink = upLink;
	}

}
