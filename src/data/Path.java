package data;

import java.util.List;
import java.util.ArrayList;


public class Path {
    private int pathTime = 10000000;
    private List<String> trip = new ArrayList<String>();

    Path(){}

    public int getPathTime() {
        return this.pathTime;
    }

    public void setPathTime(int time) {
        this.pathTime = time;
    }

    public List<String> getTrip() {
        return this.trip;
    }

    public void setTrip(ArrayList<String> st) {
        this.trip = st;
    }

    public void addToTrip(String s) {
        this.trip.add(s);
    }

    public void append(Path p) {
        this.pathTime += p.getPathTime();
        for(String s : p.getTrip()) {
            this.addToTrip(s);
        }
    }

    @Override
    public String toString() {
        return "pathTime="+ this.getPathTime() + "  trip: " + this.getTrip().toString();
    }
}
