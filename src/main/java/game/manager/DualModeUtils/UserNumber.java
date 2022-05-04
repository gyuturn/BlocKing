package game.manager.DualModeUtils;

public class UserNumber {
    public int user=1;

    private static UserNumber instance = new UserNumber();

    public static UserNumber getInstance() {
        return instance;
    }

}