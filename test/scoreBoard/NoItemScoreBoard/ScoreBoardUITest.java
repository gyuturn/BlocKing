package scoreBoard.NoItemScoreBoard;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ScoreBoardUITest {
    ScoreList scoreList = ScoreList.getInstance();

    @Test
    public void showScoreListForStartMenu(){
        //given
        ScoreBoardUI scoreBoardUI = new ScoreBoardUI();
        //when
        scoreBoardUI.showScoreListForStartMenu();
        //then
        if (scoreList.getList().size() >= 10) {
            assertThat(scoreBoardUI.listToIndex).isEqualTo(10);
        }
        else{
            assertThat(scoreBoardUI.listToIndex).isEqualTo(scoreList.getList().size());
        }
    }

}