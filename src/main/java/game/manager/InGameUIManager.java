package game.manager;

import game.GameUI;
import game.manager.gametype.GameManager_BasicMode;
import game.manager.gametype.GameManager_ItemMode;

import game.model.BlockController;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class InGameUIManager {

    private static InGameUIManager instance = new InGameUIManager();
    public static InGameUIManager getInstance() { return instance; }
    private static final int WIDTH = 10;

    /*
    public void drawBoard() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<22; i++)
        {
            for(int j=0; j<12; j++)
            {
                char curText = BoardManager.getInstance(index).board[i][j];
                sb.append(curText);
            }
            sb.append('\n');
        }

        JTextPane pane = GameUI.getInstance().pane;
        pane.setText(sb.toString());
        StyledDocument doc = pane.getStyledDocument();
        //doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);

        //doc.setCharacterAttributes(0, 1, pane.getStyle("Red"), true);

        pane.setStyledDocument(doc);

        //System.out.println("Log : Draw Board");
    }
    */

    public void drawBoard(int index) {

        JTextPane pane = GameUI.getInstance().pane[index];
        StyledDocument doc = pane.getStyledDocument();

        //Set Text
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<22; i++)
        {
            for(int j=0; j<12; j++)
            {
                char curText = BoardManager.getInstance(index).board[i][j];
                sb.append(curText);
            }
            sb.append('\n');
        }
        pane.setText(sb.toString());

        //Set Color
        int offset;
        String color ="";

        for(int i=0; i<22; i++)
        {
            for(int j=0; j<12; j++)
            {
                offset = 13 * i + j;
                color += BoardManager.getInstance(index).boardColor[i][j];
                doc.setCharacterAttributes(offset, 1, pane.getStyle(color), true);
                color = "";
            }
        }
    }

    public void drawAttackBoard(int index) {





        JTextPane attackPane = GameUI.getInstance().attackPane[index];
        StyledDocument doc = attackPane.getStyledDocument();
        attackPane.setForeground(Color.blue);

        if(index == 0){
            index = 1;
        }
        else if(index == 1){
            index = 0;
        }

        //Set Text
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                char curText = BoardManager.getInstance(index).attackBoard[i][j];
                sb.append(curText);
            }
            sb.append('\n');
        }
        attackPane.setText(sb.toString());


        System.out.println(sb);







    }


    public void drawNextBlockInfo(BlockController nextBlock, int index) {
        JTextPane pane = GameUI.getInstance().nextBlockPane[index];
        StyledDocument doc = pane.getStyledDocument();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i< nextBlock.height(); i++)
        {
            for(int j=0; j< nextBlock.width(); j++)
            {
                char curText = nextBlock.shape[i][j];
                sb.append(curText);
            }
            sb.append('\n');
        }

        pane.setText(sb.toString());

        int offset;
        String color ="";

        for(int i=0; i<nextBlock.height(); i++)
        {
            for(int j=0; j<nextBlock.width(); j++)
            {
                offset = (nextBlock.width()+1) * i + j;
                color += BoardManager.getInstance(index).nextBlockColor[i][j];
                doc.setCharacterAttributes(offset, 1, pane.getStyle(color), true);
                //System.out.println("color : " + color); //Debug
                color = "";
            }
        }

    }

    public void drawScore(int index){
        JTextPane scorePane = GameUI.getInstance().scorePane[index];

        if(GameInfoManager.getInstance().mode == GameInfoManager.GameMode.BasicMode) {
            scorePane.setText("Score :\n" + GameManager_BasicMode.getInstance(index).score + "\n" + "curSpeed :\n" + GameManager_BasicMode.getInstance(index).curSpeed);
        } else if (GameInfoManager.getInstance().mode == GameInfoManager.GameMode.ItemMode) {
            scorePane.setText("Score :\n" + GameManager_ItemMode.getInstance(index).score + "\n" + "curSpeed :\n" + GameManager_ItemMode.getInstance(index).curSpeed);
        }
    }

    public void moveScene(){

    }

}
