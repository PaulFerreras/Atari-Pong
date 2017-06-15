package superawesomepingpong;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerView {

	public PlayerModel player_model;
	
	//Color of players are set to white
	public Color color = Color.WHITE;
	
	public PlayerView(PlayerModel p) {
		player_model = p;
	}
	
}
