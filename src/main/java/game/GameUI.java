package game;
import game.container.BlockGenerator;
import game.manager.BoardManager;
import game.manager.InGameUIManager;
//import setting.FixSize;
//import setting.MainSetting;
import game.manager.gametype.GameManager_NormalMode;
import setting.ScreenSize;
import setting.SettingUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GameUI extends JFrame {

    JFrame frame = new JFrame();
    public JTextPane pane;
    private JPanel mainPanel;

    private JTextPane scorePane;

    private JTextPane nextBlockPane;

    private JButton btn;

    private ScreenSize screenSize=ScreenSize.getInstance();
    private Font f1;
    Container contentPane = frame.getContentPane();


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

        pane = new JTextPane();
        pane.setText("test");
        pane.setEditable(false);
        f1 = new Font("monospaced", Font.BOLD,this.getHeight()/38);  //폰트 사이즈 화면크기에 맞게 조정
        pane.setBounds(10,10,this.getWidth()/2,this.getHeight()*4/5);      // 변화하는 화면 크기에 사이즈 조정
        mainPanel.add(pane);

        pane.setBackground(Color.BLACK);
        pane.setForeground(Color.GREEN);
        pane.setFont(f1);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 2),
                BorderFactory.createLineBorder(Color.YELLOW, 2)
        );


        nextBlockPane = new JTextPane();
        nextBlockPane.setText("Next");
        nextBlockPane.setEditable(false);
        nextBlockPane.setBackground(Color.BLACK);
        nextBlockPane.setForeground(Color.WHITE);
        nextBlockPane.setBorder(border);
        nextBlockPane.setBounds(this.getWidth()*5/8,10,this.getWidth()/4,this.getWidth()/4);
        mainPanel.add(nextBlockPane);

        scorePane = new JTextPane();
        scorePane.setText("점    수");
        scorePane.setEditable(false);
        scorePane.setBounds(this.getWidth()*5/8,20+this.getWidth()/4,this.getWidth()/4,this.getWidth()/8);
        mainPanel.add(scorePane);



        btn = new JButton("pause");
        btn.setBounds(300,300,100,20);
        mainPanel.add(btn);

        btn.addActionListener(new ActionListener(){             // 키보드와 연동되도록
            public void actionPerformed(ActionEvent e) {
                    System.out.println("버튼이 클릭되었습니다.");
            }
        });

        //textpane 글자 가운데 정렬 설정
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


        setBtn();

        this.getContentPane().add(mainPanel);


        instance = this;

        GameManager_NormalMode.getInstance().startGameFramework();
        //GameManager_NormalMode.getInstance().stopGameFramework();
        BlockGenerator.getInstance().createBlock(); //블록 미리보기 가져오기 어떻게??
        ScreenSize.getInstance().getWidth();
        ScreenSize.getInstance().getHeight();

    }

    void setBtn(){
        JButton buttons = new JButton("다음으로 넘어가기2");
        buttons.setBounds(400,400,50,50);
        mainPanel.add(buttons);

        //다음으로 넘어가는 event
        buttons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingUI();
                setVisible(false);
            }
        });
    }

}

/* pause 버튼 키보드 연동되도록
    컴포넌트 위치
    블록 미리보기와 연결
    점수와 연결
    글꼴 더 찾아보기
 */