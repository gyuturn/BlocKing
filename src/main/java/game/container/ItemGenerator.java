package game.container;

import game.manager.BoardManager;
import game.manager.gametype.GameManager_NormalMode;
import game.model.BlockController;
import game.model.blocktypes.*;
import jdk.nashorn.internal.ir.Block;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ItemGenerator {

    private static ItemGenerator instance= new ItemGenerator(); //

    public static ItemGenerator getInstance() {
        return instance;
    }

    public static int block;

    public void addCharInShape(BlockController curBlock, char charactor) {


        //curBlock의 모든 'O'의 인덱스 찾음
        //그중 무작위 위치의 문자를 'O'에서 해당 문자로 바꿔줌
        //return curBlock
    }

//    public void setBlockMugechu(BlockController curBlock, char charactor) {
//        //curBlock 가져옴
//        curBlock = BlockGenerator.getInstance().blockQueue.poll();
//        //그 블록을 무게추 블록으로 바꿈
//        char[][] mugechuBlock;
//        mugechuBlock = new char[][]{
//                {' ', 'O', 'O', ' '},
//                {'O', 'O', 'O', 'O'}
//        };
//        curBlock.shape = mugechuBlock;
//        BlockGenerator.getInstance().initNewBlockPos(curBlock,0,5);
//    }


//    public void setOneBlock(BlockController curBlock, char charactor) {
//        //curBlock 가져옴
//        curBlock = BlockGenerator.getInstance().blockQueue.poll();
//        //그 블록을 한개짜리 블록으로 바꿈
//        char[][] dotBlock = new char[1][1];
//        dotBlock[0][0]='O';
//        curBlock.shape = dotBlock;
//        BlockGenerator.getInstance().initNewBlockPos(curBlock,0,5);
//
//    }

}
