package setting;

public class KeySetting {

    //싱글톤
    private static KeySetting keySetting = new KeySetting();

    public KeySetting() {
        this.left = "LEFT";
        this.right = "RIGHT";
        this.turnBlock = "UP";
        this.downBlock = "DOWN";
        this.stop = "T";
        this.oneTimeDown = "SPACE";
    }

    public KeySetting(String left, String right, String turnBlock, String downBlock, String stop, String oneTimeDown) {
        this.left = left;
        this.right = right;
        this.turnBlock = turnBlock;
        this.downBlock = downBlock;
        this.stop = stop;
        this.oneTimeDown = oneTimeDown;
    }

    public static KeySetting getInstance() {
        return keySetting;
    }

    private String left;

    private String right;

    private String turnBlock;

    private String downBlock;

    private String stop;

    private String oneTimeDown;






}
