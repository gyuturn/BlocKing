package scoreBoard.NoItemScoreBoard;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import scoreBoard.User;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class ScoreInputUITest {

    @Test
    public void createCompleteBtn() {
        //given
        int score=10123;
        String mode = "easy";
        ScoreInputUI scoreInputUI = new ScoreInputUI(score,mode);
        String name= "test123";
        //when
        User user = new User(name, score, mode);
        scoreInputUI.createCompleteBtn(score,mode);
        //then
        assertThat(user.getScore()).isEqualTo(10123);
        assertThat(user.getMode()).isEqualTo("easy");
        assertThat(user.getName()).isEqualTo("test123");

    }
}