package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class SBlock extends BlockController{

    public SBlock() {
		shape = new char[][] { 
			{' ', 'O', 'O'},
			{'O', 'O', ' '}
		};
		color_basic = 'G';
		color_blindMode = 'B';

		color_current = 'G';
	}
    
}