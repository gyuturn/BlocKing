package scoreBoard;

import setting.ScreenSize;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class ScoreBoardUI extends JFrame{

    private JPanel mainPanel;
    private ScreenSize screenSize = ScreenSize.getInstance();



    public ScoreBoardUI(){
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


    }

}
