package superawesomepingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class Player implements SpriteInterface{
	
	private String id;
	//Location X and Y
	private int x, y,
				width = 20, height = 100,                   //Width and Height are Set to 20, 100
				corner_width = 5, corner_height = 5,
				start_x, start_y,
				score;
	private double vx, vy;
	private static int SPEED = 10;                          //Players speed set to 10
	public boolean up_pressed, down_pressed, 				//Public variables, Controller calls on them
				   corners_visible = false;                
	private Color color = Color.WHITE;                      //Color set to white
	
	public Player(String id, int start_x, int start_y) {
		this.id = id;
		
		this.start_x = start_x;
		this.start_y = start_y;
		
		x = start_x;
		y = start_y;
		
		vx = 0.0;
		vy = 0.0;
	}
	
	public String getID() {
		return id;
	}
	
	@Override
	public double getTop() {
		return y - (height/2);
	}
	
	@Override
	public double getBottom() {
		return y + (height/2);
	}
	
	@Override
	public double getLeft() {
		return x - (width/2);
	}
	
	@Override
	public double getRight() {
		return x + (width/2);
	}
	
	@Override
	public void move() {
		//If both up and down are pressed, won't move
		if(!(up_pressed && down_pressed)) {
			if(up_pressed) {
				y -= SPEED;
			} else if(down_pressed) {
				y += SPEED;
			}
		}
	}
	
//	@Override
//	public void move() {
//		y += vy;
//	}
	
	public Shape getTopLeftCorner() {
		return new Rectangle2D.Double(getLeft(), getTop(), corner_width, corner_height);
	}
	
	public Shape getTopRightCorner() {
		return new Rectangle2D.Double(getRight() - corner_width, getTop(), corner_width, corner_height);
	}
	
	public Shape getBottomLeftCorner() {
		return new Rectangle2D.Double(getLeft(), getBottom() - corner_height, corner_width, corner_height);
	}
	
	public Shape getBottomRightCorner() {
		return new Rectangle2D.Double(getRight() - 5, getBottom() - corner_height, corner_width, corner_height);
	}
	
	public Shape[] getCorners() {
		Shape[] corners = {
			getTopLeftCorner(),
			getTopRightCorner(),
			getBottomLeftCorner(),
			getBottomRightCorner(),
		};
		
		return corners;
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle2D.Double(getLeft(), getTop(), width, height);
	}
	
	@Override
	public void drawSprite(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(getShape());

		if(corners_visible) {
			g2.setColor(Color.BLUE);
			g2.fill(getTopLeftCorner());
			g2.fill(getTopRightCorner());
			g2.fill(getBottomLeftCorner());
			g2.fill(getBottomRightCorner());
		}
	}
	
	public void scored() {
		score += 1;
	}
	
	public int getScore() {
		return score;
	}
	
	public void reset() {
		x = start_x;
		y = start_y;
	}

	@Override
	public double getVX() {
		return vx;
	}

	@Override
	public double getVY() {
		return vy;
	}
	
	public void pressedUp() {
		vy = -SPEED;
	}
	
	public void pressedDown() {
		vy = SPEED;
	}
	
	public void releasedUp() {
		vy = 0.0;
	}
	
	public void releasedDown() {
		vy = 0.0;
	}
}
