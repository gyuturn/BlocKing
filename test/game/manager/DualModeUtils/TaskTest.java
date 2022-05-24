package game.manager.DualModeUtils;

import game.manager.GameInfoManager;
import game.manager.gametype.GameManager_BasicMode;
import game.manager.gametype.GameManager_ItemMode;
import game.manager.gametype.GameManager_TimeAttackMode;
import org.junit.Test;
import org.assertj.core.api.Assertions;


import static org.assertj.core.api.Assertions.*;

public class TaskTest {
    GameInfoManager gameInfoManager = GameInfoManager.getInstance();

    @Test
    public void runBasicMode(){
        //given
        gameInfoManager.mode = GameInfoManager.GameMode.BasicMode;
        //when
        Task.runBasicMode(0);
        //then
        GameManager_BasicMode.getInstance(0).startGameFramework();
    }

    @Test
    public void runItemMode(){
        //given
        gameInfoManager.mode = GameInfoManager.GameMode.ItemMode;
        //when
        Task.runItemMode(0);
        //then
        GameManager_ItemMode.getInstance(0).startGameFramework();
    }

    @Test
    public void runTimeAttackMode(){
        //given
        gameInfoManager.mode = GameInfoManager.GameMode.TimeAttackMode;
        //when
        Task.runTimeAttackMode(0);
        //then
        GameManager_TimeAttackMode.getInstance(0).startGameFramework();
    }
}
