package game.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import game.Controller;
import game.UserNumber;
import game.model.BlockController;
import game.model.BlockModel;

import javax.swing.*;

public class BoardManager {
    Controller[] controller=Controller.getInstance();
    UserNumber userNumber = UserNumber.getInstance();

//#region Singleton



//#endregion

    public InGameUIManager inGameUIManager;
//#region Board 

    //board에 문자를 입력하여 현태 상태 표시
    // 'X' : 테두리
    // 'O' :
    public char[][] board;
    public char[][] boardColor;
    public char[][] nextBlock;
    public char[][] nextBlockColor;
    int m =5; int n=6;


    public BoardManager() {
        board = new char [][] {
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //1행
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //10행
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
            {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //20행
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        boardColor = new char [][] {
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}, //1행
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}, //10행
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}, //20행
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
            {'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'},
        };

        nextBlock = new char [][]{
                {' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' '}
        };

        nextBlockColor = new char[][]{
                {'B', 'B', 'B', 'B'},
                {'B', 'B', 'B', 'B'}
        };
    }

    public BoardManager(InGameUIManager inGameUIManager) {
        this.inGameUIManager = inGameUIManager;
    }

//#endregion

//#region Board Controll
    public BlockController setBlockPos(BlockController curBlock, int targetRow, int targetCol) {
        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
                if(curBlock.getShape(i, j) == 'O') {
                    this.board[targetRow+i][targetCol+j] = 'O';
                    this.boardColor[targetRow+i][targetCol+j] = curBlock.getColor();
                }

            }
        }

        curBlock.posRow = targetRow;
        curBlock.posCol = targetCol;

        return curBlock;
    }

    public void initBoard(){
        for(int i=0; i<20; i++){
            eraseSelectRow(i);
        }
    }



    public void eraseSelectRow(int row){
        char[] emptyLine = {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'};

        this.board[row] = emptyLine.clone();
    }

    public void eraseHalfBoard(){
        for(int i =0; i<10; i++){
            eraseSelectRow(i);
        }
    }

    public void eraseUnderBlock(BlockController curBlock){
        for(int i = 0; i<curBlock.width(); i++){
            this.board[curBlock.posRow + curBlock.height()][curBlock.posCol + i] = ' ';
        }
    }

    public void eraseBlock(BlockController curBlock) {
        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
                if(curBlock.getShape(i, j) == 'O')
                    this.board[curBlock.posRow+i][curBlock.posCol+j] = ' ';
            }
        }
    }

    public void translateBlock(BlockController curBlock, int row, int col) {
        eraseBlock(curBlock);
        if(checkDrawable(curBlock.shape, curBlock.posRow+row, curBlock.posCol+col))
            setBlockPos(curBlock, curBlock.posRow+row, curBlock.posCol+col);
        else
            setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
    }

    public int eraseFullLine() {
        int lineCount = 0;
        char[] checker = {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'};

        for(int i=0; i<22; i++) {
            if(Arrays.equals(board[i], checker)) {
                for(int j=i; j>0; j--)
                {
                    board[j] = board[j-1].clone();
                }

                lineCount++;
            }
        }

        return lineCount;
    }

    public void eraseEvent(){
        char[] checker = {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'};
        int clear[] = new int[22];
        int clearsum = 0;
        m=5; n=6;
        Arrays.fill(clear,0);
        for(int i=0; i<22; i++) {
            if(Arrays.equals(board[i], checker)) {
                clear[i]=1;
            }
        }
        for(int i=0; i<22; i++) {
            clearsum += clear[i];
        }

        if(clearsum > 0) {
            Timer timers = new Timer(1, new ActionListener()
            {


                int phase = 1;
                public void actionPerformed (ActionEvent e)

                {

                    switch (phase){
                        case 1:  for(int i=0; i<22; i++) {
                            if(clear[i]==1) {
                                board[i][1] = '┏';
                                for(int j=2; j<11; j++)
                                {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 2:  for(int i=0; i<22; i++) {
                            if(clear[i]==1) {
                                board[i][1] = ' ';
                                board[i][2] = '─';
                                for(int j=3; j<11; j++)
                                {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 3:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                board[i][1] = ' ';
                                board[i][2] = ' ';
                                board[i][3] = '─';
                                for (int j = 4; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 4:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 4; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][4] = '─';
                                for (int j = 5; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 5:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 5; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][5] = '─';
                                for (int j = 6; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 6:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 6; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][6] = '─';
                                for (int j = 7; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 7:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 7; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][7] = '─';
                                for (int j = 8; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 8:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 8; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][8] = '─';
                                for (int j = 9; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 9:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 9; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][9] = '─';
                                board[i][10] = ' ';
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 10: for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 10; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][10] = '┓';
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 11: for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 10; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][10] = '┛';
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 12: for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 9; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][9] = '_';
                                board[i][10] = ' ';
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 13:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 8; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][8] = '_';
                                for (int j = 9; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 14:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 7; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][7] = '_';
                                for (int j = 8; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 15:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 6; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][6] = '_';
                                for (int j = 7; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 16:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 5; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][5] = '_';
                                for (int j = 6; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 17:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                for (int j = 1; j < 4; j++) {
                                    board[i][j] = ' ';
                                }
                                board[i][4] = '_';
                                for (int j = 5; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 18:  for(int i=0; i<22; i++) {
                            if (clear[i] == 1) {
                                board[i][1] = ' ';
                                board[i][2] = ' ';
                                board[i][3] = '_';
                                for (int j = 4; j < 11; j++) {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 19:  for(int i=0; i<22; i++) {
                            if(clear[i]==1) {
                                board[i][1] = ' ';
                                board[i][2] = '_';
                                for(int j=3; j<11; j++)
                                {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 20:  for(int i=0; i<22; i++) {
                            if(clear[i]==1) {
                                board[i][1] = '┗';
                                for(int j=2; j<11; j++)
                                {
                                    board[i][j] = ' ';
                                }
                            }
                        }
                            inGameUIManager.drawBoard();
                            phase++;
                            break;
                        case 21: for(int i=0; i<22; i++) {
                            if(clear[i]==1) {
                                board[i][m] = '*';
                                board[i][n] = '*';
                            }
                        }
                            if(m>0) {
                                m--; n++;
                            }
                            inGameUIManager.drawBoard();
                            System.out.println("블록 제거중" + m);
                            if(m ==0){
                                ((Timer)e.getSource()).stop();
                                for(int i=0; i<22; i++) {
                                    if(clear[i]==1) {
                                        board[i][0] = 'X';
                                        for(int j=1; j<11; j++)
                                        {
                                            board[i][j] = 'O';
                                        }
                                        board[i][11] = 'X';
                                    }
                                }



                                System.out.println("블록 제거 완료" + m);
                            }
                            break;


                    }





                }

            });
            timers.start();


        }


    }




//#endregion

//#region checkDrawable

    public boolean checkDrawable(char[][] targetShape, int targetRow, int targetCol) {

        int height = targetShape.length;
        int width = 0;

        if(height > 0)
            width = targetShape[0].length;

        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                if(targetShape[i][j] == 'O' && board[targetRow+i][targetCol+j] != ' ')
                    return false;
            }
        }

        return true;
    }

    public boolean checkBlockMovable(BlockController curBlock) {
        int[] indexOfBottom = new int[curBlock.width()];

        for(int i=0; i<curBlock.width(); i++) {
            for(int j=0; j<curBlock.height(); j++) {
                if(curBlock.shape[j][i] == 'O')
                    indexOfBottom[i] = j;
            }
        }

        for(int i=0; i<curBlock.width(); i++) {
            if(board[curBlock.posRow + indexOfBottom[i] + 1][curBlock.posCol + i] != ' ') {
                return false;
            }
        }

        return true;
    }

//    public boolean checkLeftSide(BlockController curBlock) {
//        int[] indexOfLeft = new int[curBlock.height()];
//
//        for(int i=0; i<curBlock.height(); i++) {
//            for(int j=0; j<curBlock.width(); j++) {
//                if(curBlock.shape[i][j] == 'O') {
//                    indexOfLeft[i] = j;
//                    break;
//                }
//            }
//        }
//
//        for(int i=0; i<curBlock.height(); i++) {
//            if(board[curBlock.posRow + i][curBlock.posCol + indexOfLeft[i] - 1] != ' ') {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public boolean checkRightSide(BlockController curBlock) {
//        int[] indexOfRight = new int[curBlock.height()];
//
//        for(int i=0; i<curBlock.height(); i++) {
//            for(int j=0; j<curBlock.width(); j++) {
//                if(curBlock.shape[i][j] == 'O') {
//                    indexOfRight[i] = j;
//                }
//            }
//        }
//
//        for(int i=0; i<curBlock.height(); i++) {
//            if(board[curBlock.posRow + i][curBlock.posCol + indexOfRight[i] + 1] != ' ') {
//                return false;
//            }
//        }
//
//        return true;
//    }
//#endregion

//#region Debug
    public void printBoard() {
        for(int i=0; i<22; i++)
        {
            for(int j=0; j<12; j++)
            {
                char curText = this.board[i][j];
                if(curText == 0)
                    System.out.print(" ");
                else
                    System.out.print(curText);
            }
            System.out.print("\n");
        }
    }
//#endregion
}

