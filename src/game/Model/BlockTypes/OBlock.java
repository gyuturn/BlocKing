package game.Model.BlockTypes;

import game.Model.BlockController;
import java.awt.Color;

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