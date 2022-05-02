package game;

import game.manager.GameInfoManager;

public class UserNumber {
    public int user=2;

    private static UserNumber instance = new UserNumber();

    public static UserNumber getInstance() {
        return instance;
    }

}
