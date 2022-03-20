package Panel;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class FixSize extends JFrame {
    private JPanel FixSizePanel;

    public FixSize() {
        //JFrame setting
        super("software-tetris");//제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 메모리까지 종료
        this.setSize(400, 500);
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
    }
}
