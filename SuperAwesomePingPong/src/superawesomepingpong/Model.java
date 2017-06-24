package superawesomepingpong;

import java.awt.Dimension;

public class Model {
	
	private int screen_width, screen_height;
	private Player player1, player2;
	private Ball ball;
	
	public Model(int screen_width, int screen_height) {
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		//Create Players 60 units away from left and right side
		//And halfway down the screen
		player1 = new Player(60, screen_height/2);
		player2 = new Player(screen_width - 60, screen_height/2);
		
		//Create Ball
		ball = new Ball(screen_width, screen_height);
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public void update() {
		//Player 1 & 2 checks top and bottom of screen
		if(checkPlayerBoundaries(player1)) {
			player1.move();
		}
		
		if(checkPlayerBoundaries(player2)) {
			player2.move();
		}
		
		//Ball bounces on top and bottom of screen
		if(ball.vy < 0 && ball.getTop() < 0 || 
				ball.vy > 0 && ball.getBottom() > screen_height) {
			ball.vy *= -1.0;
		}
		
		if(player1.getShape().intersects(ball.getShape().getBounds2D())) {  //Ball bounces off of player 1
			if(ball.vx < 0) ball.vx *= -1.0;
		}
		
		if(player2.getShape().intersects(ball.getShape().getBounds2D())) {  //Ball bounces off of player 2
			if(ball.vx > 0) ball.vx *= -1.0;
		}
		
		if(ball.getLeft() > screen_width) {
			scorePlayer(player1);             //Player 1 Scores
		}
		
		if(ball.getRight() < 0) {
			scorePlayer(player2);             //Player 2 Scores
		}
		
//		System.out.println("ball: " + ball.getRight());
//		System.out.println("ball.vx: " + ball.vx);
//		System.out.println("screen: " + 0);
		
		ball.move();
	}
	
	public boolean checkPlayerBoundaries(Player p) {
		return p.up_pressed && p.getTop() > 0 || p.down_pressed && p.getBottom() < screen_height;
	}
	
	public void scorePlayer(Player p) {
		p.scored();
		reset();
	}
	
	public void reset() {
		player1.reset();
		player2.reset();
		ball.reset();
	}
	
}
