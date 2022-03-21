package setting;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSetting extends JFrame {

    private JPanel mainPanel;
    private JButton[] buttons = new JButton[5];
    private final String settingList[] = {"화면사이즈 조절", "게임 조작 키 설정", "스코어보드 초기화", "색맹모드", "모든 설정 기본으로 돌리기"};


    public MainSetting(){
        //JFrame setting
        super("software-tetris");//제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 메모리까지 종료
        this.setSize(400, 500);
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


    }

    void setBtn(){
        for (int i = 0; i < settingList.length; i++) {
            buttons[i] = new JButton(settingList[i]);
            buttons[i].setBounds(50,50,50,50);
            mainPanel.add(buttons[i]);
        }

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
