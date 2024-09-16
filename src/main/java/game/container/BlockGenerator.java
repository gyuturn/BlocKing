package game.container;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import game.manager.GameInfoManager;
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
import game.model.difficulty.RouletteWheel;

public class BlockGenerator {

	public Queue<BlockController> blockQueue = new LinkedList<>(); //앞으로 생성할 블록들

    private static BlockGenerator instance = new BlockGenerator(0);
	private static BlockGenerator instance2 = new BlockGenerator(1);
	private int index;
    

	private BlockGenerator(int index) {
        this.index = index;
    }

    private static ArrayList<BlockGenerator> gameManagerList =
        new ArrayList<BlockGenerator>(Arrays.asList(instance, instance2));
		
	public static BlockGenerator getInstance(int i) {
        return gameManagerList.get(i);
    }

	private GameInfoManager gameInfoManager = GameInfoManager.getInstance();

	public static int block;

	public int getBlock(){
		return block;
	}



	public BlockController getRandomBlock() {
		block = RouletteWheel.GenerateBlockByValue(gameInfoManager.difficulty);
		switch(BlockGenerator.block) {
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

    public void initNewBlockPos(BlockController curBlock, int row, int col, int index)
    {
        BoardManager.getInstance(index).setBlockPos(curBlock, row, col);
    }


    public void addBlock() {
		BlockController newBlock = getRandomBlock();
		blockQueue.add(newBlock);
	}


	public BlockController createBlock(int index) {
		BlockController curBlock = blockQueue.poll();
		initNewBlockPos(curBlock, 0, 5, index);
		return curBlock;
	}

	public void initBlockQueue() {
		blockQueue.clear();
		addBlock();
		addBlock();
	}

	public Queue<BlockController> getBlockQueue() {
		return blockQueue;
	}
}
