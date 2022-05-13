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
    private int escape;
    private int left2P;
    private int right2P;
    private int turnBlock2P;
    private int downBlock2P;
    private int oneTimeDown2P;

    private int keyNum=12;

    public KeySetting() {
        this.left = 37;
        this.right = 39;
        this.turnBlock = 38;
        this.downBlock = 40;
        this.stop = 84;
        this.oneTimeDown = 32;
        this.escape=27;

        //2p
        this.left2P=65;
        this.right2P=68;
        this.turnBlock2P=87;
        this.downBlock2P=83;
        this.oneTimeDown2P=70;

    }

    public void resetDefault(){
        this.left = 37;
        this.right = 39;
        this.turnBlock = 38;
        this.downBlock = 40;
        this.stop = 84;
        this.oneTimeDown = 32;
        this.escape=27;
    }

    //key 중복값 있는지 확인
    public boolean overLapKeySetting(){
        boolean flag=false;

        int[] checkOverlap = new int[keyNum];

        checkOverlap[0] = this.left;
        checkOverlap[1]=this.right;
        checkOverlap[2] = this.turnBlock;
        checkOverlap[3] = this.downBlock;
        checkOverlap[4] = this.oneTimeDown;
        checkOverlap[5] = this.stop;
        checkOverlap[6] = this.escape;
        checkOverlap[7] = this.left2P;
        checkOverlap[8] = this.right2P;
        checkOverlap[9] = this.oneTimeDown2P;
        checkOverlap[10] = this.turnBlock2P;
        checkOverlap[11] = this.downBlock2P;

        for (int i = 0; i < checkOverlap.length-1; i++) {
            for (int j = i + 1; j < checkOverlap.length; j++) {
                if (checkOverlap[i] == checkOverlap[j]) {
                    flag=true;
                    return flag;
                }
            }

        }

        return flag;


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

    public void setKeySetting(int left, int right, int turnBlock, int downBlock, int stop, int oneTimeDown, int escape, int left2P, int right2P, int turnBlock2P, int downBlock2P, int oneTimeDown2P) {
        this.left = left;
        this.right = right;
        this.turnBlock = turnBlock;
        this.downBlock = downBlock;
        this.stop = stop;
        this.oneTimeDown = oneTimeDown;
        this.escape = escape;
        this.left2P = left2P;
        this.right2P = right2P;
        this.turnBlock2P = turnBlock2P;
        this.downBlock2P = downBlock2P;
        this.oneTimeDown2P = oneTimeDown2P;
    }


    public int getEscape() {
        return escape;
    }


    public int getLeft2P() {
        return left2P;
    }

    public int getRight2P() {
        return right2P;
    }

    public int getTurnBlock2P() {
        return turnBlock2P;
    }

    public int getDownBlock2P() {
        return downBlock2P;
    }

    public int getOneTimeDown2P() {
        return oneTimeDown2P;
    }
}
