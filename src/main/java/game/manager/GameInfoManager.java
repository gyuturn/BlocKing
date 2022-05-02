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
        initTypeData();
    }

//#endregion

//#region GameType

    public enum GameMode {
        BasicMode,
        ItemMode,
        BattleMode_Basic,
        BattleMode_Item
    }

    public enum GameDifficulty {
        Easy,
        Normal,
        Hard
    }

    public GameMode mode = GameMode.BasicMode;
    public GameDifficulty difficulty = GameDifficulty.Easy;

//#endregion

//#region Type Data

    
    public HashMap<GameDifficulty, Difficulty> difficultiesMap = new HashMap<GameDifficulty, Difficulty>();
    public HashMap<GameDifficulty, String> difficulty_to_string = new HashMap<GameDifficulty, String>();

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
        difficulty_to_string.put(GameDifficulty.Easy, "Easy :D");

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
        difficulty_to_string.put(GameDifficulty.Normal, "Normal D:");

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
        
        difficultiesMap.put(GameDifficulty.Hard, hard);
        difficulty_to_string.put(GameDifficulty.Hard, "Hard D;");
    }

    public String difficultyToString(GameDifficulty difficulty) {
        return difficulty_to_string.get(difficulty);
    }

//#endregion
}


