//package game.manager.gametype;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import game.Controller;
//import game.GameDualModeUI;
//import game.UserNumber;
//import game.container.Block;
//import game.manager.BoardManager;
//import game.manager.GameInfoManager;
//import game.manager.GameManager;
//import game.manager.InGameUIManager;
//
//public class GameManager_ItemMode extends GameManager {
//    Controller[] controller=Controller.getInstance();
//    UserNumber userNumber = UserNumber.getInstance();
//
//    private BoardManager boardManager;
//    private Block block;
//
//
//
////#region Singleton
//
//
////#endregion
//
//
////#region GameFramework
//
//private Step curStep = Step.GameReady;
//
//public enum Step {
//
//    GameReady,
//
//    //while !gameOver
//    CreateNewBlock,
//    CheckAddItem,
//    BlockMove,
//    SetGameBalance,
//    CheckLineDelete,
//    CheckItemUse,
//    Eraseevent,
//
//    GameOver
//}
//
//    public GameManager_ItemMode(InGameUIManager inGameUIManager, BoardManager boardManager, Block block) {
//        super(InGameUIManager.getInstance());
//        this.boardManager = boardManager;
//        this.block = block;
//    }
//
//@Override
//protected void gameFramework() { //전체적인 게임의 동작 흐름
//
//    switch(curStep) {
//        case GameReady:
//            curStep = gameReady();
//            break;
//
//        case CreateNewBlock:
//            curStep = createNewBlock();
//            gameFramework();
//            break;
//
//        case CheckAddItem:
//            curStep = checkAddItem();
//            break;
//
//        case BlockMove:
//            curStep = blockMove();
//            if(curStep != Step.BlockMove) gameFramework();
//            break;
//
//        case Eraseevent:
//            curStep = eraseEvent();
//            break;
//
//        case CheckLineDelete:
//            curStep = checkLineDelete();
//            gameFramework();
//            break;
//
//        case SetGameBalance:
//            curStep = setGameBalance();
//            gameFramework();
//            break;
//
//        case CheckItemUse:
//            curStep = checkItemUse();
//            gameFramework();
//            break;
//
//        case GameOver :
//            gameOver();
//            break;
//    }
//}
//
//private Step gameReady() {
//    //게임을 준비합니다.
//    initKeyListener();
//    block.initBlockQueue();
//    //BlockGenerator.getInstance().initBlockPer();
//    //BoardManager.getInstance().initBoard();
//    initGameStatus();
//
//    return Step.CreateNewBlock;
//}
//
//public Step createNewBlock() {
//    block.addBlock();
//    curBlock = block.createBlock(this);
//    InGameUIManager.getInstance().drawNextBlockInfo(block.blockQueue.peek());
//    onBlockCreate();
//
//    return Step.CheckAddItem;
//}
//
//private Step checkAddItem() {
//    if(blockCount % 10 == 0 && blockCount > 0)
//    {
//        //아이템 생성
//    }
//    return Step.BlockMove;
//}
//
//private Step blockMove() {
//    isBlockMovable = boardManager.checkBlockMovable(curBlock);
//    if(isBlockMovable) {
//        boardManager.translateBlock(curBlock, 1, 0);
//        onBlockMove();
//        return Step.BlockMove;
//    }
//    else
//        return Step.Eraseevent;
//}
//
//    private Step eraseEvent() {
//
//        boardManager.eraseEvent();
//        return Step.CheckLineDelete;
//    }
//
//private Step checkLineDelete() {
//    int curLineCount = boardManager.eraseFullLine();
//    onLineErase(curLineCount);
//
//    return Step.SetGameBalance;
//}
//
//private Step setGameBalance() {
//    curSpeed = basicSpeed + addSpeed * (lineCount + blockCount);
//    timeScale = maxSpeed / curSpeed;
//    setTimeScale(timeScale);
//
//    return Step.CheckAddItem;
//}
//
//private Step checkItemUse() {
//    return Step.CreateNewBlock;
//}
//
//@Override
//protected void gameOver() {
//    //게임이 종료되면 호출됩니다.
//    timer.stop();
//}
//
////#endregion
//
////#region init
//
//@Override
//protected void initGameStatus() {
//    blockCount = 0;
//    lineCount = 0;
//    score = 0;
//
//    curBlock = null;
//
//    difficulty = GameInfoManager.getInstance().difficulty;
//    addSpeed = GameInfoManager.getInstance().difficultiesMap.get(difficulty).getAddSpeed();
//}
//
////#endregion
//
////#region Events
//private int onBlockMove() {
//    score += curSpeed;
//
//    return 0;
//}
//
//private int onLineErase(int count) {
//    score += curSpeed * count * 10;
//
//    if(count > 2) {
//        score += 10000;
//    }
//
//    lineCount += count;
//
//    return 0;
//}
//
//private int onBlockCreate() {
//    score += curSpeed;
//    blockCount++;
//
//    return 0;
//}
//
////#endregion
//
//
////#region Interaction
//
//public void initKeyListener() {
//    interaction_play = new Interaction_Play();
//    interaction_utils = new Interaction_Utils();
//    GameDualModeUI.getInstance().pane[0].addKeyListener(interaction_play);
//    GameDualModeUI.getInstance().pane[0].addKeyListener(interaction_utils);
//
//    GameDualModeUI.getInstance().pane[1].addKeyListener(interaction_play);
//    GameDualModeUI.getInstance().pane[1].addKeyListener(interaction_utils);
//}
//
//public class Interaction_Play implements KeyListener {
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        System.out.println("e.getKeyCode() = " + e.getKeyCode());
//        if (keySetting.getDownBlock() == e.getKeyCode()) {
//            blockMove();
//            InGameUIManager.getInstance().drawBoard();
//            System.out.println("input down");
//        }
//        else if (keySetting.getRight() == e.getKeyCode()) {
//            boardManager.translateBlock(curBlock, 0, 1);
//            InGameUIManager.getInstance().drawBoard();
//            System.out.println("input right");
//        }
//        else if (keySetting.getLeft() == e.getKeyCode()) {
//            boardManager.translateBlock(curBlock, 0, -1);
//            InGameUIManager.getInstance().drawBoard();
//            System.out.println("input left");
//        }
//        else if (keySetting.getTurnBlock() == e.getKeyCode()) {
//            boardManager.eraseBlock(curBlock);
//            curBlock.rotate();
//            if(!boardManager.checkDrawable(curBlock.shape, curBlock.posRow, curBlock.posCol)) {
//                curBlock.rotate();
//                curBlock.rotate();
//                curBlock.rotate();
//            }
//            boardManager.setBlockPos(curBlock, curBlock.posRow, curBlock.posCol);
//            InGameUIManager.getInstance().drawBoard();
//            System.out.println("input up");
//        } else if (keySetting.getOneTimeDown() == e.getKeyCode()) {
//            while(boardManager.checkBlockMovable(curBlock)) {
//                boardManager.translateBlock(curBlock, 1, 0);
//            }
//            timer.restart();
//            InGameUIManager.getInstance().drawBoard();
//        }
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
//
//public class Interaction_Utils implements KeyListener {
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(keySetting.getStop() == e.getKeyCode()) {
//            boardManager.printBoard();
//            if(timer.isRunning())
//                stopGameFramework();
//            else
//                restartGameFramework();
//        }
//        /*
//        if(keySetting.getEscape() == e.getKeyCode()) {
//            new StartUI();
//        }
//        */
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//
//    }
//}
//
////#endregion
//}
