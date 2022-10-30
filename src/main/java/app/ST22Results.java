package app;

public class ST22Results {
    String lgaCode;
    String lgaName2016;
    String lgaState2016;
    String lgaType2016;
    String lgaName2021;
    String lgaState2021;
    String lgaType2021;
    float result2016;
    float result2021;
    int rank2016;
    int rank2021;
    float[] proportions;

    // constructor method
    public ST22Results () {
    }

    // setter methods
    public void setLGACode (String lgaCode) {
        this.lgaCode = lgaCode;
    }

    public void setLGAName2016 (String lgaName2016) {
        this.lgaName2016 = lgaName2016;
    }

    public void setLGAState2016 (String lgaState2016) {
        this.lgaState2016 = lgaState2016;
    }

    public void setLGAType2016 (String lgaType2016) {
        this.lgaType2016 = lgaType2016;
    }

    public void setLGAName2021 (String lgaName2021) {
        this.lgaName2021 = lgaName2021;
    }

    public void setLGAState2021 (String lgaState2021) {
        this.lgaState2021 = lgaState2021;
    }

    public void setLGAType2021 (String lgaType2021) {
        this.lgaType2021 = lgaType2021;
    }

    public void setResult2016 (float result2016) {
        this.result2016 = result2016;
    }

    public void setResult2021 (float result2021) {
        this.result2021 = result2021;
    }

    public void setRank2016 (int rank2016) {
        this.rank2016 = rank2016;
    }

    public void setRank2021 (int rank2021) {
        this.rank2021 = rank2021;
    }

    public void setProportions (float[] proportions) {
        this.proportions = proportions;
    }

    // getter methods
    public String getLGACode () {
        return this.lgaCode;
    }

    public String getLGAName2016 () {
        return this.lgaName2016;
    }

    public String getLGAState2016 () {
        return this.lgaState2016;
    }

    public String getLGAType2016 () {
        return this.lgaType2016;
    }

    public String getLGAName2021 () {
        return this.lgaName2021;
    }

    public String getLGAState2021 () {
        return this.lgaState2021;
    }

    public String getLGAType2021 () {
        return this.lgaType2021;
    }

    public float getResult2016 () {
        return this.result2016;
    }

    public float getResult2021 () {
        return this.result2021;
    }

    public int getRank2016 () {
        return this.rank2016;
    }

    public int getRank2021 () {
        return this.rank2021;
    }

    public float[] getProportions () {
        return this.proportions;
    }
}
