package game.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

import game.GameUI;
import game.manager.GameInfoManager.GameDifficulty;
import game.manager.GameInfoManager.GameMode;
import game.manager.gametype.GameManager_NormalMode;
import game.model.BlockController;
import setting.KeySetting;

public abstract class GameManager {

//#region interaction

    protected KeySetting keySetting = KeySetting.getInstance();
    protected KeyListener interaction_play;
    protected KeyListener interaction_utils;

//#endregion


//#region flow control

    protected boolean isBlockMovable = true;
    protected boolean isGameOver = false;

    protected Timer timer;
    //private Step curStep = Step.GameReady;

//#endregion


//#region difficulty

    protected static int maxSpeed = 1000000;
    protected static int basicSpeed = 1000;
    protected static int curSpeed = 1000;
    protected static int timeScale = 1000;

    protected static int addSpeed = 0;

//#endregion


//#region status

    protected GameMode mode;
    protected GameDifficulty difficulty;

    protected int blockCount = 0;
    protected int lineCount = 0;
    protected int score = 0;

    public BlockController curBlock;
    public BlockController getCurBlock() {
        return curBlock;
    }

    protected ArrayList<Integer> targetLineIndexList = new ArrayList<Integer>();

    protected abstract void initGameStatus();

    public static boolean isPlaying = false;
    public static boolean isPause = false;
    
//#endregion


//#region gameFramework

    //public enum Step {}

    protected abstract void gameFramework();

    public void startGameFramework() {
        timer = new Timer(timeScale, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                
				oneFrame();
			}
		});

        timer.start();
    }

    public void stopGameFramework() {
        timer.stop();
        GameUI.getInstance().pane.removeKeyListener(interaction_play);
    }

    public void restartGameFramework() {
        timer.restart();
        GameUI.getInstance().pane.addKeyListener(interaction_play);
    }

    

    protected void oneFrame() {
        gameFramework();
        InGameUIManager.getInstance().drawBoard();
        printStatus();
    }

    protected abstract void gameOver();

//#endregion


//#region Utils

    protected void setTimeScale(int scale) {

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


    protected void printStatus() {
        System.out.printf("\n");
        System.out.printf("score : %d \n", score);
        System.out.printf("curSpeed : %d\n\n", curSpeed);
        InGameUIManager.getInstance().drawScore();

    }

//#endregion

}