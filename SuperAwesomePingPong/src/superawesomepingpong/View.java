package superawesomepingpong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class View extends JPanel {

	private Model model;
	private int screen_width, screen_height;
	private Player player1, player2;
	private Ball ball;
	public boolean ball_info,
				   start = false, 
				   paused = false;
	
	public View(Model m, int screen_width, int screen_height) {
		setPreferredSize(new Dimension(screen_width, screen_height));
		setBackground(Color.BLACK);
		
		model = m;
		
		this.screen_width = screen_width;
		this.screen_height = screen_height;
		
		player1 = model.getPlayer1();
		player2 = model.getPlayer2();
		
		ball = model.getBall();
		
		setFocusable(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Color.WHITE);
		
		//Draw line (dashed) down middle of screen
		float[] dash_array = {20f};
		g2.setStroke(
				new BasicStroke(5f, 
					BasicStroke.CAP_SQUARE, 
					BasicStroke.JOIN_BEVEL,
					0f, dash_array, 0.0f)
				);
		g2.drawLine(screen_width/2, 0, screen_width/2, screen_height);
		
		//Draw players scores
		g2.setFont(new Font("Agency FB Bold", Font.BOLD, 100));
		g2.drawString(Integer.toString(player1.getScore()), 230, 150);
		g2.drawString(Integer.toString(player2.getScore()), screen_width - 270, 150);
		
		//Draw hints
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Agency FB Bold", Font.BOLD, 25));
		g2.drawString("'R' = Restart     'P' = Pause", screen_width - 350, 30);
		
		player1.drawSprite(g2);
		player2.drawSprite(g2);
		
		if(model.start) {
			ball.drawSprite(g2);
		}
		
		if(ball_info) {
			g2.setColor(Color.RED);
			g2.drawString("Ball Angle: " + Math.toDegrees(ball.angle), 0, 30);
			g2.drawString("Ball Vx: " + ball.vx, 0, 60);
			g2.drawString("Ball Vy: " + ball.vy, 0, 90);
			g2.drawString("Ball x:  " + ball.x, 0, 120);
			g2.drawString("Ball y:  " + ball.y, 0, 150);
		}
		
		if(!model.start) {
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial Black", Font.PLAIN, 40));
			g2.drawString("Press 'SPACE' to begin", (screen_width/2) - 250, (screen_height/2) + 20);
		}
		
		if(paused) {
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial Black", Font.PLAIN, 100));
			g2.drawString("Game Paused", (screen_width/8), (screen_height/2));
			g2.setFont(new Font("Arial Black", Font.PLAIN, 20));
			g2.drawString("(Press 'P' to unpause)", (3*screen_width)/8, screen_height/2 + 50);
		}
	}
	
}
