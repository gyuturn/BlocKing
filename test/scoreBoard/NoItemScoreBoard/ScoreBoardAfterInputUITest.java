package scoreBoard.NoItemScoreBoard;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scoreBoard.User;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ScoreBoardAfterInputUITest {
    ScoreList scoreList = ScoreList.getInstance();

    @Before
    @After
    public void resetScoreList(){
        scoreList.deleteAll();
    }


    @Test
    public void showScoreList(){
        //given
        int score=10123;
        String mode = "easy";
        String name= "test123";
        User user = new User(name, score, mode);
        ScoreBoardAfterInputUI scoreBoardAfterInputUI = new ScoreBoardAfterInputUI(user);
        //when
        scoreBoardAfterInputUI.showScoreList(user);
        //then
        assertThat(scoreList.getList().get(0).getScore()).isEqualTo(10123);
        assertThat(scoreList.getList().get(0).getMode()).isEqualTo("easy");
        assertThat(scoreList.getList().get(0).getName()).isEqualTo("test123");



    }

}