package setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import scoreBoard.NoItemScoreBoard.ScoreList;
import scoreBoard.User;
import scoreBoard.scoreBoradItem.ItemScoreList;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class SaveAndLoad {
    private static ScreenSize screenSize = ScreenSize.getInstance();
    private static ScoreList scoreList = ScoreList.getInstance();
    private static ItemScoreList itemScoreList = ItemScoreList.getInstance();
    private static KeySetting keySetting = KeySetting.getInstance();
    private static ColorBlind colorBlind = ColorBlind.getInstance();


    public static void SaveSetting(){

        //화면
        JSONObject screenSizeJson = new JSONObject();
        screenSizeJson.put("width", screenSize.getWidth());
        screenSizeJson.put("height", screenSize.getHeight());

        //keySetting 저장
        JSONObject keySettingJson = new JSONObject();
        keySettingJson.put("left", keySetting.getLeft());
        keySettingJson.put("right", keySetting.getRight());
        keySettingJson.put("downBlock", keySetting.getDownBlock());
        keySettingJson.put("turnBlock", keySetting.getTurnBlock());
        keySettingJson.put("oneTimeDown", keySetting.getOneTimeDown());
        keySettingJson.put("stop", keySetting.getStop());
        keySettingJson.put("escape", keySetting.getEscape());

        //스코어보드(item모드 없음)
        List<User> list = scoreList.getList();
        JSONObject scoreBoardJson = new JSONObject();
        int size = list.size();
        //10개 까지만 저장
        if(size>=10){
            size=10;
        }
        for (int i=0;i<size;i++){
            scoreBoardJson.put("name"+i, list.get(i).getName());
            scoreBoardJson.put("score"+i, list.get(i).getScore());
            scoreBoardJson.put("mode"+i, list.get(i).getMode());
        }

        //스코어보드(item모드)
        List<User> listForItem = itemScoreList.getList();
        JSONObject scoreBoardItemJson = new JSONObject();
        int sizeForItemList = listForItem.size();
        //10개 까지만 저장
        if(sizeForItemList>=10){
            sizeForItemList=10;
        }
        for (int i=0;i<sizeForItemList;i++){
            scoreBoardItemJson.put("name"+i, listForItem.get(i).getName());
            scoreBoardItemJson.put("score"+i, listForItem.get(i).getScore());
            scoreBoardItemJson.put("mode"+i, listForItem.get(i).getMode());
        }

        //색맹모드
        JSONObject colorBlindJson = new JSONObject();
        colorBlindJson.put("colorBlind", colorBlind.getColorBlind());

        try{
            //스크린 사이즈 저장
            FileWriter screenSizeFile = new FileWriter("src/main/java/save/ScreenSize.json");
            screenSizeFile.write(screenSizeJson.toJSONString());
            screenSizeFile.flush();
            screenSizeFile.close();

            //keySetting json 파일에 저장
            FileWriter keySettingFile = new FileWriter("src/main/java/save/KeySetting.json");
            keySettingFile.write(keySettingJson.toJSONString());
            keySettingFile.flush();
            keySettingFile.close();

            //스코어리스트 10개까지 저장
            FileWriter scoreListFile = new FileWriter("src/main/java/save/NoItemScoreList.json");
            scoreListFile.write(scoreBoardJson.toJSONString());
            scoreListFile.flush();
            scoreListFile.close();

            //Item스코어리스트 10개까지 저장
            FileWriter ItemScoreListFile = new FileWriter("src/main/java/save/ItemScoreList.json");
            ItemScoreListFile.write(scoreBoardItemJson.toJSONString());
            ItemScoreListFile.flush();
            ItemScoreListFile.close();

            //색맹모드 저장
            FileWriter colorBlindFile = new FileWriter("src/main/java/save/ColorBlind.json");
            colorBlindFile.write(colorBlindJson.toJSONString());
            colorBlindFile.flush();
            colorBlindFile.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void LoadSetting() {
        JSONParser parser = new JSONParser();

        try{
            //화면 크기 load
            InputStream getScreenSize = new FileInputStream("src/main/java/save/ScreenSize.json");
            HashMap<String,Object> screenSizeMap = new ObjectMapper().readValue(getScreenSize, HashMap.class);
            screenSize.setWidth((Integer) screenSizeMap.get("width"));
            screenSize.setHeight((Integer) screenSizeMap.get("height"));

            //스코어 보드 load
            InputStream getScoreBoard = new FileInputStream("src/main/java/save/NoItemScoreList.json");
            HashMap<String,Object> scoreBoardMap = new ObjectMapper().readValue(getScoreBoard, HashMap.class);
            System.out.println("scoreBoardMap = " + scoreBoardMap.size());
            int size = scoreBoardMap.size()/3;
            if (size >= 10) {
                size=10;
            }
            for (int i = 0; i < size; i++) {
                scoreList.push(new User((String) scoreBoardMap.get("name" + i), (Integer) scoreBoardMap.get("score" + i), (String)scoreBoardMap.get("mode" + i)));

            }
            scoreList.sortDescByScore();

            //Item스코어 보드 load
            InputStream getItemScoreBoard = new FileInputStream("src/main/java/save/ItemScoreList.json");
            HashMap<String,Object> ItemscoreBoardMap = new ObjectMapper().readValue(getItemScoreBoard, HashMap.class);
            System.out.println("scoreBoardMap = " + ItemscoreBoardMap.size());
            int ItemListSize = ItemscoreBoardMap.size()/3;
            if (ItemListSize >= 10) {
                ItemListSize=10;
            }
            for (int i = 0; i < ItemListSize; i++) {
                itemScoreList.push(new User((String) ItemscoreBoardMap.get("name" + i), (Integer) ItemscoreBoardMap.get("score" + i), (String)ItemscoreBoardMap.get("mode" + i)));

            }
            itemScoreList.sortDescByScore();

            //keySetting Load
            InputStream getKeySetting = new FileInputStream("src/main/java/save/KeySetting.json");
            HashMap<String,Object> getKeySettingMap = new ObjectMapper().readValue(getKeySetting, HashMap.class);
            int left = (Integer)getKeySettingMap.get("left");
            int right = (Integer)getKeySettingMap.get("right");
            int downBlock = (Integer)getKeySettingMap.get("downBlock");
            int turnBlock = (Integer)getKeySettingMap.get("turnBlock");
            int stop = (Integer)getKeySettingMap.get("stop");
            int oneTimeDown = (Integer)getKeySettingMap.get("oneTimeDown");
            int escape = (Integer)getKeySettingMap.get("escape");
            keySetting.setKeySetting(left,right,turnBlock,downBlock,stop,oneTimeDown,escape);


            //색맹모드 load
            InputStream getColorBlind = new FileInputStream("src/main/java/save/ColorBlind.json");
            HashMap<String,Object> colorBlindMap = new ObjectMapper().readValue(getColorBlind, HashMap.class);
            colorBlind.setCurColorBlind((Integer) colorBlindMap.get("colorBlind"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
