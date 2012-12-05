package data;

import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.LinkedList;


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
    
    // I'm not sure how you want this data packaged and sent.								## FIXME
    public ArrayList<Edge> getNearbyIncomingTrainsForStation(Station s) {
    	Station temp = getStationByName(s.getName());
    	return temp.getAllIncomingEdges();
    }
    
    
    public boolean addEdge(int time, String line, String tripID, Station from, Station to) throws IllegalArgumentException {
        if(!stations.contains(from)) {
            throw new IllegalArgumentException("from is not in graph");
        }
        if(!stations.contains(to)) {
            throw new IllegalArgumentException("to is not in graph");
        }
        Edge e = new Edge(time, line, tripID, from, to);
        from.addOutgoingEdge(e);
        to.addIncomingEdge(e);
        edges.add(e);
        return true;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    /*
    public void clearStationMarks() {
        for (Station vert : stations) {
            vert.clearMark();
        }
    }

    public void clearEdgeMarks() {
        for (Edge<T> edge : edges) {
            edge.clearMark();
        }
    }
    */

    public Pathway<TrainConnection> depthFirstSearch(Station startStation, Station endStation, int currentTime, int departByTime, int arriveByTime) {
    	Pathway<TrainConnection> pathway = new Pathway<TrainConnection>(arriveByTime);    	
    	System.out.println("PathwayTime=" + pathway.getTime());
    	
        for (int i = 0; i < startStation.getIncomingEdgeCount(); i++) {
    	//for (int i = 0; i < startStation.getPotentialIncomingPathEdgeCount(departByTime); i++) {
        	Pathway<TrainConnection> tempPathway = new Pathway<TrainConnection>(arriveByTime);
            Edge e = startStation.getIncomingEdge(i);
            
            /*
            System.out.println("CurrentTimeSentToGraph=" + currentTime);
            System.out.println("DepartByTimeSentToGraph=" + departByTime);
            System.out.println("ArriveByTimeSentToGraph=" + arriveByTime);
            System.out.println("EdgeWeight=" + e.getWeight());
            System.out.println("Edge=" + e);
            System.out.println("++++++++++++");
            System.out.println("++++++++++++");
			*/

            if((e.getWeight() - departByTime) >= 0) {		
            	// And since we've already departed, we don't need to pass the variable to dfsHelper (only arrivalTime)
            	TrainConnection firstStop = new TrainConnection(e.getTripID(), 
            									e.getEndStation().getName(), e.getWeight());
                    	tempPathway.add(firstStop);
                    	dfsHelper(startStation, endStation, pathway, tempPathway, arriveByTime);
                    //break;
            }
        }
        return pathway;
    }
    
    private void dfsHelper(Station ss, Station es, Pathway<TrainConnection> completedPath, 
    		Pathway<TrainConnection> tp, int arriveByTime) {
    	
    	int t;
    	/*if(tp.isEmpty()) {			// shouldn't need it.
    		t = 0;
    	} else {
    	*/
    		t = tp.get(tp.size() - 1).getTime();
    	//}
    	int tempTime = t;
    	String tempTripID = tp.get(tp.size() - 1).getTripID();
    	
    	//for (int i = 0; i < ss.getOutgoingEdgeCount(); i++) {
    	for (int i = 0; i < ss.getPotentialOutgoingPathEdgeCount(tempTime); i++) {
    		//Edge e = ss.getOutgoingEdge(i);
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
    				if ((e.getWeight() < completedPath.get((completedPath.size() - 1)).getTime()) ||
    						tp.size() < completedPath.size()) {
    					completedPath.setTo(tp);
        				tp.removeLastElement();
    					break;
    				}
    			} else {
    				completedPath.setTo(tp);
    				tp.removeLastElement();
    				break;
    			}
    		} else {
    			/*
    			if (tempTC.getStation().equals("Park Street")) {
    				int q = 999;
    				q += 1;
    			}
    			*/
    			tp.add(tempTC);
    			dfsHelper(e.getEndStation(), es, completedPath, tp, arriveByTime);
    		}
    	}
		tp.removeLastElement();  	// If can't find anything from this station, pop it off.
    	// If we did find something, we would have already mutated completedPath.
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

