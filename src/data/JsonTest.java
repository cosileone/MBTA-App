package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

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
        ObjectMapper mapper = new ObjectMapper();

        try {

            BufferedReader fileReader = new BufferedReader(new FileReader("TestBlue_2012_10_19.json"));
            JsonNode rootNode = mapper.readTree(fileReader);

            // IMPORTANT
            // without this option set adding new fields breaks old code
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


            JsonNode triplNode = rootNode.path("TripList");

            TripList tripl = new TripList();

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


            System.out.println(tripl.toString());
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++");

            //} catch (JsonGenerationException e) {
            //e.printStackTrace();
            //} catch (JsonMappingException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

