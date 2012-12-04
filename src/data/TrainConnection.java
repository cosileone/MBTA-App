package data;

public class TrainConnection {
	private String tripID;
	private String station;
	private int time;
	
	public TrainConnection(String tripID, String s, int t) {
		this.tripID = tripID;
		this.station = s;
		this.time = t;
	}
	
	public String getTripID() {
		return this.tripID;
	}
	public String getStation() {
		return this.station;
	}
	public int getTime() {
		return this.time;
	}
	
	public String toString() {
		return "tripID:" + tripID + " station:" + station + " time:" + time;
	}
}
