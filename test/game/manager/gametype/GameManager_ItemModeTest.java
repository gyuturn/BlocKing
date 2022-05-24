package game.manager.gametype;

import game.container.BlockGenerator;
import game.manager.BoardManager;
import game.manager.GameInfoManager;
import game.model.BlockController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import setting.KeySetting;

import static org.assertj.core.api.Assertions.assertThat;

public class GameManager_ItemModeTest {

    GameManager_ItemMode gameManager_itemMode = GameManager_ItemMode.getInstance(0);
    GameInfoManager gameInfoManager = GameInfoManager.getInstance();
    BoardManager boardManager = BoardManager.getInstance(0);
    KeySetting keySetting = KeySetting.getInstance();

    @Test
    public void initGameStatus(){
        //given
        gameInfoManager.difficulty = GameInfoManager.GameDifficulty.Easy;
        //then
        gameManager_itemMode.initGameStatus();
        //when
        Assertions.assertThat(gameManager_itemMode.getDifficulty()).isSameAs(GameInfoManager.GameDifficulty.Easy);
        Assertions.assertThat(gameManager_itemMode.getCurBlock()).isSameAs(null);
        Assertions.assertThat(gameManager_itemMode.getBlockCount()).isSameAs(0);
        Assertions.assertThat(gameManager_itemMode.getScore()).isSameAs(0);
    }
    @Test
    public void initBlockGenerator(){
        //given
        gameManager_itemMode.initBlockGenerator();
        gameManager_itemMode.initBoardManage();
        //when
        BlockGenerator.getInstance(0).addBlock();
        gameManager_itemMode.curBlock = BlockGenerator.getInstance(0).createBlock(0);
        //then
        Assertions.assertThat(gameManager_itemMode.getCurBlock()).isNotNull();
    }

    @Test
    public void blockMove(){
        //given
        initBlockGenerator();
        //when
        GameManager_ItemMode.Step step = gameManager_itemMode.blockMove();
        //then
        Assertions.assertThat(step).isSameAs(GameManager_ItemMode.Step.BlockMove);
    }

    @Test
    public void checkGameOver() {
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
        GameManager_ItemMode.Step step = gameManager_itemMode.checkGameOver();
        //then
        assertThat(step).isSameAs(GameManager_ItemMode.Step.GameOver);
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
            GameManager_ItemMode.Step step = gameManager_itemMode.checkGameOver();
            //then
            assertThat(step).isSameAs(GameManager_ItemMode.Step.CreateNewBlock);
        }

        @Test
        public void CheckAddItem(){
            //given
            //when
            //then
        }

    @Test
    public void onGameEnd(){
        //given
        //when
        gameManager_itemMode.curStep = GameManager_ItemMode.Step.GameReady;
        //then
        Assertions.assertThat(gameManager_itemMode.curStep).isSameAs(GameManager_ItemMode.Step.GameReady);
    }

    @Test
    public void keyPressedBlockLeftMove(){
        //given
        keySetting.resetDefault();
        initBlockGenerator();
        //when
        GameManager_ItemMode.Step step = null;
        if (keySetting.getLeft() ==37) {
            gameManager_itemMode.blockMove();
            step = gameManager_itemMode.blockMove();
        }
        //then
        Assertions.assertThat(step).isSameAs(GameManager_ItemMode.Step.BlockMove);
    }

    @Test
    public void keyPressedBlockRightMove(){
        //given
        keySetting.resetDefault();
        initBlockGenerator();
        //when
        GameManager_ItemMode.Step step = null;
        if (keySetting.getRight() ==39) {
            gameManager_itemMode.blockMove();
            step = gameManager_itemMode.blockMove();
        }
        //then
        Assertions.assertThat(step).isSameAs(GameManager_ItemMode.Step.BlockMove);
    }

    @Test
    public void keyPressedTurnBlock(){
        //given
        keySetting.resetDefault();
        initBlockGenerator();
        //when
        GameManager_ItemMode.Step step = null;
        BlockController curBlock = gameManager_itemMode.curBlock;
        if (keySetting.getTurnBlock() ==38) {
            BoardManager.getInstance(0).eraseBlock(curBlock);
            curBlock.rotate();
            if(!BoardManager.getInstance(0).checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                curBlock.rotate();
                curBlock.rotate();
                curBlock.rotate();
            }
            BoardManager.getInstance(0).setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
            step = gameManager_itemMode.blockMove();
        }
        //then
        Assertions.assertThat(step).isSameAs(GameManager_ItemMode.Step.BlockMove);
    }

    }

