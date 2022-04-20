package game.model.difficulty;

import game.manager.GameInfoManager;

import java.util.*;

public class RouletteWheel {

    //ArrayLists for storing inputs and wheel
    private ArrayList<Double> wheel = new ArrayList<Double>();
    private ArrayList<Double> inputs;
    private static int blockNum=7;
    private static GameInfoManager gameInfoManager = GameInfoManager.getInstance();

    private double inputSum;


    public ArrayList<Double> getWheel() {
        return wheel;
    }
    public int getSize(){
        return wheel.size();
    }

    public RouletteWheel(ArrayList<Double> inputList) {

        //give inputList global access
        inputs = inputList;
        wheel.add(inputList.get(0));

        //create ArrayList of summed values
        for (int i = 1; i < inputList.size(); i++) {
            wheel.add(wheel.get(i - 1) + inputList.get(i));
        }

        inputSum = wheel.get(wheel.size() - 1);
    }
    //블럭생성시 이 함수 사용
    public  static int GenerateBlockByValue(GameInfoManager.GameDifficulty gameDifficulty){
        Difficulty difficulty = gameInfoManager.difficultiesMap.get(gameDifficulty);
        ArrayList<Double> blocks = new ArrayList<Double>();

        for (int i = 0; i < blockNum; i++) {
            //I블록인 경우
            if (i == 0) {
                blocks.add(difficulty.getPerIBlock());
            }
            else{
                blocks.add((difficulty.getPerOBlock()));
            }
        }
        RouletteWheel rouletteWheel = new RouletteWheel(blocks);
        ArrayList<Double> wheel = rouletteWheel.getWheel();



        double r = Math.random();
        double random = r * difficulty.getSum();
        int blockShape=0; //초기값 설정
        for (int i = 0; i < rouletteWheel.getWheel().size(); i++) {
            if(i==0){
                if (random >= 0 && wheel.get(i) >random) {
                    blockShape = i;
                    break;
                }
            }
            else if (random >= wheel.get(i-1) && random < wheel.get(i)) {
                blockShape=i;
                break;
            }
        }

        return blockShape;
    }


    public static int GenerateBlockByValueTest(GameInfoManager.GameDifficulty gameDifficulty){
            int[] test = new int[blockNum];
            for (int i = 0; i < 7000; i++) {
                int blockShape = RouletteWheel.GenerateBlockByValue(gameDifficulty);
                test[blockShape]++;
            }

            for (int i = 0; i < 7; i++) {
                System.out.println(i + "번째" + test[i]);
            }
            int measuredValue = test[0];

        return measuredValue;
    }

    public static double ComputeErrorPercent(int measuredValue,int trueValue) {
        double error = Math.abs(trueValue - measuredValue);
        double errorPercent = error / trueValue * 100;
        System.out.println("errorPercent = " + errorPercent);
        return errorPercent;
    }










}

