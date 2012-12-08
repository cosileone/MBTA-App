package GUI;

import static GUI.Constants.*;
import data.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class MainWindowGUI extends JFrame {
	public ArrayList<String> journey = new ArrayList<String>();
	public boolean sortedList = false;
	public int tripPreference = 0;
	public int departByTime = 0;
	public int arriveByTime = (int) Double.POSITIVE_INFINITY;
	public Pathway<TrainConnection> resultPath;
	
	public MainWindowGUI(){
		initComponents();
	}
	
	private void initComponents() {
		/* Setup bare window */
		JFrame window = new JFrame(APP_TITLE);
		window.setLayout(new BorderLayout());

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(GUI_DIMENSIONS);
		window.setPreferredSize(GUI_DIMENSIONS);
		
		/* Start Adding components to window */
		JTabbedPane tabs = new JTabbedPane();
		JPanel mapTabContents = makeMapTab();
		JPanel itinTabContents = makeItinTab();
		JPanel testTabContents = makeTestTab();
		
		tabs.addTab(MAP_TITLE, null, mapTabContents, MAP_TOOLTIP);
		tabs.addTab(ITINERARY_TITLE, null, itinTabContents, ITINERARY_TOOLTIP);
		tabs.addTab(TEST_TAB_TITLE+" ", null, testTabContents, TEST_TAB_TOOLTIP);
		window.add(tabs, BorderLayout.CENTER);
		
		window.validate();
		window.pack();
		window.setVisible(true);
	}
	
	private JPanel makeMapTab(){
		/* ---- Cyan wrapper ---- */
		final JPanel mapPanel = new JPanel(new BorderLayout());
		mapPanel.setBackground(Color.CYAN);
		
		/* ---- North Section of wrapper ---- */		
		JPanel northPanel = new JPanel();
		northPanel.setBorder(BorderFactory.createEtchedBorder());
		
		//destinationList is actually part of Destination List Components
		JPanel destinationList = new JPanel();
		
		/* Text input components */
		final GhostTextField inputField = new GhostTextField(INPUT_FIELD_FILLER, 20);
		inputField.setForeground(GRAY_TEXT_COLOR);
		JButton submitDestination = new JButton(SUBMIT_DESTINATION_TEXT);
		
		/* listener for the input field */
		ActionListener inputFieldListener = new DestinationEavesdropper(destinationList) {
			public void actionPerformed(ActionEvent ae) {
				String entry = inputField.getText();
				addToJourney(entry);
				System.out.println("Putting \""+ inputField.getText() + "\" into journey list.");
				refreshDestinationList(this.destinationPanel);
			}
		};
		inputField.addActionListener(inputFieldListener);
		submitDestination.addActionListener(inputFieldListener);
		
		/* Dropdown components */
		/* Reusable listener for multiple components */
		ActionListener comboBoxListener = new DestinationEavesdropper(destinationList) {
			public void actionPerformed(ActionEvent ae) {
				JComboBox box = (JComboBox) ae.getSource();
				String entry = (String) box.getItemAt(box.getSelectedIndex());
				addToJourney(entry);
				System.out.println("Putting \""+ entry + "\" into journey list.");
				refreshDestinationList(this.destinationPanel);
			}
		};
		
		//JLabel dropdownLabel = new JLabel(DROPDOWN_LABEL_TEXT);
		
		JComboBox blueDropdown = new JComboBox(BLUE_STATIONS);
		blueDropdown.setForeground(Color.BLUE);
		blueDropdown.setMaximumRowCount(10);
		blueDropdown.addActionListener(comboBoxListener);
		
		JComboBox redDropdown = new JComboBox(RED_STATIONS);
		redDropdown.setForeground(MYRED);
		redDropdown.setMaximumRowCount(15);
		redDropdown.addActionListener(comboBoxListener);
		
		JComboBox orangeDropdown = new JComboBox(ORANGE_STATIONS);
		orangeDropdown.setForeground(MYORANGE);
		orangeDropdown.setMaximumRowCount(15);
		orangeDropdown.addActionListener(comboBoxListener);
		
		JCheckBox sortedCheckbox = new JCheckBox(SORT_DEST_CHECKBOX_TEXT);
		sortedCheckbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				sortedList = !sortedList;
			}
		});
		
		JComboBox tripStyles = new JComboBox(TRIP_PREFERENCES);
		tripStyles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JComboBox temp = (JComboBox) ae.getSource();
				tripPreference = temp.getSelectedIndex();
			}
		});
		int currEpochTime = JsonTest.getCurrentEpochTime();
		ArrayList<String> loTimes = new ArrayList<String>();
		for (int i = 0; i < 359; i++) { // six hours worth of time
			int tempTime = currEpochTime + (60*i);
			String hrf = JsonTest.epochToHumanReadable(tempTime);
			loTimes.add(hrf);
		}
		String[] times = loTimes.toArray(new String[loTimes.size()]);
		JComboBox departTime = new JComboBox(times);
		departTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JComboBox temp = (JComboBox) ae.getSource();
				String time = (String) temp.getSelectedItem();
				departByTime = JsonTest.humanReadableTimeToEpoch(time);
			}
		});
		JComboBox arriveTime = new JComboBox(times);
		arriveTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JComboBox temp = (JComboBox) ae.getSource();
				String time = (String) temp.getSelectedItem();
				arriveByTime = JsonTest.humanReadableTimeToEpoch(time);
			}
		});
		
		northPanel.add(inputField);
		northPanel.add(submitDestination);
		//northPanel.add(dropdownLabel);
		northPanel.add(blueDropdown);
		northPanel.add(redDropdown);
		northPanel.add(orangeDropdown);
		northPanel.add(sortedCheckbox);
		northPanel.add(tripStyles);
		northPanel.add(departTime);
		northPanel.add(arriveTime);
		
		mapPanel.add(northPanel, BorderLayout.PAGE_START);
		/* ---- End North Section ---- */
		
		/* Map Region Components */
		JPanel mapRegion = new JPanel(new BorderLayout());
		ImageIcon mapImage = new ImageIcon(MAP_FILEPATH2);
		JLabel map = new JLabel(mapImage);
		JScrollPane mapScrollPane = new JScrollPane(map);
        mapScrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        HandScrollListener scrollListener = new HandScrollListener(map);
        mapScrollPane.getViewport().addMouseMotionListener(scrollListener);
        mapScrollPane.getViewport().addMouseListener(scrollListener);
        
		mapRegion.add(mapScrollPane, BorderLayout.CENTER);
		
		/* Destination List Components */
		JScrollPane scrollableDestinations = new JScrollPane(destinationList);
		JPanel destinationPane = new JPanel(new BorderLayout());
		destinationPane.setBackground(Color.WHITE);
		destinationPane.setBorder(BorderFactory.createEtchedBorder());
		Dimension size = getPreferredSize();
		size.width = (int)(GUI_DIMENSIONS.width/5);
		destinationPane.setPreferredSize(size);
		//Only print destinations on button press for now
		JButton planTrip = new JButton(PLAN_TRIP_BUTTON_TEXT);
		planTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JTabbedPane tabs = (JTabbedPane) mapPanel.getParent();
				if(validJourney()){
					if (sortedList) {
						resultPath = JsonTest.fastestSortedPath(journey, departByTime, arriveByTime, tripPreference);
					} else {
						resultPath = JsonTest.fastestUnsortedPath(journey, departByTime, arriveByTime, tripPreference);
					}
					tabs.setSelectedIndex(1);
					System.out.println(resultPath);
				}
				System.out.println(journey.toString());
			}
		});
		
		destinationList.setBackground(Color.WHITE);
		destinationList.setLayout(new BoxLayout(destinationList, BoxLayout.Y_AXIS));
		
		destinationPane.add(scrollableDestinations, BorderLayout.CENTER);
		destinationPane.add(planTrip, BorderLayout.PAGE_END);
		
		mapPanel.add(mapRegion, BorderLayout.CENTER);
		mapPanel.add(destinationPane, BorderLayout.EAST);
		/* ---- End Cyan wrapper ---- */
		
		return mapPanel;
	}
	
	private JPanel makeItinTab(){
		/* Wrapper for Itinerary Tab */
		JPanel itineraryPanel = new JPanel(new BorderLayout());
		JPanel linearMapRegion = new JPanel(new BorderLayout());
		
		LinearMap linearMap = new LinearMap();
		
		linearMapRegion.setBackground(Color.CYAN);
		linearMapRegion.add(linearMap);
		
		JTextArea instructions = new JTextArea("Look to the command line for your trip.");
		instructions.setEditable(false);
		
		itineraryPanel.add(linearMapRegion, BorderLayout.CENTER);
		itineraryPanel.add(instructions, BorderLayout.LINE_END);
		
		return itineraryPanel;
	}
	
	private JPanel makeTestTab(){
		JPanel initPanel = new JPanel(new BorderLayout());
		JButton printTrains = new JButton("Print All Trains");
		final JTextArea output = new JTextArea();
		output.setEditable(false);
		output.setText("Train and station data printed here");
		
		printTrains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Graph allData = DataFactory.fetchAllData(true);
				ArrayList<Train> allTrains = allData.getAllTrains();
				String buffer = new String();
				
				int currentTime = JsonTest.getCurrentEpochTime();
				for (Train t : allTrains) {
					buffer += "Train " + t.getTripID() + " on the " + t.getLine() + " line is about to arrive at " + t.getStation() + " in " + (t.getEpochTime() - currentTime) + " seconds, and is heading to " + t.getDestination() + ".\n";
				}
				output.setText(buffer);
			}
		});
		JScrollPane scrollingOutput = new JScrollPane(output);
		initPanel.add(printTrains, BorderLayout.PAGE_START);
		initPanel.add(scrollingOutput, BorderLayout.CENTER);
		
		return initPanel;
	}
	
	private void addToJourney(String entry){
		if (journey.contains(entry) || entry.compareTo("") == 0){
			//don't add
		}
		else {
			journey.add(entry);
		}
	}
	
	private void refreshDestinationList(JPanel dest){
		dest.removeAll();
		
		// Unique action listener allows a button to remove itself from list of destinations
		ActionListener button_removeSelf = new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				JButton button = (JButton) ae.getSource();
				JPanel parent = (JPanel) button.getParent();
				journey.remove(button.getText());
				parent.remove(button);
				
				parent.repaint();
				parent.revalidate();
			}
		};
		
		// for every string in our Path structure's list of destinations, make a button
		for (String s : journey){
			JButton tempButton = new JButton(s);
			tempButton.setSize(DESTINATION_LIST_BUTTON_SIZE);
			tempButton.addActionListener(button_removeSelf);
			dest.add(tempButton);
		}
		
		dest.revalidate();
	}
	
	private boolean validJourney(){
		if(journey.isEmpty()){
			return false;
		}
		else {
			return true;
		}
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
