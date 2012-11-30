package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

//import java.io.File;
//import com.fasterxml.jackson.core.*;
//import com.fasterxml.jackson.annotation.*;

//import com.fasterxml.jackson.core.JsonParseException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

//import java.net.URL;

public class JsonTest
{
    public static void main(String[] args) throws IOException {
            TripList tripl = new TripList();

        try {

            ArrayList<String> files = new ArrayList<String>();
            files.add("Resources/Test Files/TestBlue_2012_10_19.json");
            //files.add("Resources/Test Files/TestOrange_2012_10_19.json");
            // files.add("Resources/Test Files/TestRed_2012_10_19.json");

            tripl = jsonFilesToTripList(files);

            System.out.println(tripl.toString());
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            //JsonTest t = new JsonTest();
            Path p = findQuickestPathBetween2Stations("Airport", "", "Maverick", "", tripl, 0);
            Path q = findQuickestPathBetween2Stations("Airport", "", "Government Center", "", tripl, 0);
            //Path r = findQuickestPathBetween2Stations("Porter Square", "", "Central Square", "", tripl, 0);
            //Path r = findQuickestPathBetween2Stations("Ruggles", "", "Back Bay", "", tripl, 0);
            System.out.println(p.getPathTime());
            System.out.println(p.getTrip());
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println(q.getPathTime());
            System.out.println(q.getTrip());
            System.out.println("++++++++++++++++++++++++++++");
            //System.out.println(r.toString());
            System.out.println("++++++++++++++++++++++++++++");

            //} catch (JsonGenerationException e) {
            //e.printStackTrace();
            //} catch (JsonMappingException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static TripList jsonFilesToTripList(ArrayList<String> filenames) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TripList tripl = new TripList();

        try {

            for(String filename : filenames) {

                BufferedReader fileReader = new BufferedReader(new FileReader(filename));
                JsonNode rootNode = mapper.readTree(fileReader);

                // IMPORTANT
                // without this option set adding new fields breaks old code
                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                JsonNode triplNode = rootNode.path("TripList");

                int ct = triplNode.get("CurrentTime").asInt();
                tripl.setCurrentTime(ct);
                String line = triplNode.get("Line").asText();
                tripl.setLine(line);

                JsonNode tNode = triplNode.path("Trips");

                Iterator<JsonNode> ite = tNode.elements();

                while(ite.hasNext()) {

                    JsonNode tempTripNode = ite.next();
                    Trip tempTrip = new Trip();

                    String tID = tempTripNode.get("TripID").asText();
                    tempTrip.setTripID(tID);
                    String dest = tempTripNode.get("Destination").asText();
                    tempTrip.setDestination(dest);

                    if(tempTripNode.has("Position")) {

                        JsonNode posNode = tempTripNode.path("Position");
                        Position pos = new Position();

                        int tTStamp = posNode.get("Timestamp").asInt();
                        pos.setTimestamp(tTStamp);
                        int tTrain = posNode.get("Train").asInt();
                        pos.setTrain(tTrain);
                        double tLat = posNode.get("Lat").asDouble();
                        pos.setLat(tLat);
                        double tLong = posNode.get("Long").asDouble();
                        pos.setLong(tLong);
                        int tHead = posNode.get("Heading").asInt();
                        pos.setHeading(tHead);

                        tempTrip.setPosition(pos);
                    }

                    JsonNode pNode = tempTripNode.path("Predictions");
                    Iterator<JsonNode> itePred = pNode.elements();

                    while(itePred.hasNext()) {

                        JsonNode tPredNode = itePred.next();
                        Prediction tempPred = new Prediction();

                        int tStopID = tPredNode.get("StopID").asInt();
                        tempPred.setStopID(tStopID);
                        String tStop = tPredNode.get("Stop").asText();
                        tempPred.setStop(tStop);
                        int tSeconds = tPredNode.get("Seconds").asInt();
                        tempPred.setSeconds(tSeconds);

                        tempTrip.addPrediction(tempPred);
                    }

                    tripl.addTrip(tempTrip);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tripl;
    }



    public static Path findQuickestPathBetween2Stations(String startStation, String ssLine, String endStation, String esLine, TripList loTrips, int currTime) {
        Path path = new Path();
        //boolean needToSwitchLines = !(ssLine.equals(esLine));

        for(Trip tempTrip : loTrips.getTrips()) {
            boolean foundStartStation = false;
            boolean foundEndStation = false;
            Path tempPath = new Path();
            int tempTime = currTime;

            for(Prediction tempPred : tempTrip.getPredictions()) {
                String tempPredStop = tempPred.getStop();
                int tempPredSecs = tempPred.getSeconds();

                if(!foundStartStation) {
                    if(tempPredStop.equals(startStation)) {
                        foundStartStation = true;
                        tempTime = tempPredSecs;
                        tempPath.setPathTime(tempTime);
                        tempPath.addToTrip(startStation);
                    }
                } else if(tempPredStop.equals(endStation)) {
                    if(tempPredSecs < path.getPathTime()) {    // Fastest path so far!!
                        tempTime = tempPredSecs;
                        tempPath.setPathTime(tempTime);
                        tempPath.addToTrip(endStation);
                        path = tempPath;       // setting the path - running best path
                    }
                } else if(tempPredStop.equals("State") || tempPredStop.equals("JFK/UMass") || tempPredStop.equals("Downtown Crossing")) {   // equal a transfer station
                    System.out.println(tempPredSecs);
                    Path aPath = findQuickestPathBetween2Stations(tempPredStop, "", endStation, "", loTrips, tempTime);
                    tempPath.append(aPath);
                    if((tempPath.getTrip().contains(endStation)) && (tempPath.getPathTime() < path.getPathTime())) {
                        path = tempPath;
                    }
                }
            }
        }

        return path;
    }


}

