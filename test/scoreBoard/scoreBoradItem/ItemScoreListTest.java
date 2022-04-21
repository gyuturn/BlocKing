package scoreBoard.scoreBoradItem;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import scoreBoard.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ItemScoreListTest {
    ItemScoreList itemScoreList = ItemScoreList.getInstance();

    @After
    public void afterDeleteAll(){
        itemScoreList.deleteAll();
    }

    @Test
    public void push() {
        //given
        User test1 = new User("test1", 100,"easy");
        User test2 = new User("test2", 100,"easy");
        //when
        itemScoreList.push(test1);
        itemScoreList.push(test2);
        //then
        assertThat(test1).isSameAs(itemScoreList.getList().get(0));
        assertThat(test2).isSameAs(itemScoreList.getList().get(1));
    }

    @Test
    public void deleteAll() {
        //given
        User test1 = new User("test1", 100,"easy");
        User test2 = new User("test2", 100,"easy");
        //when
        itemScoreList.deleteAll();
        //then
        assertThat(itemScoreList.getList().size()).isEqualTo(0);
    }

    @Test
    public void sortDescByScore() {
        //given
        User user1 = new User("test1", 100,"easy");
        User user2 = new User("test2", 60,"easy");
        User user3 = new User("test3", 80,"easy");
        itemScoreList.push(user1);
        itemScoreList.push(user2);
        itemScoreList.push(user3);

        //when
        itemScoreList.sortDescByScore();
        //then
        //비교하기 위해 내림차순으로 넣기
        assertThat(100).isEqualTo(itemScoreList.getList().get(0).getScore());
        assertThat(80).isEqualTo(itemScoreList.getList().get(1).getScore());
        assertThat(60).isEqualTo(itemScoreList.getList().get(2).getScore());
    }
}