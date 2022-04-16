package start;

import setting.ScreenSize;

public class IsButtonClicked {


    private  boolean gameBtnClicked;
    private  boolean settingBtnClicked;
    private  boolean scbBtnClicked;
    private  boolean exitBtnClicked;

    public static IsButtonClicked btnClicked = new IsButtonClicked();

    private IsButtonClicked() {
        this.gameBtnClicked= false;
        this.settingBtnClicked = false;
        this.scbBtnClicked = false;
        this.exitBtnClicked = false;
    }

    public static IsButtonClicked getInstance() {
        return btnClicked;
    }

    public  boolean isGameBtnClicked() {
        if(gameBtnClicked) return true;
        else return false;
    }
    public  boolean isSettingBtnClicked() {
        if(settingBtnClicked) return true;
        else return false;
    }
    public  boolean isScbBtnClicked() {
        if(scbBtnClicked) return true;
        else return false;
    }
    public  boolean isExitBtnClicked() {
        if(exitBtnClicked) return true;
        else return false;
    }

    public void setGameBtnClicked() {
        gameBtnClicked= true;
        settingBtnClicked= false;
        scbBtnClicked= false;
        exitBtnClicked= false;
    }
    public void setSettingBtnClicked() {
        gameBtnClicked= false;
        settingBtnClicked= true;
        scbBtnClicked= false;
        exitBtnClicked= false;
    }
    public void setScbBtnClicked() {
        gameBtnClicked= false;
        settingBtnClicked= false;
        scbBtnClicked= true;
        exitBtnClicked= false;
    }
    public void setExitBtnClicked() {
        gameBtnClicked= false;
        settingBtnClicked= false;
        scbBtnClicked= false;
        exitBtnClicked= true;
    }

}
