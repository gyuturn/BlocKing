package setting.UI;

import org.junit.Test;
import setting.ScreenSize;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class KeySettingUITest {

    ScreenSize screenSize = ScreenSize.getInstance();

    @Test
    public void checkBtnTitle(){
        //given
        String Img1 = "./src/main/java/start/img/title1.png";
        String Img2 = "./src/main/java/start/img/title2.png";
        String Img3 = "./src/main/java/start/img/title3.png";
        KeySettingUI keySettingUI = new KeySettingUI();
        //when
        keySettingUI.setTitle();
        //then
        if (screenSize.getWidth() == 800) {
            assertThat(keySettingUI.titleImg1.getDescription()).isEqualTo(Img1);
        } else if (screenSize.getWidth() == 1024) {
            assertThat(keySettingUI.titleImg2.getDescription()).isEqualTo(Img2);
        } else {
            assertThat(keySettingUI.titleImg3.getDescription()).isEqualTo(Img3);
        }
    }

}