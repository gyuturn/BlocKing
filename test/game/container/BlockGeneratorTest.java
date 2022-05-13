package game.container;

import game.manager.BoardManager;
import game.manager.GameManager;
import game.manager.gametype.GameManager_BasicMode;
import game.model.BlockController;
import game.model.blocktypes.*;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Queue;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BlockGeneratorTest {
    BoardManager boardManager = BoardManager.getInstance(0);
    BlockGenerator blockGenerator = BlockGenerator.getInstance();
    GameManager gameManager = GameManager_BasicMode.getInstance(0);



    @After
    public void resetQueue(){
        Queue<BlockController> blockQueue = blockGenerator.getBlockQueue();
        while (!blockQueue.isEmpty()) {
            blockQueue.poll();
        }
    }

    @Test
    @DisplayName("랜덤한 숫자 생성 test")//0~6의 범위 생성
    public void getRandomNumber() {
        //given
        blockGenerator.getRandomBlock();
        //when
        int block = blockGenerator.getBlock();
        //then
        boolean test;
        if (block >= 0 & block <= 6) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(true);
    }

    @Test
    @DisplayName("랜덤블록 생성 후 타입 확인 test")
    public void getRandomBlock(){
        //given
        boolean test=false;
        //when
        BlockController randomBlock = blockGenerator.getRandomBlock();
        int blockShape = blockGenerator.getBlock();
        //then
        if(blockShape==0){
            assertInstanceOf(IBlock.class, randomBlock);
        } else if (blockShape == 1) {
            assertInstanceOf(JBlock.class, randomBlock);
        } else if (blockShape == 2) {
            assertInstanceOf(LBlock.class, randomBlock);
        }else if (blockShape == 3) {
            assertInstanceOf(ZBlock.class, randomBlock);
        }else if (blockShape == 4) {
            assertInstanceOf(SBlock.class, randomBlock);
        }else if (blockShape == 5) {
            assertInstanceOf(TBlock.class, randomBlock);
        }else if (blockShape == 6) {
            assertInstanceOf(OBlock.class, randomBlock);
        }
    }

    @Test
    @DisplayName("생성된 블록 queue에 넣음 test")//큐의 사이즈 확인
    public void addBlock(){
        //given
        Queue<BlockController> blockQueue = blockGenerator.getBlockQueue();
        int beforeSize = blockQueue.size();
        //when
        blockGenerator.addBlock();
        //then
        assertThat(blockQueue.size()).isEqualTo(beforeSize+1); // 큐에 넣은 후 사이즈 확인
    }

    @Test
    @DisplayName("처음 시작시에는 블록 두개 queue push test")// 큐의 사이즈 확인
    public void initBlockQueue(){
        //given
        Queue<BlockController> blockQueue = blockGenerator.getBlockQueue();
        int beforeSize = blockQueue.size();
        //when
        blockGenerator.initBlockQueue();
        //then
        assertThat(blockQueue.size()).isEqualTo(beforeSize+2); // 큐에 넣은 후 사이즈 확인
    }

    @Test
    @DisplayName("queue 에 있는 블럭을 가져와 board 생성 test")
    public void createBlock(){
        //given
        blockGenerator.addBlock();
        Queue<BlockController> blockQueue = blockGenerator.getBlockQueue();
        BlockController peekBlock = blockQueue.peek();
        //when
        blockGenerator.createBlock(0);
        //then
        assertThat(peekBlock.posRow).isSameAs(0);
        assertThat(peekBlock.posCol).isSameAs(5);

    }

//    @Test
//    @DisplayName("랜덤한 숫자에 따른 블록 생성(0행 5열에 생성) test")
//    public void getRandomBlock(){
//        //given
//        boolean test=false;
//        //when
//        BlockController block = blockGenerator.getRandomBlock();
//        int blockShape =  blockGenerator.getBlock();
//        BlockController createdBlock = BoardManager.getInstance().setBlockPos(block, 0, 5);
//        //then
//        assertThat(createdBlock.posRow).isEqualTo(0);
//        assertThat(createdBlock.posCol).isEqualTo(5);
//
//        System.out.println("blockShape = " + blockShape);
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < boardManager.board[i].length; j++) {
//                System.out.print(boardManager.board[i][j]);
//            }
//            System.out.println();
//        }
//        //blockI ㅡ모양
//        if(blockShape==0){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', 'O', 'O', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}
//            };
//
//
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                       test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                    if(test==false){
//                        break;
//                    }
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//        //blockJ ㄱ모양
//        else if(blockShape==1){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O','O', ' ', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', ' ', ' ', 'O', ' ', ' ', ' ', 'X'}
//            };
//
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                        test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                }
//                if(test==false){
//                    break;
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//        //blockL ㄱ 반대모양
//        else if(blockShape==2){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O','O', ' ', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', 'O', ' ', ' ', ' ', ' ', ' ', 'X'}
//            };
//
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                        test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                }
//                if(test==false){
//                    break;
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//        //blockZ ㄹ모양
//        else if(blockShape==3){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O',' ', ' ', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', ' ', 'O', 'O', ' ', ' ', ' ', 'X'}
//            };
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//
//
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                        test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                }
//                if(test==false){
//                    break;
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//        //blockS ㄹ반대모양
//        else if(blockShape==4){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', ' ', 'O','O', ' ', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', ' ', ' ', ' ', ' ', 'X'}
//            };
//
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                        test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                }
//                if(test==false){
//                    break;
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//        //blockT ㅗ모양
//        else if(blockShape==5){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', ' ', 'O',' ', ' ', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', 'O', ' ', ' ', ' ', 'X'}
//            };
//
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                        test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                }
//                if(test==false){
//                    break;
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//        //blockZ O모양
//        else if(blockShape==6){
//            char [][] makeBoard = new char [][]{
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O',' ', ' ', ' ', ' ', 'X'}, //1행
//                    {'X', ' ', ' ', ' ', ' ', 'O', 'O', ' ', ' ', ' ', ' ', 'X'}
//            };
//
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < boardManager.board[i].length; j++) {
//                    if(boardManager.board[i][j]==makeBoard[i][j]){
//                        test=true;
//                    }
//                    else{
//                        test=false;
//                        break;
//                    }
//                }
//                if(test==false){
//                    break;
//                }
//            }
//            Assertions.assertThat(test).isEqualTo(true);
//        }
//
//
//
//
//
//    }
}