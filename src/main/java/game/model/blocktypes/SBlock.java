package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class SBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new char[][] { 
			{' ', 'O', 'O'},
			{'O', 'O', ' '}
		};
		color = Color.GREEN;
    }

    public SBlock() {
		shape = new char[][] { 
			{' ', 'O', 'O'},
			{'O', 'O', ' '}
		};
		color = Color.GREEN;
	}
    
}