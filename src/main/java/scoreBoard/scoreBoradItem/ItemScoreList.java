package scoreBoard.scoreBoradItem;


import scoreBoard.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ItemScoreList {
    private List<User> list = new ArrayList<>();

    private static ItemScoreList ItemScoreList = new ItemScoreList();

    //싱글톤
    public static ItemScoreList getInstance() {
        return ItemScoreList;
    }

    public List<User> getList() {
        return list;
    }

    public List<User> push(User user){
        this.list.add(user);
        return this.list;
    }

    public void deleteAll(){
        this.list.clear();
    }

    public List<User> sortDescByScore(){
        Collections.sort(list);
        return this.list;
    }





}
