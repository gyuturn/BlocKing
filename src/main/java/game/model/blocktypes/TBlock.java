package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class TBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new int[][] { 
			{0, 1, 0},
			{1, 1, 1}
		};
		color = Color.MAGENTA;
    }

    public TBlock() {
		shape = new int[][] { 
			{0, 1, 0},
			{1, 1, 1}
		};
		color = Color.MAGENTA;
	}
    
}