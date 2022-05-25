package game.manager.gametype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sun.corba.se.spi.orbutil.threadpool.Work;
import game.GameUI;
import game.container.BlockGenerator;
import game.container.ItemGenerator;
import game.container.ItemGenerator.ItemType;
import game.manager.BoardManager;
import game.manager.DualModeUtils.GameEndForDualUI;
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

protected Step curStep = Step.GameReady;

public boolean isResurrection = false;
public static boolean isDoubleScore = false;


public enum Step {

    GameReady,
    
    //while !gameOver
    CreateNewBlock,
    BlockMove,
    EraseAnimation,
    EraseLine,
    SendAttackBoard,
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

        case SendAttackBoard:
            curStep = sendAttackBoard();
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
    //시작하기전에 무게추 블럭이었으면 바닥에 닿아도 움직이지 못하게 함
    if (curItem == ItemType.Weight) {
        if (checkCurBlockIsWeight()) {
            if(UserNumber.getInstance().user==2) {
                GameUI.getInstance().pane[0].addKeyListener(interaction_play);
                GameUI.getInstance().pane[1].addKeyListener(interaction_play);
            }else {
                GameUI.getInstance().pane[0].addKeyListener(interaction_play);
            }
        }
    }

    BlockGenerator.getInstance(index).addBlock();
    curBlock = BlockGenerator.getInstance(index).createBlock(index);

    BlockController nextBlock = BlockGenerator.getInstance(index).blockQueue.peek();
    BoardManager.getInstance(index).setNextBlockColor(nextBlock);
    InGameUIManager.getInstance().drawNextBlockInfo(nextBlock, index);
    onBlockCreate();

    return Step.BlockMove;
}

public Step blockMove() {
    isBlockMovable = BoardManager.getInstance(index).checkBlockMovable(curBlock);

    if (isBlockMovable) {
        BoardManager.getInstance(index).translateBlock(curBlock, 1, 0);
        onBlockMove();

        //무게추 아이템이 블록에 닿으면 키 제거
        if(checkCurBlockIsWeight()&&!BoardManager.getInstance(index).checkBlockMovable(curBlock)){
            removeKeyControl();
        }
        else
        {
            isBlockMovable = BoardManager.getInstance(index).checkBlockMovable(curBlock);
            if(!isBlockMovable)
            {
                return Step.EraseAnimation;
            }
        }
        return Step.BlockMove;
    } else {
        //무게추 검사 -> 맞으면 아이템 사용, 아니면 Erase animation
        if (curItem == ItemType.Weight) {
            if (checkCurBlockIsWeight()) {
                removeKeyControl();
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
            {'O', 'O', 'O', 'O'},
            {' ', ' ', ' ', ' '},
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
//        if(UserNumber.getInstance().user==2) {
//            GameUI.getInstance().pane[0].addKeyListener(interaction_play);
//            GameUI.getInstance().pane[1].addKeyListener(interaction_play);
//        }else {
//            GameUI.getInstance().pane[0].addKeyListener(interaction_play);
//        }
        return Step.EraseAnimation;
    }

}

private Step eraseAnimation() {

    BoardManager.getInstance(index).eraseEvent(targetLineIndexList, index);
    
    return Step.EraseLine;
}

private Step eraseLine() {
    int curLineCount = BoardManager.getInstance(index).eraseFullLine(index);
    onLineErase(curLineCount);

    return Step.SendAttackBoard;
}

private Step sendAttackBoard() {

    if(UserNumber.getInstance().user==2) {
        BoardManager.getInstance(index).attackEvent(index);
    }
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

public Step checkGameOver() {
    BlockController nextBlock = BlockGenerator.getInstance(index).blockQueue.peek();
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
        if(UserNumber.getInstance().user==2){
            instance.onGameEnd();
            instance2.onGameEnd();
            new GameEndForDualUI(instance.score, instance2.score);
        }
        else{
            instance.onGameEnd();
            new ScoreInputUI(score,GameInfoManager.getInstance().difficultyToString(difficulty));
        }
        GameUI.getInstance().setVisible(false);
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
    BlockGenerator.getInstance(index).initBlockQueue();
}

//#endregion
//private int onBlockMoveDouble(){
//    score += 2 * curSpeed;
//    InGameUIManager.getInstance().drawScore(index);
//    return 0;
//}

//    static int indexDouble =0;
//// 스레드를 사용해 백그라운드에서 30초동안
//static class CheckDouble extends Thread{
//    public void run(){
//
//        long start = System.currentTimeMillis();
//        long end = start + 30 * 1000;
//        while (System.currentTimeMillis() < end) {
//
//        }
//        checkDouble[indexDouble]=checkDouble[indexDouble+1];
//        indexDouble++;
//        isDoubleScore=false;
//
//    }
//}
//
//static CheckDouble[] checkDouble = new CheckDouble[4];
//

//static CheckDouble checkDouble = new CheckDouble();
//static CheckDouble checkDouble2 = new CheckDouble();
//static CheckDouble checkDouble3 = new CheckDouble();
//static CheckDouble checkDouble4 = new CheckDouble();
//static CheckDouble checkDouble5 = new CheckDouble();


//#region Events

public int onBlockMove() {
    if(isDoubleScore==true) {
        score +=  curSpeed;
    }
    score += curSpeed;
    InGameUIManager.getInstance().drawScore(index);
    
    return 0;
}


private int onLineErase(int count) {
    if(isDoubleScore==true) {       // 적용
        score += curSpeed * count * 10;
    }
    score += curSpeed * count * 10;

    if(count > 2) {
        if(isDoubleScore==true) {       // 적용
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

    if(isDoubleScore==true) {
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
    if(isResurrection==true) {
        BoardManager.getInstance(index).eraseHalfBoard();

        isResurrection = false;
        return Step.CreateNewBlock;
    }
    else {
        return Step.GameOver;
    }

}
private void checkDoubleBonusChance() { // 사용 X
    if(isDoubleScore==true){
        long start = System.currentTimeMillis();
        long end = start + 30*1000;
        while (System.currentTimeMillis() < end) {

        }
        isDoubleScore = false;
    }
}


private void checkSmallBlockChance() {

}

private void checkWeightUse() {

}



//#region Utils
private void checkAddItem() {

    if(lineCount % 1 == 0 && lineCount > 0) //a-b>10 b -= 10;
    {
        BlockController targetBlock = BlockGenerator.getInstance(index).blockQueue.peek();
        ItemType itemType = ItemGenerator.getInstance().SelectRandomItem();
        System.out.println(itemType);



//        무게추아이템 test
        itemType = ItemType.Weight;

        switch(itemType) {
            case Weight:
                isDoubleScore = false;
                ItemGenerator.getInstance().setBlockMugechu(targetBlock);
                curItem = ItemType.Weight;
                break;
            case LineClear:
                isDoubleScore = false;
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'L');
                curItem = ItemType.LineClear;
                break;
            case Resurrection:
                isDoubleScore = false;
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'R');
                curItem = ItemType.Resurrection;
                isResurrection = true;
                break;
            case DoubleBonusChance:
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'D');
                curItem = ItemType.DoubleBonusChance;
                isDoubleScore = true;
                break;
            case SmallBlockChance:
                isDoubleScore = false;
                ItemGenerator.getInstance().setOneBlock(targetBlock);
                ItemGenerator.getInstance().addCharInShape(targetBlock, 'S');
                curItem = ItemType.SmallBlockChance;
                break;


        }

//        if(curItem == ItemType.SmallBlockChance) {
//            ItemGenerator.getInstance().setOneBlock(targetBlock);
//            curItem = ItemType.None;
//            return;
//        }

        BlockController nextBlock = BlockGenerator.getInstance(index).blockQueue.peek();
        BoardManager.getInstance(index).setNextBlockColor(nextBlock);
        InGameUIManager.getInstance().drawNextBlockInfo(nextBlock, index);
        //System.out.println(isResurrection);
        lineCount=0;
        System.out.println(isDoubleScore);


    }
}

public void removeKeyControl(){
    if(UserNumber.getInstance().user==2) {
        GameUI.getInstance().pane[0].removeKeyListener(interaction_play);
        GameUI.getInstance().pane[1].removeKeyListener(interaction_play);
    }else {
        GameUI.getInstance().pane[0].removeKeyListener(interaction_play);
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
        if (keySetting.getDownBlock() == e.getKeyCode()) {
            blockMove();
        }
        else if (keySetting.getRight() == e.getKeyCode()) {
            BoardManager.getInstance(index).translateBlock(curBlock, 0, 1);
        }
        else if (keySetting.getLeft() == e.getKeyCode()) {
            BoardManager.getInstance(index).translateBlock(curBlock, 0, -1);
        }
        else if (keySetting.getTurnBlock() == e.getKeyCode()) {
            if(checkCurBlockIsWeight()) return; //현재 블록이 무게추인 경우 turnBlock 적용 x
            BoardManager.getInstance(index).eraseBlock(curBlock);
            curBlock.rotate();
            if(!BoardManager.getInstance(index).checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                curBlock.rotate();
                curBlock.rotate();
                curBlock.rotate();
            }
            BoardManager.getInstance(index).setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
        } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
            while(BoardManager.getInstance(index).checkBlockMovable(curBlock)) {
                BoardManager.getInstance(index).translateBlock(curBlock, 1, 0);
                InGameUIManager.getInstance().drawScore(index);
            }
            //무게추인 경우 블럭에 닿을시 바로 keycontrol 제거
            if(checkCurBlockIsWeight()){
                removeKeyControl();
            }
            timer.restart();
            InGameUIManager.getInstance().drawBoard(index);
        }
        else{
            if (keySetting.getDownBlock2P() == e.getKeyCode()) {
                blockMove();
            } else if (keySetting.getRight2P() == e.getKeyCode()) {
                BoardManager.getInstance(index).translateBlock(curBlock, 0, 1);
            } else if (keySetting.getLeft2P() == e.getKeyCode()) {
                BoardManager.getInstance(index).translateBlock(curBlock, 0, -1);
            } else if (keySetting.getTurnBlock2P() == e.getKeyCode()) {
                BoardManager.getInstance(index).eraseBlock(curBlock);
                curBlock.rotate();
                if (!BoardManager.getInstance(index).checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
                    curBlock.rotate();
                    curBlock.rotate();
                    curBlock.rotate();
                }
                BoardManager.getInstance(index).setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
            } else if (keySetting.getOneTimeDown2P() == e.getKeyCode()) {
                while (BoardManager.getInstance(index).checkBlockMovable(curBlock)) {
                    BoardManager.getInstance(index).translateBlock(curBlock, 1, 0);
                    InGameUIManager.getInstance().drawScore(index);
                }
                timer.restart();
                InGameUIManager.getInstance().drawBoard(index);
            }
        }
        isBlockMovable = BoardManager.getInstance(index).checkBlockMovable(curBlock);
        if(isBlockMovable) {
            curStep = Step.BlockMove;
        }
        InGameUIManager.getInstance().drawScore(index);
        InGameUIManager.getInstance().drawBoard(index);
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

