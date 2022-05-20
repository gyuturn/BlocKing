package scoreBoard.scoreBoradItem;

import org.junit.Test;
import scoreBoard.NoItemScoreBoard.ScoreBoardUI;
import scoreBoard.NoItemScoreBoard.ScoreList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ItemScoreBoardUITest {

    ItemScoreList itemScoreList = ItemScoreList.getInstance();

    @Test
    public void showScoreListForStartMenu(){
        //given
        ItemScoreBoardUI itemScoreBoardUI = new ItemScoreBoardUI();
        //when
        itemScoreBoardUI.showScoreListForStartMenu();
        //then
        if (itemScoreList.getList().size() >= 10) {
            assertThat(itemScoreBoardUI.listToIndex).isEqualTo(10);
        }
        else{
            assertThat(itemScoreBoardUI.listToIndex).isEqualTo(itemScoreList.getList().size());
        }
    }

}