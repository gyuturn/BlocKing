package scoreBoard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ScoreListTest {
    ScoreList scoreList = ScoreList.getInstance();
    @AfterEach
    public void deleteAll(){
        scoreList.deleteAll();
    }

    @Test
    public void ScoreListSortByDescTest() {
        //given
        User user1 = new User("test1", 100);
        User user2 = new User("test2", 60);
        User user3 = new User("test3", 80);


        scoreList.push(user1);
        scoreList.push(user2);
        scoreList.push(user3);

        //when
        scoreList.sortDescByScore();
        //then
        //비교하기 위해 내림차순으로 넣기
        Assertions.assertEquals(100, scoreList.getList().get(0).getScore());
        Assertions.assertEquals(80, scoreList.getList().get(1).getScore());
        Assertions.assertEquals(60, scoreList.getList().get(2).getScore());

    }

}