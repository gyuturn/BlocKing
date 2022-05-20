package scoreBoard.scoreBoradItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scoreBoard.NoItemScoreBoard.ScoreBoardAfterInputUI;
import scoreBoard.NoItemScoreBoard.ScoreList;
import scoreBoard.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ItemScoreBoardAfterInputUITest {

    ItemScoreList itemScoreList = ItemScoreList.getInstance();

    @Before
    @After
    public void resetScoreList(){
        itemScoreList.deleteAll();
    }


    @Test
    public void showScoreList(){
        //given
        int score=10123;
        String mode = "easy";
        String name= "test123";
        User user = new User(name, score, mode);
        ItemScoreBoardAfterInputUI itemScoreBoardAfterInputUI = new ItemScoreBoardAfterInputUI(user);
        //when
        itemScoreBoardAfterInputUI.showScoreList(user);
        //then
        assertThat(itemScoreList.getList().get(0).getScore()).isEqualTo(10123);
        assertThat(itemScoreList.getList().get(0).getMode()).isEqualTo("easy");
        assertThat(itemScoreList.getList().get(0).getName()).isEqualTo("test123");

    }

}