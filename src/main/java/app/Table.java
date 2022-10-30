package app;

public class Table {

    public String code; 
    public String name;
    public String indig;
    public String nonindig; 
    public String propIndig; 
    public String propNon;
    public String total;
    public String gap;

    public Table() {
    }

    public Table(String code, String name, String indig, String nonindig, String total, String propIndig, String propNon, String gap) {
        this.code = code; 
        this.name = name; 
        this.indig = indig; 
        this.nonindig = nonindig; 
        this.propIndig = propIndig; 
        this.propNon = propNon; 
        this.total = total; 
        this.gap = gap; 
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getIndig() {
        return indig;
    }

    public String getNonindig() {
        return nonindig;
    }

    public String getTotal() {
        return total;
    }

    public String getPropIndig() {
        return propIndig;
    }

    public String getPropNon() {
        return propNon;
    }
    
    public String getGap() {
        return gap;
    }




}
