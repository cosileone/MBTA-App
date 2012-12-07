package data;

import java.util.Comparator;

public class Edge {
	private int weight;
	private String line;
	private String tripID;
	private Station startStation;
	private Station endStation;
	private String destination;
	
	public Edge(int w, String l, String t, Station ss, Station es, String destination) {
		this.weight = w;
		this.line = l;
		this.tripID = t;
		this.startStation = ss;
		this.endStation = es;
		this.destination = destination;
	}
	
	public int getWeight() {
		return this.weight;
	}
	public String getLine() {
		return this.line;
	}
	public String getTripID() {
		return this.tripID;
	}
	public Station getStartStation() {
		return this.startStation;
	}
	public Station getEndStation() {
		return this.endStation;
	}
	public String getDestination() {
		return this.destination;
	}
	
	public static Comparator<Edge> EdgeWeightComparator = new Comparator<Edge>() {
		public int compare(Edge e1, Edge e2) {
			return e1.weight - e2.weight;
		}
	};
	
	
	public String toString() {
		return "weight:" + weight + " line:" + line + " tripID:" + tripID + 
		" ss:" + startStation.getName() + " es:" + endStation.getName();
	}
}
