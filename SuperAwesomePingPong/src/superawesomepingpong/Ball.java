package superawesomepingpong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball implements SpriteInterface {

	private int radius = 8,                  //Radius set to 8
				screen_width,
				screen_height;
	public double x, y, angle, vx, vy;
	private static int SPEED = 8;             //Speed set to 8
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
	
	public void getNewAngleRight() {
		getNewAngle();
		
		double angle_degrees = Math.toDegrees(angle);
		
		if(angle_degrees >= 90 && angle_degrees <= 270) {
			getNewAngleRight();
		}
	}
	
	public void getNewAngleLeft() {
		getNewAngle();
		
		double angle_degrees = Math.toDegrees(angle);
		
		if(angle_degrees >= 0 && angle_degrees <= 90 ||
				angle_degrees >= 270 && angle_degrees <= 360) {
			getNewAngleLeft();
		}
	}
	
	@Override
	public double getTop() {
		return y - radius;
	}
	
	@Override
	public double getBottom() {
		return y + radius;
	}
	
	@Override
	public double getLeft() {
		return x - radius;
	}
	
	@Override
	public double getRight() {
		return x + radius;
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
	
	public void stop() {
		x = screen_width/2;
		y = screen_height/2;
		
		vx = 0;
		vy = 0;
	}
	
	public void reset(Player p) {
		x = screen_width/2;
		y = screen_height/2;
		
		SPEED = 8;
		
		if (p != null) {
			switch(p.getID()) {
			case "Player1": getNewAngleLeft(); break;
			case "Player2": getNewAngleRight(); break;
			}
		} else {
			getNewAngle();
		}
	}
	
	public void increaseSpeed() {
		SPEED++;
		
		if(vx < 0) {
			vx = Math.abs(SPEED * Math.cos(angle)) * -1.0;
		} else {
			vx = Math.abs(SPEED * Math.cos(angle));
		}
		
		
		if(vy < 0) {
			vy = Math.abs(SPEED * Math.sin(angle)) * -1.0;
		} else {
			vy = Math.abs(SPEED * Math.sin(angle));
		}
		
	}

	@Override
	public double getVX() {
		return vx;
	}

	@Override
	public double getVY() {
		return vy;
	}

}
