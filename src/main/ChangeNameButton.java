package main;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ChangeNameButton extends JMenuItem{
	public ChangeNameButton() {
		super("Change Object Name");
		this.addActionListener(e -> onClick());
	}
	
	private void onClick() {
		JOptionPane optionPane = new JOptionPane();
        String new_name =  optionPane.showInputDialog(this, "input name");
        if(new_name != null ){
            Canva.selected_objs.get(0).name = new_name;
            repaint();
        }
	}
}
