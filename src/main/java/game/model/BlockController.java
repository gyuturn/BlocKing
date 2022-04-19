package game.model;

import java.awt.Color;

public abstract class BlockController extends BlockModel {

    public char getShape(int row, int col) {
		return shape[row][col];
	}
	
	public Color getColor() {
		return color;
	}

	public Color getColor_blindMode() {
		return color_colorBlindMode;
	}
	
	public void rotate() {

		int w = width();
		int h = height();
        
        char[][] newShape = new char[w][h];

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

}
