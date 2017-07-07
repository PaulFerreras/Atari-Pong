package ataripong;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Model {
	
	private int screen_width, screen_height, collision_counter;
	private Player player1, player2, last_scored;
	private Player[] players;
	private Ball ball;
	private boolean start = false, pause = false;
	private Timer timer;
	private Sounds sounds;
	
	public Model(int screen_width, int screen_height) {
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		//Create Players 60 units away from left and right side
		//And halfway down the screen
		player1 = new Player("Player1", 60, screen_height/2);
		player2 = new Player("Player2", screen_width - 60, screen_height/2);
		
		//Create Ball
		ball = new Ball(screen_width, screen_height);
		
		//Create Array of Players
		players = new Player[2];
		players[0] = player1;
		players[1] = player2;
		
		//Create Timer
		//Timer resets ball 1 second after player scores
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ball.reset(last_scored);
			}
		});
		timer.setRepeats(false);
		
		sounds = new Sounds();
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
	
	public boolean isStarted() {
		return start;
	}
	
	public boolean isPaused() {
		return pause;
	}
	
	public void pressedStart() {
		start = true;
	}
	
	public void pressedPause() {
		pause = !pause;
	}
	
	public void update() {
		if(start) {
			if(!pause) {
				
				//Move players
				for(Player p : players) {
					if (!checkTopAndBottomBoundaries(p)) p.move();
				}
				
				//Check if either player scored
				if(ball.getLeft() > screen_width) {
					scorePlayer(player1);             //Player 1 Scores
				} else if(ball.getRight() < 0) {
					scorePlayer(player2);             //Player 2 Scores
				} else {
					
					//Check ball collisions
					if(checkTopAndBottomBoundaries(ball)) {
						ball.setVY(ball.getVY() * -1.0);                                            //Ball bounces off top or bottom boundaries
						sounds.wallBounce();
					}
					checkPlayersAndBallCollision();                                 //Ball bounces off players
					
					//Move ball
					ball.move();
					
				}
			}
		}  
	}
	
	private boolean checkTopAndBottomBoundaries(SpriteInterface s) {
		return s.getVY() < 0.0 && s.getTop() <= 0                                   //Checks top boundary
				|| s.getVY() > 0.0 && s.getBottom() >= screen_height;               //Checks bottom boundary
	}
	
	private void checkPlayersAndBallCollision() {
		for(Player p : players) {
			if(checkCollisionWithBall(p.getShape())) {
				
				sounds.playerBounce();
				collision_counter++;
				if(collision_counter%3 == 0) ball.increaseSpeed();
				
				if(checkCollisionWithBall(p.getTopLeftCorner()) 
						|| checkCollisionWithBall(p.getBottomLeftCorner())) {
					ball.getNewAngleLeft();
					break;                                                             //Ball collides with corner, exit for loop
				} else if(checkCollisionWithBall(p.getTopRightCorner())
						|| checkCollisionWithBall(p.getBottomRightCorner())) {
					ball.getNewAngleRight();                                           //Ball collides with corner, exit for loop
					break;
				}
				
				switch(p.getName()) {
				case "Player1": if(ball.getVX() < 0) ball.setVX(ball.getVX() * -1.0); break;                //Ball bounces right
				case "Player2": if(ball.getVX() > 0) ball.setVX(ball.getVX() * -1.0); break;                //Ball bounces left
				}
			}
		}
	}
	
	//Determines collision btw shape and ball
	private boolean checkCollisionWithBall(Shape s) {
		return s.intersects(ball.getShape().getBounds2D()) 
				|| s.contains(ball.getShape().getBounds2D());
	}
	
	private void scorePlayer(Player p) {
		sounds.score();
		
		p.scored();
		
		//Keep track of who scored last
		last_scored = p;

		//Ball will stop and reset after 1 second
		reset();
	}
	
	public void reset() {
		ball.stop();
		timer.start();
	}
	
}
