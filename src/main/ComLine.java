package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ComLine extends BaseLine{
	protected Point start_port;
	protected Point end_port;
	public ComLine(int start, int end, int start_port, int end_port) {
		super(start, end, start_port, end_port);
	}
	
	
	@Override
	protected void draw(Graphics g) {
		this.start_port = new Point(Canva.all_objs.get(super.start_obj).ports.get(super.start_port_num));
		this.end_port = new Point(Canva.all_objs.get(super.end_obj).ports.get(super.end_port_num));
		Point up = new Point(-20, 20);
		Point rotated_up = new Point(super.rotate(up, start_port, end_port));
		Point down = new Point(-20, -20);
		Point rotated_down = new Point(super.rotate(down, start_port, end_port));
		Point mid = new Point(-40, 0);
		Point rotated_mid = new Point(super.rotate(mid, start_port, end_port));
		g.setColor(Color.BLACK);
		g.drawLine(this.start_port.x, this.start_port.y, this.end_port.x, this.end_port.y);
		g.drawLine(this.end_port.x, this.end_port.y, rotated_up.x, rotated_up.y);
		g.drawLine(this.end_port.x, this.end_port.y, rotated_down.x, rotated_down.y);
		g.drawLine(rotated_up.x, rotated_up.y, rotated_mid.x, rotated_mid.y);
		g.drawLine(rotated_down.x, rotated_down.y, rotated_mid.x, rotated_mid.y);
	}
}
