package game.manager;

import java.util.Arrays;

import game.model.BlockController;
import game.model.BlockModel;

public class BoardManager {

//#region Singleton
    
    private static BoardManager instance = new BoardManager();

    public static BoardManager getInstance() {
        return instance;
    }
    
//#endregion

//#region Board 

    //board에 문자를 입력하여 현태 상태 표시
    // 'X' : 테두리
    // 'O' :
    public char[][] board;
    public char[][] boardColor;

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
    }

//#endregion

//#region Board Controll
    public BlockController setBlockPos(BlockController curBlock, int targetRow, int targetCol) {
        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
				board[targetRow+i][targetCol+j] = curBlock.getShape(i, j) ;
            }
        }
        
        curBlock.posRow = targetRow;
        curBlock.posCol = targetCol;

        return curBlock;
    }
    
    public void eraseBlock(BlockController curBlock) {
        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
                if(curBlock.getShape(i, j) == 'O')
                    board[curBlock.posRow+i][curBlock.posCol+j] = ' ';
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
                if(curBlock.shape[j][i] == 1)
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

    public boolean checkLeftSide(BlockController curBlock) {
        int[] indexOfLeft = new int[curBlock.height()];

        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
                if(curBlock.shape[i][j] == 1) {
                    indexOfLeft[i] = j;
                    break;
                }
            }
        }

        for(int i=0; i<curBlock.height(); i++) {
            if(board[curBlock.posRow + i][curBlock.posCol + indexOfLeft[i] - 1] != ' ') {
                return false;
            }
        }

        return true;
    }

    public boolean checkRightSide(BlockController curBlock) {
        int[] indexOfRight = new int[curBlock.height()];

        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
                if(curBlock.shape[i][j] == 1) {
                    indexOfRight[i] = j;
                }
            }
        }

        for(int i=0; i<curBlock.height(); i++) {
            if(board[curBlock.posRow + i][curBlock.posCol + indexOfRight[i] + 1] != ' ') {
                return false;
            }
        }

        return true;
    }
//#endregion

//#region Debug
    public void printBoard() {
        for(int i=0; i<22; i++)
        {
            for(int j=0; j<12; j++)
            {
                char curText = BoardManager.getInstance().board[i][j];
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

