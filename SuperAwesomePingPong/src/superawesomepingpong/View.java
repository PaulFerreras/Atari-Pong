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
	private Player player1, player2;
	private Ball ball;
	private int screen_width, screen_height;
	public boolean debug_mode;
	
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
		g2.drawString("Press 'SPACE' to start", screen_width/2 - 106, 30);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Agency FB Bold", Font.BOLD, 25));
		g2.drawString("Press 'R' to reset", screen_width/2 - 90, 60);
		
		if(debug_mode) {
			g2.setColor(Color.RED);
			g2.drawString("Ball Angle: " + Math.toDegrees(ball.angle), 800, 30);
			g2.drawString("Ball Vx: " + ball.vx, 800, 60);
			g2.drawString("Ball Vy: " + ball.vy, 800, 90);
			g2.drawString("Ball x:  " + ball.x, 800, 120);
			g2.drawString("Ball y:  " + ball.y, 800, 150);
		}
		
		player1.drawSprite(g2);
		player2.drawSprite(g2);
		
		ball.drawSprite(g2);
	}
	
}
