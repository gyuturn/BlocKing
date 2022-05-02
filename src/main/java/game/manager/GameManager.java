package game.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import game.Controller;
import game.GameDualModeUI;
import game.UserNumber;
import game.manager.GameInfoManager.GameDifficulty;
import game.manager.GameInfoManager.GameMode;
import game.model.BlockController;
import setting.KeySetting;

public abstract class GameManager {

    Controller[] controller=Controller.getInstance();
    UserNumber userNumber = UserNumber.getInstance();


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


    //#region dependency
    public InGameUIManager inGameUIManager;
    public GameManager(InGameUIManager inGameUIManager) {
        this.inGameUIManager = inGameUIManager;
    }

    public GameManager() {
    }


    public BlockController getCurBlock() {
        return curBlock;
    }

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
        GameDualModeUI.getInstance().pane[0].removeKeyListener(interaction_play);
        GameDualModeUI.getInstance().pane[1].removeKeyListener(interaction_play);
    }

    public void restartGameFramework() {
        timer.restart();
        GameDualModeUI.getInstance().pane[0].addKeyListener(interaction_play);
        GameDualModeUI.getInstance().pane[1].addKeyListener(interaction_play);
    }

    

    protected void oneFrame() {
        gameFramework();
        inGameUIManager.drawBoard();
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
        inGameUIManager.drawScore();

    }

//#endregion

}