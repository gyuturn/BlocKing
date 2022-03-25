package game.Model.BlockTypes;

import game.Model.BlockController;
import java.awt.Color;

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