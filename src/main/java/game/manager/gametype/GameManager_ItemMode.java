package game.manager.gametype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import game.GameUI;
import game.GameUI;
import game.container.BlockGenerator;
import game.container.ItemGenerator;
import game.container.ItemGenerator.ItemType;
import game.manager.BoardManager;
import game.manager.DualModeUtils.UserNumber;
import game.manager.GameInfoManager;
import game.manager.GameManager;
import game.manager.InGameUIManager;
import game.model.BlockController;
import scoreBoard.NoItemScoreBoard.ScoreInputUI;

public class GameManager_ItemMode extends GameManager {
    
//#region Singleton

    private GameManager_ItemMode(int index) {
        this.index = index;
    }

    private static GameManager_ItemMode instance = new GameManager_ItemMode(0);
    private static GameManager_ItemMode instance2 = new GameManager_ItemMode(1);

    private static ArrayList<GameManager_ItemMode> gameManagerList =
        new ArrayList<GameManager_ItemMode>(Arrays.asList(instance, instance2));

    public static GameManager_ItemMode getInstance(int i) {
        return gameManagerList.get(i);
    }

//#endregion


//#region GameFramework

private ItemType curItem = ItemType.None;

private Step curStep = Step.GameReady;

public enum Step {

    GameReady,
    
    //while !gameOver
    CreateNewBlock,
    BlockMove,
    EraseAnimation,
    EraseLine,
    SetGameBalance,
    CheckGameOver,
    
    CheckItemUse,
    GameOver
}

@Override
protected void gameFramework() { //전체적인 게임의 동작 흐름
    
    switch(curStep) {
        case GameReady:
            curStep = gameReady();
            break;

        case CreateNewBlock:
            curStep = createNewBlock();
            break;

        case BlockMove:
            curStep = blockMove();
            if(curStep != Step.BlockMove) gameFramework();
            break;

        case EraseAnimation:
            curStep = eraseAnimation();
            break;

        case EraseLine:
            curStep = eraseLine();
            gameFramework();
            break;

        case SetGameBalance:
            curStep = setGameBalance();
            gameFramework();
            break;

        case CheckItemUse:
            curStep = checkItemUse();
            gameFramework();
            break;
        
        case CheckGameOver:
            curStep = checkGameOver();
            break;

        case GameOver :
            gameOver();
            break;

    }
}

private Step gameReady() {
    //게임을 준비합니다.
    System.out.println("아이템모드");
    initKeyListener();
    initGameStatus();
    initBlockGenerator();
    initBoardManage();
    isPlaying = true;

    return Step.CreateNewBlock;
}

public Step createNewBlock() {
    BlockGenerator.getInstance().addBlock();
    curBlock = BlockGenerator.getInstance().createBlock(index);

    BlockController nextBlock = BlockGenerator.getInstance().blockQueue.peek();
    BoardManager.getInstance(index).setNextBlockColor(nextBlock);
    InGameUIManager.getInstance().drawNextBlockInfo(nextBlock, index);
    onBlockCreate();

    return Step.BlockMove;
}

private Step blockMove() {
    isBlockMovable = BoardManager.getInstance(index).checkBlockMovable(curBlock);

    if (isBlockMovable) {
        BoardManager.getInstance(index).translateBlock(curBlock, 1, 0);
        onBlockMove();
        return Step.BlockMove;
    } else {
        //무게추 검사 -> 맞으면 아이템 사용, 아니면 Erase animation
        if (curItem == ItemType.Weight) {
            if (checkCurBlockIsWeight()) {
                if (UserNumber.getInstance().user == 2) {
                    GameUI.getInstance().pane[0].removeKeyListener(interaction_play);  //땅에 닿은경우 블록 이동 제한
                    GameUI.getInstance().pane[1].removeKeyListener(interaction_play);  //땅에 닿은경우 블록 이동 제한
                } else {
                    GameUI.getInstance().pane[0].removeKeyListener(interaction_play);  //땅에 닿은경우 블록 이동 제한
                }
                return WeightBlockMove();
            }
        }
        return Step.EraseAnimation;
    }
}


private boolean checkCurBlockIsWeight(){
    char[][] mugechuBlock;
    mugechuBlock = new char[][]{
            {' ', 'O', 'O', ' '},
            {'O', 'O', 'O', 'O'}
    };

    for (int i = 0; i < curBlock.shape.length; i++) {
        for (int j = 0; j < curBlock.shape[i].length; j++) {
            if (curBlock.shape[i][j]!=mugechuBlock[i][j]) {
                return false;
            }
        }
    }
    return true;

}

private Step WeightBlockMove(){
    boolean isWeightMovable = BoardManager.getInstance(index).checkWeightMovable(curBlock);
    if(isWeightMovable){
        BoardManager.getInstance(index).useWeight(curBlock);
        BoardManager.getInstance(index).eraseBlock(curBlock);
        BoardManager.getInstance(index).setBlockPos(curBlock, curBlock.posRow+1, curBlock.posCol);
        return Step.BlockMove;
    }
    else{
        //이동제한 다시 풀기
        if(UserNumber.getInstance().user==2) {
            GameUI.getInstance().pane[0].addKeyListener(interaction_play);
            GameUI.getInstance().pane[1].addKeyListener(interaction_play);
        }else {
            GameUI.getInstance().pane[0].addKeyListener(interaction_play);
        }

        return Step.EraseAnimation;
    }

}

private Step eraseAnimation() {

    BoardManager.getInstance(index).eraseEvent(targetLineIndexList, index);
    
    return Step.EraseLine;
}

private Step eraseLine() {
    int curLineCount = BoardManager.getInstance(index).eraseFullLine();
    onLineErase(curLineCount);

    return Step.SetGameBalance;
}

private Step setGameBalance() {
    curSpeed = basicSpeed + addSpeed * (lineCount + blockCount);
    timeScale = maxSpeed / curSpeed;
    setTimeScale(timeScale);

    return Step.CheckItemUse;
}

private Step checkItemUse() {
    return Step.CheckGameOver;
}

private Step checkGameOver() {
    BlockController nextBlock = BlockGenerator.getInstance().blockQueue.peek();
    for(int i=0; i<nextBlock.height(); i++) {
        for(int j=0; j<nextBlock.width(); j++) {
            if(nextBlock.getShape(i, j) != ' ') {
                if(BoardManager.getInstance(index).board[i][j + 5] != ' ')
                    return checkResurrectionUse();
            }
        }
    }
    return Step.CreateNewBlock;
}

@Override
protected void gameOver() {
    onGameEnd();
    new ScoreInputUI(score,GameInfoManager.getInstance().difficultyToString(difficulty));

}

//#endregion

//#region init

public void initKeyListener() {
    interaction_play = new Interaction_Play();
    interaction_utils = new Interaction_Utils();
    //GameUI.getInstance().pane[index].addKeyListener(interaction_play);
    //GameUI.getInstance().pane[index].addKeyListener(interaction_utils);

    if(UserNumber.getInstance().user==2) {
        GameUI.getInstance().pane[0].addKeyListener(interaction_play);
        GameUI.getInstance().pane[0].addKeyListener(interaction_utils);
        GameUI.getInstance().pane[1].addKeyListener(interaction_play);
        GameUI.getInstance().pane[1].addKeyListener(interaction_utils);
    }
    else{
        GameUI.getInstance().pane[0].addKeyListener(interaction_play);
        GameUI.getInstance().pane[0].addKeyListener(interaction_utils);
    }
}

@Override
protected void initGameStatus() {
    blockCount = 0;
    lineCount = 0;
    score = 0;
    curSpeed = basicSpeed;
    
    curBlock = null;
    
    difficulty = GameInfoManager.getInstance().difficulty;
    addSpeed = GameInfoManager.getInstance().difficultiesMap.get(difficulty).getAddSpeed();
}

protected void initBoardManage() {
    BoardManager.getInstance(index).initBoard();
}

protected void initBlockGenerator() {
    BlockGenerator.getInstance().initBlockQueue();
}

//#endregion

//#region Events
private int onBlockMove() {
    
    if(curItem == ItemType.DoubleBonusChance) {
        score += curSpeed;
    }
    score += curSpeed;
    InGameUIManager.getInstance().drawScore(index);
    
    return 0;
}

private int onLineErase(int count) {
    if(curItem == ItemType.DoubleBonusChance) {
        score += curSpeed * count * 10;
    }
    score += curSpeed * count * 10;

    if(count > 2) {
        if(curItem == ItemType.DoubleBonusChance) {
            score += 10000;
        }
        score += 10000;
    }
    InGameUIManager.getInstance().drawScore(index);

    lineCount += count;

    //checkAddItem();

    return 0;
}

private int onBlockCreate() {

    if(curItem == ItemType.DoubleBonusChance) {
        score += curSpeed;
    }
    score += curSpeed;
    blockCount++;
    InGameUIManager.getInstance().drawScore(index);
    checkAddItem();
    return 0;
}

private void onGameEnd() {
    stopGameFramework();
    
    isPlaying = false;
    curStep = Step.GameReady;
}

//#endregion

//#region item logic
private Step checkResurrectionUse() { //
    if(curItem == ItemType.Resurrection) {
        BoardManager.getInstance(index).eraseHalfBoard();
        curItem = ItemType.None;
        return Step.CreateNewBlock;
    }
    else {
        return Step.GameOver;
    }

}

private void checkDoubleBonusChance() { //
    
}


private void checkSmallBlockChance() {

}

private void checkWeightUse() {

}



//#region Utils
private void checkAddItem() {

    if( lineCount > 0) //a-b>10 b -= 10;
    {
        BlockController targetBlock = BlockGenerator.getInstance().blockQueue.peek();
        ItemType itemType = ItemGenerator.getInstance().SelectRandomItem();

        System.out.println(itemType);

        if(curItem == ItemType.SmallBlockChance) {

            ItemGenerator.getInstance().setOneBlock(targetBlock);
            curItem = ItemType.None;
            return;
        }

        //무게추아이템 test
        itemType = ItemType.Weight;

        switch(itemType) {
            case Weight:
                ItemGenerator.getInstance().setBlockMugechu(targetBlock);
                curItem = ItemType.Weight;
                break;
            case LineClear:
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'L');
                curItem = ItemType.LineClear;
                break;
            case Resurrection:
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'R');
                curItem = ItemType.Resurrection;
                break;
            case DoubleBonusChance:
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'D');
                curItem = ItemType.DoubleBonusChance;
                break;
            case SmallBlockChance:
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'S');
                curItem = ItemType.SmallBlockChance;
                break;
        }
        BlockController nextBlock = BlockGenerator.getInstance().blockQueue.peek();
        BoardManager.getInstance(index).setNextBlockColor(nextBlock);
        InGameUIManager.getInstance().drawNextBlockInfo(nextBlock, index);
    }
}
//#endregion

//#region Interaction

public class Interaction_Play implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
            
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("e.getKeyCode() = " + e.getKeyCode());
        if (keySetting.getDownBlock() == e.getKeyCode()) {
            blockMove();
            InGameUIManager.getInstance().drawScore(index);
            InGameUIManager.getInstance().drawBoard(index);
            //System.out.println("input down");
        }
        else if (keySetting.getRight() == e.getKeyCode()) {
            BoardManager.getInstance(index).translateBlock(curBlock, 0, 1);
            InGameUIManager.getInstance().drawBoard(index);
            //System.out.println("input right");
        }
        else if (keySetting.getLeft() == e.getKeyCode()) {
            BoardManager.getInstance(index).translateBlock(curBlock, 0, -1);
            InGameUIManager.getInstance().drawBoard(index);
            //System.out.println("input left");
        }
        else if (keySetting.getTurnBlock() == e.getKeyCode()) {
            //System.out.println(curStep);
            BoardManager.getInstance(index).eraseBlock(curBlock);
            curBlock.rotate();
            if(!BoardManager.getInstance(index).checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                curBlock.rotate();
                curBlock.rotate();
                curBlock.rotate();
            }
            BoardManager.getInstance(index).setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
            InGameUIManager.getInstance().drawBoard(index);
            //System.out.println("input up");
        } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
            while(BoardManager.getInstance(index).checkBlockMovable(curBlock)) {
                BoardManager.getInstance(index).translateBlock(curBlock, 1, 0);
                InGameUIManager.getInstance().drawScore(index);
            }
            timer.restart();
            InGameUIManager.getInstance().drawBoard(index);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

public class Interaction_Utils implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(keySetting.getStop() == e.getKeyCode()) {
            BoardManager.getInstance(index).printBoard();
            if(timer.isRunning())
                stopGameFramework();
            else
                restartGameFramework();
        }
        /*
        if(keySetting.getEscape() == e.getKeyCode()) {
            new StartUI();
        }
        */
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

//#endregion
}
