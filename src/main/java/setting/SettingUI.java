package setting;

import game.GameUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingUI extends JFrame {

    private JPanel mainPanel;
    private ScreenSize screenSize = ScreenSize.getInstance();
    private JButton[] buttons = new JButton[5];
    private final String settingList[] = {"화면사이즈 조절", "게임 조작 키 설정", "스코어보드 초기화", "색맹모드", "모든 설정 기본으로 돌리기"};


    public SettingUI(){
        //JFrame setting
        super("software-tetris");//제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 메모리까지 종료
        this.setSize(screenSize.getWidth(),screenSize.getHeight());
        this.setVisible(true);

        //board display setting
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5)
        );
        mainPanel.setBorder(border);

        this.getContentPane().add(mainPanel,BorderLayout.CENTER);
        setBtn();
//        setLabel();
        backBtn();


    }

    public void backBtn(){
        JButton button = new JButton("뒤로가기");
        mainPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameUI();
                setVisible(false);
            }
        });

    }



    public void setLabel(){
        JLabel settingLabel = new JLabel("SETTING");
        settingLabel.setForeground(Color.GRAY);
        settingLabel.setBackground(Color.WHITE);

        mainPanel.add(settingLabel,"North");
    }

    public void setBtn(){
        //btnPanel setting
        JPanel btnPanel = new JPanel();
        GridLayout gridLayout=new GridLayout( 5,1);
        btnPanel.setLayout(gridLayout);
        btnPanel.setBackground(Color.BLACK);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(screenSize.getHeight()/2,0,0,0));



        for (int i = 0; i < settingList.length; i++) {
            buttons[i] = new JButton(settingList[i]);
            btnPanel.add(buttons[i]);

        }
        mainPanel.add(btnPanel, "Center");

        //화면사이즈 조절 event
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FixSize();
                setVisible(false);
            }
        });
    }

}
