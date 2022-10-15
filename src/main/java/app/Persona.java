package app;

public class Persona {

   private int code;
   private int year;
   private String name;
   
   // Constructor
   public Persona(int code, int year, String name) {
      this.code = code;
      this.year = year;
      this.name = name;
   }

   // Getter methods
   public int getCode() {
      return code;
   }

   public int getYear() {
      return year;
   }

   public String getName() {
      return name;
   }
}
