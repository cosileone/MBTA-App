package data;

import java.util.ArrayList;

public class Pathway<T> extends ArrayList<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ArrayList<TrainConnection> tc = new ArrayList<TrainConnection>();
	private int currentTime;
	
	public Pathway(int actualTime){
		super();
		this.currentTime = actualTime;
	};
	

	public void setTo(Pathway<T> temp) {
		this.clear();
		for (T t : temp) {
			this.add(t);
		}
		//this.currentTime = temp.currentTime;
	}
	public int getTime() {
		if (this.isEmpty()) {
			return this.currentTime;
		} else {
			TrainConnection t = (TrainConnection) this.get(this.size() - 1);
			return t.getTime();
		}
	}
	public void removeLastElement() {
		int i = this.size() - 1;
		this.remove(i);
	}
	public void append(Pathway<T> pt) {
		for (T t : pt) {
			this.add(t);
		}
	}
	public int numTransfers() {
		if (this.isEmpty()) {
			return 0;
		} else {
			int counter = 0;
			for (int i = 0, j = 1; j < this.size(); i++, j++) {
				TrainConnection tc1 = (TrainConnection) this.get(i);
				TrainConnection tc2 = (TrainConnection) this.get(j);
				if (!tc1.getTripID().equals(tc2.getTripID())) {
					counter++;
				}
			}
			return counter;
		}
	}
	public int totalTimeTraveled() {
		/*
		if (this.size() == 0) {
			return (int) Double.POSITIVE_INFINITY;
		} else {
		*/
			TrainConnection tcFirst = (TrainConnection) this.get(0);
			return (this.getTime() - tcFirst.getTime());
		//}
	}
}
