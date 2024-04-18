package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Class_ extends Draw_obj{
	public Class_(int x, int y, int width, int height, int depth) {
		super(x, y, width, height, depth);
		super.name = "Class";
	}
	
	@Override
	protected void draw(Graphics g) {
		g.setColor(Color.GRAY);
        g.fillRect(super.x, super.y, super.width, super.height);
        g.setColor(Color.BLACK);
        g.drawRect(super.x, super.y, super.width, super.height);
        g.drawLine(super.x, super.y + super.height/3, super.x + super.width, super.y + super.height/3);
        g.drawLine(super.x, super.y + super.height/3*2, super.x + super.width, super.y + super.height/3*2);
        
        Font foo = new Font(Font.DIALOG, Font.BOLD, 16);

        int stringWidth = g.getFontMetrics(foo).stringWidth(super.name);
        double dx = (width - stringWidth)/2;
        g.setFont(foo);    
        g.drawString(super.name, super.x + (int)dx, super.y + 25);
	}
	
}
