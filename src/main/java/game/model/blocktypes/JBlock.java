package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class JBlock extends BlockController {
    

    public JBlock() {
		shape = new char[][]{
				{'O', 'O', 'O'},
				{' ', ' ', 'O'}
		};
		//color = Color.CYAN;
		//color_colorBlindMode = Color.CYAN;

		color_basic = 'C';
		color_blindMode = 'C';

		color_current = 'C';
	}

}
