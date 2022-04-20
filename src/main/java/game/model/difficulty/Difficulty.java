package game.model.difficulty;

public class Difficulty {

    private int addSpeed;

    private double perIBlock;
    private double perJBlock;
    private double perLBlock;
    private double perOBlock;
    private double perSBlock;
    private double perTBlock;
    private double perZBlock;

    public Difficulty(
        int addSpeed,

        double perIBlock,
        double perJBlock,
        double perLBlock,
        double perOBlock,
        double perSBlock,
        double perTBlock,
        double perZBlock) {

            this.addSpeed = addSpeed;

            this.perIBlock = perIBlock;
            this.perJBlock = perJBlock;
            this.perLBlock = perLBlock;
            this.perOBlock = perOBlock;
            this.perSBlock = perSBlock;
            this.perTBlock = perTBlock;
            this.perZBlock = perZBlock;
    }

    public double getSum(){
        return this.perIBlock + this.perJBlock + this.perLBlock + this.perOBlock + this.perSBlock + this.perTBlock
                + this.perZBlock;
    }

    public double getPerIBlock() {
        return perIBlock;
    }

    public double getPerOBlock() {
        return perOBlock;
    }


}
