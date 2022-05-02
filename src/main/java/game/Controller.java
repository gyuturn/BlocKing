package game;

import game.container.Block;
import game.manager.BoardManager;
import game.manager.InGameUIManager;
import game.manager.gametype.GameManager_BasicMode;

public class Controller {
    public GameManager_BasicMode gameManager_basicMode;
    public BoardManager boardManager ;
    public Block block;
    public int user;


    static UserNumber userNumber = UserNumber.getInstance();

    private  static Controller[] instance = new Controller[userNumber.user];

    private Controller(GameManager_BasicMode gameManager_basicMode, BoardManager boardManager,Block block,int user) {
        this.gameManager_basicMode = gameManager_basicMode;
        this.boardManager = boardManager;
        this.block=block;
        this.user=user;
    }

    public static void configuration(int user,GameManager_BasicMode gameManager_basicMode, BoardManager boardManager,Block block){
            instance[user] = new Controller(gameManager_basicMode,boardManager,block,user);
    }

    public static Controller[] getInstance(){
        return instance;
    }

}
