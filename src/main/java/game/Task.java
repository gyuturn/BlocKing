package game;

import game.container.Block;
import game.manager.BoardManager;
import game.manager.GameManager;
import game.manager.InGameUIManager;
import game.manager.gametype.GameManager_BasicMode;

public class Task{
    public GameManager_BasicMode gameManager_basicMode;
    public Block block;
    public BoardManager boardManager;
    public InGameUIManager inGameUIManager;
    public int user;


    public void run(int user) {
        this.user=user;

        boardManager = new BoardManager();
        block = new Block(boardManager);

        gameManager_basicMode = new GameManager_BasicMode();

        inGameUIManager = new InGameUIManager(boardManager, gameManager_basicMode, user);

        gameManager_basicMode = new GameManager_BasicMode(inGameUIManager, boardManager, block,user);

        inGameUIManager = new InGameUIManager(boardManager, gameManager_basicMode,user);

        gameManager_basicMode = new GameManager_BasicMode(inGameUIManager, boardManager, block,user);

        boardManager = new BoardManager(inGameUIManager);

        inGameUIManager = new InGameUIManager(boardManager, gameManager_basicMode,user);

        Controller.configuration(user,gameManager_basicMode, boardManager,block);

        gameManager_basicMode.startGameFramework();
    }


}