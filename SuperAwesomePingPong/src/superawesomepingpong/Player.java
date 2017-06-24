package superawesomepingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class Player implements SpriteInterface{
	
	//Location X and Y
	private int x, y,
				width = 20, height = 100,                   //Width and Height are Set to 20, 100
				start_x, start_y,
				score;
	private static int SPEED = 10;                          //Players speed set to 10
	public boolean up_pressed, down_pressed;                //Public variables, Controller calls on them
	private Color color = Color.WHITE;                      //Color set to white
	
	public Player(int start_x, int start_y) {
		this.start_x = start_x;
		this.start_y = start_y;
		
		x = start_x;
		y = start_y;
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
	
	@Override
	public Shape getShape() {
		return new Rectangle2D.Double(x - (width/2), y - (height/2), width, height);
	}
	
	@Override
	public void drawSprite(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(getShape());
	}
	
	public int getTop() {
		return y - (height/2);
	}
	
	public int getBottom() {
		return y + (height/2);
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
}
