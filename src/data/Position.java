package data;

public class Position
{
    private int timestamp = 0;
    private int train = 0;
    private double lat = 0.0;
    private double longitude = 0.0;
    private int heading = 0;

    public int getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
    public int getTrain() {
        return this.train;
    }
    public void setTrain(int train) {
        this.train = train;
    }
    public double getLat() {
        return this.lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLong() {
        return this.longitude;
    }
    public void setLong(double longitude) {
        this.longitude = longitude;
    }
    public int getHeading() {
        return this.heading;
    }
    public void setHeading(int heading) {
        this.heading = heading;
    }

    @Override
    public String toString() {
        return "Position [timestamp=" + timestamp + ", train=" + train + ", lat=" + lat + ", longitude=" + longitude + ", heading=" + heading + "]";
    }
}
