package data;

public class Train {
	private String station;
	private int epochTime;
	private String destination;
	
	public Train(String s, int e, String d) {
		this.station = s;
		this.epochTime = e;
		this.destination = d;
	}
	
	
	public String getStation() {
		return this.station;
	}
	public int getEpochTime() {
		return this.epochTime;
	}
	public String getDestination() {
		return this.destination;
	}
}
