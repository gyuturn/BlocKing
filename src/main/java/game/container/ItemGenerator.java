package game.container;

import game.Controller;
import game.UserNumber;
import game.model.BlockController;

import java.util.Arrays;

public class ItemGenerator {

    private static ItemGenerator instance= new ItemGenerator(); //

    public static ItemGenerator getInstance() {
        return instance;
    }

    public static int block;

    Controller[] controller=Controller.getInstance();
    UserNumber userNumber = UserNumber.getInstance();



    public void addCharInShape(BlockController curBlock, char charactor) {
        curBlock = controller[0].block.blockQueue.poll();
        int[] arr = new int [5];
        for(int i=0;i<5;i++){
            //if()
            arr[i]= Arrays.asList(curBlock.shape).indexOf('O');
        }
        //curBlock의 모든 'O'의 인덱스 찾음
        //그중 무작위 위치의 문자를 'O'에서 해당 문자로 바꿔줌
        //return curBlock
    }

    public BlockController setBlockMugechu(BlockController curBlock, char charactor) {
        //curBlock 가져옴
        curBlock = controller[0].block.blockQueue.poll();
        //그 블록을 무게추 블록으로 바꿈
        char[][] mugechuBlock;
        mugechuBlock = new char[][]{
                {' ', 'O', 'O', ' '},
                {'O', 'O', 'O', 'O'}
        };
        curBlock.shape = mugechuBlock;
        controller[0].block.initNewBlockPos(curBlock,0,5);
        return curBlock;

    }


    public BlockController setOneBlock(BlockController curBlock, char charactor) {
        //curBlock 가져옴
        curBlock = controller[0].block.blockQueue.poll();
        //그 블록을 한개짜리 블록으로 바꿈
        char[][] dotBlock;
        dotBlock = new char[][]{
                {'O'}
        };
        curBlock.shape = dotBlock;
        controller[0].block.initNewBlockPos(curBlock,0,5);
        return curBlock;
    }

}
