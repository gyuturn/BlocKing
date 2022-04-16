package game.manager;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class BoardManagerTest {

    @Test
    @DisplayName("보드판 생성 test(20행 10열)")//실제는 22행 12열 이지만 각 끝의 xx도 포함하여 22행 12열임
    public void createBoardTest() {
        //given
        BoardManager boardInstance = BoardManager.getInstance();
        //when
        int row = boardInstance.board.length;
        int col = boardInstance.board[0].length;
        for (int i = 0; i < boardInstance.board.length; i++) {
            col=boardInstance.board[i].length;
            if(col!=12){
                col=-1;
            }
        }
        //then
        Assertions.assertThat(row).isEqualTo(22);
        Assertions.assertThat(col).isEqualTo(12);
    }

}