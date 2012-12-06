package data;

import java.util.ArrayList;
import java.util.Collections;

public class Station {
	private ArrayList<Edge> outgoingEdges = new ArrayList<Edge>();
	private ArrayList<Edge> incomingEdges = new ArrayList<Edge>();
	private String name;
	
	public Station(String n) {
		this.name = n;
	}
	
	public void addOutgoingEdge(Edge e) {
		/// sort this shit
		if(!this.name.equals("NULL_STATION")) {
			this.outgoingEdges.add(e);
			Collections.sort(this.outgoingEdges, Edge.EdgeWeightComparator);	
		}
	}
	public ArrayList<Edge> getAllOutgoingEdges() {
		return this.outgoingEdges;
	}
	public void addIncomingEdge(Edge e) {
		/// sort this shit
		if(!this.name.equals("NULL_STATION")) {
			this.incomingEdges.add(e);
			Collections.sort(this.incomingEdges, Edge.EdgeWeightComparator);
		}
	}
	public ArrayList<Edge> getAllIncomingEdges() {
		return this.incomingEdges;
	}
	public String getName() {
		return this.name;
	}
	
	public int getOutgoingEdgeCount() {
		return this.outgoingEdges.size();
	}
	public int getIncomingEdgeCount() {
		return this.incomingEdges.size();
	}
	public Edge getOutgoingEdge(int i) {
		return this.outgoingEdges.get(i);
	}
	public Edge getIncomingEdge(int i) {
		return this.incomingEdges.get(i);
	}
	public int getPotentialOutgoingPathEdgeCount(int time) {
		int count = 0;
		for (Edge e : outgoingEdges) {
			if (time <= e.getWeight()) {
				count++;
			}
		}
		return count;
	}
	public Edge getPotentialOutgoingPathEdge(int idx, int time) {
		ArrayList<Edge> ae = new ArrayList<Edge>();
		for (Edge e : outgoingEdges) {
			if (time <= e.getWeight()) {
				ae.add(e);
			}
		}
		return ae.get(idx);
	}
	
	public int getPotentialIncomingPathEdgeCount(int time) {
		int count = 0;
		for (Edge e : outgoingEdges) {
			if (time <= e.getWeight()) {
				count++;
			}
		}
		return count;
	}
	public Edge getPotentialIncomingPathEdge(int idx, int time) {
		ArrayList<Edge> ae = new ArrayList<Edge>();
		for (Edge e : outgoingEdges) {
			if (time <= e.getWeight()) {
				ae.add(e);
			}
		}
		return ae.get(idx);
	}
	
	
	public String toString() {
		String s = "";
		s += name;
		for (Edge e : outgoingEdges) {
		//	s += " outgoingEdge" + e.toString() + "   ";
		}
		for (Edge e : incomingEdges) {
		//	s += " incomingEdge" + e.toString() + "   ";
		}
		return s;
	}
}
