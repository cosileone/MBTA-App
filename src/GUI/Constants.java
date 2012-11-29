package GUI;

import java.awt.*;

public final class Constants {
	/* Setting up the initial GUI window constants */
	public static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Dimension GUI_DIMENSIONS = setGUIDimensions();
	public static final String APP_TITLE = "MBTA Travel Application";
	/* Layout for components */
	public static final GridBagLayout GRID_BAG_LAYOUT = new GridBagLayout();
	/* Tab Titles and Tooltip Strings */
	public static final String MAP_TITLE = "Geo Map";
	public static final String MAP_TOOLTIP = "Switch to MAP view to see a GEO-MAP and enter destinations to plan your trip.";
	public static final String ITINERARY_TITLE = "Itinerary";
	public static final String ITINERARY_TOOLTIP = "Switch to ITINERARY view to see a LINEAR-MAP and written directions to get to your destination.";
	/* Map Tab Components */
	public static final String INPUT_FIELD_FILLER = "Please enter a location.";
	
	
	
	
	
	/* Help set correct GUI window dimensions based on total screen size */
	public static final Dimension setGUIDimensions() {
		Dimension temp = SCREENSIZE;
		temp.width = (int) ((temp.width >= 1024) ? temp.width*.65 : 800);
		temp.height = (int) ((temp.height >= 800) ? temp.height*.75 : 600);
		
		return temp;
	}
}
