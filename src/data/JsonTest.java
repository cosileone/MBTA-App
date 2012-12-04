package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Stack;
>>>>>>> 21f57a69474641f3d2c6f48a3e4e9fbb3b3a966e

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
	public enum TrainLine {
		BLUE,
		RED,
		ORANGE,
		TRANSFER;
	}
	
	public final static String BLUE_LINE = "blue";
	public final static String RED_LINE = "red";
	public final static String RED_A_LINE = "red_a";
	public final static String RED_B_LINE = "red_b";
	public final static String ORANGE_LINE = "orange";
	public final static String TRANSFER_LINE = "transfer";
	
	
    public enum Stop {
        WONDERLAND ("Wonderland", BLUE_LINE),             // Blue line trains
        REVERE_BEACH ("Revere Beach", BLUE_LINE),
        BEACHMONT ("Beachmont", BLUE_LINE),
        SUFFOLK_DOWNS ("Suffolk Downs", BLUE_LINE),
        ORIENT_HEIGHTS ("Orient Heights", BLUE_LINE),
        WOOD_ISLAND ("Wood Island", BLUE_LINE),
        AIRPORT ("Airport", BLUE_LINE),
        MAVERICK ("Maverick", BLUE_LINE),
        AQUARIUM ("Aquarium", BLUE_LINE),
        STATE ("State Street", TRANSFER_LINE),
        GOVERNMENT_CENTER ("Government Center", BLUE_LINE),
        BOWDOIN ("Bowdoin", BLUE_LINE),
        OAK_GROVE ("Oak Grove", ORANGE_LINE),                // Orange line trains
        MALDEN_CENTER ("Malden Center", ORANGE_LINE),
        WELLINGTON ("Wellington", ORANGE_LINE),
        SULLIVAN_SQUARE ("Sullivan", ORANGE_LINE),		// Sullivan, or sullican square?
        COMMUNITY_COLLEGE ("Community College", ORANGE_LINE),
        NORTH_STATION ("North Station", ORANGE_LINE),
        HAYMARKET_STATION ("Haymarket", ORANGE_LINE),		// haymarket or haymarket station?
        // STATE_STREET ("State Street", TRANSFER_LINE), --- blue line has it covered
        DOWNTOWN_CROSSING ("Downtown Crossing", TRANSFER_LINE),
        CHINATOWN ("Chinatown", ORANGE_LINE),
        TUFTS_MEDICAL_CENTER ("Tufts Medical", ORANGE_LINE),
        BACK_BAY ("Back Bay", ORANGE_LINE),
        MASSACHUSETTS_AVENUE ("Mass Ave", ORANGE_LINE),
        RUGGLES ("Ruggles", ORANGE_LINE),
        ROXBURY_CROSSING ("Roxbury Crossing", ORANGE_LINE),
        JACKSON_SQUARE ("Jackson Square", ORANGE_LINE),
        STONY_BROOK ("Stony Brook", ORANGE_LINE),
        GREEN_STREET ("Green Street", ORANGE_LINE),
        FOREST_HILLS ("Forest Hills", ORANGE_LINE),
        ALEWIFE ("Alewife", RED_LINE),	// Red line begins
        DAVIS ("Davis", RED_LINE),
        PORTER_SQUARE ("Porter Square", RED_LINE),
        HARVARD_SQUARE ("Harvard Square", RED_LINE),
        CENTRAL_SQUARE ("Central Square", RED_LINE),
        KENDALL_MIT ("Kendall/MIT", RED_LINE),
        CHARLES_MGH ("Charles/MGH", RED_LINE),
        PARK_STREET ("Park Street", RED_LINE),
        //	DtX --- taken care of by orange line
        SOUTH_STATION ("South Station", RED_LINE),
        BROADWAY ("Broadway", RED_LINE),
        ANDREW ("Andrew", RED_LINE),
        JFK_UMASS ("JFK/UMass", TRANSFER_LINE),
        NORTH_QUINCY ("North Quincy", RED_A_LINE),	// subsection of red line-A
        WOLLASTON ("Wollaston", RED_A_LINE),
        QUINCY_CENTER ("Quincy Center", RED_A_LINE),
        QUINCY_ADAMS ("Quincy Adams", RED_A_LINE),
        BRAINTREE ("Braintree", RED_A_LINE),
        SAVIN_HILL ("Savin Hill", RED_B_LINE),	// subsection of red line-B
        FIELDS_CORNER ("Fields Corner", RED_B_LINE),
        SHAWMUT ("Shawmut", RED_B_LINE),
        ASHMONT ("Ashmont", RED_B_LINE);

        private final String name;
        private final String line;
        Stop (String name, String line) {
            this.name = name;
            this.line = line;
        }
        
        public String getName() {
        	return name;
        }
        public String getline() {
        	return line;
        }
        
        public static ArrayList<Station> getArrayListOfStations() {
        	ArrayList<Station> arrs = new ArrayList<Station>();
        	for (Stop s : Stop.values()) {
        		Station station = new Station(s.name);
        		arrs.add(station);
        	}
        	return arrs;
        }
    }


    public static void main(String[] args) throws IOException {
            TripList tripl = new TripList();
<<<<<<< HEAD

        try {

=======
            
        try {
>>>>>>> 21f57a69474641f3d2c6f48a3e4e9fbb3b3a966e
            String testBlue = "Resources/Test Files/TestBlue_2012_10_19.json";
            String testOrange = "Resources/Test Files/TestOrange_2012_10_19.json";
            String testRed = "Resources/Test Files/TestRed_2012_10_19.json";

<<<<<<< HEAD
=======
            String shortTestBlue = "Resources/Test Files/shortTestBlue.json";
            String shortTestOrange = "Resources/Test Files/shortTestOrange.json";
            String shortTestRed = "Resources/Test Files/shortTestRed.json";


>>>>>>> 21f57a69474641f3d2c6f48a3e4e9fbb3b3a966e
            ArrayList<String> files = new ArrayList<String>();
            files.add(testBlue);
            files.add(testOrange);
            files.add(testRed);

            tripl = jsonFilesToTripList(files);
<<<<<<< HEAD

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
=======
            Graph g = new Graph();
            tripListToGraph(tripl, g);
            Station airport = g.getStationByName("Airport");
            Station maverick = g.getStationByName("Maverick");
            Station gov = g.getStationByName("Government Center");
            Station backbay = g.getStationByName("Back Bay");
            Station porter = g.getStationByName("Porter Square");
            Station harvard = g.getStationByName("Harvard Square");
            Station savHill = g.getStationByName("Savin Hill");
            Station braintree = g.getStationByName("Braintree");
            
            System.out.println("Graph:" + g.toString());
            int largeInt = 20000;
            
            // A thing to note, we define quickest path as a path that gets you there the soonest
            //  rather than one that gets you there the soonest and in the least amount of travel time
            //  although, I don't think the latter would be overly difficult to implement.
            Pathway<TrainConnection> am = g.depthFirstSearch(airport, maverick, 0, largeInt);
            System.out.println(am.toString());
            Pathway<TrainConnection> ma = g.depthFirstSearch(maverick, airport, 0, largeInt);
            System.out.println(ma.toString());
            Pathway<TrainConnection> ag = g.depthFirstSearch(airport, gov, 0, largeInt);
            System.out.println(ag.toString());
            Pathway<TrainConnection> ga = g.depthFirstSearch(gov, airport, 0, largeInt);
            System.out.println(ga.toString());
            Pathway<TrainConnection> abb = g.depthFirstSearch(airport, backbay, 0, largeInt);
            System.out.println(abb.toString());
            Pathway<TrainConnection> bba = g.depthFirstSearch(backbay, airport, 0, largeInt);
            System.out.println(bba.toString());
            
            // RED
            Pathway<TrainConnection> ph = g.depthFirstSearch(harvard, porter, 0, largeInt);
            System.out.println(ph.toString());
            Pathway<TrainConnection> hp = g.depthFirstSearch(porter, harvard, 0, largeInt);
            System.out.println(hp.toString());
            
            Pathway<TrainConnection> sb = g.depthFirstSearch(savHill, braintree, 0, largeInt);
            System.out.println(sb.toString());
            
            Pathway<TrainConnection> bs = g.depthFirstSearch(braintree, savHill, 0, largeInt);
            System.out.println(bs.toString());

>>>>>>> 21f57a69474641f3d2c6f48a3e4e9fbb3b3a966e
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

                tempTrip.setLine(line);
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
<<<<<<< HEAD
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
=======
        }
        return tripl;
>>>>>>> 21f57a69474641f3d2c6f48a3e4e9fbb3b3a966e
    }

    
    public static void tripListToGraph(TripList t, Graph g) {
    	ArrayList<Station> arrs = new ArrayList<Station>();
    	arrs = Stop.getArrayListOfStations();
    	Station nullStation = new Station("NULL_STATION");
    	arrs.add(nullStation);
    	g.addAllStations(arrs);
    	
    	
    	for (Trip trip : t.getTrips()) {
    		for (int i = 0, j = 1; j < trip.getPredictions().size(); i++, j++) {
        		Prediction p1 = trip.getPredictions().get(i);
        		Prediction p2 = trip.getPredictions().get(j);
        		
        		int weight = p2.getSeconds();
        		String line = trip.getLine();
        		String tripID = trip.getTripID();
        		String startStation = p1.getStop();
        		String endStation = p2.getStop();
        		Station ss = getStationByName(arrs, startStation);
        		Station es = getStationByName(arrs, endStation);
        		//Edge e = new Edge(weight, line, tripID, ss, es);
        		//ss.addOutgoingEdge(e);
        		g.addEdge(weight, line, tripID, ss, es);
        		
        		int w = p1.getSeconds();
    			if ((i == 0) && (w >= 0)) {			// If at the first station in the list, 
    				g.addEdge(w, line, tripID, nullStation, ss);
    			}
        	}
    	}
    }
    	
    public static Station getStationByName(ArrayList<Station> arrs, String name) {
    	for (Station s : arrs) {
    		if (s.getName().equals(name)) {
    			return s;
    		}
    	}
    	throw new IllegalArgumentException(name + " is not in the list");
    }
    

}

