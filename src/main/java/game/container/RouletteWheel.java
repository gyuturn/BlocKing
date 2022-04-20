package game.container;

import java.util.*;

public class RouletteWheel {

    //ArrayLists for storing inputs and wheel
    private ArrayList<Double> wheel = new ArrayList<Double>();
    private ArrayList<Double> inputs;
    private static int blockNum=7;

    private double inputSum;


    public ArrayList<Double> getWheel() {
        return wheel;
    }
    public double getSum(){
        return inputSum;
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

    //easy블럭 생성시 이 함수 사용
    public static int EasyModeIncludeTest(){
        boolean testPass=false;
        while (testPass==false) {
            int[] test = new int[blockNum];
            for (int i = 0; i < 7000; i++) {
                int blockShape = RouletteWheel.EasyMode();
                test[blockShape]++;
            }
            double trueValue = 1200;
            double measuredValue = test[0];
            double error = Math.abs(trueValue - measuredValue);
            double errorPercent = error / trueValue * 100;
            System.out.println("errorPercent = " + errorPercent);

            if(errorPercent<=5)
                testPass=true;

        }
        return RouletteWheel.EasyMode();
    }

    public  static int EasyMode(){
        ArrayList<Double> blocks = new ArrayList<Double>();
        for (int i = 0; i < blockNum; i++) {
            //I블록인 경우
            if (i == 0) {
                blocks.add((double)1.2);
            }
            else{
                blocks.add((double) 1.0);
            }
        }
        RouletteWheel rouletteWheel = new RouletteWheel(blocks);
        ArrayList<Double> wheel = rouletteWheel.getWheel();
        double r = Math.random();
        double random = r * 7.2;
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


    public static int HardMode(){
        ArrayList<Double> blocks = new ArrayList<Double>();
        for (int i = 0; i < blockNum; i++) {
            //I블록인 경우
            if (i == 0) {
                blocks.add((double)1.0);
            }
            else{
                blocks.add((double) 1.2);
            }
        }
        RouletteWheel rouletteWheel = new RouletteWheel(blocks);
        ArrayList<Double> wheel = rouletteWheel.getWheel();
        double r = Math.random();
        double random = r * 7.2;
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





}

