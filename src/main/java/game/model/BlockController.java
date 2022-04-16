package game.model;

import java.awt.Color;

public abstract class BlockController extends BlockModel {

    public int getShape(int row, int col) {
		return shape[row][col];
	}
	
	public Color getColor() {
		return color;
	}
	
	public void rotate() {
        
        int[][] newShape = new int[width()][height()];

        for(int i=0; i<width(); i++)
        {
            for(int j=0; j<height(); j++)
            {
                newShape[i][j] = getShape(j, height()-1-i); 
            }

            shape = newShape;
        }

    }
	
	public int height() {
		return shape.length;
	}
	
	public int width() {
		if(shape.length > 0)
			return shape[0].length;
		return 0;
	}

    protected abstract void initModel();

}
