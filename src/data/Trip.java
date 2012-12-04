package data;

import java.util.ArrayList;
import java.util.List;

public class Trip
{
    private String tripID;
    private String line;
    private String destination;
    private Position position = new Position();
    private List<Prediction> predictions = new ArrayList<Prediction>();

    public String getTripID() {
        return this.tripID;
    }
    public void setTripID(String tripID) {
        this.tripID = tripID;
    }
    public String getLine() {
        return this.line;
    }
    public void setLine(String l) {
        this.line = l;
    }
    public String getDestination() {
        return this.destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Position getPosition() {
        return this.position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public List<Prediction> getPredictions() {
        return this.predictions;
    }
    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }
    public void addPrediction(Prediction pred) {
        this.predictions.add(pred);
    }

    public boolean containsPredStation(String station) {
        for(Prediction pred : this.predictions) {
            if(pred.getStop().equals(station)) {
                return true;
                }
        }
        return false;
    }

    public Prediction getPredByStation(String station) {
        for(Prediction pred : this.predictions) {
            if(pred.getStop().equals(station)) {
                return pred;
            }
        }
        return null;
    }

    public String toString() {
        String s = "Trip [tripID=" + tripID + ", line=" + line + ", destination=" + destination + ", position=" + position.toString() + ", predictions=" + predictions;
        //        String preds = "";
        //        for (int i = 0; i < predictions.length; i++) {
        //            preds += predictions[i].toString();
        //        }
        //        return s + preds + "]";
        return s;
    }


}
