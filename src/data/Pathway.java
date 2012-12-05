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
}
