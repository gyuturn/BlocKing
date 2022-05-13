package setting;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import scoreBoard.NoItemScoreBoard.ScoreList;
import scoreBoard.User;
import scoreBoard.scoreBoradItem.ItemScoreList;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class SaveAndLoadTest {
    private ScreenSize screenSize = ScreenSize.getInstance();
    private ColorBlind colorBlind = ColorBlind.getInstance();
    private ScoreList scoreList = ScoreList.getInstance();
    private ItemScoreList itemScoreList = ItemScoreList.getInstance();
    private KeySetting keySetting = KeySetting.getInstance();

    @After
    @Before
    public void resetSetting() throws DuplicateKeySettingException {
        screenSize.resetDefault();
        colorBlind.resetDefault();
        scoreList.deleteAll();
        itemScoreList.deleteAll();
        keySetting.resetDefault();
        SaveAndLoad.SaveSetting();
    }

    @Test
    @DisplayName("설정 저장 test(색맹모드 OFF)")
    public void saveSetting() throws DuplicateKeySettingException {
        //given
        screenSize.setWidth(800);
        screenSize.setHeight(600);

        colorBlind.setCurColorBlind(ColorBlind.ColorSetting.BASIC);

        //10개이상 넣기
        scoreList.push(new User("test1", 100, "easy"));
        scoreList.push(new User("test2", 150, "easy"));
        scoreList.push(new User("test3", 140, "easy"));
        scoreList.push(new User("test4", 130, "hard"));
        scoreList.push(new User("test5", 120, "easy"));
        scoreList.push(new User("test6", 110, "normal"));
        scoreList.push(new User("test7", 100, "hard"));
        scoreList.push(new User("test8", 90, "normal"));
        scoreList.push(new User("test9", 80, "easy"));
        scoreList.push(new User("test10", 70, "normal"));
        scoreList.push(new User("test11", 60, "easy"));

        itemScoreList.push(new User("test1", 100, "easy"));
        itemScoreList.push(new User("test2", 150, "easy"));
        itemScoreList.push(new User("test3", 140, "easy"));
        itemScoreList.push(new User("test4", 130, "hard"));
        itemScoreList.push(new User("test5", 120, "easy"));
        itemScoreList.push(new User("test6", 110, "normal"));
        itemScoreList.push(new User("test7", 100, "hard"));
        itemScoreList.push(new User("test8", 90, "normal"));
        itemScoreList.push(new User("test9", 80, "easy"));
        itemScoreList.push(new User("test10", 70, "normal"));
        itemScoreList.push(new User("test11", 60, "easy"));

        keySetting.setKeySetting(1, 2, 3, 4, 5, 6, 7,8,9,10,11,12);

        //when
        SaveAndLoad.SaveSetting();
        //then
        assertThat(screenSize).isEqualTo(SaveAndLoad.readFileScreenSize());
        assertThat(colorBlind).isEqualTo(SaveAndLoad.readFileColorBlind());
        assertThat(keySetting).isEqualTo(SaveAndLoad.readFileKeySetting());
        assertThat(itemScoreList).isEqualTo(SaveAndLoad.readFileItemScoreList());
        assertThat(scoreList).isEqualTo(SaveAndLoad.readFileScoreList());
    }

    @Test
    @DisplayName("설정 저장 test(색맹모드 ON)")
    public void saveSetting2() throws DuplicateKeySettingException {
        //given
        screenSize.setWidth(800);
        screenSize.setHeight(600);

        colorBlind.setCurColorBlind(ColorBlind.ColorSetting.ColorBlinded);

        //10개이상 넣기
        scoreList.push(new User("test1", 100, "easy"));
        scoreList.push(new User("test2", 150, "easy"));
        scoreList.push(new User("test3", 140, "easy"));
        scoreList.push(new User("test4", 130, "hard"));
        scoreList.push(new User("test5", 120, "easy"));
        scoreList.push(new User("test6", 110, "normal"));
        scoreList.push(new User("test7", 100, "hard"));
        scoreList.push(new User("test8", 90, "normal"));
        scoreList.push(new User("test9", 80, "easy"));
        scoreList.push(new User("test10", 70, "normal"));
        scoreList.push(new User("test11", 60, "easy"));

        itemScoreList.push(new User("test1", 100, "easy"));
        itemScoreList.push(new User("test2", 150, "easy"));
        itemScoreList.push(new User("test3", 140, "easy"));
        itemScoreList.push(new User("test4", 130, "hard"));
        itemScoreList.push(new User("test5", 120, "easy"));
        itemScoreList.push(new User("test6", 110, "normal"));
        itemScoreList.push(new User("test7", 100, "hard"));
        itemScoreList.push(new User("test8", 90, "normal"));
        itemScoreList.push(new User("test9", 80, "easy"));
        itemScoreList.push(new User("test10", 70, "normal"));
        itemScoreList.push(new User("test11", 60, "easy"));
        keySetting.setKeySetting(1, 2, 3, 4, 5, 6, 7,8,9,10,11,12);

        //when
        SaveAndLoad.SaveSetting();
        //then
        assertThat(screenSize).isEqualTo(SaveAndLoad.readFileScreenSize());
        assertThat(colorBlind).isEqualTo(SaveAndLoad.readFileColorBlind());
        assertThat(keySetting).isEqualTo(SaveAndLoad.readFileKeySetting());
        assertThat(itemScoreList).isEqualTo(SaveAndLoad.readFileItemScoreList());
        assertThat(scoreList).isEqualTo(SaveAndLoad.readFileScoreList());
    }


    @Test(expected = DuplicateKeySettingException.class)
    @DisplayName("설정 저장 중 중복된 키값이 있는경우")
    public void saveSettingErrVer() throws DuplicateKeySettingException {

        //given
        screenSize.setWidth(800);
        screenSize.setHeight(600);

        colorBlind.setCurColorBlind(ColorBlind.ColorSetting.ColorBlinded);

        //10개이상 넣기
        scoreList.push(new User("test1", 100, "easy"));
        scoreList.push(new User("test2", 150, "easy"));
        scoreList.push(new User("test3", 140, "easy"));
        scoreList.push(new User("test4", 130, "hard"));
        scoreList.push(new User("test5", 120, "easy"));
        scoreList.push(new User("test6", 110, "normal"));
        scoreList.push(new User("test7", 100, "hard"));
        scoreList.push(new User("test8", 90, "normal"));
        scoreList.push(new User("test9", 80, "easy"));
        scoreList.push(new User("test10", 70, "normal"));
        scoreList.push(new User("test11", 60, "easy"));

        itemScoreList.push(new User("test1", 100, "easy"));
        itemScoreList.push(new User("test2", 150, "easy"));
        itemScoreList.push(new User("test3", 140, "easy"));
        itemScoreList.push(new User("test4", 130, "hard"));
        itemScoreList.push(new User("test5", 120, "easy"));
        itemScoreList.push(new User("test6", 110, "normal"));
        itemScoreList.push(new User("test7", 100, "hard"));
        itemScoreList.push(new User("test8", 90, "normal"));
        itemScoreList.push(new User("test9", 80, "easy"));
        itemScoreList.push(new User("test10", 70, "normal"));
        itemScoreList.push(new User("test11", 60, "easy"));

        keySetting.setKeySetting(1, 2, 3, 3, 5, 6, 7,8,9,10,11,12);

        //when
        SaveAndLoad.saveKeySetting();
        //then

    }
    @Test
    @DisplayName("설정된 기능 시작 시 Load test")
    public void LoadSetting() throws DuplicateKeySettingException {
        //given
        saveSetting();
        //when
        SaveAndLoad.LoadSetting();
        //then
        assertThat(SaveAndLoad.readFileScreenSize()).isEqualTo(screenSize);
        assertThat(SaveAndLoad.readFileColorBlind()).isEqualTo(colorBlind);
        assertThat(SaveAndLoad.readFileKeySetting()).isEqualTo(keySetting);
        assertThat(SaveAndLoad.readFileItemScoreList()).isEqualTo(itemScoreList);
        assertThat(SaveAndLoad.readFileScoreList()).isEqualTo(scoreList);

    }


}