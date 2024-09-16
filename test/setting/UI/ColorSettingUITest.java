package setting.UI;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import setting.ColorBlind;
import setting.ScreenSize;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ColorSettingUITest {
    ScreenSize screenSize = ScreenSize.getInstance();
    ColorBlind colorBlind = ColorBlind.getInstance();

    @Test
    public void testImgSize(){
        //given
        String Img1 = "./src/main/java/start/img/title1.png";
        String Img2 = "./src/main/java/start/img/title2.png";
        String Img3 = "./src/main/java/start/img/title3.png";
        ColorSettingUI colorSettingUI = new ColorSettingUI();
        //when
        colorSettingUI.setTitle();
        //then
        if (screenSize.getWidth() == 800) {
            assertThat(colorSettingUI.titleImg1.getDescription()).isEqualTo(Img1);
        } else if (screenSize.getWidth() == 1024) {
            assertThat(colorSettingUI.titleImg2.getDescription()).isEqualTo(Img2);
        } else {
            assertThat(colorSettingUI.titleImg3.getDescription()).isEqualTo(Img3);
        }
    }

    @Test
    public void selectedBtnShows(){
        //given
        ColorSettingUI colorSettingUI = new ColorSettingUI();
        //when
        colorSettingUI.selectedBtnShows();
        //then
        if(colorBlind.getColorBlind()==ColorBlind.ColorSetting.BASIC){
            assertThat(colorSettingUI.colorSettingBtns[0].isSelected()).isEqualTo(true);
        }else{
            assertThat(colorSettingUI.colorSettingBtns[1].isSelected()).isEqualTo(true);
        }
    }
}