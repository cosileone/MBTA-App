package GUI;

import static GUI.Constants.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;

public class MainWindowGUI extends javax.swing.JFrame {
	String redText;
	String orangeText;
	String blueText;
	
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

	public MainWindowGUI(){
		initComponents();
	}
	
	private void initComponents() {
		/* Setup bare window */
		JFrame window = new JFrame(APP_TITLE);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(GUI_DIMENSIONS);
		window.setPreferredSize(GUI_DIMENSIONS);
		
		/* Layout for components */
		
		
		/* Start Adding components to window */
		
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowGUI();
            }
        });
	}

}
