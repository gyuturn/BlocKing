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
    protected void GameFramework() { //전체적인 게임의 동작 흐름
        GameReady();
        StartTimer();

        while(!isGameOver)
        {
            CreateNewBlock();   //블록 생성
            CheckBlockConfirm(); //블록이 바닥에 닿으면 return 합니다.

            SetGameBalance(); //게임 진행도에 따라 블럭 속도 조절
        }

        GameOver();
    }


    private void GameReady() {
        //게임을 준비합니다.
    }

    private void StartTimer() {

        timer = new Timer(timeScale, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OneFrame();
			}
		});

        timer.start();
    }

    private void CreateNewBlock() {
        //curBlock = BlockGenerator.getInstance().CreateBlock();
        blockCount++;
    }

    private void CheckBlockConfirm() {
        while(!isGameOver)
        {
            if(isBlockStop) return;
        }
    }

    private void SetGameBalance() {
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
    protected void GameOver() {
        //게임이 종료되면 호출됩니다.
    }

    @Override
    public void StartGameFramework() {
        //GameFramework를 시작
    }

    @Override
    public void StopGameFramework() {
        //GameFramework를 멈추고 싶은 경우
        //일시정지 등등
    }
    
    //#endregion

    //#region OneFrame

    @Override
    protected void OneFrame() { //매 프레임마다 진행되는 동작
        MoveDown();
        RequestDrawBoard();
    }

    private void MoveDown() {

    }
    private void RequestDrawBoard() {

    }

    //#endregion

    //#region Utils

    private void SetTimeScale(int scale) {
        
        timeScale = scale;

        timer = new Timer(timeScale, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OneFrame();
			}
		});

        timer.start();
    }

    //#endregion

    //#region Interaction

    public void InitKeyListener() {
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
				MoveDown();
				RequestDrawBoard();
				System.out.println("input down");
				break;
			case KeyEvent.VK_RIGHT:
				MoveDown();
				RequestDrawBoard();
				System.out.println("input right");
				break;
			case KeyEvent.VK_LEFT:
				MoveDown();
				RequestDrawBoard();
				System.out.println("input left");
				break;
			case KeyEvent.VK_UP:
				//eraseCurr();
				//curr.rotate();
				RequestDrawBoard();
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
