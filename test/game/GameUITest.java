package game;

import org.junit.Test;
import setting.ScreenSize;
import setting.UI.SettingUI;

import static org.assertj.core.api.Assertions.assertThat;

public class GameUITest {

    @Test
    public void GameUI(){
        ScreenSize screenSize = ScreenSize.getInstance();
        //given
        GameUI gameUI = new GameUI();
        //when
        //then
        assertThat(gameUI.getSize().width).isEqualTo(screenSize.getWidth());
        assertThat(gameUI.getSize().height).isEqualTo(screenSize.getHeight());
    }
}
