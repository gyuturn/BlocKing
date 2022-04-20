package game.manager;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import game.model.difficulty.Difficulty;

public class GameInfoManager {

//#region Singleton

    private static GameInfoManager instance = new GameInfoManager();
    
    public static GameInfoManager getInstance() {
        return instance;
    }

    private GameInfoManager() {

    }

//#endregion

//#region GameType

    public enum GameMode {
        BasicMode,
        ItemMode
    }

    public enum GameDifficulty {
        Easy,
        Normal,
        Hard
    }

//#endregion

//#region Type Data
    
    public HashMap<GameDifficulty, Difficulty> difficultiesMap = new HashMap<GameDifficulty, Difficulty>();

    private void initTypeData() {

        Difficulty easy = new Difficulty(
            8,
            1.2,
            1,
            1,
            1,
            1,
            1,
            1
        );
        
        difficultiesMap.put(GameDifficulty.Easy, easy);

        Difficulty normal = new Difficulty(
            10,
            1,
            1,
            1,
            1,
            1,
            1,
            1
        );
        
        difficultiesMap.put(GameDifficulty.Normal, normal);

        Difficulty hard = new Difficulty(
            12,
            0.8,
            1,
            1,
            1,
            1,
            1,
            1
        );
        
        difficultiesMap.put(GameDifficulty.Easy, hard);
    }

//#endregion
}


