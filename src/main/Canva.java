package main;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Canva extends JPanel {
	
	public static List<BaseLine> all_lines = new ArrayList<>();
	public static List<Draw_obj> all_objs = new ArrayList<>();
	public static List<Draw_obj> selected_objs = new ArrayList<>();
	public static List<Group_obj> group_objs = new ArrayList<>();
	public static int group_nums = -1;

	private int startX, startY, endX, endY;
	private int start_obj_id, end_obj_id, start_port_id, end_port_id;
	private int rect_width = 200, rect_height = 150, oval_width = 200, oval_height = 150;
	private int all_objs_depth = -1;
	private boolean is_pressed = false, is_selected = false;

	public Canva() {
		this.setBounds(170, 10, 1000, 950);
		this.setBackground(Color.WHITE);

        this.addMouseListener(new MouseAdapter() {
        	
            public void mousePressed(MouseEvent e) {
            	is_pressed = true;
            	selected_objs.clear();
            	startX = e.getX();
        		startY = e.getY();
        		endX = e.getX();
        		endY = e.getY();
            	
            	switch(ModeController.mode) {
            	
            	case("Select") :
            		is_selected = false;

            		for (int i = all_objs.size() - 1; i >= 0; i--) {
            			Draw_obj s = all_objs.get(i);
        	        	if (s.x < startX && s.y < startY && s.x + s.width > startX && s.y + s.height > startY) {
        	        		if (s.is_grouped) {
        	        			
        	        			int last_group_id = s.group_ids.get(s.group_ids.size() - 1);
        	        			for (Draw_obj item : group_objs.get(last_group_id).group_items) {
        	        				selected_objs.add(item);
        	        				item.count_dis(startX, startY);
        	        			}
        	        		}
        	        		else {
        	        			selected_objs.add(s);
            	        		
            	        		// distance of mouse and the object
            	        		s.count_dis(startX, startY);
        	        		}
        	        		is_selected = true;
        	        		break;
        	        	}
        	        } 
            		repaint();
            		break;
            	
            	case("Class") :
            		all_objs_depth++;
            		System.out.printf("Obj No.%d\n", all_objs_depth);
	            	all_objs.add(new Class_(startX, startY, rect_width, rect_height, all_objs_depth));
	                repaint();
	                break;
	                
            	case("Use Case") :
            		all_objs_depth++;
            		System.out.printf("Obj No.%d\n", all_objs_depth);
	            	all_objs.add(new UseCase(startX, startY, oval_width, oval_height, all_objs_depth));
	                repaint();
            		break;
            		
            	case("Association Line") :
            		is_selected = false;
            		for (int i = all_objs.size() - 1; i >= 0; i--) {
            			Draw_obj s = all_objs.get(i);
            			if (s.x < startX && s.y < startY && s.x + s.width > startX && s.y + s.height > startY) {
            				is_selected = true;
            				start_obj_id = s.depth;
            				
            				int smallest_dis = 2000, t = -1;
            				for (Point p : s.ports) {
            					t++;
            					if (p.distance(startX, startY) < smallest_dis) {
            						smallest_dis = (int) p.distance(startX, startY);
            						start_port_id = t;
            					}
            				}
            				break;
            			}
            		}
            		startX = all_objs.get(start_obj_id).ports.get(start_port_id).x;
            		startY = all_objs.get(start_obj_id).ports.get(start_port_id).y;
            		repaint();
            		break;
            		
            	case("Generalization Line") :
            		is_selected = false;
            		for (int i = all_objs.size() - 1; i >= 0; i--) {
            			Draw_obj s = all_objs.get(i);
            			if (s.x < startX && s.y < startY && s.x + s.width > startX && s.y + s.height > startY) {
            				is_selected = true;
            				start_obj_id = s.depth;
            				
            				int smallest_dis = 2000, t = -1;
            				for (Point p : s.ports) {
            					t++;
            					if (p.distance(startX, startY) < smallest_dis) {
            						smallest_dis = (int) p.distance(startX, startY);
            						start_port_id = t;
            					}
            				}
            				break;
            			}
            		}
            		startX = all_objs.get(start_obj_id).ports.get(start_port_id).x;
            		startY = all_objs.get(start_obj_id).ports.get(start_port_id).y;
            		repaint();
            		break;
            		
            	case("Composition Line") :
	        		is_selected = false;
	        		for (int i = all_objs.size() - 1; i >= 0; i--) {
	        			Draw_obj s = all_objs.get(i);
	        			if (s.x < startX && s.y < startY && s.x + s.width > startX && s.y + s.height > startY) {
	        				is_selected = true;
	        				start_obj_id = s.depth;
	        				
	        				int smallest_dis = 2000, t = -1;
	        				for (Point p : s.ports) {
	        					t++;
	        					if (p.distance(startX, startY) < smallest_dis) {
	        						smallest_dis = (int) p.distance(startX, startY);
	        						start_port_id = t;
	        					}
	        				}
	        				break;
	        			}
	        		}
	        		startX = all_objs.get(start_obj_id).ports.get(start_port_id).x;
	        		startY = all_objs.get(start_obj_id).ports.get(start_port_id).y;
	        		repaint();
	        		break;
            	}
            	
            	
            }

            public void mouseReleased(MouseEvent e) {
            	is_pressed = false;
        		endX = e.getX();
        		endY = e.getY();
        		
            	switch(ModeController.mode) {
            	
            	case("Select") :
            		if (!is_selected) {
            			int width = endX - startX;
        	            int height = endY - startY;
        	            int tmpStartX = width > 0 ? startX : endX;
        	            int tmpStartY = height > 0 ? startY : endY;
                		
                		for (Draw_obj s : all_objs) {
                			if (s.x > tmpStartX && s.y > tmpStartY && s.x + s.width < tmpStartX + Math.abs(width) && s.y + s.height < tmpStartY + Math.abs(height)) {
                				if (s.is_grouped) {
            	        			
                					int last_group_id = s.group_ids.get(s.group_ids.size() - 1);
            	        			for (Draw_obj item : group_objs.get(last_group_id).group_items) {
            	        				selected_objs.add(item);
            	        			}
            	        		}
                				else {
                					selected_objs.add(s);
                				}
                			}
                		}
            		}
            		repaint();
	                break;
	                
            	case("Class") :
	                break;
            	
            	case("Use Case") :
            		break;
            	
            	case("Association Line") :
            		if (is_selected) {
            			for (int i = all_objs.size() - 1; i >= 0; i--) {
                			Draw_obj s = all_objs.get(i);
                			if (s.x < endX && s.y < endY && s.x + s.width > endX && s.y + s.height > endY) {
                				end_obj_id = s.depth;
                				
                				int smallest_dis = 2000, t = -1;
                				for (Point p : s.ports) {
                					t++;
                					if (p.distance(endX, endY) < smallest_dis) {
                						smallest_dis = (int) p.distance(endX, endY);
                						end_port_id = t;
                					}
                				}
                				all_lines.add(new AssLine(start_obj_id, end_obj_id, start_port_id, end_port_id));
                				break;
                			}
            			}
            		}
            		repaint();
            		break;
            		
            	case("Generalization Line") :
            		if (is_selected) {
            			for (int i = all_objs.size() - 1; i >= 0; i--) {
                			Draw_obj s = all_objs.get(i);
                			if (s.x < endX && s.y < endY && s.x + s.width > endX && s.y + s.height > endY) {
                				end_obj_id = s.depth;
                				
                				int smallest_dis = 2000, t = -1;
                				for (Point p : s.ports) {
                					t++;
                					if (p.distance(endX, endY) < smallest_dis) {
                						smallest_dis = (int) p.distance(endX, endY);
                						end_port_id = t;
                					}
                				}
                				all_lines.add(new GenLine(start_obj_id, end_obj_id, start_port_id, end_port_id));
                				break;
                			}
            			}
            		}
            		repaint();
            		break;
            		
            	case("Composition Line") :
            		if (is_selected) {
            			for (int i = all_objs.size() - 1; i >= 0; i--) {
                			Draw_obj s = all_objs.get(i);
                			if (s.x < endX && s.y < endY && s.x + s.width > endX && s.y + s.height > endY) {
                				end_obj_id = s.depth;
                				
                				int smallest_dis = 2000, t = -1;
                				for (Point p : s.ports) {
                					t++;
                					if (p.distance(endX, endY) < smallest_dis) {
                						smallest_dis = (int) p.distance(endX, endY);
                						end_port_id = t;
                					}
                				}
                				all_lines.add(new ComLine(start_obj_id, end_obj_id, start_port_id, end_port_id));
                				break;
                			}
            			}
            		}
            		repaint();
            		break;
            	}
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
            	endX = e.getX();
        		endY = e.getY();
        		
            	switch(ModeController.mode) {
            	
            	case("Select") :
            		repaint();
            		break;
            		
            	case("Class"):
            		break;
            	
            	case("Use Case") :
            		break;
            	
            	case("Association Line") :
            		repaint();
            		break;
            		
            	case("Generalization Line") :
            		repaint();
            		break;
            		
            	case("Composition Line") :
            		repaint();
            		break;
            	}
            }
        });
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		switch(ModeController.mode) {
		
		case("Select") :
			if (is_selected) {
				for (Draw_obj s : selected_objs) {
					s.move(endX + s.disX, endY + s.disY);
				}
			}
			else if (is_pressed) {
				g.setColor(Color.BLACK);
				
	            int width = endX - startX;
	            int height = endY - startY;
	            int tmpStartX = width > 0 ? startX : endX;
	            int tmpStartY = height > 0 ? startY : endY;
	            
	            g.drawRect(tmpStartX, tmpStartY, Math.abs(width), Math.abs(height));
			}
			break;
		
		case("Class") :
	        break;
		
		case("Use case") :
	        break;
		
		case("Association Line") :
			if (is_pressed && is_selected) {
				g.setColor(Color.BLACK);
	        	g.drawLine(startX, startY, endX, endY);
			}
    		break;
    		
		case("Generalization Line") :
			if (is_pressed && is_selected) {
				g.setColor(Color.BLACK);
	        	g.drawLine(startX, startY, endX, endY);
			}
    		break;
    		
		case("Composition Line") :
			if (is_pressed && is_selected) {
				g.setColor(Color.BLACK);
	        	g.drawLine(startX, startY, endX, endY);
			}
    		break;
		}
		
		for (Draw_obj s : all_objs) {
        	s.draw(g);
        }
		for (Draw_obj s : selected_objs) {
			s.selected_draw(g);
		}
		for (BaseLine l : all_lines) {
			l.draw(g);
		}
	}
}
