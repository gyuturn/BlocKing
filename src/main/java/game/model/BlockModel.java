package game.model;

import java.awt.Color;

public abstract class BlockModel {

    public char[][] shape;
    //protected Color color;
    //protected Color color_colorBlindMode;
    public char color_basic; //기본 색
    public char color_current; //현재 사용중인 색깔

    public int posRow = 0;
    public int posCol = 5;
    
    
}
