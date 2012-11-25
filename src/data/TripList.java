package data;

import java.util.ArrayList;
import java.util.List;

public class TripList
{
    private int currentTime;
    private String line;
    private List<Trip> trips = new ArrayList<Trip>();

    public int getCurrentTime() {
        return this.currentTime;
    }
    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
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

    @Override
    public String toString() {
        String s = "TripList [currentTime=" + currentTime + ", line=" + line + ", trips=" + trips;

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
