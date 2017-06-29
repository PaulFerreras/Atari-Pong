package superawesomepingpong;

import java.awt.Graphics2D;
import java.awt.Shape;

public interface SpriteInterface {

	public double getTop();
	public double getBottom();
	public double getLeft();
	public double getRight();
	public double getVX();
	public double getVY();
	public void move();
	public Shape getShape();
	public void drawSprite(Graphics2D g2);
	
}
