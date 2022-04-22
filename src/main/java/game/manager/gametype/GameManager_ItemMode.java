package game.manager.gametype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameUI;
import game.container.BlockGenerator;
import game.container.ItemGenerator;
import game.container.ItemGenerator.ItemType;
import game.manager.BoardManager;
import game.manager.GameInfoManager;
import game.manager.GameManager;
import game.manager.InGameUIManager;
import game.model.BlockController;
import scoreBoard.NoItemScoreBoard.ScoreInputUI;

public class GameManager_ItemMode extends GameManager {
    
//#region Singleton

    private static GameManager_ItemMode instance = new GameManager_ItemMode();
    
    public static GameManager_ItemMode getInstance() {
        return instance;
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
    initKeyListener();
    initGameStatus();
    initBlockGenerator();
    initBoardManage();
    isPlaying = true;

    return Step.CreateNewBlock;
}

public Step createNewBlock() {
    BlockGenerator.getInstance().addBlock();
    curBlock = BlockGenerator.getInstance().createBlock();

    BlockController nextBlock = BlockGenerator.getInstance().blockQueue.peek();
    BoardManager.getInstance().setNextBlockColor(nextBlock);
    InGameUIManager.getInstance().drawNextBlockInfo(nextBlock);
    onBlockCreate();

    return Step.BlockMove;
}

private Step blockMove() {
    isBlockMovable = BoardManager.getInstance().checkBlockMovable(curBlock);
    if(isBlockMovable) {
        BoardManager.getInstance().translateBlock(curBlock, 1, 0);
        onBlockMove();
        return Step.BlockMove;
    }
    else //무게추 검사 -> 맞으면 아이템 사용, 아니면 Erase animation
        return Step.EraseAnimation;
}

private Step eraseAnimation() {

    BoardManager.getInstance().eraseEvent(targetLineIndexList);
    
    return Step.EraseLine;
}

private Step eraseLine() {
    int curLineCount = BoardManager.getInstance().eraseFullLine();
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
                if(BoardManager.getInstance().board[i][j + 5] != ' ')
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
    GameUI.getInstance().pane.addKeyListener(interaction_play);
    GameUI.getInstance().pane.addKeyListener(interaction_utils);
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
    BoardManager.getInstance().initBoard();
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
    InGameUIManager.getInstance().drawScore();
    
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
    InGameUIManager.getInstance().drawScore();

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
    InGameUIManager.getInstance().drawScore();
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
        BoardManager.getInstance().eraseHalfBoard();
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

    if(lineCount % 10 == 0 && lineCount > 0) //a-b>10 b -= 10;
    {
        BlockController targetBlock = BlockGenerator.getInstance().blockQueue.peek();
        ItemType itemType = ItemGenerator.getInstance().SelectRandomItem();

        System.out.println(itemType);

        if(curItem == ItemType.SmallBlockChance) {

            ItemGenerator.getInstance().setOneBlock(targetBlock);
            curItem = ItemType.None;
            return;
        }

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
        BoardManager.getInstance().setNextBlockColor(nextBlock);
        InGameUIManager.getInstance().drawNextBlockInfo(nextBlock);
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
            InGameUIManager.getInstance().drawScore();
            InGameUIManager.getInstance().drawBoard();
            //System.out.println("input down");
        }
        else if (keySetting.getRight() == e.getKeyCode()) {
            BoardManager.getInstance().translateBlock(curBlock, 0, 1);
            InGameUIManager.getInstance().drawBoard();
            //System.out.println("input right");
        }
        else if (keySetting.getLeft() == e.getKeyCode()) {
            BoardManager.getInstance().translateBlock(curBlock, 0, -1);
            InGameUIManager.getInstance().drawBoard();
            //System.out.println("input left");
        }
        else if (keySetting.getTurnBlock() == e.getKeyCode()) {
            //System.out.println(curStep);
            BoardManager.getInstance().eraseBlock(curBlock);
            curBlock.rotate();
            if(!BoardManager.getInstance().checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                curBlock.rotate();
                curBlock.rotate();
                curBlock.rotate();
            }
            BoardManager.getInstance().setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
            InGameUIManager.getInstance().drawBoard();
            //System.out.println("input up");
        } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
            while(BoardManager.getInstance().checkBlockMovable(curBlock)) {
                BoardManager.getInstance().translateBlock(curBlock, 1, 0);
                InGameUIManager.getInstance().drawScore();
            }
            timer.restart();
            InGameUIManager.getInstance().drawBoard();
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
            BoardManager.getInstance().printBoard();
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
