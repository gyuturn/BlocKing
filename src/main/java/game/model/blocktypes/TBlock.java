package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class TBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new char[][] { 
			{' ', 'O', ' '},
			{'O', 'O', 'O'}
		};
		color = Color.MAGENTA;
    }

    public TBlock() {
		shape = new char[][] { 
			{' ', 'O', ' '},
			{'O', 'O', 'O'}
		};
		color = Color.MAGENTA;
	}
    
}