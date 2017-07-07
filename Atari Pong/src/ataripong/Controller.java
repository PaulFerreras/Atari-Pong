package ataripong;

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
		case KeyEvent.VK_W: player1.pressedUp(); break;
		case KeyEvent.VK_S: player1.pressedDown(); break;
		case KeyEvent.VK_UP: player2.pressedUp(); break;
		case KeyEvent.VK_DOWN: player2.pressedDown(); break;
		}
		
		updatePlayers();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W: player1.releasedUp(); break;
		case KeyEvent.VK_S: player1.releasedDown(); break;
		case KeyEvent.VK_UP: player2.releasedUp(); break;
		case KeyEvent.VK_DOWN: player2.releasedDown(); break;
		case KeyEvent.VK_SPACE: model.pressedStart(); break;
		case KeyEvent.VK_P: if (model.isStarted()) model.pressedPause(); break;   		          //Pauses Game                
		case KeyEvent.VK_R: if(model.isStarted() && !model.isPaused()) model.reset(); break;	  //Resets Players and Ball
		}
		
		//Debug mode
		if(e.isControlDown()) {
			if(e.getKeyCode() == KeyEvent.VK_D) {
				view.pressedBallInfo();								                  //Turns on Ball Info
			} else if (e.getKeyCode() == KeyEvent.VK_C) {
				player1.pressedCornersVisible();                                                  //Turns
				player2.pressedCornersVisible();                                                  //Corners Visible
			}
		}
		
		updatePlayers();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	private void updatePlayers() {
		player1.update();
		player2.update();
	}
}
