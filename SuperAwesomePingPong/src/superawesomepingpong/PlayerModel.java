package superawesomepingpong;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class PlayerModel {

	private int x, y, 

				//Width and Height are Set to 20, 60
				width = 20, 
				height = 60;
	
	public PlayerModel(int x, int mainframe_height) {
		this.x = x;
		
		//Place player halfway down the screen
		y = mainframe_height/2;
	}
	
	public void moveUp() {
		y++;
	}
	
	public void moveDown() {
		y--;
	}
	
	public Shape getShape() {
		return new Rectangle2D.Double(x - (width/2), y - (height/2),
				x + (width/2), y + (height/2));
	}
	
}
