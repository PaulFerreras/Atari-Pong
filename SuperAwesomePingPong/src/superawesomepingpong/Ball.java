package superawesomepingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball implements SpriteInterface {

	private int  radius = 8,                  //Radius set to 8
				screen_width,
				screen_height;
	public double x, y, angle, vx, vy;
	private static int SPEED = 10;             //Speed set to 10
	private Color color = Color.WHITE;        //Color set to white
	
	public Ball(int screen_width, int screen_height) {
		//Start ball at middle of screen
		this.x = screen_width/2;
		this.y = screen_height/2;
		
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		getNewAngle();
	}
	
	public void changeAngle(double degrees) {
		angle = Math.toRadians(degrees);
		
		//Set new velocities with new angle
		vx = SPEED * Math.cos(angle);
		vy = SPEED * Math.sin(angle);
	}
	
	public void getNewAngle() {
		angle = (Math.random() * Math.PI * 2);
		
		//Set new velocities with new angle
		vx = SPEED * Math.cos(angle);
		vy = SPEED * Math.sin(angle);
		
		//Prevents ball from starting off to slow
		if(vx < 2.0 && vx > -2.0) {
			getNewAngle();
		}
	}
	
	@Override
	public void move() {
		x += vx;
		y += vy;
	}
	
	@Override
	public Shape getShape() {
		return new Ellipse2D.Double(x - radius, y - radius, radius*2, radius*2);
	}

	@Override
	public void drawSprite(Graphics2D g2) {
		g2.setColor(color);
		g2.fill(getShape());
	}
	
	public double getTop() {
		return y - radius;
	}
	
	public double getBottom() {
		return y + radius;
	}
	
	public double getLeft() {
		return x - radius;
	}
	
	public double getRight() {
		return x + radius;
	}
	
	public void reset() {
		this.x = screen_width/2;
		this.y = screen_height/2;
		
		getNewAngle();
	}

}
