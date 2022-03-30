package game.model.blocktypes;

import java.awt.Color;

import game.model.BlockController;

public class OBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new int[][] { 
			{1, 1}, 
			{1, 1}
		};
		color = Color.YELLOW;
    }

    public OBlock() {
		shape = new int[][] { 
			{1, 1}, 
			{1, 1}
		};
		color = Color.YELLOW;
	}
    
}