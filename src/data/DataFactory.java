package data;

import GUI.Constants.*;

public class DataFactory {
	public Graph allTrains = fetchAllData(true);
	
	public static Graph fetchAllData(boolean fromInternet){
		if(fromInternet) {
			return JsonTest.getGraphFromInternet();
		}
		else {
			return JsonTest.getGraphFromInternet();
		}
	}
	
}
