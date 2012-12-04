package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class DestinationEavesdropper implements ActionListener {
	JPanel destinationPanel;
	
	public DestinationEavesdropper(JPanel destination){
		destinationPanel = destination;
	}
	
	public void actionPerformed(ActionEvent ae) {
		//Do nothing
	}

}
