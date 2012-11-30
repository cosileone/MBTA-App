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

            String testBlue = "Resources/Test Files/TestBlue_2012_10_19.json";
            String testOrange = "Resources/Test Files/TestOrange_2012_10_19.json";
            String testRed = "Resources/Test Files/TestRed_2012_10_19.json";

            ArrayList<String> files = new ArrayList<String>();
            files.add(testBlue);
            files.add(testOrange);
            files.add(testRed);

            tripl = jsonFilesToTripList(files);

            //tripl = jfttl(testBlue);
            //tripl = jfttl(testOrange);
            //tripl = jfttl(testRed);

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
            Path s = findQuickestPathBetween2Stations("State Street", "", "Airport", "", tripl, 0);
            Path pp = findQuickestPathBetween2Stations("Airport", "", "Back Bay", "", tripl, 0);
            System.out.println(p.toString());
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println(q.toString());
            System.out.println("++++++++++++++++++++++++++++");
            //System.out.println(r.toString());
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println(s.toString());
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println(pp.toString());
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
        TripList tl1 = jfttl(filenames.get(0));
        TripList tl2 = jfttl(filenames.get(1));
        TripList tl3 = jfttl(filenames.get(2));

        tl1.append(tl2);
        tl1.append(tl3);

        return tl1;
    }


    public static TripList jfttl(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TripList tripl = new TripList();

        try {

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
        catch (IOException e) {
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
                tempTime = tempPredSecs;

                if(!foundStartStation) {
                    if(tempPredStop.equals(startStation)) {
                        foundStartStation = true;
                        //tempPath.setPathTime(tempTime);
                        tempPath.addToTrip(tempPredStop);
                    }
                } else if((tempTime > path.getPathTime()) || (tempTime < currTime)) {
                    break;
                } else if(tempPredStop.equals(endStation)) {
                        tempPath.setPathTime(tempTime);
                        tempPath.addToTrip(endStation);
                        path = tempPath;
                        break;
                } else if(tempPredStop.equals("State") || tempPredStop.equals("JFK/UMass") || tempPredStop.equals("Downtown Crossing")) {
                    Path aPath = findQuickestPathBetween2Stations(tempPredStop, "", endStation, "", loTrips, tempTime);
                    if(aPath.getTrip().contains(endStation) && (aPath.getPathTime() < path.getPathTime())) {
                        tempPath.append(aPath);
                        path = tempPath;
                    }
                } else {
                    tempPath.addToTrip(tempPredStop);
                }
            }
        }
        return path;
    }


}

