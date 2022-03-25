package game.Model.BlockTypes;

import game.Model.BlockController;
import java.awt.Color;

public class JBlock extends BlockController {
    
    @Override
    protected void initModel()
    {
        shape = new int[][] { 
			{1, 1, 1, 1}
		};
		color = Color.CYAN;
    }

    public JBlock() {
		shape = new int[][] { 
			{1, 1, 1, 1}
		};
		color = Color.CYAN;
	}

}
