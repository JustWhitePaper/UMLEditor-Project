package main;

import javax.swing.JMenuItem;

public class UngroupButton  extends JMenuItem{
	public UngroupButton() {
		super("Ungroup");
		this.addActionListener(e -> onClick());
	}
	
	private void onClick() {
		if (Canva.selected_objs.size() != 0) {
			boolean flag = true;
			int compare = Canva.selected_objs.get(0).group_ids.get(Canva.selected_objs.get(0).group_ids.size()-1);
			
			for (Draw_obj s : Canva.selected_objs) {
				if (s.group_ids.get(s.group_ids.size()-1) != compare) {
					flag = false;
					break;
				};
			}
			
			if (flag) {
				for (Draw_obj s : Canva.selected_objs) {
					int tmp_last_group_id = s.group_ids.size()-1;
					s.group_ids.remove(tmp_last_group_id);
					if (tmp_last_group_id == 0) {
						s.is_grouped = false;
					}
				}
			}
		}
	}
}
