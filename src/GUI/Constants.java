package GUI;

import java.awt.*;

public final class Constants {
	/* Setting up the initial GUI window constants */
	public static final Dimension SCREENSIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Dimension GUI_DIMENSIONS = setGUIDimensions();
	public static final String APP_TITLE = "MBTA Travel Application";
	
	
	
	
	/* Help set correct GUI window dimensions based on total screen size */
	public static final Dimension setGUIDimensions() {
		Dimension temp = SCREENSIZE;
		temp.width = (int) ((temp.width >= 1024) ? temp.width*.65 : 800);
		temp.height = (int) ((temp.height >= 800) ? temp.height*.75 : 600);
		
		return temp;
	}
}
