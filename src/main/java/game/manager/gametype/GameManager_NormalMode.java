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

public class GameManager_NormalMode extends GameManager {

    private boolean isGameOver = false;
    private boolean isBlockStop = false;
    private static int timeScale = 1000;
    private int blockCount = 0;
    private int lineCount = 0;
    private int score = 0;

    public BlockController curBlock;

    private Timer timer;
    private KeyListener interaction;
    private Step curStep = Step.GameReady;

//#region Singleton

    private static GameManager_NormalMode instance = new GameManager_NormalMode();
    
    public static GameManager_NormalMode getInstance() {
        return instance;
    }

//#endregion

//#region GameFramework

    public enum Step {

	    GameReady,
        StartTimer,
        
        //while !gameOver
        CreatNewBlock,
        BlockMove,
        SetGameBalance,

        GameOver
    }
    
    @Override
    protected void gameFramework() { //전체적인 게임의 동작 흐름
        
        switch(curStep) {
            case GameReady:
                gameReady(); 
                
                curStep = Step.StartTimer; break;
            case StartTimer :
                curStep = Step.CreatNewBlock; break;

            case CreatNewBlock:
                createNewBlock();
                
                curStep = Step.BlockMove; break;
            case BlockMove:
                moveBlock();
                if(isBlockStop) curStep = Step.SetGameBalance; break;

            case SetGameBalance:
                setGameBalance();
                curStep = Step.CreatNewBlock; break;
            case GameOver :
                gameOver(); break;
        }
/*
        gameReady();
        startTimer();

        while(!isGameOver)
        {
            createNewBlock();   //블록 생성
            checkBlockConfirm(); //블록이 바닥에 닿으면 return 합니다.

            setGameBalance(); //게임 진행도에 따라 블럭 속도 조절
        }

        gameOver();
*/
    }

    private void gameReady() {
        //게임을 준비합니다.
        initKeyListener();
        BlockGenerator.getInstance().initBlockQueue();
    }

    public void createNewBlock() {
        BlockGenerator.getInstance().createBlock();
        InGameUIManager.getInstance().drawBoard();
        blockCount++;

        System.out.println("create");
    }

    private void moveBlock() {
        BoardManager.getInstance().translateBlock(curBlock, 1, 0);
        //curBlock
    }

    private void setGameBalance() {
        //일정 수 이상 블록이 삭제되면 떨어지는 속도가 증가합니다.
        if(timeScale < blockCount / 10)
        {
            timeScale--;
        }
        //일정 수 이상 줄이 삭제되면 떨어지는 속도가 증가합니다.
        if(timeScale < lineCount / 10)
        {
            timeScale--;
        }
    }
    
    @Override
    protected void gameOver() {
        //게임이 종료되면 호출됩니다.
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
        //GameFramework를 멈추고 싶은 경우
        //일시정지 등등
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
        interaction = new Interaction();
        GameUI.getInstance().pane.addKeyListener(interaction);
    }

    public class Interaction implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
                BoardManager.getInstance().translateBlock(curBlock, 1, 0);  
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
                
				requestDrawBoard();
				System.out.println("input up");
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}

//#endregion

}
