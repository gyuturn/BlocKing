package game.Model.BlockTypes;

import game.Model.BlockController;
import java.awt.Color;

public class LBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new int[][] { 
			{1, 1, 1},
			{1, 0, 0}
		};
		color = Color.ORANGE;
    }

    public LBlock() {
		shape = new int[][] { 
			{1, 1, 1},
			{1, 0, 0}
		};
		color = Color.ORANGE;
	}
    
}