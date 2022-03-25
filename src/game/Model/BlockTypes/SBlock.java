package game.Model.BlockTypes;

import game.Model.BlockController;
import java.awt.Color;

public class SBlock extends BlockController{

    @Override
    protected void initModel()
    {
        shape = new int[][] { 
			{0, 1, 1},
			{1, 1, 0}
		};
		color = Color.GREEN;
    }

    public SBlock() {
		shape = new int[][] { 
			{0, 1, 1},
			{1, 1, 0}
		};
		color = Color.GREEN;
	}
    
}