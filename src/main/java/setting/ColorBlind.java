package setting;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class ColorBlind {
    //싱글톤
    private static ColorBlind colorBlind = new ColorBlind();

    public static ColorBlind getInstance() {
        return colorBlind;
    }

    private int curColorBlind=0;

    public int getColorBlind() {
        return curColorBlind;
    }

    public void setCurColorBlind(int curColorBlind) {
        this.curColorBlind = curColorBlind;
    }

    //0:일반 1:적녹색맹 2: 청황색맹




}
