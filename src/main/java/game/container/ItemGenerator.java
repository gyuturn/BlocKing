package game.container;

import game.model.BlockController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemGenerator {

    private static ItemGenerator instance= new ItemGenerator();

    public static ItemGenerator getInstance() {
        return instance;
    }

    public static int block;

    
    public enum ItemType {
        None,
        
        Weight,             //custom
        LineClear,          //L
        Resurrection,       //R
        DoubleBonusChance,  //D
        SmallBlockChance    //S
    }

    public ItemType SelectRandomItem() {
        Random random = new Random();
        int randomNum = random.nextInt(1);
        switch(randomNum) {
            case 0:
                return ItemType.Weight;
            case 1:
                return ItemType.LineClear;
            case 2:
                return ItemType.Resurrection;
            case 3:
                return ItemType.DoubleBonusChance;
            case 4:
                return ItemType.SmallBlockChance;
        }
        return ItemType.SmallBlockChance;
    }


    public void addCharInShape(BlockController curBlock, char itemChar) {

        ArrayList<Integer> row = new ArrayList<Integer>();
        ArrayList<Integer> col = new ArrayList<Integer>();

        //curBlock의 모든 'O'의 인덱스 찾음
        for(int i=0; i<curBlock.height(); i++) {
            for(int j=0; j<curBlock.width(); j++) {
                if(curBlock.getShape(i,j) == 'O') {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        //무작위 'O'인덱스를 고름
        Random random = new Random();
        int randomIndex = random.nextInt(row.size());
       
        curBlock.shape[row.get(randomIndex)][col.get(randomIndex)] = itemChar;
        System.out.println("flag");
        System.out.println(curBlock.shape[row.get(randomIndex)][col.get(randomIndex)]);
        System.out.println(row.get(randomIndex));
        System.out.println(col.get(randomIndex));
    }

    public BlockController setBlockMugechu(BlockController curBlock) {
        //그 블록을 무게추 블록으로 바꿈
        char[][] mugechuBlock;
        mugechuBlock = new char[][]{
                {' ', 'O', 'O', ' '},
                {'O', 'O', 'O', 'O'}
        };
        curBlock.shape = mugechuBlock;
        return curBlock;

    }


    public BlockController setOneBlock(BlockController curBlock, char charactor) {
        //curBlock 가져옴
        curBlock = BlockGenerator.getInstance().blockQueue.poll();
        //그 블록을 한개짜리 블록으로 바꿈
        char[][] dotBlock;
        dotBlock = new char[][]{
                {'O'}
        };
        curBlock.shape = dotBlock;
        BlockGenerator.getInstance().initNewBlockPos(curBlock,0,5);
        return curBlock;
    }

    //다음 아이템이 어떤건지 정하는 함수 - 열거형 Random

}
