
package Data;

import java.awt.geom.*;

public class Train{
	private String Line;
	private String tripID;
   	private String destination;
   	private int stopID;
   	private String stop;
   	private int SecondsAway;
   	private int trainNumber;
   	private Point2D Location;
 	
   	public String getLine(){
		return this.Line;
	}
	public void setLine(String line){
		this.Line = line;
	}
 	public String getDestination(){
		return this.destination;
	}
	public void setDestination(String destination){
		this.destination = destination;
	}
 	public String getTripID(){
		return this.tripID;
	}
	public void setTripID(String tripID){
		this.tripID = tripID;
	}
	public int getStopID() {
		return this.stopID;
	}
	public void setStopID(int stopID) {
		this.stopID = stopID;
	}
	public String getStop() {
		return this.stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public int getSecondsAway() {
		return this.SecondsAway;
	}
	public void setSecondsAway(int secondsAway) {
		this.SecondsAway = secondsAway;
	}
	public int getTrainNumber() {
		return this.trainNumber;
	}
	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}
	public Point2D getLocation() {
		return Location;
	}
	public void setLocation(Point2D location) {
		this.Location = location;
	}
}
