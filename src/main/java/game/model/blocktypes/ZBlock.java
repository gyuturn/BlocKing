package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class ZBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new int[][] { 
			{1, 1, 0},
			{0, 1, 1}
		};
		color = Color.RED;
    }

    public ZBlock() {
		shape = new int[][] { 
			{1, 1, 0},
			{0, 1, 1}
		};
		color = Color.RED;
	}
    
}