package game.manager;

import game.GameUI;
import game.manager.gametype.GameManager_NormalMode;
import game.model.BlockController;

import javax.swing.*;
import javax.swing.text.StyledDocument;

public class InGameUIManager {

    private static InGameUIManager instance = new InGameUIManager();
    public static InGameUIManager getInstance() { return instance; }
    private static final int WIDTH = 10;

    public void drawBoard() {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<22; i++)
        {
            for(int j=0; j<12; j++)
            {
                char curText = BoardManager.getInstance().board[i][j];
                sb.append(curText);
            }
            sb.append('\n');
        }

        JTextPane pane = GameUI.getInstance().pane;
        pane.setText(sb.toString());
        StyledDocument doc = pane.getStyledDocument();
        //doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        pane.setStyledDocument(doc);

        //System.out.println("Log : Draw Board");
    }

    public void drawNextBlockInfo(BlockController nextBlock) {
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

        JTextPane pane = GameUI.getInstance().nextBlockPane;
        pane.setText(sb.toString());
        StyledDocument doc = pane.getStyledDocument();
        //doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        pane.setStyledDocument(doc);

    }

    public void drawScore(){
        JTextPane scorePane = GameUI.getInstance().scorePane;
        scorePane.setText("Score :\n" + GameManager_NormalMode.getInstance().score + "\n" + "curSpeed :\n" + GameManager_NormalMode.getInstance().curSpeed);
    }

    public void moveScene(){

    }

}
