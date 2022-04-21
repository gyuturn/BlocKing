package game.manager.gametype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.GameUI;
import game.container.BlockGenerator;
import game.manager.BoardManager;
import game.manager.GameInfoManager;
import game.manager.GameManager;
import game.manager.InGameUIManager;
import game.model.BlockController;
import scoreBoard.NoItemScoreBoard.ScoreInputUI;
import start.StartUI;

public class GameManager_BasicMode extends GameManager {
    
//#region Singleton

    private static GameManager_BasicMode instance = new GameManager_BasicMode();
    
    public static GameManager_BasicMode getInstance() {
        return instance;
    }

//#endregion


//#region GameFramework

protected Step curStep = Step.GameReady;

public enum Step {

    GameReady,

    //while !gameOver
    CreateNewBlock,
    BlockMove,
    EraseAnimation,
    EraseLine,
    SetGameBalance,
    CheckGameOver,

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

        case CheckGameOver:
            curStep = checkGameOver();
            gameFramework();
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

public Step blockMove() {
    isBlockMovable = BoardManager.getInstance().checkBlockMovable(curBlock);
    if(isBlockMovable) {
        BoardManager.getInstance().translateBlock(curBlock, 1, 0);
        onBlockMove();
        return Step.BlockMove;
    }
    else
        return Step.EraseAnimation;
}

private Step eraseAnimation() {
    
    BoardManager.getInstance().eraseEvent(targetLineIndexList);
    return Step.EraseLine;
}


public Step checkLineDelete() {

private Step eraseLine() {

    int curLineCount = BoardManager.getInstance().eraseFullLine();
    onLineErase(curLineCount);

    return Step.SetGameBalance;
}

private Step setGameBalance() {
    curSpeed = basicSpeed + addSpeed * (lineCount + blockCount);
    timeScale = maxSpeed / curSpeed;
    setTimeScale(timeScale);

    return Step.CheckGameOver;
}

public Step checkGameOver() {
    BlockController nextBlock = BlockGenerator.getInstance().blockQueue.peek();
    for(int i=0; i<nextBlock.height(); i++) {
        for(int j=0; j<nextBlock.width(); j++) {
            if(nextBlock.getShape(i, j) != ' ') {
                if(BoardManager.getInstance().board[i][j + 5] != ' ')
                    return Step.GameOver;
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
    score += curSpeed;

    return 0;
}

public int onLineErase(int count) {
    score += curSpeed * count * 10;

    if(count > 2) {
        score += 10000;
    }

    lineCount += count;

    return 0;
}

private int onBlockCreate() {
    score += curSpeed;
    blockCount++;

    return 0;
}

public void onGameEnd() {
    stopGameFramework();
    
    isPlaying = false;
    curStep = Step.GameReady;
}

//#endregion


//#region Interaction

public class Interaction_Play implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
            
    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("e.getKeyCode() = " + e.getKeyCode());
        if (keySetting.getDownBlock() == e.getKeyCode()) {
            blockMove();
            InGameUIManager.getInstance().drawBoard();
            System.out.println("input down");
        }
        else if (keySetting.getRight() == e.getKeyCode()) {
            BoardManager.getInstance().translateBlock(curBlock, 0, 1);
            InGameUIManager.getInstance().drawBoard();
            System.out.println("input right");
        }
        else if (keySetting.getLeft() == e.getKeyCode()) {
            BoardManager.getInstance().translateBlock(curBlock, 0, -1);
            InGameUIManager.getInstance().drawBoard();
            System.out.println("input left");
        }
        else if (keySetting.getTurnBlock() == e.getKeyCode()) {
            BoardManager.getInstance().eraseBlock(curBlock);
            curBlock.rotate();
            if(!BoardManager.getInstance().checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                curBlock.rotate();
                curBlock.rotate();
                curBlock.rotate();
            }
            BoardManager.getInstance().setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
            InGameUIManager.getInstance().drawBoard();
            System.out.println("input up");
        } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
            while(BoardManager.getInstance().checkBlockMovable(curBlock)) {
                BoardManager.getInstance().translateBlock(curBlock, 1, 0);
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
        
        if(keySetting.getEscape() == e.getKeyCode()) {
            onGameEnd();
            new StartUI();
            GameUI.getInstance().dispose();
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

//#endregion

}
