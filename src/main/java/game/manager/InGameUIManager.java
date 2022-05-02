package game.manager;

import game.GameDualModeUI;
import game.UserNumber;
import game.manager.gametype.GameManager_BasicMode;
import game.model.BlockController;

import javax.swing.*;
import javax.swing.text.StyledDocument;

public class InGameUIManager {

    UserNumber userNumber = UserNumber.getInstance();
    private BoardManager boardManager;
    private GameManager_BasicMode gameManager_basicMode;
    private int user;



//    private static InGameUIManager instance = new InGameUIManager();
//
//    public static InGameUIManager getInstance() {
//        return instance;
//    }


    private static final int WIDTH = 10;

    public InGameUIManager(BoardManager boardManager, GameManager_BasicMode gameManager_basicMode, int user) {
        this.boardManager = boardManager;
        this.gameManager_basicMode = gameManager_basicMode;
        this.user=user;
    }


    /*
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

        //doc.setCharacterAttributes(0, 1, pane.getStyle("Red"), true);

        pane.setStyledDocument(doc);

        //System.out.println("Log : Draw Board");
    }
    */



    public void drawBoard() {

            JTextPane pane = GameDualModeUI.getInstance().pane[user];
            StyledDocument doc = pane.getStyledDocument();

            //Set Text
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < 22; i++) {
                for (int j = 0; j < 12; j++) {
                    char curText = boardManager.board[i][j];
                    sb.append(curText);
                }
                sb.append('\n');
            }
            pane.setText(sb.toString());


            //Set Color
            int offset;
            String color = "";

            for (int i = 0; i < 22; i++) {
                for (int j = 0; j < 12; j++) {
                    offset = 13 * i + j;
                    color += boardManager.boardColor[i][j];
                    doc.setCharacterAttributes(offset, 1, pane.getStyle(color), true);
                    color = "";
                }
            }



    }



    public void drawNextBlockInfo(BlockController nextBlock) {

            JTextPane pane = GameDualModeUI.getInstance().nextBlockPane[user];
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
                    color += boardManager.nextBlockColor[i][j];
                    doc.setCharacterAttributes(offset, 1, pane.getStyle(color), true);
                    color = "";
                }
            }



    }

    public void drawScore(){
            JTextPane scorePane = GameDualModeUI.getInstance().scorePane[user];
            scorePane.setText("Score :\n" + gameManager_basicMode.score + "\n" + "curSpeed :\n" + gameManager_basicMode.curSpeed);
    }

    public void moveScene(){

    }

}
