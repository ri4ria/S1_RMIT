package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 */
public class LGA {
   // LGA 2016 Code
   private int code;

   // LGA Year
   private int year;

   // LGA 2016 Name
   private String name;
   

   /**
    * Create an LGA and set the fields
    */
   public LGA(int code, int year, String name) {
      this.code = code;
      this.year = year;
      this.name = name;
   }

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
