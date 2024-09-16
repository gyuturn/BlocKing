package game.container;

import game.manager.BoardManager;
import game.manager.GameManager;
import game.manager.gametype.GameManager_ItemMode;
import game.model.BlockController;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

import static game.container.ItemGenerator.ItemType.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ItemGeneratorTest {
    BoardManager boardManager = BoardManager.getInstance(0);
    ItemGenerator itemGenerator = ItemGenerator.getInstance();
    GameManager gameManager = GameManager_ItemMode.getInstance(0);


    @Test
    public void SelectRandomItem(){
        //given
        Random random = new Random();
        int randomNum = random.nextInt(5);
        //when
        ItemGenerator.ItemType randomItem = itemGenerator.SelectRandomItem();
        //then
        if(randomItem==Weight){
            Assertions.assertThat(randomItem).isEqualTo(Weight);
        }
        else if(randomItem==LineClear){
            Assertions.assertThat(randomItem).isEqualTo(LineClear);
        }
        else if(randomItem==Resurrection){
            Assertions.assertThat(randomItem).isEqualTo(Resurrection);
        }
        else if(randomItem==DoubleBonusChance){
            Assertions.assertThat(randomItem).isEqualTo(DoubleBonusChance);
        }
        else if(randomItem==SmallBlockChance){
            Assertions.assertThat(randomItem).isEqualTo(SmallBlockChance);
        }

    }

}
