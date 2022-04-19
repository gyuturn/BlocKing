package setting;

public class KeySetting {

    //싱글톤
    private static KeySetting keySetting = new KeySetting();

    public static KeySetting getInstance() {
        return keySetting;
    }

    private int left;

    private int right;

    private int turnBlock;

    private int downBlock;

    private int stop;

    private int oneTimeDown;

    private static int keyLength;

    public KeySetting() {
        this.left = 37;
        this.right = 39;
        this.turnBlock = 38;
        this.downBlock = 40;
        this.stop = 84;
        this.oneTimeDown = 32;
    }

    public void resetDefault(){
        this.left = 37;
        this.right = 39;
        this.turnBlock = 38;
        this.downBlock = 40;
        this.stop = 84;
        this.oneTimeDown = 32;
    }

    //key 중복값 있는지 확인
    public boolean overLapKeySetting(){
        int left = this.left;
        int right=this.right;
        int turnBlock = this.turnBlock;
        int downBlock = this.downBlock;
        int oneTimeDown = this.oneTimeDown;
        int stop = this.stop;

        return false;

    }

    public static KeySetting getKeySetting() {
        return keySetting;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTurnBlock() {
        return turnBlock;
    }

    public int getDownBlock() {
        return downBlock;
    }

    public int getStop() {
        return stop;
    }

    public int getOneTimeDown() {
        return oneTimeDown;
    }

    public void setKeySetting(int left, int right, int turnBlock, int downBlock, int stop, int oneTimeDown) {
        this.left = left;
        this.right = right;
        this.turnBlock = turnBlock;
        this.downBlock = downBlock;
        this.stop = stop;
        this.oneTimeDown = oneTimeDown;
    }


    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setTurnBlock(int turnBlock) {
        this.turnBlock = turnBlock;
    }

    public void setDownBlock(int downBlock) {
        this.downBlock = downBlock;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public void setOneTimeDown(int oneTimeDown) {
        this.oneTimeDown = oneTimeDown;
    }
}
