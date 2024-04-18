package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class BaseButton extends JButton {
	public BaseButton(String name) {
		super(name);
		this.setSize(100, 100); // not working
		this.addActionListener(e -> onClick());
		
		
	}
	
	private void onClick() {
		this.setBackground(Color.WHITE);
		ModeController.mode = this.getText();
		System.out.printf("Now mode : %s\n", ModeController.mode);
		for (Component c : this.getParent().getComponents()) {
			if (c != this) {
				c.setBackground(new Color(152, 245, 255));
			}
		}
	}
}
