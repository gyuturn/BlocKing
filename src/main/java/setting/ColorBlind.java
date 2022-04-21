package setting;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class ColorBlind {
    //싱글톤
    private static ColorBlind colorBlind = new ColorBlind();
    

    public enum ColorSetting {
        BASIC,
        ColorBlinded,
    }

    public static ColorBlind getInstance() {
        return colorBlind;
    }

    private ColorSetting curColorBlind = ColorSetting.BASIC;

    public ColorSetting getColorBlind() {
        return curColorBlind;
    }

    public void setCurColorBlind(ColorSetting curColorBlind) {
        this.curColorBlind = curColorBlind;
    }

    public void resetDefault() {
        curColorBlind = ColorSetting.BASIC;
    }

    //0:일반 1:색맹모드





}
