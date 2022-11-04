package app;

public class ST32Results {
    int code;
    String name;
    int result;
    String proportion;

    // constructor method
    public ST32Results () {
    }

    // setter methods
    public void setLGACode (int code) {
        this.code = code;
    }

    public void setLGAName (String name) {
        this.name = name;
    }

    public void setResult (int result) {
        this.result = result;
    }

    public void setProportion (String proportion) {
        this.proportion = proportion;
    }

    // getter methods
    public int getLGACode () {
        return this.code;
    }

    public String getLGAName () {
        return this.name;
    }

    public int getResult () {
        return this.result;
    }

    public String getProportion () {
        return this.proportion;
    }
}
