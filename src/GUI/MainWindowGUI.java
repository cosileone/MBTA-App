package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	
	JTextArea routes = new JTextArea("Routes Printed Here...", 20,60);
	JTextField inputRoutes = new JTextField("Enter Destinations Here!", 50);

	public MainWindowGUI(){
		initComponents();
	}
	
	private void initComponents() {
		JFrame frame = new JFrame();
		routes.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(routes);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// Text Area still won't scroll!? :(
		scrollpane.setPreferredSize(new Dimension(250, 250));
		
		JButton calculateButton = new javax.swing.JButton("Fetch Train Data");
		inputRoutes = new JTextField();
		inputRoutes.setForeground(Color.LIGHT_GRAY);
		inputRoutes.setText("Destination Finding not yet developed.");
		
		// Frame
		frame.setTitle("MBTA Train Application");
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(204,246,255));
		frame.setResizable(false);
		
		//Panel
		JPanel centerPanel = new JPanel();
		centerPanel.add(inputRoutes);
		centerPanel.add(routes);
		centerPanel.add(calculateButton);
		frame.add(centerPanel);
		
		//Action Listeners
		calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateRoutes(evt);
            }
        });
		inputRoutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateRoutes(evt);
            }
        });
		
		routes.setLineWrap(true);
		
		// Layout
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
	}
	
	private void calculateRoutes(ActionEvent e) {
		routes.setText("");
		fetchData();
		String input = inputRoutes.getText();
		//int start = nthOccurrence(redText, ',', 4)+2;
		//int finish = nthOccurrence(redText, ',', 5);
		
		routes.append(redText);
		//routes.append("\nNext train to "+ input + " in " + redText.substring(start,finish) + " seconds!");
		inputRoutes.setText("");
    }
	
	public static int nthOccurrence(String str, char c, int n) {
	    int pos = str.indexOf(c, 0);
	    while (n-- > 0 && pos != -1)
	        pos = str.indexOf(c, pos+1);
	    return pos;
	}
	
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowGUI();
            }
        });
	}

}
