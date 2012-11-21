package Data;

import java.util.List;

public class TrainMap {
   	private Number currentTime;
   	private List<Train> triplist;
   	
 	public Number getCurrentTime(){
		return this.currentTime;
	}
	public void setCurrentTime(Number currentTime){
		this.currentTime = currentTime;
	}
 	public List<Train> getTripList(){
 		return this.triplist;
 	}
 	public void setTripList(List<Train> triplist){
 		this.triplist = triplist;
 	}
}
