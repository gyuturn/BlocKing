package setting;

import game.GameUI;
import scoreBoard.ScoreBoardUI;
import scoreBoard.ScoreList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class SettingUI extends JFrame {

    private JPanel mainPanel;
    private ScreenSize screenSize = ScreenSize.getInstance();
    private JButton[] buttons = new JButton[6];
    private ScoreList scoreList = ScoreList.getInstance();

    private final String settingList[] = {"화면사이즈 조절", "게임 조작 키 설정", "스코어보드 초기화", "색맹모드", "모든 설정 기본으로 돌리기","스코어 보드"};  //스코어 보드는 테스트용 -> 실제는 게임 시작화면에 있어야함


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

        //종료 시 현재 setting 및 scoreBoard 저장
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                SaveAndLoad.SaveSetting();
            }
        });


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
        GridLayout gridLayout=new GridLayout( 6,1);
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
                new FixSizeUI();
                setVisible(false);
            }
        });

        //스코어 보드 기록 초기화
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?","스코어 보드 기록 초기화",YES_NO_OPTION);
                if(result==0){
                    scoreList.deleteAll();
                }



            }
        });


        //스코어 보드 event--test용
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScoreBoardUI();
                setVisible(false);
            }
        });
    }

}
