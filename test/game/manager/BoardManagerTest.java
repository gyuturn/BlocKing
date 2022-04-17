package game.manager;

import game.container.BlockGenerator;
import game.model.BlockController;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Queue;

import static org.junit.Assert.*;

public class BoardManagerTest {
    //dependencies
    BoardManager boardManager = BoardManager.getInstance();
    BlockGenerator blockGenerator = BlockGenerator.getInstance();

    @After
    public void resetBoard(){
        boardManager.board=new char [][] {
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

    }
    @After
    public void resetQueue(){
        Queue<BlockController> blockQueue = blockGenerator.getBlockQueue();
        while (!blockQueue.isEmpty()) {
            blockQueue.poll();
        }
    }


    @Test
    @DisplayName("보드판 생성 test(20행 10열)")//실제는 22행 12열 이지만 각 끝의 xx도 포함하여 22행 12열임
    public void createBoard() {
        //given
         //boardManager 생성
        //when
        int row = boardManager.board.length;
        int col = boardManager.board[0].length;
        for (int i = 0; i < boardManager.board.length; i++) {
            col = boardManager.board[i].length;
            if (col != 12) {
                col = -1;
            }
        }
        //then
        Assertions.assertThat(row).isEqualTo(22);
        Assertions.assertThat(col).isEqualTo(12);
    }

    @Test
    @DisplayName("생성된 블록 화면에 적용 test") //실제 화면과도 맞는지 비교 (0행 5열에 생성)
    public void setBlockPos() {
        //given
        boolean test = false;
        blockGenerator.addBlock();//임의의 블록 생성
        int blockShape = blockGenerator.getBlock();
        Queue<BlockController> blockQueue = blockGenerator.getBlockQueue();
        //when
        BlockController block = blockQueue.poll();
        BlockController createdBlock = BoardManager.getInstance().setBlockPos(block, 0, 5);
        //then
        //blockI ㅡ모양
        if(blockShape==0){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', 'O', 'O', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}
            };


            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {
                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                    if(test==false){
                        break;
                    }
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }
        //blockJ ㄱ모양
        else if(blockShape==1){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O','O', ' ', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'X'}
            };

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {
                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                }
                if(test==false){
                    break;
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }
        //blockL ㄱ 반대모양
        else if(blockShape==2){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O','O', ' ', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', 'O', ' ', ' ', ' ', ' ', ' ', 'X'}
            };

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {
                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                }
                if(test==false){
                    break;
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }
        //blockZ ㄹ모양
        else if(blockShape==3){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O',' ', ' ', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', ' ', 'O', 'O', ' ', ' ', ' ', 'X'}
            };
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {


                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                }
                if(test==false){
                    break;
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }
        //blockS ㄹ반대모양
        else if(blockShape==4){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', ' ', 'O','O', ' ', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', ' ', ' ', ' ', ' ', 'X'}
            };

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {
                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                }
                if(test==false){
                    break;
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }
        //blockT ㅗ모양
        else if(blockShape==5){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', ' ', 'O',' ', ' ', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' ', 'X'}
            };

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {
                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                }
                if(test==false){
                    break;
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }
        //blockZ O모양
        else if(blockShape==6){
            char [][] makeBoard = new char [][]{
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O',' ', ' ', ' ', ' ', 'X'}, //1행
                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', ' ', ' ', ' ', ' ', 'X'}
            };

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < boardManager.board[i].length; j++) {
                    if(boardManager.board[i][j]==makeBoard[i][j]){
                        test=true;
                    }
                    else{
                        test=false;
                        break;
                    }
                }
                if(test==false){
                    break;
                }
            }
            Assertions.assertThat(test).isEqualTo(true);
        }




    }

    @Test
    @DisplayName("생성되었던 블록이 erase 되는지 test")// board값이 O->' '으로 번경
    public void eraseBlock(){
        //given
        blockGenerator.addBlock();
        BlockController block = blockGenerator.createBlock();//board에 그려진 상태 0행 5열에 생성되어 있음
        //when
        boardManager.eraseBlock(block);
        //then
        boolean flag=true;
        for (int i = 0; i < boardManager.board.length; i++) {
            for (int j = 0; j < boardManager.board[i].length; j++) {
                if(boardManager.board[i][j]=='O')
                    flag=false;
            }
        }
        Assertions.assertThat(flag).isEqualTo(true);
    }

    @Test
    @DisplayName("생성되어 있었던 Block row,col Translate")
    public void translateBlock(){
        //given
        blockGenerator.addBlock();
        BlockController block = blockGenerator.createBlock();//board에 그려진 상태 0행 5열에 생성되어 있음
        int beforeCol = block.posCol;
        int beforeRow = block.posRow;
        //when
        int addRow=0;
        int addCol=1;// 오른쪽으로 한칸 이동
        boardManager.translateBlock(block,addRow,addCol);
        //then
        Assertions.assertThat(block.posCol).isEqualTo(beforeCol+addCol);
        Assertions.assertThat(block.posRow).isEqualTo(beforeRow+addRow);
    }


    //checkMovableTest
    //eraseLineTest

}