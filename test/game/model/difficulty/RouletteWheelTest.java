package game.model.difficulty;

import game.manager.GameInfoManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class RouletteWheelTest {
    GameInfoManager gameInfoManager = GameInfoManager.getInstance();

    @Test
    @DisplayName("EasyMode")
    public void generateBlockByValueTestEasyMode() {
        //given
        GameInfoManager.GameDifficulty easy = GameInfoManager.GameDifficulty.Easy;
        //when
        int measuredValue = RouletteWheel.GenerateBlockByValueTest(easy);
        double errorPercent = RouletteWheel.ComputeErrorPercent(measuredValue, 1200);
        //then
        Assertions.assertThat(errorPercent).isLessThan(5);
    }

    @Test
    @DisplayName("EasyMode")
    public void generateBlockByValueTestEasyMode() {
        //given
        GameInfoManager.GameDifficulty easy = GameInfoManager.GameDifficulty.Easy;
        //when
        int measuredValue = RouletteWheel.GenerateBlockByValueTest(easy);
        double errorPercent = RouletteWheel.ComputeErrorPercent(measuredValue, 1200);
        //then
        Assertions.assertThat(errorPercent).isLessThan(5);
    }

    @Test
    public void generateBlockByValueTest() {
    }


}