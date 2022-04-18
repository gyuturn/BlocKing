package game;
import game.manager.InGameUIManager;
import game.manager.gametype.GameManager_NormalMode;
//import setting.FixSize;
//import setting.MainSetting;
import setting.SaveAndLoad;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class GameUI extends JFrame {

    public JTextPane pane;
    private JTextPane scorePane;
    private JTextPane nextBlockPane;
    private JPanel mainPanel;
    private ScreenSize screenSize=ScreenSize.getInstance();
    private Font f1;

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

        mainPanel.setBackground(Color.CYAN);
        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5)
        );


        f1 = new Font("monospaced", Font.BOLD,13);

        pane = new JTextPane();

        pane.setText("test");
        pane.setEditable(false);

        //tpName의 styleDocument를 가져와 가운데 정렬 설정
        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);


        pane.setBackground(Color.BLACK);
        pane.setForeground(Color.GREEN);
        pane.setFont(f1);

        //pane.setBounds(30,30,500,1000);
        mainPanel.add(pane);



        this.getContentPane().add(mainPanel,BorderLayout.CENTER);
        setBtn();


        //add(tpName);

        instance = this;

        GameManager_NormalMode.getInstance().startGameFramework();

        //종료 시 현재 setting값 저장
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                SaveAndLoad.SaveSetting();
            }
        });


    }

    void setBtn(){
        JButton buttons = new JButton("다음으로 넘어가기2");
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