package scoreBoard;

import org.junit.Test;
import setting.ScreenSize;
import setting.UI.FixSizeUI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class SelectScoreBoardUITest {
    ScreenSize screenSize = ScreenSize.getInstance();

    @Test
    public void selectBackImg(){
        //given
        String Img1 = "./src/main/java/start/img/title1.png";
        String Img2 = "./src/main/java/start/img/title2.png";
        String Img3 = "./src/main/java/start/img/title3.png";
        SelectScoreBoardUI selectScoreBoardUI = new SelectScoreBoardUI();
        //when
        selectScoreBoardUI.setTitle();
        //then
        if (screenSize.getWidth() == 800) {
            assertThat(selectScoreBoardUI.titleImg1.getDescription()).isEqualTo(Img1);
        } else if (screenSize.getWidth() == 1024) {
            assertThat(selectScoreBoardUI.titleImg2.getDescription()).isEqualTo(Img2);
        } else {
            assertThat(selectScoreBoardUI.titleImg3.getDescription()).isEqualTo(Img3);
        }
    }
}