package blueprints;

public class ArSmrtTrsmBp extends Blueprint {
	
	private String service;
	private int bandwidth;
	private int latency;
	
	
	//constructors
	public ArSmrtTrsmBp() {
		
	}
	
	public ArSmrtTrsmBp(String service, int bandwidth, int latency) {
		this.service = service;
		this.bandwidth = bandwidth;
		this.latency = latency;
	}
	
	//getters - setters
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public int getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	public int getLatency() {
		return latency;
	}

	public void setLatency(int latency) {
		this.latency = latency;
	}

}
