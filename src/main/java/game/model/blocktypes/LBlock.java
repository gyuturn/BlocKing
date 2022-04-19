package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class LBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new char[][] { 
			{'O', 'O', 'O'},
			{'O', ' ', ' '}
		};
		color = Color.ORANGE;
    }

    public LBlock() {
		shape = new char[][] { 
			{'O', 'O', 'O'},
			{'O', ' ', ' '}
		};
		color = Color.ORANGE;
	}
    
}