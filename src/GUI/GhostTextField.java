package GUI;

import static GUI.Constants.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GhostTextField extends JTextField implements FocusListener, ActionListener {

    private final String hint;

    public GhostTextField(final String hint) {
        super(hint);
        this.hint = hint;
        super.addFocusListener(this);
    }
    
    public GhostTextField(final String hint, final int columns) {
        super(hint, columns);
        this.hint = hint;
        super.addFocusListener(this);
        super.addActionListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
    	super.setText("");
    	this.setForeground(Color.BLACK);
    }
    
    @Override
    public void focusLost(FocusEvent e) {
    	this.setForeground(GRAY_TEXT_COLOR);
        if(this.getText().isEmpty()) {
            super.setText(hint);
        }
    }

    @Override
    public String getText() {
        String typed = super.getText();
        return typed.equals(hint) ? "" : typed;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.getText().isEmpty()) {
			System.out.println("[Enter]");
		}
		else {
			System.out.println(this.getText()+"[Enter]");
		}
		this.setText("");
	}
}
