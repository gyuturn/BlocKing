package game.manager.gametype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Controller;
import game.GameDualModeUI;
import game.UserNumber;
import game.container.Block;
import game.manager.BoardManager;
import game.manager.GameInfoManager;
import game.manager.GameManager;
import game.manager.InGameUIManager;
import game.model.BlockController;
import scoreBoard.NoItemScoreBoard.ScoreInputUI;
import start.StartUI;

public class GameManager_BasicMode extends GameManager {

    private BoardManager boardManager;
    private Block block;
    private int user;






//#region GameFramework

private Step curStep = Step.GameReady;

    public GameManager_BasicMode(InGameUIManager inGameUIManager, BoardManager boardManager, Block block,int user) {
        super(inGameUIManager);
        this.boardManager = boardManager;
        this.block = block;
        this.user=user;
    }

    public GameManager_BasicMode() {
    }


    public enum Step {

    GameReady,

    //while !gameOver
    CreateNewBlock,
    BlockMove,
    CheckLineDelete,
    Eraseevent,
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

        case Eraseevent:
            curStep = eraseEvent();
            break;

        case CheckLineDelete:
            curStep = checkLineDelete();
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
    block.addBlock();
    curBlock = block.createBlock(this);
    super.inGameUIManager.drawNextBlockInfo(block.blockQueue.peek());
    onBlockCreate();

    return Step.BlockMove;
}

private Step blockMove() {
    isBlockMovable = boardManager.checkBlockMovable(curBlock);
    if(isBlockMovable) {
        boardManager.translateBlock(curBlock, 1, 0);
        onBlockMove();
        return Step.BlockMove;
    }
    else
        return Step.Eraseevent;
}

    private Step eraseEvent() {

        boardManager.eraseEvent();
        return Step.CheckLineDelete;
    }

private Step checkLineDelete() {
    int curLineCount = boardManager.eraseFullLine();
    onLineErase(curLineCount);

    return Step.SetGameBalance;
}

private Step setGameBalance() {
    curSpeed = basicSpeed + addSpeed * (lineCount + blockCount);
    timeScale = maxSpeed / curSpeed;
    setTimeScale(timeScale);

    return Step.CheckGameOver;
}

private Step checkGameOver() {
    BlockController nextBlock = block.blockQueue.peek();
    for(int i=0; i<nextBlock.height(); i++) {
        for(int j=0; j<nextBlock.width(); j++) {
            if(nextBlock.getShape(i, j) != ' ') {
                if(boardManager.board[i][j + 5] != ' ')
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
//    GameUI.getInstance().pane.addKeyListener(interaction_play);
//    GameUI.getInstance().pane.addKeyListener(interaction_utils);
    GameDualModeUI.getInstance().pane[0].addKeyListener(interaction_play);
    GameDualModeUI.getInstance().pane[0].addKeyListener(interaction_utils);
    GameDualModeUI.getInstance().pane[1].addKeyListener(interaction_play);
    GameDualModeUI.getInstance().pane[1].addKeyListener(interaction_utils);
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
    boardManager.initBoard();
}

protected void initBlockGenerator() {
    block.initBlockQueue();
}

//#endregion

//#region Events
private int onBlockMove() {
    score += curSpeed;

    return 0;
}

private int onLineErase(int count) {
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

private void onGameEnd() {
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
        if(user==0) {
            if (keySetting.getDownBlock() == e.getKeyCode()) {
                blockMove();
                inGameUIManager.drawBoard();
                System.out.println("input down");
            } else if (keySetting.getRight() == e.getKeyCode()) {
                boardManager.translateBlock(curBlock, 0, 1);
                inGameUIManager.drawBoard();
                System.out.println("input right");
            } else if (keySetting.getLeft() == e.getKeyCode()) {
                boardManager.translateBlock(curBlock, 0, -1);
                inGameUIManager.drawBoard();
                System.out.println("input left");
            } else if (keySetting.getTurnBlock() == e.getKeyCode()) {
                boardManager.eraseBlock(curBlock);
                curBlock.rotate();
                if (!boardManager.checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                    curBlock.rotate();
                    curBlock.rotate();
                    curBlock.rotate();
                }
                boardManager.setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
                inGameUIManager.drawBoard();
                System.out.println("input up");
            } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
                while (boardManager.checkBlockMovable(curBlock)) {
                    boardManager.translateBlock(curBlock, 1, 0);
                }
                timer.restart();
                inGameUIManager.drawBoard();
            }
        }

        // 2인 키
        else if(user==1) {
            if (83 == e.getKeyCode()) {
                blockMove();
                inGameUIManager.drawBoard();
                System.out.println("input down");
            } else if (68 == e.getKeyCode()) {
                boardManager.translateBlock(curBlock, 0, 1);
                inGameUIManager.drawBoard();
                System.out.println("input right 2p");
            } else if (65 == e.getKeyCode()) {
                boardManager.translateBlock(curBlock, 0, -1);
                inGameUIManager.drawBoard();
                System.out.println("input left");
            } else if (87 == e.getKeyCode()) {
                boardManager.eraseBlock(curBlock);
                curBlock.rotate();
                if (!boardManager.checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                    curBlock.rotate();
                    curBlock.rotate();
                    curBlock.rotate();
                }
                boardManager.setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
                inGameUIManager.drawBoard();
                System.out.println("input up");
            } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
                while (boardManager.checkBlockMovable(curBlock)) {
                    boardManager.translateBlock(curBlock, 1, 0);
                }
                timer.restart();
                inGameUIManager.drawBoard();
            }
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
            boardManager.printBoard();
            if(timer.isRunning())
                stopGameFramework();
            else
                restartGameFramework();
        }
        
        if(keySetting.getEscape() == e.getKeyCode()) {
            onGameEnd();
            new StartUI();
            GameDualModeUI.getInstance().dispose();
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}

//#endregion

}
