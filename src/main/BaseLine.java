package main;

import java.awt.Graphics;
import java.awt.Point;

public class BaseLine {
	
	protected int start_obj, end_obj;
	protected int start_port_num, end_port_num; // up 0, down 1, left 2, right 3
	public BaseLine(int start, int end, int start_port, int end_port) {
		this.start_obj = start;
		this.end_obj = end;
		this.start_port_num = start_port;
		this.end_port_num = end_port;
	}
	
	protected void draw(Graphics g) {}
	protected Point rotate(Point pt, Point start, Point end){
        Point vec = new Point(end.x - start.x, end.y-start.y);
        Point ret = new Point();
        double sin = (vec.getY()/vec.distance(0, 0));
        double cos = (vec.getX()/vec.distance(0, 0));
        ret.x = (int) (pt.x * cos - pt.y*sin);
        ret.y = (int) (pt.x * sin + pt.y*cos);
        ret.x+=end.x;
        ret.y+=end.y;
        return ret;
    }
}
