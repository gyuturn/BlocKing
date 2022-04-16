package start;

import game.GameUI;
import scoreBoard.ScoreBoardUI;
import setting.ScreenSize;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static start.IsButtonClicked.btnClicked;

public class StartUI extends JFrame {

    private JPanel mainPanel;
    private ScreenSize screenSize = ScreenSize.getInstance();
    ImageIcon titleImg1 = new ImageIcon("src/start/img/title1.png");
    ImageIcon titleImg2 = new ImageIcon("src/start/img/title2.png");
    ImageIcon titleImg3 = new ImageIcon("src/start/img/title3.png");



    public StartUI(){
        //JFrame setting
        super("software-tetris");//제목
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 메모리까지 종료
        this.setSize(screenSize.getWidth(), screenSize.getHeight());
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


        titleBtn();
        gameBtn();
        settingBtn();
        scbBtn();
        exitBtn();
        //defaultBtn();



    }


    public void titleBtn(){
        JButton settingTitles = new JButton(titleImg1);
        //설정에서 이미지 크기를 변경하면 타이틀 이미지 크기도 조정됨
        if(screenSize.getWidth() == 400){
            settingTitles = new JButton(titleImg1);
        }
        else if(screenSize.getWidth() == 600){
            settingTitles = new JButton(titleImg2);
        }
        else{
            settingTitles = new JButton(titleImg3);
        }
        settingTitles.setBorderPainted(false);
        settingTitles.setFocusPainted(false);
        settingTitles.setContentAreaFilled(false);

        settingTitles.setPreferredSize(new Dimension((int)(screenSize.getWidth()*0.8), (int)(screenSize.getWidth()*0.17))); // 버튼 크기 지정
        mainPanel.add(settingTitles);

        settingTitles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameUI();
                setVisible(false);
            }
        });
    }

    public void gameBtn(){
        JButton gameButtons = new JButton("게임 시작");
        mainPanel.add(gameButtons);

        btnClicked.setGameBtnClicked();
        if(btnClicked.isGameBtnClicked()){
            gameButtons.setForeground(Color.red);
        }

        //다음으로 넘어가는 event
        gameButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameUI();
                setVisible(false);
            }
        });

        gameButtons.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e)  {
                if(btnClicked.isGameBtnClicked()){
                    // 37 왼쪽, 38 위쪽, 39 오른쪽, 40 아래쪽
                    //if( e.getKeyCode() == 37 )
                    //if( e.getKeyCode() == 38 )
                    if( e.getKeyCode() == 39 ){
                        btnClicked.setSettingBtnClicked();
                    }
                    if( e.getKeyCode() == 40 ){
                        btnClicked.setSettingBtnClicked();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


    }

    public void settingBtn(){
        JButton settingButtons = new JButton("설정 메뉴");
        mainPanel.add(settingButtons);

        if(btnClicked.isSettingBtnClicked()){
            settingButtons.setForeground(Color.red);
        }

        boolean isClicked = false;
        if(isClicked == true){
           settingButtons.setForeground(Color.red);
        }

        //다음으로 넘어가는 event
        settingButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new MainSetting();
                setVisible(false);
            }
        });

        settingButtons.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e)  {
                if(btnClicked.isSettingBtnClicked()){
                    // 37 왼쪽, 38 위쪽, 39 오른쪽, 40 아래쪽
                    if( e.getKeyCode() == 37 ) {
                        btnClicked.setGameBtnClicked();
                    }
                    if( e.getKeyCode() == 38 ) {
                        btnClicked.setGameBtnClicked();
                    }
                    if( e.getKeyCode() == 39 ){
                        btnClicked.setScbBtnClicked();
                    }
                    if( e.getKeyCode() == 40 ){
                        btnClicked.setScbBtnClicked();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    public void scbBtn(){//스코어보드 버튼
        JButton scbButtons = new JButton("스코어보드");
        mainPanel.add(scbButtons);

        boolean isClicked = false;
        if(isClicked == true){
            scbButtons.setForeground(Color.red);
        }

        //다음으로 넘어가는 event
        scbButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScoreBoardUI();
                setVisible(false);
            }
        });
    }

    public void exitBtn(){
        JButton exitButtons = new JButton("게임 종료");
        mainPanel.add(exitButtons);

        boolean isClicked = false;
        if(isClicked == true){
            exitButtons.setForeground(Color.red);
        }

        //다음으로 넘어가는 event
        exitButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void defaultBtn(){
        JButton defaultButtons = new JButton("기본값 버튼입니다");
        mainPanel.add(defaultButtons);

        boolean isClicked = false;
        if(isClicked == true){
            defaultButtons.setForeground(Color.red);
        }

        //다음으로 넘어가는 event
        defaultButtons.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("버튼의 기본값입니다.");
            }
        });
    }

}
