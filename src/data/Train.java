package data;

public class Train {
	private String station;
	private int epochTime;
	private String destination;
	private String line;
	private String tripID;
	
	public Train(String s, int e, String d, String l, String t) {
		this.station = s;
		this.epochTime = e;
		this.destination = d;
		this.line = l;
		this.tripID = t;
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
	public String getLine() {
		return this.line;
	}
	public String getTripID() {
		return this.tripID;
	}
}
