package start;

import game.GameUI;
import setting.ScreenSize;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IsButtonClicked {


    private  boolean gameBtnClicked;
    private  boolean settingBtnClicked;
    private  boolean scbBtnClicked;
    private  boolean exitBtnClicked;
    //스코어보드
    private  boolean fixSizeBtnClicked;
    private  boolean ksuBtnClicked;
    private  boolean initialScbBtnClicked;
    private  boolean colorBlindBtnClicked;
    private  boolean returnAllBtnClicked;
    private  boolean backStartBtnClicked;


    public static IsButtonClicked btnClicked = new IsButtonClicked();

    private IsButtonClicked() {
        this.gameBtnClicked= false;
        this.settingBtnClicked = false;
        this.scbBtnClicked = false;
        this.exitBtnClicked = false;
        // 스코어보드
        this.fixSizeBtnClicked = false;
        this.ksuBtnClicked = false;
        this.initialScbBtnClicked = false;
        this.colorBlindBtnClicked = false;
        this.returnAllBtnClicked = false;
        this.backStartBtnClicked = false;
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

    // 스코어보드


    public  boolean isFixSizeBtnClicked() {
        if(fixSizeBtnClicked) return true;
        else return false;
    }

    public  boolean isKsuBtnClicked() {
        if(ksuBtnClicked) return true;
        else return false;
    }

    public  boolean isInitialScbBtnClicked() {
        if(initialScbBtnClicked) return true;
        else return false;
    }

    public  boolean isColorBlindBtnClicked() {
        if(colorBlindBtnClicked) return true;
        else return false;
    }

    public  boolean isReturnAllBtnClicked() {
        if(returnAllBtnClicked) return true;
        else return false;
    }

    public  boolean isBackStartBtnClicked() {
        if(backStartBtnClicked) return true;
        else return false;
    }



    public void setGameBtnClicked() {
        gameBtnClicked= true;
        settingBtnClicked= false;
        scbBtnClicked= false;
        exitBtnClicked= false;
        System.out.println("game");
    }
    public void setSettingBtnClicked() {
        gameBtnClicked= false;
        settingBtnClicked= true;
        scbBtnClicked= false;
        exitBtnClicked= false;
        System.out.println("set");
    }
    public void setScbBtnClicked() {
        gameBtnClicked= false;
        settingBtnClicked= false;
        scbBtnClicked= true;
        exitBtnClicked= false;
        System.out.println("scb");
    }
    public void setExitBtnClicked() {
        gameBtnClicked= false;
        settingBtnClicked= false;
        scbBtnClicked= false;
        exitBtnClicked= true;
        System.out.println("exit");
    }
    //스코어보드

    public void setFixSizeBtnClicked() {
        fixSizeBtnClicked = true;
        ksuBtnClicked = false;
        initialScbBtnClicked = false;
        colorBlindBtnClicked = false;
        returnAllBtnClicked = false;
        backStartBtnClicked = false;
        System.out.println("fixsize");
    }

    public void setKsuBtnClicked() {
        fixSizeBtnClicked = false;
        ksuBtnClicked = true;
        initialScbBtnClicked = false;
        colorBlindBtnClicked = false;
        returnAllBtnClicked = false;
        backStartBtnClicked = false;
        System.out.println("ksu");
    }

    public void setInitialScbBtnClicked() {
        fixSizeBtnClicked = false;
        ksuBtnClicked = false;
        initialScbBtnClicked = true;
        colorBlindBtnClicked = false;
        returnAllBtnClicked = false;
        backStartBtnClicked = false;
        System.out.println("initialize");
    }

    public void setColorBlindBtnClicked() {
        fixSizeBtnClicked = false;
        ksuBtnClicked = false;
        initialScbBtnClicked = false;
        colorBlindBtnClicked = true;
        returnAllBtnClicked = false;
        backStartBtnClicked = false;
        System.out.println("colorblind");
    }

    public void setReturnAllBtnClicked() {
        fixSizeBtnClicked = false;
        ksuBtnClicked = false;
        initialScbBtnClicked = false;
        colorBlindBtnClicked = false;
        returnAllBtnClicked = true;
        backStartBtnClicked = false;
        System.out.println("returnall");
    }

    public void setBackStartBtnClicked() {
        fixSizeBtnClicked = false;
        ksuBtnClicked = false;
        initialScbBtnClicked = false;
        colorBlindBtnClicked = false;
        returnAllBtnClicked = false;
        backStartBtnClicked = true;
        System.out.println("backtostart");
    }



}
