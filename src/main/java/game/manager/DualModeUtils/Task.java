package game.manager.DualModeUtils;

import game.manager.GameInfoManager;
import game.manager.gametype.GameManager_BasicMode;
import game.manager.gametype.GameManager_ItemMode;

public class Task {
    public void runBasicMode(int user) {
        if (GameInfoManager.getInstance().mode == GameInfoManager.GameMode.BasicMode) {
            GameManager_BasicMode.getInstance(user).startGameFramework();
        }
        else{
            GameManager_ItemMode.getInstance(user).startGameFramework();
        }


    }

    public void runItemMode(int user) {
        GameManager_ItemMode.getInstance(user).startGameFramework();
    }
}
