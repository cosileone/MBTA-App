package data;

public class Prediction
{
    private int stopID;
    private String stop;
    private int seconds;

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
    public int getSeconds() {
        return this.seconds;
    }
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString()
    {
        return "Prediction [stopID=" + stopID + ", stop=" + stop + ", seconds=" + seconds + "]";
    }
}
