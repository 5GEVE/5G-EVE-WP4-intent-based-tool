package blueprints;

public class AGVBp extends Blueprint {
	
	private int numOfAgvs;
	private String location;
	private String image;
	private int dataRate;
	private String sapLocation;
	private String service;
	private String atomicFuncComp;
	
	//constructors
	public AGVBp() {
		
	}
	
	public AGVBp(int numOfAgvs, String location, String image, int dataRate, String sapLocation, String service, String atomicFuncComp) {
		this.numOfAgvs = numOfAgvs;
		this.location = location;
		this.image = image;
		this.dataRate = dataRate;
		this.sapLocation = sapLocation;
		this.service = service;
		this.atomicFuncComp = atomicFuncComp;
	}

	//getters - setters
	public String getAtomicFuncComp() {
		return atomicFuncComp;
	}

	public void setAtomicFuncComp(String atomicFuncComp) {
		this.atomicFuncComp = atomicFuncComp;
	}

	public int getNumOfAgvs() {
		return numOfAgvs;
	}

	public void setNumOfAgvs(int numOfAgvs) {
		this.numOfAgvs = numOfAgvs;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getDataRate() {
		return dataRate;
	}

	public void setDataRate(int d) {
		this.dataRate = d;
	}

	public String getSapLocation() {
		return sapLocation;
	}

	public void setSapLocation(String sapLocation) {
		this.sapLocation = sapLocation;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	
}
