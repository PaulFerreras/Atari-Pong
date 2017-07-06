package superawesomepingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Player extends Sprite{
	
	private String name;
	private int width = 20, height = 100,                   //Width and Height are Set to 20, 100
				corner_width = 5, corner_height = 5,        //Corners set to 5, 5
				score;
	private static int SPEED = 10;                          //Players speed set to 10
	private boolean up_pressed, down_pressed, 				//Public variables, Controller calls on them
				   corners_visible = false;                
	private Color player_color = Color.WHITE,               //Player color set to white
				  corner_color = Color.BLUE;                //Corner color set to blue
	private Sprite.SpriteView player_view;
	
	public Player(String name, int x, int y) {
		this.name = name;
		
		setX(x);
		setY(y);
		
		setWidth(width);
		setHeight(height);
		
		setColor(player_color);
	}
	
	@Override
	public Shape getShape() {
		return new Rectangle2D.Double(getLeft(), getTop(), width, height);
	}
	
	//Uses Anonymous Class
	//To allow overriding of method without needing to subclass
	@Override
	public Sprite.SpriteView getSpriteView() {
		if(player_view == null) player_view = new SpriteView(this) {
			
			//Override drawSprite method to draw corners
			@Override
			public void drawSprite(Graphics2D g2) {
				super.drawSprite(g2);
				
				if(corners_visible) {
					g2.setColor(corner_color);
					g2.fill(getTopLeftCorner());
					g2.fill(getTopRightCorner());
					g2.fill(getBottomLeftCorner());
					g2.fill(getBottomRightCorner());
				}
			}
		};
		
		return player_view;
	}
	
	public String getName() {
		return name;
	}
	
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
	
	public void pressedCornersVisible() {
		corners_visible = !corners_visible;
	}
	
	public void pressedUp() {
		up_pressed = true;
	}
	
	public void releasedUp() {
		up_pressed = false;
	}
	
	public void pressedDown() {
		down_pressed = true;
	}
	
	public void releasedDown() {
		down_pressed = false;
	}
	
	public void update() {
		//If both up and down are pressed, will not move
		//If both up and down are not pressed, will not move
		if(up_pressed == down_pressed) {
			setVY(0.0);                                     //Do nothing
		} else if(up_pressed) {
			setVY(-SPEED);                                  //Move up
		} else {
			setVY(SPEED);                                   //Move down
		}
	}
	
	public void scored() {
		score += 1;
	}
	
	public int getScore() {
		return score;
	}
	
}
