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
        double errorPercent = RouletteWheel.ComputeErrorPercent(measuredValue, 8571.428571428571);
        //then
        Assertions.assertThat(errorPercent).isLessThan(5);
    }

    @Test
    @DisplayName("NorMal Mode")
    public void generateBlockByValueTestNorMalMode() {
        //given
        GameInfoManager.GameDifficulty normal = GameInfoManager.GameDifficulty.Normal;
        //when
        int measuredValue = RouletteWheel.GenerateBlockByValueTest(normal);
        double errorPercent = RouletteWheel.ComputeErrorPercent(measuredValue, 7142.857142857143);
        //then
        Assertions.assertThat(errorPercent).isLessThan(5);
    }

    @Test
    @DisplayName("Hard Mode")
    public void generateBlockByValueTestHardMode() {
        //given
        GameInfoManager.GameDifficulty hard = GameInfoManager.GameDifficulty.Hard;
        //when
        int measuredValue = RouletteWheel.GenerateBlockByValueTest(hard);
        double errorPercent = RouletteWheel.ComputeErrorPercent(measuredValue, 5714.285714285714);
        //then
        Assertions.assertThat(errorPercent).isLessThan(5);
    }

    @Test
    public void nonFucntionalTestByRouteWheelEasyModePrt(){
        //given
        int[] test = new int[7];
        GameInfoManager.GameDifficulty easy = GameInfoManager.GameDifficulty.Easy;
        //when
        for (int i = 0; i < 50000; i++) {
            int blockShape = RouletteWheel.GenerateBlockByValue(easy);
            test[blockShape]++;
        }
        //then
        for (int i = 0; i < 7; i++) {
            System.out.println(i+"번째:"+test[i]);
        }
    }

    @Test
    public void nonFucntionalTestByRouteWheelHardModePrt(){
        //given
        int[] test = new int[7];
        GameInfoManager.GameDifficulty hard = GameInfoManager.GameDifficulty.Hard;
        //when
        for (int i = 0; i < 50000; i++) {
            int blockShape = RouletteWheel.GenerateBlockByValue(hard);
            test[blockShape]++;
        }
        //then
        for (int i = 0; i < 7; i++) {
            System.out.println(i+"번째:"+test[i]);
        }
    }

    @Test
    public void nonFucntionalTestByRouteWheelNormalPrt(){
        //given
        int[] test = new int[7];
        GameInfoManager.GameDifficulty normal = GameInfoManager.GameDifficulty.Normal;
        //when
        for (int i = 0; i < 50000; i++) {
            int blockShape = RouletteWheel.GenerateBlockByValue(normal);
            test[blockShape]++;
        }
        //then
        for (int i = 0; i < 7; i++) {
            System.out.println(i+"번째:"+test[i]);
        }
    }




}