package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class TBlock extends BlockController{

    public TBlock() {
		shape = new char[][] { 
			{' ', 'O', ' '},
			{'O', 'O', 'O'}
		};
		color = Color.MAGENTA;
		color_colorBlindMode = Color.GRAY;
	}
    
}