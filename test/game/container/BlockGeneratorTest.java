package game.container;

import game.manager.BoardManager;
import game.model.BlockController;
import game.model.blocktypes.IBlock;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class BlockGeneratorTest {
    BoardManager boardManager = BoardManager.getInstance();

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

    @Test
    @DisplayName("랜덤한 숫자 생성 test")//0~6의 범위 생성
    public void getRandomNumber() {
        //given
        BlockGenerator instance = BlockGenerator.getInstance();
        //when
        instance.createBlock();
        int block = instance.getBlock();
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
    @DisplayName("랜덤한 숫자에 따른 블록 생성(0행 5열에 생성) test")
    public void generateBlock(){
        //given
        boolean test=false;
        BlockGenerator instance = BlockGenerator.getInstance();
        BoardManager boardManager = BoardManager.getInstance();
        //when
        BlockController block = instance.createBlock();
        int blockShape =  instance.getBlock();
//
        BlockController createdBlock = BoardManager.getInstance().setBlockPos(block, 0, 5);
        //then
        assertThat(createdBlock.posRow).isEqualTo(0);
        assertThat(createdBlock.posCol).isEqualTo(5);

        System.out.println("blockShape = " + blockShape);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < boardManager.board[i].length; j++) {
                System.out.print(boardManager.board[i][j]);
            }
            System.out.println();
        }
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
}