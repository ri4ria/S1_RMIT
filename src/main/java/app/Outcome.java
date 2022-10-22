package app;

public class Outcome {

    public String OutcomeID; 
    public String Title;
    public String Descrip;

    public Outcome() {
    }

    public Outcome(String id, String title, String descrip) {
        this.OutcomeID = id;
        this.Title = title; 
        this.Descrip = descrip;
    }

    public String getDescription() {
        return Descrip;
    }
}
