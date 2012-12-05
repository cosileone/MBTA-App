package GUI;

import static GUI.Constants.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LinearMap extends JPanel {
	int numBlueStations = BLUE_STATIONS.length;
	int numRedStations = RED_STATIONS.length;
	int numOrangeStations = ORANGE_STATIONS.length;
	
	public LinearMap(){
		paintComponents(null);
	}
	
    public void paintComponent(Graphics g) {
		super.paintComponents(g);
		
		double xInterval = 0.0;
		double yInterval = 0.0;
		
		int bigCircleRad = 21;
		int bigCircleDisplacement = (int)(bigCircleRad/2);
		
		int gap = bigCircleDisplacement*2;
		
		int panelWidth = this.getWidth();
		int panelHeight = this.getHeight();
		int i = 0;
		
		xInterval = panelWidth/7;
		yInterval = panelHeight/numBlueStations;
		
		int rectangleWidth = (int) (bigCircleRad*.75);
		int rectangleHeight = (int) ((numBlueStations-1)*yInterval);
		
		int littleCircleRad = rectangleWidth-2;
		int littleCircleDisplacement = (int)(littleCircleRad/2);
		
		g.setColor(Color.BLUE);
		g.fillRect((int)(.4*xInterval)-(rectangleWidth/2)+1, gap, rectangleWidth, rectangleHeight);
		for (i = 0; i < numBlueStations; i++) {
			if (i == 0 || i == numBlueStations-1) {
				g.setColor(Color.BLUE);
				g.fillOval((int) (.4*xInterval-bigCircleDisplacement), (int) (i*yInterval+gap)-bigCircleDisplacement, bigCircleRad, bigCircleRad);
			}
			g.setColor(Color.WHITE);
			g.fillOval((int) (.4*xInterval-littleCircleDisplacement), (int) (i*yInterval+gap)-littleCircleDisplacement, littleCircleRad, littleCircleRad);
			g.setColor(Color.BLACK);
			g.drawString(BLUE_STATIONS[i], (int) (.8*xInterval), (int) (i*yInterval+gap));
		}
		
		int lineSplit = new ArrayList<String>(Arrays.asList(RED_STATIONS)).indexOf("JFK/UMass");
		yInterval = panelHeight/(numRedStations-5);
		rectangleHeight = (int) ((numRedStations-6)*yInterval);
		g.setColor(Color.RED);
		g.fillRect((int)(2*xInterval)-(rectangleWidth/2)+1, (panelHeight/2)-rectangleWidth, (int) (xInterval*1.5)+rectangleWidth, rectangleWidth);
		g.fillRect((int)(2*xInterval)-(rectangleWidth/2)+1, gap, rectangleWidth, rectangleHeight);
		for (i = 0; i < numRedStations-5; i++) {
			if (i == 0 || i == numRedStations-6) {
				g.setColor(Color.RED);
				g.fillOval((int) (2*xInterval-bigCircleDisplacement), (int) (i*yInterval+gap)-bigCircleDisplacement, bigCircleRad, bigCircleRad);
			}
			g.setColor(Color.WHITE);
			g.fillOval((int) (2*xInterval-littleCircleDisplacement), (int) (i*yInterval+gap)-littleCircleDisplacement, littleCircleRad, littleCircleRad);
			g.setColor(Color.BLACK);
			g.drawString(RED_STATIONS[i], (int) (2.45*xInterval), (int) (i*yInterval+gap));
		}
		
		int newStartPoint = (int) (lineSplit*yInterval);
		yInterval = (newStartPoint/5);
		g.setColor(Color.RED);
		g.fillRect((int)(3.5*xInterval)-(rectangleWidth/2)+1, newStartPoint, rectangleWidth, (int) (yInterval*4)+gap);
		for (int j = 0; j < 5; j++) {
			if (j == 4) {
				g.setColor(Color.RED);
				g.fillOval((int) (3.5*xInterval-bigCircleDisplacement), (int) (newStartPoint + (j*yInterval+gap)-bigCircleDisplacement), bigCircleRad, bigCircleRad);
			}
			g.setColor(Color.WHITE);
			g.fillOval((int) (3.5*xInterval-littleCircleDisplacement), (int) (newStartPoint + (j*yInterval+gap)-littleCircleDisplacement), littleCircleRad, littleCircleRad);
			g.setColor(Color.BLACK);
			g.drawString(RED_STATIONS[i+j], (int) (3.85*xInterval), (int) (newStartPoint + (j*yInterval+gap)));
		}
		
		yInterval = panelHeight/numOrangeStations;
		rectangleHeight = (int) ((numOrangeStations-1)*yInterval);
		g.setColor(MYORANGE);
		g.fillRect((int)(5*xInterval)-(rectangleWidth/2)+1, gap, rectangleWidth, rectangleHeight);
		for (i = 0; i < numOrangeStations; i++) {
			if (i == 0 || i == numOrangeStations-1) {
				g.setColor(MYORANGE);
				g.fillOval((int) (5*xInterval-bigCircleDisplacement), (int) (i*yInterval+gap)-bigCircleDisplacement, bigCircleRad, bigCircleRad);
			}
			g.setColor(Color.WHITE);
			g.fillOval((int) (5*xInterval-littleCircleDisplacement), (int) (i*yInterval+gap)-littleCircleDisplacement, littleCircleRad, littleCircleRad);
			g.setColor(Color.BLACK);
			g.drawString(ORANGE_STATIONS[i], (int) (5.5*xInterval), (int) (i*yInterval+gap));
		}
    }
}
