package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UseCase extends Draw_obj{

	public UseCase(int x, int y, int width, int height, int depth) {
		super(x, y, width, height, depth);
		super.name = "Use Case";
	}
	
	@Override
	protected void draw(Graphics g) {
		g.setColor(new Color(166, 255, 166));
        g.fillOval(super.x, super.y, super.width, super.height);
		g.setColor(Color.BLACK);
        g.drawOval(super.x, super.y, super.width, super.height);
        
        Font foo = new Font(Font.DIALOG, Font.BOLD, 16);

        int stringWidth = g.getFontMetrics(foo).stringWidth(super.name);
        double dx = (width - stringWidth)/2;
        g.setFont(foo);    
        g.drawString(super.name, super.x + (int)dx, super.y + 25);
        
	}
}
