package game.container;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class RouletteWheelTest {

    @Test
    @DisplayName("이지모드 I블록 생성 test")
    public void EasyMode() {
        //given
        int[] block = new int[7];
        //when

            RouletteWheel.EasyModeIncludeTest();

        //then(오차범위 계산)


    }

}