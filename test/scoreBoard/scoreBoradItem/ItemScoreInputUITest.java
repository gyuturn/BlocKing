package scoreBoard.scoreBoradItem;

import org.junit.Test;
import scoreBoard.NoItemScoreBoard.ScoreInputUI;
import scoreBoard.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ItemScoreInputUITest {
    @Test
    public void createCompleteBtn() {
        //given
        int score=10123;
        String mode = "easy";
        ItemScoreInputUI itemScoreInputUI = new ItemScoreInputUI(score,mode);
        String name= "test123";
        //when
        User user = new User(name, score, mode);
        itemScoreInputUI.createCompleteBtn(score,mode);
        //then
        assertThat(user.getScore()).isEqualTo(10123);
        assertThat(user.getMode()).isEqualTo("easy");
        assertThat(user.getName()).isEqualTo("test123");

    }
}
