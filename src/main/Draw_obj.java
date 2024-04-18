package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Draw_obj{
	protected int x, y, width, height, depth;
	protected int disX, disY;
	protected String name;
	protected List<Point> ports = new ArrayList<>();
	protected List<Integer> group_ids = new ArrayList<>();
	protected boolean is_grouped = false;
	
	public Draw_obj(int x, int y, int width, int height, int depth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.depth = depth;
		this.ports.add(new Point(x + width/2, y)); // up
		this.ports.add(new Point(x + width/2, y + height)); // down
		this.ports.add(new Point(x, y + height/2)); // left
		this.ports.add(new Point(x + width, y + height/2)); // right
	}
	
	protected void draw(Graphics g) {}
	protected void move(int moveX, int moveY) {
		this.x = moveX;
		this.y = moveY;
		ports.set(0, new Point(x + width/2, y));
		ports.set(1, new Point(x + width/2, y + height));
		ports.set(2, new Point(x, y + height/2));
		ports.set(3, new Point(x + width, y + height/2));
	}
	protected void count_dis(int startX, int startY) {
		this.disX = this.x - startX;
		this.disY = this.y - startY;
	}
	protected void selected_draw(Graphics g) {
		int port_len = 5;
		g.setColor(Color.BLACK);
		g.fillRect(x + width/2 - port_len, y - port_len, 10, 10);  // up
		g.fillRect(x + width/2 - port_len, y + height - port_len, 10, 10);  // down
		g.fillRect(x - port_len, y + height/2 - port_len, 10, 10);  // left
		g.fillRect(x - port_len + width, y + height/2 - port_len, 10, 10);  // right
	}
}
