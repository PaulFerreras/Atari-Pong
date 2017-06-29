package superawesomepingpong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{

	private Model model;
	private View view;
	private Player player1, player2;
	
	public Controller(Model m, View v) {
		model = m;
		view = v;
		
		player1 = model.getPlayer1();
		player2 = model.getPlayer2();
		
		view.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: player1.up_pressed = true; break;
		case KeyEvent.VK_S: player1.down_pressed = true; break;
		case KeyEvent.VK_UP: player2.up_pressed = true; break;
		case KeyEvent.VK_DOWN: player2.down_pressed = true; break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: player1.up_pressed = false; break;
		case KeyEvent.VK_S: player1.down_pressed = false; break;
		case KeyEvent.VK_UP: player2.up_pressed = false; break;
		case KeyEvent.VK_DOWN: player2.down_pressed = false; break;
		case KeyEvent.VK_SPACE: model.start = true; view.start = true; break;
		case KeyEvent.VK_P:
			if (model.start) {
				model.paused = !model.paused; view.paused = !view.paused; 		//Pauses Game
			}
			break;                   
		case KeyEvent.VK_R: if(model.start && !model.paused) model.reset(); break;							//Resets Players and Ball
		}
		
		//Turns on debug mode
		if(e.isControlDown()) {
			if(e.getKeyCode() == KeyEvent.VK_D) {
				view.ball_info = !view.ball_info;								//Turns on Ball Info
			} else if (e.getKeyCode() == KeyEvent.VK_C) {
				player1.corners_visible = !player1.corners_visible;
				player2.corners_visible = !player2.corners_visible;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
