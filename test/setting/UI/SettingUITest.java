package setting.UI;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import setting.ScreenSize;

import javax.swing.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class SettingUITest {
    ScreenSize screenSize = ScreenSize.getInstance();

    @Test
    @DisplayName("init")
    public void init() {
        //given
        SettingUI settingUI = new SettingUI();
        //when
        settingUI.init();
        //then
        assertThat(settingUI.getSize().width).isEqualTo(screenSize.getWidth());
        assertThat(settingUI.getSize().height).isEqualTo(screenSize.getHeight());
    }


    @Test
    @DisplayName("setTitle")
    public void setTitle(){
        //given
        String Img1 = "./src/main/java/start/img/title1.png";
        String Img2 = "./src/main/java/start/img/title2.png";
        String Img3 = "./src/main/java/start/img/title3.png";
        SettingUI settingUI = new SettingUI();
        //when
        settingUI.setTitle();
        //then
        if (screenSize.getWidth() == 800) {
            assertThat(settingUI.titleImg1.getDescription()).isEqualTo(Img1);
        } else if (screenSize.getWidth() == 1024) {
            assertThat(settingUI.titleImg2.getDescription()).isEqualTo(Img2);
        } else {
            assertThat(settingUI.titleImg3.getDescription()).isEqualTo(Img3);
        }
    }
}