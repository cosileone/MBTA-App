package GUI;

import static GUI.Constants.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
//Test Guest Commit
public class MainWindowGUI extends JFrame {
	
	public MainWindowGUI(){
		initComponents();
	}
	
	private void initComponents() {
		/* Setup bare window */
		JFrame window = new JFrame(APP_TITLE);
		window.setLayout(new BorderLayout());
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(GUI_DIMENSIONS);
		window.setPreferredSize(GUI_DIMENSIONS);
		
		/* Start Adding components to window */
		JTabbedPane tabs = new JTabbedPane();
		JPanel mapTabContents = makeMapTab();
		JPanel itinTabContents = makeItinTab();
		
		tabs.addTab(MAP_TITLE, null, mapTabContents, MAP_TOOLTIP);
		tabs.addTab(ITINERARY_TITLE, null, itinTabContents, ITINERARY_TOOLTIP);
		window.add(tabs, BorderLayout.CENTER);
		
		window.pack();
	}
	
	protected JPanel makeMapTab(){
//		GridBagConstraints c = new GridBagConstraints();
		
		/* * * Cyan wrapper * * */
		JPanel mapPanel = new JPanel();
		mapPanel.setBackground(Color.CYAN);
		mapPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		/* ---- North Section of wrapper ---- */
		GridBagConstraints nGBC = new GridBagConstraints();
		
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.setBackground(Color.LIGHT_GRAY);
		
		/* Text input components */
		GhostTextField inputField = new GhostTextField(INPUT_FIELD_FILLER, 20);
		inputField.setForeground(GRAY_TEXT_COLOR);
		JButton submitDestination = new JButton(SUBMIT_DESTINATION_TEXT);
		
		/* Dropdown components */
		JLabel dropdownLabel = new JLabel(DROPDOWN_LABEL_TEXT);
		JComboBox blueDropdown = new JComboBox(BLUE_STATIONS);
		blueDropdown.setForeground(Color.BLUE);
		JComboBox redDropdown = new JComboBox(RED_STATIONS);
		redDropdown.setForeground(MYRED);
		JComboBox orangeDropdown = new JComboBox(ORANGE_STATIONS);
		orangeDropdown.setForeground(MYORANGE);
		
		JCheckBox sortedCheckbox = new JCheckBox(SORT_DEST_CHECKBOX_TEXT);
		
		northPanel.add(inputField, nGBC);
		nGBC.gridx = 1;
		northPanel.add(submitDestination, nGBC);
		nGBC.gridx = 2;
		northPanel.add(dropdownLabel, nGBC);
		nGBC.gridx = 3;
		northPanel.add(blueDropdown, nGBC);
		nGBC.gridx = 4;
		northPanel.add(redDropdown, nGBC);
		nGBC.gridx = 5;
		northPanel.add(orangeDropdown, nGBC);
		nGBC.gridx = 6;
		northPanel.add(sortedCheckbox, nGBC);
		
		mapPanel.add(northPanel, BorderLayout.NORTH);
		/* ---- End North Section ---- */
		
		/* Destination List Components */
		JPanel destinationList = new JPanel(new GridBagLayout());
		destinationList.setBackground(Color.WHITE);
		
		/* Map Region Components */
		JPanel mapRegion = new JPanel();
//		mapRegion.setPreferredSize();
		mapRegion.setBackground(Color.BLACK);
		
//		/* Start adding components to the Panel */
//		c.weightx = 1;
//		c.weighty = 1;
//		c.gridx = 0;
//		c.gridy = 0;
//		//cut items
//		// ...
//		//end cut
//		c.gridy = 1;
//		c.gridx = 0;
//		c.gridwidth = 6; // 6 Components in location entry region
//		c.fill = GridBagConstraints.BOTH;
//		mapPanel.add(mapRegion, c);
		
		
		return mapPanel;
	}
	
	protected JPanel makeItinTab(){
		GridBagConstraints c = new GridBagConstraints();
		JPanel initPanel = new JPanel(new GridBagLayout(), true);
		return initPanel;
	}
	
	public static void main(String[] args) {
		/* Makes new thread for application (to be safe) */
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowGUI();
            }
        });
	}

}
