package main;

import javax.swing.JMenuItem;

public class GroupButton extends JMenuItem{
	public GroupButton() {
		super("Group");
		this.addActionListener(e -> onClick());
	}
	
	private void onClick() {
		if (Canva.selected_objs.size() != 0) {
			Group_obj group = new Group_obj(Canva.selected_objs);
			Canva.group_objs.add(group);
		}
	}
}
