package game;
//import setting.FixSize;
//import setting.MainSetting;
import game.manager.BoardManager;
import setting.ColorBlind;
import setting.DuplicateKeySettingException;
import setting.SaveAndLoad;
import game.manager.GameInfoManager;
import game.manager.GameManager;
import game.manager.gametype.GameManager_BasicMode;
import game.manager.gametype.GameManager_ItemMode;
import setting.ScreenSize;
import setting.UI.SettingUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static setting.ColorBlind.ColorSetting.ColorBlinded;


public class GameUI extends JFrame {

    JFrame frame = new JFrame();
    public JTextPane pane;
    private JPanel mainPanel;

    public JTextPane scorePane;

    public JTextPane nextBlockPane;
    private JLabel label, scoreLabel;
    private JButton btn;

    private ScreenSize screenSize=ScreenSize.getInstance();
    private Font f1;
    Container contentPane = frame.getContentPane();
    ColorBlind colorBlind = ColorBlind.getInstance();


    private static GameUI instance;
    public static GameUI getInstance() {
        if(instance != null)
            return instance;
        else
        {
            System.out.println("Error : GameUI Instance == null ");
            return null;
        }
    }

    public GameUI(){
        //JFrame setting
        super("software-tetris");//제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 메모리까지 종료
        this.setSize(screenSize.getWidth(), screenSize.getHeight());
        this.setVisible(true);

        //board display setting
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.CYAN);

        //보드 UI 부분
        pane = new JTextPane();
        pane.setText("start!");
        pane.setEditable(false);
        f1 = new Font("monospaced", Font.BOLD,this.getHeight()/38);  //폰트 사이즈 화면크기에 맞게 조정
        pane.setBounds(10,10,this.getWidth()/2,this.getHeight()*4/5);      // 변화하는 화면 크기에 맞춰 사이즈 조정
        mainPanel.add(pane);

        pane.setBackground(Color.BLACK);
        pane.setForeground(Color.GREEN);
        pane.setFont(f1);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 2),
                BorderFactory.createLineBorder(Color.YELLOW, 2)
        );

        // Next Block 텍스트 출력
        label = new JLabel();
        label.setText("Next Block");
        label.setBackground(Color.BLACK);
        label.setFont(f1);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBounds(this.getWidth()*5/8,0,this.getWidth()/4,30);
        mainPanel.add(label);

        // Next Block 미리보기 UI 부분
        nextBlockPane = new JTextPane();
        nextBlockPane.setEditable(false);
        nextBlockPane.setBackground(Color.BLACK);
        nextBlockPane.setForeground(Color.WHITE);
        nextBlockPane.setBorder(border);
        nextBlockPane.setFont(f1);
        nextBlockPane.setBounds(this.getWidth()*5/8,30,this.getWidth()/4,this.getWidth()/8);
        mainPanel.add(nextBlockPane);

        // Score 텍스트 출력
        scoreLabel = new JLabel();
        scoreLabel.setText("Score");
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setFont(f1);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setBounds(this.getWidth()*5/8,30+this.getWidth()/4,this.getWidth()/4,30);
        mainPanel.add(scoreLabel);

        // Score UI 부분
        scorePane = new JTextPane();
        scorePane.setEditable(false);
        scorePane.setFont(f1);
        scorePane.setBackground(Color.BLACK);
        scorePane.setForeground(Color.WHITE);
        scorePane.setBounds(this.getWidth()*5/8,60+this.getWidth()/4,this.getWidth()/4,this.getWidth()/8);
        mainPanel.add(scorePane);


        //pause 버튼 --> 다른걸로 변경 예정
//        btn = new JButton("pause");
//        btn.setBounds(300,300,100,20);
//        mainPanel.add(btn);
//
//        btn.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e) {
//                    System.out.println("버튼이 클릭되었습니다.");
//            }
//        });

        // 글자 가운데 정렬 설정
        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        StyledDocument nextDoc = nextBlockPane.getStyledDocument();
        SimpleAttributeSet nextCenter = new SimpleAttributeSet();
        StyleConstants.setAlignment(nextCenter, StyleConstants.ALIGN_CENTER);
        nextDoc.setParagraphAttributes(0, nextDoc.getLength(), nextCenter, false);

        StyledDocument scoreDoc = scorePane.getStyledDocument();
        SimpleAttributeSet scoreCenter = new SimpleAttributeSet();
        StyleConstants.setAlignment(scoreCenter, StyleConstants.ALIGN_CENTER);
        scoreDoc.setParagraphAttributes(0, scoreDoc.getLength(), center, false);

        //글자 색 정보 저장
        addCharacterPaneStyle();
        addCharacternextBlockPaneStyle();

        //setBtn();

        this.getContentPane().add(mainPanel);

        instance = this;

        //게임 시작
        switch(GameInfoManager.getInstance().mode) {
            case BasicMode:
                GameManager_BasicMode.getInstance().startGameFramework();
                break;
            case ItemMode:
                GameManager_ItemMode.getInstance().startGameFramework();
        }
        

        //Screensize 값 가져오기
        ScreenSize.getInstance().getWidth();
        ScreenSize.getInstance().getHeight();

        //종료 시 현재 setting값 저장
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                try {
                    SaveAndLoad.SaveSetting();
                } catch (DuplicateKeySettingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

//    void setBtn(){
//        JButton buttons = new JButton("다음으로 넘어가기2");
//        buttons.setBounds(this.getWidth()*5/8,this.getHeight()-100,100,50);
//        mainPanel.add(buttons);
//
//        //다음으로 넘어가는 event
//        buttons.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new SettingUI(SettingUI.Scene.GameUI);
//                dispose();
//            }
//        });
//    }


    private void addCharacternextBlockPaneStyle()
    {
        //색깔 종류
        javax.swing.text.Style style5 = nextBlockPane.addStyle("B", null);
        javax.swing.text.Style style6 = nextBlockPane.addStyle("P", null);
        javax.swing.text.Style style7 = nextBlockPane.addStyle("L", null);
        javax.swing.text.Style style8 = nextBlockPane.addStyle("A", null);
        javax.swing.text.Style style12 = nextBlockPane.addStyle("D", null);
           //블록에 대한 색깔들
        javax.swing.text.Style IBlock = nextBlockPane.addStyle("R", null);
        javax.swing.text.Style JBlock = nextBlockPane.addStyle("O", null);
        javax.swing.text.Style LBlock = nextBlockPane.addStyle("W", null);
        javax.swing.text.Style OBlock = nextBlockPane.addStyle("C", null);
        javax.swing.text.Style SBlock = nextBlockPane.addStyle("Y", null);
        javax.swing.text.Style TBlock = nextBlockPane.addStyle("G", null);
        javax.swing.text.Style ZBlock = nextBlockPane.addStyle("M", null);


        //색깔 설정
            StyleConstants.setForeground(IBlock, Color.RED);
            StyleConstants.setForeground(JBlock, Color.ORANGE);
            StyleConstants.setForeground(SBlock, Color.YELLOW);
            StyleConstants.setForeground(TBlock, Color.GREEN);
            StyleConstants.setForeground(style5, Color.BLUE);
            StyleConstants.setForeground(style6, Color.PINK);
            StyleConstants.setForeground(style7, Color.LIGHT_GRAY);
            StyleConstants.setForeground(style8, Color.GRAY);
            StyleConstants.setForeground(LBlock, Color.WHITE);
            StyleConstants.setForeground(OBlock, Color.CYAN);
            StyleConstants.setForeground(ZBlock, Color.MAGENTA);
            StyleConstants.setForeground(style12, Color.DARK_GRAY);

            if(colorBlind.getColorBlind()== ColorBlinded){
            StyleConstants.setForeground(IBlock, new Color(255, 194, 10));
            StyleConstants.setForeground(JBlock, new Color(12,123,220));
            StyleConstants.setForeground(SBlock, new Color(153,79,0));
            StyleConstants.setForeground(TBlock, new Color(254,254,98));
            StyleConstants.setForeground(LBlock, new Color(211,95,183));
            StyleConstants.setForeground(OBlock,  new Color(225,190,160));
            StyleConstants.setForeground(ZBlock,  new Color(212,17,89));
        }
    }

    private void addCharacterPaneStyle()
    {
        //색깔 종류
        javax.swing.text.Style style5 = pane.addStyle("B", null);
        javax.swing.text.Style style6 = pane.addStyle("P", null);
        javax.swing.text.Style style7 = pane.addStyle("L", null);
        javax.swing.text.Style style8 = pane.addStyle("A", null);
        javax.swing.text.Style style12 = pane.addStyle("D", null);
        //블록에 대한 색깔들
        javax.swing.text.Style IBlock = pane.addStyle("R", null);
        javax.swing.text.Style JBlock = pane.addStyle("O", null);
        javax.swing.text.Style LBlock = pane.addStyle("W", null);
        javax.swing.text.Style OBlock = pane.addStyle("C", null);
        javax.swing.text.Style SBlock = pane.addStyle("Y", null);
        javax.swing.text.Style TBlock = pane.addStyle("G", null);
        javax.swing.text.Style ZBlock = pane.addStyle("M", null);


        //색깔 설정
        StyleConstants.setForeground(IBlock, Color.RED);
        StyleConstants.setForeground(JBlock, Color.ORANGE);
        StyleConstants.setForeground(SBlock, Color.YELLOW);
        StyleConstants.setForeground(TBlock, Color.GREEN);
        StyleConstants.setForeground(style5, Color.BLUE);
        StyleConstants.setForeground(style6, Color.PINK);
        StyleConstants.setForeground(style7, Color.LIGHT_GRAY);
        StyleConstants.setForeground(style8, Color.GRAY);
        StyleConstants.setForeground(LBlock, Color.WHITE);
        StyleConstants.setForeground(OBlock, Color.CYAN);
        StyleConstants.setForeground(ZBlock, Color.MAGENTA);
        StyleConstants.setForeground(style12, Color.DARK_GRAY);

        if(colorBlind.getColorBlind()== ColorBlinded){
            StyleConstants.setForeground(IBlock, new Color(255, 194, 10));
            StyleConstants.setForeground(JBlock, new Color(12,123,220));
            StyleConstants.setForeground(SBlock,new Color(153,79,0));
            StyleConstants.setForeground(TBlock, new Color(254,254,98));
            StyleConstants.setForeground(LBlock, new Color(211,95,183));
            StyleConstants.setForeground(OBlock,  new Color(225,190,160));
            StyleConstants.setForeground(ZBlock,  new Color(212,17,89));
        }
    }

}
