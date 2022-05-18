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

        //무게추 아이템이 블록에 닿으면 키 제거
        if(checkCurBlockIsWeight()&&!BoardManager.getInstance(index).checkBlockMovable(curBlock)){
            removeKeyControl();
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

private int onBlockMove() {
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

    if( lineCount % 1 == 0 && lineCount > 0) //a-b>10 b -= 10;
    {
        BlockController targetBlock = BlockGenerator.getInstance().blockQueue.peek();
        ItemType itemType = ItemGenerator.getInstance().SelectRandomItem();
        System.out.println(itemType);



        //무게추아이템 test
//        itemType = ItemType.Weight;

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

        BlockController nextBlock = BlockGenerator.getInstance().blockQueue.peek();
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
            if(checkCurBlockIsWeight()) return; //현재 블록이 무게추인 경우 turnBlock 적용 x
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
            //무게추인 경우 블럭에 닿을시 바로 keycontrol 제거
            if(checkCurBlockIsWeight()){
                removeKeyControl();
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

