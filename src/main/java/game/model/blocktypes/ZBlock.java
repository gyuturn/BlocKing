package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class ZBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new char[][] { 
			{'O', 'O', ' '},
			{' ', 'O', 'O'}
		};
		color = Color.RED;
    }

    public ZBlock() {
		shape = new char[][] { 
			{'O', 'O', ' '},
			{' ', 'O', 'O'}
		};
		color = Color.RED;
	}
    
}