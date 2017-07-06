package superawesomepingpong;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Ball extends Sprite {

	private int radius = 8,                  //Radius set to 8
				screen_width,
				screen_height;
	public double angle;
	private static int SPEED = 8;             //Speed set to 8
	private Color color = Color.WHITE;        //Color set to white
	
	public Ball(int screen_width, int screen_height) {
		//Start ball at middle of screen
		setX(screen_width/2);
		setY(screen_height/2);
		
		setWidth(radius * 2);
		setHeight(radius * 2);
		
		setColor(color);
		
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		getNewAngle();
	}
	
	public void getNewAngle() {
		angle = (Math.random() * Math.PI * 2);
		
		//Set new velocities with new angle
		setVX(SPEED * Math.cos(angle));
		setVY(SPEED * Math.sin(angle));
		
		//Prevents ball from starting off to slow
		if(getVX() < 2.0 && getVX() > -2.0) {
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
	public Shape getShape() {
		return new Ellipse2D.Double(getLeft(), getTop(), radius*2, radius*2);
	}
	
	public void stop() {
		setX(screen_width/2);
		setY(screen_height/2);
		
		setVX(0);
		setVY(0);
	}
	
	public void reset(Player p) {
		SPEED = 8;
		
		if (p != null) {
			switch(p.getName()) {
			case "Player1": getNewAngleLeft(); break;
			case "Player2": getNewAngleRight(); break;
			}
		} else {
			getNewAngle();
		}
	}
	
	public void increaseSpeed() {
		SPEED++;
		
		if(getVX() < 0) {
			setVX(Math.abs(SPEED * Math.cos(angle)) * -1.0);
		} else {
			setVX(Math.abs(SPEED * Math.cos(angle)));
		}
		
		
		if(getVY() < 0) {
			setVY(Math.abs(SPEED * Math.sin(angle)) * -1.0);
		} else {
			setVY(Math.abs(SPEED * Math.sin(angle)));
		}
		
	}

}
