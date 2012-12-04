package data;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

//marked for deletion because this was from 1st iteration of APP
public class DataFactory {
	String redText;
	String orangeText;
	String blueText;
	
	ObjectMapper mapper = new ObjectMapper();
	
	public void fetchData(){
		try {
			URL redData = new URL("http://developer.mbta.com/Data/Red.txt");
			URL orangeData = new URL("http://developer.mbta.com/Data/Orange.txt");
			URL blueData = new URL("http://developer.mbta.com/Data/Blue.txt");
			
			Scanner scan = new Scanner(redData.openStream());
			scan.useDelimiter("\\Z");
			redText = scan.next();
			
			scan = new Scanner(orangeData.openStream());
			scan.useDelimiter("\\Z");
			redText = scan.next();
			
			scan = new Scanner(blueData.openStream());
			scan.useDelimiter("\\Z");
			redText = scan.next();
			
		}
		catch(IOException ex) {
		   // there was some connection problem, or the file did not exist on the server,
		   // or your URL was not in the right format.
		   // think about what to do now, and put it here.
		   ex.printStackTrace(); // for now, simply output it.
		}
	}
}
