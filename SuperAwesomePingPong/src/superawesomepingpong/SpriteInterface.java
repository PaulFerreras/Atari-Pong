package superawesomepingpong;

import java.awt.Color;
import java.awt.Shape;

public interface SpriteInterface {
	
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public double getVX();
	public double getVY();
	public void setVX(double vx);
	public void setVY(double vy);
	public void setWidth(int w);
	public void setHeight(int h);
	public double getTop();
	public double getBottom();
	public double getLeft();
	public double getRight();
	public void move();
	public Shape getShape();
	public Color getColor();
	public void setColor(Color c);
	public Sprite.SpriteView getSpriteView();
	
}
