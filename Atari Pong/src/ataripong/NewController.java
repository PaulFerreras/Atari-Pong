package ataripong;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class NewController {

	private Model model;
	private View view;
	private Player player1, player2;
	private AbstractAction p1_pressed_up_action,
					   p2_pressed_up_action,
					   p1_pressed_down_action,
					   p2_pressed_down_action,
					   p1_released_up_action,
					   p2_released_up_action,
					   p1_released_down_action,
					   p2_released_down_action,
					   ball_info_action,
					   start_action,
					   pause_action;
	
	final static int PRESSED = 0,
					 RELEASED = 1,
					 UP = 0,
					 DOWN = 1;
	
	public NewController(Model m, View v) {
		model = m;
		view = v;
		
		player1 = model.getPlayer1();
		player2 = model.getPlayer2();
		
		createActions();
		bindKeys();
	}
	
	private void createActions() {
		p1_pressed_up_action = new MoveAction(player1, PRESSED, UP);
		p2_pressed_up_action = new MoveAction(player2, PRESSED, UP);
		p1_pressed_down_action = new MoveAction(player1, PRESSED, DOWN);
		p2_pressed_down_action = new MoveAction(player2, PRESSED, DOWN);
		p1_released_up_action = new MoveAction(player1, RELEASED, UP);
		p2_released_up_action = new MoveAction(player2, RELEASED, UP);
		p1_released_down_action = new MoveAction(player1, RELEASED, DOWN);
		p2_released_down_action = new MoveAction(player2, RELEASED, DOWN);
		
		//Debug Actions
		ball_info_action = new DebugAction("Ball Info");
		start_action = new DebugAction("Start");
		pause_action = new DebugAction("Pause");
		
	}
	
	private void bindKeys() {
		view.putInput("UP", "Player1 Move Up");
		view.putInput("W", "Player2 Move Up");
		view.putInput("DOWN", "Player1 Move Down");
		view.putInput("S", "Player2 Move Down");
		view.putInput("released UP", "Player1 released Up");
		view.putInput("released W", "Player2 released Up");
		view.putInput("released DOWN", "Player1 released Down");
		view.putInput("released S", "Player2 released Down");
		view.putAction("Player1 Move Up", p1_pressed_up_action);
		view.putAction("Player2 Move Up", p2_pressed_up_action);
		view.putAction("Player1 Move Down", p1_pressed_down_action);
		view.putAction("Player2 Move Down", p2_pressed_down_action);
		view.putAction("Player1 released Up", p1_released_up_action);
		view.putAction("Player1 released Down", p2_released_up_action);
		view.putAction("Player2 released Up", p1_released_down_action);
		view.putAction("Player2 released Down", p2_released_down_action);
	}
	
	private void updatePlayers() {
		player1.update();
		player2.update();
	}
	
	private class MoveAction extends AbstractAction {

		private Player player;
		private int button_status, direction;
		
		private MoveAction(Player p, int button_status, int direction) {
			player = p;
			this.button_status = button_status;
			this.direction = direction;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(button_status) {
			case PRESSED:
				switch(direction) {
				case UP: player.pressedUp(); break;
				case DOWN: player.pressedDown(); break;
				}
				break;
			case RELEASED:
				switch(direction) {
				case UP: player.releasedUp(); break;
				case DOWN: player.releasedDown(); break;
				}
				break;
			}
			
		}
		
	}
	
	private class DebugAction extends AbstractAction {
		
		private String debug;
		
		private DebugAction (String debug) {
			this.debug = debug;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			switch(debug) {
			case "Ball Info": view.pressedBallInfo(); break;
			case "Start": model.pressedStart(); break;
			case "Pause": model.pressedPause(); break;
			}
		}
	}

}
