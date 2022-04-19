package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class JBlock extends BlockController {
    
    @Override
    protected void initModel()
    {
        shape = new char[][] {
				{'O', 'O', 'O'},
				{' ', ' ', 'O'}
		};
		color = Color.CYAN;
    }

    public JBlock() {
		shape = new char[][]{
				{'O', 'O', 'O'},
				{' ', ' ', 'O'}
		};
		color = Color.CYAN;
	}

}
