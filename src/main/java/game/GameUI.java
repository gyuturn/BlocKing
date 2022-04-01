package game;
import game.manager.InGameUIManager;
//import setting.FixSize;
//import setting.MainSetting;
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


public class GameUI extends JFrame {

    public JTextPane pane;
    private JTextPane scorePane;
    private JTextPane nextBlockPane;
    private JPanel mainPanel;
    private ScreenSize screenSize=ScreenSize.getInstance();

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




        pane = new JTextPane();

        pane.setText("test");
        pane.setEditable(false);

        //tpName의 styleDocument를 가져와 가운데 정렬 설정
        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        pane.setBackground(Color.BLACK);
        //pane.setBounds(30,30,500,1000);
        mainPanel.add(pane);



        this.getContentPane().add(mainPanel,BorderLayout.CENTER);
        setBtn();


        //add(tpName);

        instance = this;

        InGameUIManager.getInstance().drawBoard();

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