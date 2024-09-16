package scoreBoard.NoItemScoreBoard;

import scoreBoard.User;
import setting.DuplicateKeySettingException;
import setting.SaveAndLoad;
import setting.ScreenSize;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class  ScoreInputUI  extends JFrame{
    private JPanel mainPanel;
    private ScreenSize screenSize = ScreenSize.getInstance();
    private JPanel inputScorePanel;
    private JTextField name = new JTextField(10);


    //게임 후 점수를 parameter로 받음
    public ScoreInputUI(int score,String mode){
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

        createInputScore(score,mode);
        createCompleteBtn(score,mode);
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

    public void createCompleteBtn(int score,String mode) {
        JButton completeInput = new JButton("완료");
        inputScorePanel.add(completeInput);

        //사용자 점수 입력 받은 후 업데이트 스코어보드 화면
        completeInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User(name.getText(), score,mode);
                new ScoreBoardAfterInputUI(user);
                setVisible(false);
            }
        });

    }

    public void createInputScore(int score,String mode) {
        inputScorePanel = new JPanel();
        inputScorePanel.setBackground(Color.BLACK);

        GridLayout gridLayout=new GridLayout( 5,1);
        inputScorePanel.setLayout(gridLayout);

        String scoreToString = Integer.toString(score);


        JLabel scoreLabel = new JLabel("등록점수:"+scoreToString);
        scoreLabel.setForeground(Color.WHITE);
        inputScorePanel.add(scoreLabel);

        JLabel modeLabel = new JLabel("Mode:"+mode);
        modeLabel.setForeground(Color.WHITE);
        inputScorePanel.add(modeLabel);

        JLabel nameLabel = new JLabel("이름입력");
        nameLabel.setForeground(Color.WHITE);
        inputScorePanel.add(nameLabel);
        inputScorePanel.add(name);

        inputScorePanel.setBorder(BorderFactory.createEmptyBorder(screenSize.getHeight()/4,0,0,0));

        mainPanel.add(inputScorePanel);

    }
}
