package app;

public class Table {

    public String code; 
    public String name;
    public String indig;
    public String nonindig; 
    public String nonstated; 
    public String total;
    public String gap;
    public String proportional;

    public Table() {
    }

    public Table(String code, String name, String indig, String nonindig, String nonstated, String total, String gap,
            String proportional) {
        this.code = code; 
        this.name = name; 
        this.indig = indig; 
        this.nonindig = nonindig; 
        this.nonstated = nonstated; 
        this.total = total; 
        this.gap = gap; 
        this.proportional = proportional; 
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

    public String getNonstated() {
        return nonstated;
    }

    public String getTotal() {
        return total;
    }
    
    public String getGap() {
        return gap;
    }

    public String getProportional() {
        return proportional;
    }



}
