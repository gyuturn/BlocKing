package game.Manager.GameType;

import game.Manager.GameManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.Model.BlockController;

public class GameManager_NormalMode extends GameManager {

    private boolean isGameOver = false;
    private boolean isBlockStop = false;
    private static int timeScale = 1000;
    private int blockCount = 0;
    private int lineCount = 0;
    private int score = 0;

    private BlockController curBlock;

    Timer timer;
    private KeyListener interaction;

    //#region Singleton

    private static GameManager_NormalMode instance = new GameManager_NormalMode();
    
    public GameManager_NormalMode getInstance() {
        return instance;
    }

    //#endregion

    //#region GameFramework

    
    @Override
    protected void gameFramework() { //전체적인 게임의 동작 흐름
        gameReady();
        startTimer();

        while(!isGameOver)
        {
            createNewBlock();   //블록 생성
            checkBlockConfirm(); //블록이 바닥에 닿으면 return 합니다.

            setGameBalance(); //게임 진행도에 따라 블럭 속도 조절
        }

        gameOver();
    }


    private void gameReady() {
        //게임을 준비합니다.
    }

    private void startTimer() {

        timer = new Timer(timeScale, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				oneFrame();
			}
		});

        timer.start();
    }

    private void createNewBlock() {
        //curBlock = BlockGenerator.getInstance().CreateBlock();
        blockCount++;
    }

    private void checkBlockConfirm() {
        while(!isGameOver)
        {
            if(isBlockStop) return;
        }
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

    @Override
    public void startGameFramework() {
        //GameFramework를 시작
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
        moveDown();
        requestDrawBoard();
    }

    private void moveDown() {

    }
    private void requestDrawBoard() {

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
        //GameUI.getInstance().pane.add(addKeyListener(interaction));
    }

    public class Interaction implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DOWN:
				moveDown();
				requestDrawBoard();
				System.out.println("input down");
				break;
			case KeyEvent.VK_RIGHT:
				moveDown();
				requestDrawBoard();
				System.out.println("input right");
				break;
			case KeyEvent.VK_LEFT:
				moveDown();
				requestDrawBoard();
				System.out.println("input left");
				break;
			case KeyEvent.VK_UP:
				//eraseCurr();
				//curr.rotate();
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
