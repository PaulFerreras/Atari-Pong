package superawesomepingpong;

import javax.swing.JFrame;

public class Model {

	private JFrame main_frame;
	private PlayerModel player1_model, player2_model;
	
	public Model(JFrame mf) {
		main_frame = mf;
		
		//Create Players 20 units away from left and right side
		//And halfway down the screen
		player1_model = new PlayerModel(20, mf.getHeight()/2);
		player2_model = new PlayerModel(mf.getWidth() - 20, mf.getHeight());
	}
	
	public PlayerModel getPlayer1() {
		return player1_model;
	}
	
	public PlayerModel getPlayer2() {
		return player2_model;
	}
	
}
