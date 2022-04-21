package start;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class IsButtonClickedTest {
    IsButtonClicked isButtonClicked = IsButtonClicked.getInstance();

    @After
    public void resetBtns(){
        isButtonClicked.resetBtns();
    }

    @Test
    @DisplayName("startUI에서 시작버튼에서 설정버튼으로 ")
    public void 시작버튼(){
        //given
        isButtonClicked.setGameBtnClicked();
        //when
        isButtonClicked.setSettingBtnClicked();
        //then
        assertThat(isButtonClicked.isGameBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isSettingBtnClicked()).isEqualTo(true);
    }

    @Test
    @DisplayName("startUI에서 설정버튼에서 스코어버튼으로 ")
    public void 스코어버튼(){
        //given
        isButtonClicked.setSettingBtnClicked();
        //when
        isButtonClicked.setScbBtnClicked();
        //then
        assertThat(isButtonClicked.isSettingBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isScbBtnClicked()).isEqualTo(true);
    }

    @Test
    @DisplayName("startUI에서 스코어버튼에서 종료버튼으로 ")
    public void 종료버튼(){
        //given
        isButtonClicked.setScbBtnClicked();
        //when
        isButtonClicked.setExitBtnClicked();
        //then
        assertThat(isButtonClicked.isScbBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isExitBtnClicked()).isEqualTo(true);
    }


    @Test
    @DisplayName("SettingUI에서 화면사이즈버튼에서 게임조작키버튼으로 ")
    public void 게임조작버튼(){
        //given
        isButtonClicked.setFixSizeBtnClicked();
        //when
        isButtonClicked.setKsuBtnClicked();
        //then
        assertThat(isButtonClicked.isFixSizeBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isKsuBtnClicked()).isEqualTo(true);
    }

    @Test
    @DisplayName("SettingUI에서 게임조작키버튼에서 스코어초기화버튼으로 ")
    public void 스코어초기화버튼(){
        //given
        isButtonClicked.setKsuBtnClicked();
        //when
        isButtonClicked.setInitialScbBtnClicked();
        //then
        assertThat(isButtonClicked.isKsuBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isInitialScbBtnClicked()).isEqualTo(true);
    }

    @Test
    @DisplayName("SettingUI에서 스코어보드초기화버튼에서 색맹모드버튼으로 ")
    public void 색맹모드버튼(){
        //given
        isButtonClicked.setInitialScbBtnClicked();
        //when
        isButtonClicked.setColorBlindBtnClicked();
        //then
        assertThat(isButtonClicked.isInitialScbBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isColorBlindBtnClicked()).isEqualTo(true);
    }


    @Test
    @DisplayName("SettingUI에서 색맹모드버튼에서 모든설정초기화버튼으로 ")
    public void 모든설정초기화버튼(){
        //given
        isButtonClicked.setColorBlindBtnClicked();
        //when
        isButtonClicked.setReturnAllBtnClicked();
        //then
        assertThat(isButtonClicked.isColorBlindBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isReturnAllBtnClicked()).isEqualTo(true);
    }

    @Test
    @DisplayName("SettingUI에서 모든설정초기화버튼에서 시작메뉴버튼으로 ")
    public void 시작메뉴버튼(){
        //given
        isButtonClicked.setReturnAllBtnClicked();
        //when
        isButtonClicked.setBackStartBtnClicked();
        //then
        assertThat(isButtonClicked.isReturnAllBtnClicked()).isEqualTo(false);
        assertThat(isButtonClicked.isBackStartBtnClicked()).isEqualTo(true);
    }





}