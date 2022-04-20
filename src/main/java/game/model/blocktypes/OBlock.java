package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class OBlock extends BlockController{

    public OBlock() {
		shape = new char[][] { 
			{'O', 'O'}, 
			{'O', 'O'}
		};
		color_basic = 'Y';
		color_blindMode = 'Y';

		color_current = 'Y';
	}
    
}