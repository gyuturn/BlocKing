package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class LBlock extends BlockController{

    public LBlock() {
		shape = new char[][] { 
			{'O', 'O', 'O'},
			{'O', ' ', ' '}
		};
		color = Color.ORANGE;
		color_colorBlindMode = Color.ORANGE;
	}
    
}