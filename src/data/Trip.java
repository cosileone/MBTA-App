package data;

import java.util.ArrayList;
import java.util.List;

public class Trip
{
    private String tripID;
    private String destination;
    private Position position = new Position();
    private List<Prediction> predictions = new ArrayList<Prediction>();

    public String getTripID() {
        return this.tripID;
    }
    public void setTripID(String tripID) {
        this.tripID = tripID;
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

    public String toString() {
        String s = "Trip [tripID=" + tripID + ", destination=" + destination + ", position=" + position.toString() + ", predictions=" + predictions;
        //        String preds = "";
        //        for (int i = 0; i < predictions.length; i++) {
        //            preds += predictions[i].toString();
        //        }
        //        return s + preds + "]";
        return s;
    }


}
