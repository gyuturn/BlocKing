package setting;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class KeySettingTest {
    KeySetting keySetting = KeySetting.getInstance();

    @After
    public void resetDefaultValue() {
        keySetting.resetDefault();
    }


    @Test
    @DisplayName("키 설정 기본값으로 설정")
    public void resetDefault() {
        //given
        keySetting.setKeySetting(1, 2, 3, 4, 5, 6, 7,8,9,10,11,12);
        //when
        keySetting.resetDefault();
        //then
        assertThat(keySetting.getLeft()).isEqualTo(37);
        assertThat(keySetting.getRight()).isEqualTo(39);
        assertThat(keySetting.getTurnBlock()).isEqualTo(38);
        assertThat(keySetting.getDownBlock()).isEqualTo(40);
        assertThat(keySetting.getStop()).isEqualTo(84);
        assertThat(keySetting.getOneTimeDown()).isEqualTo(32);
        assertThat(keySetting.getEscape()).isEqualTo(27);

    }

    @Test
    @DisplayName("키 세팅 설정 test")
    public void setKeySetting() {
        //given
        keySetting.resetDefault();
        //when
        keySetting.setKeySetting(1, 2, 3, 4, 5, 6, 7,8,9,10,11,12);
        //then
        assertThat(keySetting.getLeft()).isEqualTo(1);
        assertThat(keySetting.getRight()).isEqualTo(2);
        assertThat(keySetting.getTurnBlock()).isEqualTo(3);
        assertThat(keySetting.getDownBlock()).isEqualTo(4);
        assertThat(keySetting.getStop()).isEqualTo(5);
        assertThat(keySetting.getOneTimeDown()).isEqualTo(6);
        assertThat(keySetting.getEscape()).isEqualTo(7);
    }

    @Test
    @DisplayName("키 중복값 찾기")
    public void overLapKeySetting(){
        //given
        keySetting.setKeySetting(1, 2, 1, 4, 5, 6, 7,8,9,10,11,12);
        //when
        boolean flag = keySetting.overLapKeySetting();
        //then
        assertThat(flag).isEqualTo(true);
    }
}