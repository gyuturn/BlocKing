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

    

}
