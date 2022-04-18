package game.manager.gametype;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import game.GameUI;
import game.container.BlockGenerator;
import game.manager.BoardManager;
import game.manager.GameManager;
import game.manager.InGameUIManager;
import game.model.BlockController;
import javafx.scene.layout.Background;

public class GameManager_NormalMode extends GameManager {

    private boolean isBlockMovable = true;
    private boolean isGameOver = false;
    
    private static int maxSpeed = 100000;
    private static int basicSpeed = 100;
    private static int curSpeed = 100;
    private static int timeScale = 1000;
    
    private int blockCount = 0;
    private int lineCount = 0;
    private int score = 0;

    

    private Timer timer;
    private KeyListener interaction_play;
    private KeyListener interaction_utils;
    private Step curStep = Step.GameReady;

    public BlockController curBlock;
    public BlockController getCurBlock() {
        return curBlock;
    }

//#region Singleton

    private static GameManager_NormalMode instance = new GameManager_NormalMode();
    
    public static GameManager_NormalMode getInstance() {
        return instance;
    }

//#endregion

//#region GameFramework

    public enum Step {

	    GameReady,
        
        //while !gameOver
        CreatNewBlock,
        BlockMove,
        CheckLineDelete,
        SetGameBalance,

        GameOver
    }
    
    @Override
    protected void gameFramework() { //전체적인 게임의 동작 흐름
        
        switch(curStep) {
            case GameReady:
                curStep = gameReady(); 
                break;

            case CreatNewBlock:
                curStep = createNewBlock();
                break;

            case BlockMove:
                curStep = blockMove();
                if(curStep != Step.BlockMove) gameFramework();
                break;
            
            case CheckLineDelete:
                curStep = checkLineDelete();
                gameFramework();
                break;

            case SetGameBalance:
                curStep = setGameBalance();
                gameFramework();
                break;

            case GameOver :
                gameOver();
                break;
        }
    }

    private Step gameReady() {
        //게임을 준비합니다.
        initKeyListener();
        BlockGenerator.getInstance().initBlockQueue();

        return Step.CreatNewBlock;
    }

    public Step createNewBlock() {
        BlockGenerator.getInstance().addBlock();
        BlockGenerator.getInstance().createBlock();
        blockCount++;

        return Step.BlockMove;
    }

    private Step blockMove() {
        isBlockMovable = BoardManager.getInstance().checkBlockMovable(curBlock);
        if(isBlockMovable) {
            BoardManager.getInstance().translateBlock(curBlock, 1, 0);
            return Step.BlockMove;
        }
        else
            return Step.CheckLineDelete;
    }

    private Step checkLineDelete() {
        int curLineCount = BoardManager.getInstance().eraseFullLine();
        lineCount += curLineCount;
        score += curLineCount * curSpeed;

        System.out.printf("\n");
        System.out.printf("score : %d \n", score);
        System.out.printf("curSpeed : %d\n\n", curSpeed);

        return Step.SetGameBalance;
    }

    private Step setGameBalance() {
        curSpeed = basicSpeed + lineCount + blockCount;
        timeScale = maxSpeed / curSpeed;
        setTimeScale(timeScale);

        return Step.CreatNewBlock;
    }
    
    @Override
    protected void gameOver() {
        //게임이 종료되면 호출됩니다.
        timer.stop();
    }

    public void startGameFramework() {

        timer = new Timer(timeScale, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				oneFrame();
			}
		});

        timer.start();
    }
    
    @Override
    public void stopGameFramework() {
        timer.stop();
        GameUI.getInstance().pane.removeKeyListener(interaction_play);
    }

    public void restartGameFramework() {
        timer.restart();
        GameUI.getInstance().pane.addKeyListener(interaction_play);
    }
    
    
//#endregion

//#region OneFrame

    @Override
    protected void oneFrame() { //매 프레임마다 진행되는 동작
        gameFramework();
        requestDrawBoard();
    }

    private void requestDrawBoard() {
        InGameUIManager.getInstance().drawBoard();
    }

//#endregion

//#region Utils

    private void setTimeScale(int scale) {

        timer.stop();
        
        timeScale = scale;

        timer = new Timer(timeScale, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				oneFrame();
			}
		});

        timer.start();
    }

//#endregion

//#region Interaction

    public void initKeyListener() {
        interaction_play = new Interaction_Play();
        interaction_utils = new Interaction_Utils();
        GameUI.getInstance().pane.addKeyListener(interaction_play);
        GameUI.getInstance().pane.addKeyListener(interaction_utils);
    }

    public class Interaction_Play implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
                blockMove();
                requestDrawBoard();
				System.out.println("input down");
				break;
			case KeyEvent.VK_RIGHT:
                BoardManager.getInstance().translateBlock(curBlock, 0, 1);
                requestDrawBoard();
				System.out.println("input right");
				break;
			case KeyEvent.VK_LEFT:
                BoardManager.getInstance().translateBlock(curBlock, 0, -1);
				requestDrawBoard();
				System.out.println("input left");
				break;
			case KeyEvent.VK_UP:
                BoardManager.getInstance().eraseBlock(curBlock);
                curBlock.rotate();
                if(!BoardManager.getInstance().checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                    curBlock.rotate();
                    curBlock.rotate();
                    curBlock.rotate();
                }
                BoardManager.getInstance().setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
				requestDrawBoard();
				System.out.println("input up");
				break;
            case KeyEvent.VK_SPACE:
                while(BoardManager.getInstance().checkBlockMovable(curBlock)) {
                    BoardManager.getInstance().translateBlock(curBlock, 1, 0);
                }
                timer.restart();
                requestDrawBoard();
                break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

    public class Interaction_Utils implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
            case KeyEvent.VK_T:
                BoardManager.getInstance().printBoard();
                if(timer.isRunning())
                    stopGameFramework();
                else
                    restartGameFramework();
                break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

//#endregion

}
