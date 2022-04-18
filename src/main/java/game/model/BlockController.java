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

		int w = width();
		int h = height();
        
        int[][] newShape = new int[w][h];

        for(int i=0; i<w; i++) {
            for(int j=0; j<h; j++) {
                newShape[i][j] = getShape(h - 1 - j, i);
            }            
        }

		shape = newShape;

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
