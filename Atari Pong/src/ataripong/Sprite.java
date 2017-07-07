package ataripong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class Sprite implements SpriteInterface {

	private int width, height;
	private double x, y, vx, vy;
	private Color color;
	private Sprite.SpriteView sprite_view;
	
	@Override
	public double getX() {
		return x;
	}
	
	@Override
	public double getY() {
		return y;
	}
	
	@Override
	public void setX(double x) {
		this.x = x;
	}
	
	@Override
	public void setY(double y) {
		this.y = y;
	}
	
	@Override
	public double getVX() {
		return vx;
	}

	@Override
	public double getVY() {
		return vy;
	}
	
	@Override
	public void setVX(double vx) {
		this.vx = vx;
	}
	
	@Override
	public void setVY(double vy) {
		this.vy = vy;
	}
	
	@Override
	public void setWidth(int w) {
		width = w;
	}
	
	@Override
	public void setHeight(int h) {
		height = h;
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
		x += vx;
		y += vy;
	}

	@Override
	abstract public Shape getShape();
	
	@Override
	public Color getColor() {
		return color;
	}
	
	@Override
	public void setColor(Color c) {
		color = c;
	}
	
	//Factory method to only make one sprite view
	@Override
	public Sprite.SpriteView getSpriteView() {
		//Checks to see if sprite view has been made yet
		//If not, make a new sprite view
		if(sprite_view == null) sprite_view = new SpriteView(this);
		
		return sprite_view;
	}
	
	public class SpriteView {
		
		private Sprite sprite;
		
		public SpriteView(Sprite s) {
			sprite = s;
		}

		public void drawSprite(Graphics2D g2) {
			g2.setColor(sprite.getColor());
			g2.fill(sprite.getShape());
		}
		
	}

}
