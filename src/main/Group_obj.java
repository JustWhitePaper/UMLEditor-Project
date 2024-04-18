package main;

import java.util.ArrayList;
import java.util.List;

public class Group_obj {
	protected List<Draw_obj> group_items = new ArrayList<>();
	
	public Group_obj(List<Draw_obj> g) {
		Canva.group_nums++;
		
		for (Draw_obj s : g) {
			this.group_items.add(s);
			s.group_ids.add(Canva.group_nums);
			s.is_grouped = true;
		}
	}
}

