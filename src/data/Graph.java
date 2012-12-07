package data;

import java.util.ArrayList;


public class Graph {

    private ArrayList<Station> stations = new ArrayList<Station>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public Graph(){}


    public boolean isEmpty() {
        return stations.size() == 0;
    }

    public boolean addStation(Station s) {
        boolean added = false;
        if(stations.contains(s) == false) {
            added = stations.add(s);
        }
        return added;
    }
    public void addAllStations(ArrayList<Station> arrs) {
    	this.stations = arrs;
    }

    public int size() {
        return stations.size();
    }

    public Station getStation(int n) {
        return stations.get(n);
    }
    public Station getStationByName(String n) {
    	for (Station s : stations) {
    		if (s.getName().equals(n)) {
    			return s;
    		}
    	}
    	throw new IllegalArgumentException("station:" + n + " not found");
    }
    public ArrayList<Station> getstations() {
        return this.stations;
    }
    
    public ArrayList<Edge> getNearbyIncomingTrainsForStation(Station s) {
    	Station temp = getStationByName(s.getName());
    	return temp.getAllIncomingEdges();
    }
    
    
    public boolean addEdge(int time, String line, String tripID, Station from, Station to, String destination) throws IllegalArgumentException {
        if(!stations.contains(from)) {
            throw new IllegalArgumentException("from is not in graph");
        }
        if(!stations.contains(to)) {
            throw new IllegalArgumentException("to is not in graph");
        }
        Edge e = new Edge(time, line, tripID, from, to, destination);
        from.addOutgoingEdge(e);
        to.addIncomingEdge(e);
        edges.add(e);
        return true;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    public Pathway<TrainConnection> depthFirstSearch(Station startStation, Station endStation, 
    										 	int departByTime, 
    											int arriveByTime, int userCondition) {

    	Pathway<TrainConnection> pathway = new Pathway<TrainConnection>(arriveByTime);
    	
        for (int i = 0; i < startStation.getIncomingEdgeCount(); i++) {
        	Pathway<TrainConnection> tempPathway = new Pathway<TrainConnection>(arriveByTime);
            Edge e = startStation.getIncomingEdge(i);

            if((e.getWeight() - departByTime) >= 0) {		
            	// And since we've already departed, we don't need to pass the variable to dfsHelper (only arrivalTime)
            	TrainConnection firstStop = new TrainConnection(e.getTripID(), 
            									e.getEndStation().getName(), e.getWeight());
                    	tempPathway.add(firstStop);
                    	dfsHelper(startStation, endStation, pathway, tempPathway, arriveByTime, userCondition);
            }
        }
        return pathway;
    }
    
    private void dfsHelper(Station ss, Station es, Pathway<TrainConnection> completedPath, 
    		Pathway<TrainConnection> tp, int arriveByTime, int userCondition) {
    	// Should take an int that tells us whether the user want to get earliest arival/departure | fastest route | fewest transfers

    	int t = tp.get(tp.size() - 1).getTime();
       	int tempTime = t;
    	String tempTripID = tp.get(tp.size() - 1).getTripID();
    	
    	for (int i = 0; i < ss.getPotentialOutgoingPathEdgeCount(tempTime); i++) {
    		Edge e = ss.getPotentialOutgoingPathEdge(i, tempTime);
    		if (!e.getTripID().equals(tempTripID)) {
    			// takes care of any transfers - only need to transfer if changing tripIDs
    			tempTime = 0;
    			tempTime = t + 60;			
    		} else {
    			tempTime = 0;
    			tempTime = t;
    		}
    		
    		TrainConnection tempTC = new TrainConnection(e.getTripID(), e.getEndStation().getName(), e.getWeight());
    		if ((e.getWeight() < tempTime) || 
    				(e.getWeight() > completedPath.getTime()) ||
    				(e.getWeight() > arriveByTime)) {
    			continue;
    		} else if (e.getEndStation().getName().equals(es.getName())) {  
    			// could potentially grab the latest, so make sure edges are sorted.
    			tp.add(tempTC);
    			if (!completedPath.isEmpty()) {
    				switch (userCondition) {
    				case 0: //fastest route
        				if (tp.totalTimeTraveled() < completedPath.totalTimeTraveled()) {
        					completedPath.setTo(tp);
            				tp.removeLastElement();
        					break;
        				}
    					break;
    				case 1: // fewest transfers
        				if (tp.numTransfers() < completedPath.numTransfers()) {
        					completedPath.setTo(tp);
            				tp.removeLastElement();
        					break;
        				}
    					break;
    				case 2: // earliest departure
        				if (tp.get(0).getTime() < completedPath.get(0).getTime()) {
        					completedPath.setTo(tp);
            				tp.removeLastElement();
        					break;
        				}
    					break;
    				default: // earliest arrival
        				if (tp.getTime() < completedPath.getTime()) {//||
        						//tp.size() < completedPath.size()) {
        					completedPath.setTo(tp);
            				tp.removeLastElement();
        					break;
        				}
    					break;
    				}
    				/*
    				if ((e.getWeight() < completedPath.get((completedPath.size() - 1)).getTime()) ||
    						tp.size() < completedPath.size()) {
    					completedPath.setTo(tp);
        				tp.removeLastElement();
    					break;
    				}
    				*/
    			} else {
    				completedPath.setTo(tp);
    				tp.removeLastElement();
    				break;
    			}
    		} else {
    			tp.add(tempTC);
    			dfsHelper(e.getEndStation(), es, completedPath, tp, arriveByTime, userCondition);
    		}
    	}
		tp.removeLastElement();  	// If can't find anything from this station, pop it off.
    	// If we did find something, we would have already mutated completedPath.
    }
    
    public ArrayList<Train> getAllTrains() {
        Station s = this.getStationByName("NULL_STATION");
        ArrayList<Train> arrT = new ArrayList<Train>();
        for (Edge e : s.getAllOutgoingEdges()) {
        	int time = e.getWeight();
        	String destination = e.getDestination();
        	
        	Train tempTrain = new Train(e.getEndStation().getName(), time, destination, e.getLine(), e.getTripID());
        	arrT.add(tempTrain);
        }
        
    	return arrT;
    }
    
    public ArrayList<Train> getAllTrainsIntoStation(String s){
        ArrayList<Train> arrt = new ArrayList<Train>();
        Station station = this.getStationByName(s);
        
        for (Edge e : station.getAllIncomingEdges()) {
        	// int time = e.getWeight();
        	// String destination = e.getDestination();
        	String stationName = station.getName();
        	int arrivalTime = e.getWeight();
        	String destination = e.getDestination();
        	Train tempTrain = new Train(stationName, arrivalTime, destination, e.getLine(), e.getTripID());
        	
        	arrt.add(tempTrain);
        }
        return arrt;
    }
    
    public String toString() {
    	
    	String string = "";
    	for (Station s : stations) {
    		string += "Station:" + s + "   ";
    	}
    	for (Edge e : edges) {
    		string += "Edge:" + e + "   ";
    	}
    	return string;
    }
    
}

