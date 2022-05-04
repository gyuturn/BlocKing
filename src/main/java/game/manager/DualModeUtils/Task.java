package game.manager.DualModeUtils;

import game.manager.gametype.GameManager_BasicMode;
import game.manager.gametype.GameManager_ItemMode;

public class Task {
    public void runBasicMode(int user) {

        GameManager_BasicMode.getInstance(user).startGameFramework();
    }

    public void runItemMode(int user) {
        GameManager_ItemMode.getInstance(user).startGameFramework();
    }
}
