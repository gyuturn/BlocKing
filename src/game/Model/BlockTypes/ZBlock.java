package game.Model.BlockTypes;

import game.Model.BlockController;
import java.awt.Color;

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