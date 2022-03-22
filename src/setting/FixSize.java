package setting;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FixSize extends JFrame {
    private JPanel FixSizePanel;
    private JRadioButton[] radioButtons = new JRadioButton[3];

    public FixSize(ScreenSize screenSize) {
        //JFrame setting
        super("software-tetris");//제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 메모리까지 종료
        this.setSize(screenSize.getWidth(),screenSize.getHeight());
        this.setVisible(true);

        //board display setting
        FixSizePanel = new JPanel();
        FixSizePanel.setBackground(Color.BLACK);

        CompoundBorder border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 10),
                BorderFactory.createLineBorder(Color.DARK_GRAY, 5)
        );
        FixSizePanel.setBorder(border);

        this.getContentPane().add(FixSizePanel,BorderLayout.CENTER);

        selectSize(screenSize);
    }

    public void selectSize(ScreenSize screenSize) {
        JPanel radioPanel = new JPanel();
        GridLayout gridLayout=new GridLayout( 3,1);
        radioPanel.setLayout(gridLayout);
        radioPanel.setBackground(Color.BLACK);

        radioButtons[0] = new JRadioButton("400*500");
        radioButtons[1] = new JRadioButton("600*800");
        radioButtons[2] = new JRadioButton("800*1000");

        for (int i = 0; i < 3; i++) {
            radioPanel.add(radioButtons[i]);
        }

        FixSizePanel.add(radioPanel, "Center");


        JButton btn = new JButton("완료");
        FixSizePanel.add(btn, "South");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioButtons[0].isSelected()) {
                    //400*500
                    screenSize.setWidth(400);
                    screenSize.setHeight(500);

                } else if (radioButtons[1].isSelected()) {
                    screenSize.setWidth(600);
                    screenSize.setHeight(800);

                }
                else{
                    screenSize.setWidth(800);
                    screenSize.setHeight(1000);

                }

                new MainSetting(screenSize);
                setVisible(false);

            }
        });


    }
}
