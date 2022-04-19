package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class OBlock extends BlockController{

    public OBlock() {
		shape = new char[][] { 
			{'O', 'O'}, 
			{'O', 'O'}
		};
		color = Color.YELLOW;
		color_colorBlindMode = Color.YELLOW;
	}
    
}