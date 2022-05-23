package game;

import org.junit.Test;
import setting.ScreenSize;
import setting.UI.KeySettingUI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class SelectGameTypeUITest {
    ScreenSize screenSize = ScreenSize.getInstance();

    @Test
    public void selectGameTypeUI(){
        //given
        String Img1 = "./src/main/java/start/img/title1.png";
        String Img2 = "./src/main/java/start/img/title2.png";
        String Img3 = "./src/main/java/start/img/title3.png";
        SelectGameTypeUI selectGameTypeUI = new SelectGameTypeUI();
        //when
        selectGameTypeUI.setTitle();
        //then
        if (screenSize.getWidth() == 800) {
            assertThat(selectGameTypeUI.titleImg1.getDescription()).isEqualTo(Img1);
        } else if (screenSize.getWidth() == 1024) {
            assertThat(selectGameTypeUI.titleImg2.getDescription()).isEqualTo(Img2);
        } else {
            assertThat(selectGameTypeUI.titleImg3.getDescription()).isEqualTo(Img3);
        }
    }

}