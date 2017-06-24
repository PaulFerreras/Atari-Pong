package superawesomepingpong;

import java.awt.Graphics2D;
import java.awt.Shape;

public interface SpriteInterface {

	public Shape getShape();
	public void drawSprite(Graphics2D g2);
	public void move();
	
}
