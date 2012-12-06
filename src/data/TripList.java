package data;

import java.util.ArrayList;
import java.util.List;

public class TripList
{
    private int currentEpochTime;
    private String line;
    private List<Trip> trips = new ArrayList<Trip>();

    public int getCurrentEpochTime() {
        return this.currentEpochTime;
    }
    public void setCurrentEpochTime(int currentEpochTime) {
        this.currentEpochTime = currentEpochTime;
    }
    public String getLine() {
        return this.line;
    }
    public void setLine(String line) {
        this.line = line;
    }
    public List<Trip> getTrips() {
        return this.trips;
    }
    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
    public void addTrip(Trip t) {
        this.trips.add(t);
    }

    public void append(TripList tl) {
        for(Trip t : tl.getTrips()) {
            this.addTrip(t);
        }
    }

    @Override
    public String toString() {
        String s = "TripList [CurrentEpochTime=" + currentEpochTime + ", Line=" + line + ", Trips=" + trips;

        //        String strips = "";
        //        System.out.println("tripsLength= "+ trips.length);
        //        for (int i = 0; i < trips.length; i++) {
        //            System.out.println("i= " + i);
        //            strips += trips[i].toString();
        //        }

        //        return s + strips + "]";
        return s;
    }


}
