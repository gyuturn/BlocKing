package game.model;

import java.awt.Color;

public abstract class BlockModel {

    public int[][] shape;
    protected Color color;

    public int posRow = 0;
    public int posCol = 5;
    
    /* 필요한가? 확인할것
    public BlockModel() {
        shape = new int[][]{ 
            {1, 1}, 
            {1, 1}
        };
        color = Color.YELLOW;
    }
    */
}
