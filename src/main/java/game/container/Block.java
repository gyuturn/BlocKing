package game.container;

import java.util.LinkedList;
import java.util.Queue;

import game.Controller;
import game.UserNumber;
import game.manager.GameInfoManager;
import game.manager.GameManager;
import game.model.BlockController;
import game.model.blocktypes.IBlock;
import game.model.blocktypes.JBlock;
import game.model.blocktypes.LBlock;
import game.model.blocktypes.OBlock;
import game.model.blocktypes.SBlock;
import game.model.blocktypes.TBlock;
import game.model.blocktypes.ZBlock;

import game.manager.BoardManager;

import game.model.difficulty.RouletteWheel;

public class Block {

	public Queue<BlockController> blockQueue = new LinkedList<>(); //앞으로 생성할 블록들


	public Block(BoardManager boardManager) {
		this.boardManager = boardManager;
	}



	private GameInfoManager gameInfoManager = GameInfoManager.getInstance();

	public static int block;

	public int getBlock(){
		return block;
	}

	public BoardManager boardManager;







    
    public BlockController getRandomBlock() {
		block = RouletteWheel.GenerateBlockByValue(gameInfoManager.difficulty);
		switch(Block.block) {
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

    public void initNewBlockPos(BlockController curBlock, int row, int col)
    {
        boardManager.setBlockPos(curBlock, row, col);
    }


    public void addBlock() {
		BlockController newBlock = getRandomBlock();
		blockQueue.add(newBlock);
	}



	public BlockController createBlock(GameManager gameManager) {
		BlockController curBlock = blockQueue.poll();
		initNewBlockPos(curBlock, 0, 5);
		gameManager.curBlock = curBlock;
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
