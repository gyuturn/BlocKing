package game.container;

import game.manager.BoardManager;
import game.model.BlockController;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class BlockGeneratorTest {

    @Test
    @DisplayName("랜덤한 숫자 생성 test")//0~6의 범위 생성
    public void getRandomNumber() {
        //given
        BlockGenerator instance = BlockGenerator.getInstance();
        //when
        instance.createBlock();
        int block = instance.getBlock();
        //then
        boolean test;
        if (block >= 0 & block <= 6) {
            test = true;
        } else {
            test = false;
        }
        assertThat(test).isEqualTo(true);
    }

    @Test
    @DisplayName("랜덤한 숫자에 따른 블록 생성(0행 5열에 생성)")
    public void generateBlock(){
        //given
        BlockGenerator instance = BlockGenerator.getInstance();
        //when
        BlockController block = instance.createBlock();
        BlockController createdBlock = BoardManager.getInstance().setBlockPos(block, 0, 5);
        //then
        assertThat(createdBlock.posRow).isEqualTo(0);
        assertThat(createdBlock.posCol).isEqualTo(5);


    }
}