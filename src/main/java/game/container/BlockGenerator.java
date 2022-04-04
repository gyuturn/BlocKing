package game.container;

import java.util.Random;

import game.model.BlockController;
import game.model.BlockModel;
import game.model.blocktypes.IBlock;
import game.model.blocktypes.JBlock;
import game.model.blocktypes.LBlock;
import game.model.blocktypes.OBlock;
import game.model.blocktypes.SBlock;
import game.model.blocktypes.TBlock;
import game.model.blocktypes.ZBlock;

import game.manager.BoardManager;

public class BlockGenerator {

    private static BlockGenerator instance = new BlockGenerator();
    
    public static BlockGenerator getInstance() {
        return instance;
    }
    
    private BlockController getRandomBlock() {
		Random rnd = new Random(System.currentTimeMillis());
		int block = rnd.nextInt(6);
		switch(block) {
		case 0:
			return new IBlock();
		case 1:
			return new JBlock();
		case 2:
			return new LBlock();
		case 3:
			return new ZBlock();
		case 4:
			return new SBlock();
		case 5:
			return new TBlock();
		case 6:
			return new OBlock();			
		}
		return new LBlock();
	}

    private void initNewBlockPos(BlockController curBlock, int row, int col)
    {
        BoardManager.getInstance().setBlockPos(curBlock, row , col);
    }

    public void createBlock() {
        BlockController newBlock = getRandomBlock();
        initNewBlockPos(newBlock, 0, 5);
    }
    
}
