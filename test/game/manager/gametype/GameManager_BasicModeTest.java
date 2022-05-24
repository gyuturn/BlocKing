package game.manager.gametype;

import game.GameUI;
import game.container.BlockGenerator;
import game.manager.BoardManager;
import game.manager.GameInfoManager;
import game.manager.InGameUIManager;
import game.model.BlockController;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import setting.KeySetting;

import javax.swing.*;

import static org.assertj.core.api.Assertions.*;

public class GameManager_BasicModeTest {

    GameManager_BasicMode gameManager_basicMode = GameManager_BasicMode.getInstance(0);
    GameInfoManager gameInfoManager = GameInfoManager.getInstance();
    BoardManager boardManager = BoardManager.getInstance(0);
    KeySetting keySetting = KeySetting.getInstance();

    @After
    @Before
    public void resetBoard() {
        boardManager.board = new char[][]{
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //1행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //10행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //20행
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };
        keySetting.resetDefault();
    }


    @Test
    public void initGameStatus() {

        //given
        gameInfoManager.difficulty = GameInfoManager.GameDifficulty.Easy;
        //when
        gameManager_basicMode.initGameStatus();
        //then
        Assertions.assertThat(gameManager_basicMode.getDifficulty()).isSameAs(GameInfoManager.GameDifficulty.Easy);
        Assertions.assertThat(gameManager_basicMode.getCurBlock()).isSameAs(null);
        Assertions.assertThat(gameManager_basicMode.getBlockCount()).isSameAs(0);
        Assertions.assertThat(gameManager_basicMode.getScore()).isSameAs(0);
    }

    @Test
    public void initBlockGenerator(){
        //given
        gameManager_basicMode.initBlockGenerator();
        gameManager_basicMode.initBoardManage();
        //when
        BlockGenerator.getInstance(0).addBlock();
        gameManager_basicMode.curBlock = BlockGenerator.getInstance(0).createBlock(0);
        //then
        Assertions.assertThat(gameManager_basicMode.getCurBlock()).isNotNull();
    }

    @Test
    public void blockMove(){
        //given
        gameManager_basicMode.initBlockGenerator();
        //when
        GameManager_BasicMode.Step step = gameManager_basicMode.blockMove();
        //then
        Assertions.assertThat(step).isSameAs(GameManager_BasicMode.Step.BlockMove);
    }

    @Test
    public void checkLineDelete(){
        //given
        boardManager.board = new char[][]{
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //1행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //10행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'}, //20행
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };

        //when
        int curLineCount = BoardManager.getInstance(0).eraseFullLine(0);
         gameManager_basicMode.onLineErase(curLineCount);
        //then
        Assertions.assertThat(curLineCount).isEqualTo(3);

    }

    @Test
    public void checkGameOver(){
        //given
        initBlockGenerator();
        boardManager.board = new char[][]{
                {'X', ' ', ' ', ' ', 'O', 'O', 'O', 'O', ' ', ' ', ' ', 'X'}, //1행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //10행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'}, //20행
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };
        //when
        GameManager_BasicMode.Step step = gameManager_basicMode.checkGameOver();
        //then
        assertThat(step).isSameAs(GameManager_BasicMode.Step.GameOver);
    }

    @Test
    public void checkGameOverNotGameOver(){
        //given
        initBlockGenerator();
        boardManager.board = new char[][]{
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //1행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}, //10행
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'}, //20행
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}
        };
        //when
        GameManager_BasicMode.Step step = gameManager_basicMode.checkGameOver();
        //then
        assertThat(step).isSameAs(GameManager_BasicMode.Step.CreateNewBlock);
    }

    @Test
    public void onGameEnd(){
        //given
        //when
        gameManager_basicMode.curStep = GameManager_BasicMode.Step.GameReady;
        //then
        Assertions.assertThat(gameManager_basicMode.curStep).isSameAs(GameManager_BasicMode.Step.GameReady);
    }

    @Test
    public void keyPressedBlockLeftMove(){
        //given
        keySetting.resetDefault();
        initBlockGenerator();
        //when
        GameManager_BasicMode.Step step = null;
        if (keySetting.getLeft() ==37) {
            blockMove();
            step = gameManager_basicMode.blockMove();
        }
        //then
        Assertions.assertThat(step).isSameAs(GameManager_BasicMode.Step.BlockMove);
    }

    @Test
    public void keyPressedBlockRightMove(){
        //given
        keySetting.resetDefault();
        initBlockGenerator();
        //when
        GameManager_BasicMode.Step step = null;
        if (keySetting.getRight() ==39) {
            blockMove();
            step = gameManager_basicMode.blockMove();
        }
        //then
        Assertions.assertThat(step).isSameAs(GameManager_BasicMode.Step.BlockMove);
    }

    @Test
    public void keyPressedTurnBlock(){
        //given
        keySetting.resetDefault();
        initBlockGenerator();
        //when
        GameManager_BasicMode.Step step = null;
        BlockController curBlock = gameManager_basicMode.curBlock;
        if (keySetting.getTurnBlock() ==38) {
            BoardManager.getInstance(0).eraseBlock(curBlock);
            curBlock.rotate();
            if(!BoardManager.getInstance(0).checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                curBlock.rotate();
                curBlock.rotate();
                curBlock.rotate();
            }
            BoardManager.getInstance(0).setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
            step = gameManager_basicMode.blockMove();
        }
        //then
        Assertions.assertThat(step).isSameAs(GameManager_BasicMode.Step.BlockMove);
    }




}