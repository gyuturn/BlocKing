package scoreBoard;

public class User implements Comparable<User>{

    private String name;

    private int score;

    private String mode;



    public User(String name, int score,String mode) {
        this.name = name;
        this.score = score;
        this.mode = mode;
    }

    public String getName() {
        return name;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public String getMode() {
        return mode;
    }






    //내림차순 정렬
    @Override
    public int compareTo(User o) {
        return o.score - this.getScore();
    }
}
