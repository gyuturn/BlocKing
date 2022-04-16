package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class IBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new int[][] {
            {1, 1, 1, 1}
        };
        color = Color.CYAN;
    }

    public IBlock() {
		shape = new int[][] { 
			{1, 1, 1, 1}
		};
		color = Color.CYAN;
	}
}
