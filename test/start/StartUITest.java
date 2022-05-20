package start;

import game.SelectDualGameTypeUI;
import org.junit.Test;
import setting.ScreenSize;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class StartUITest {

    ScreenSize screenSize = ScreenSize.getInstance();

    @Test
    public void selectDualGameTypeUI(){
        //given
        String Img1 = "./src/main/java/start/img/title1.png";
        String Img2 = "./src/main/java/start/img/title2.png";
        String Img3 = "./src/main/java/start/img/title3.png";
        StartUI startUI = new StartUI();
        //when
        startUI.titleBtn();
        //then
        if (screenSize.getWidth() == 800) {
            assertThat(startUI.titleImg1.getDescription()).isEqualTo(Img1);
        } else if (screenSize.getWidth() == 1024) {
            assertThat(startUI.titleImg2.getDescription()).isEqualTo(Img2);
        } else {
            assertThat(startUI.titleImg3.getDescription()).isEqualTo(Img3);
        }
    }

}