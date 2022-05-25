package game.manager.gametype;

import game.GameUI;
import game.manager.BoardManager;
import game.manager.GameInfoManager;
import org.junit.Test;
import setting.KeySetting;

public class NonFuntionalTest {

    GameManager_BasicMode gameManager_basicMode = GameManager_BasicMode.getInstance(0);
    GameManager_ItemMode gameManager_itemMode = GameManager_ItemMode.getInstance(0);
    GameInfoManager gameInfoManager = GameInfoManager.getInstance();
    BoardManager boardManager = BoardManager.getInstance(0);
    KeySetting keySetting = KeySetting.getInstance();
    GameUI gameUI = new GameUI();

    @Test
    public void RunningTimeBasicGameFlow(){
        long before = System.currentTimeMillis();
        gameManager_basicMode.curStep = GameManager_BasicMode.Step.GameReady;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.CreateNewBlock;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.BlockMove;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.EraseAnimation;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.EraseLine;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.SetGameBalance;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.CheckGameOver;
        gameManager_basicMode.gameFramework();


        long after = System.currentTimeMillis();
        System.out.println("basicmode 시간:"+(after-before)+"ms");

    }

    @Test
    public void RunningTimeSendAttackGameFlow(){
        long before = System.currentTimeMillis();
        gameManager_basicMode.curStep = GameManager_BasicMode.Step.GameReady;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.CreateNewBlock;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.BlockMove;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.EraseAnimation;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.EraseLine;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.SendAttackBoard;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.SetGameBalance;
        gameManager_basicMode.gameFramework();

        gameManager_basicMode.curStep = GameManager_BasicMode.Step.CheckGameOver;
        gameManager_basicMode.gameFramework();


        long after = System.currentTimeMillis();
        System.out.println("공격 포함 시간:"+(after-before)+"ms");

    }
//
//    @Test
//    public void RunningTimeItemModeGameFlow(){
//        long before = System.currentTimeMillis();
//
////        gameManager_itemMode.startGameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.GameReady;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.CreateNewBlock;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.BlockMove;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.EraseAnimation;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.EraseLine;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.SendAttackBoard;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.SetGameBalance;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.CheckItemUse;
//        gameManager_itemMode.gameFramework();
//
//        gameManager_itemMode.curStep = GameManager_ItemMode.Step.CheckGameOver;
//        gameManager_itemMode.gameFramework();
//
//
//        long after = System.currentTimeMillis();
//        System.out.println("아이템모드 시간:"+(after-before)+"ms");
//
//    }
}
