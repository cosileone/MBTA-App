package data;

import GUI.Constants.*;

public class DataFactory {
	public Graph allTrains = fetchAllData(true);
	
	private Graph fetchAllData(boolean internetData){
		if(internetData) {
			return JsonTest.getGraphFromInternet();
		}
		else {
			return JsonTest.getGraphFromInternet();
		}
	}
	
	
}
