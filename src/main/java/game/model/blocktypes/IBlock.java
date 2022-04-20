package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class IBlock extends BlockController{

    public IBlock() {
		shape = new char[][] {
            {'O', 'O', 'O', 'O'}
        };
        //color = Color.WHITE;
        //color_colorBlindMode = Color.WHITE;
        color_basic = 'W';
        color_blindMode = 'W';

        color_current = 'W';
	}
}
