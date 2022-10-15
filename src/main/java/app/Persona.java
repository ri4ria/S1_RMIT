package app;

public class Persona {

   private String name;
   private int age;
   private String ethnicity;
   private String quote;
   private String image_file_path;
   
   // Constructor
   public Persona(String name, int age, String ethnicity, String quote, String image_file_path) {
      this.name = name;
      this.age = age;
      this.ethnicity = ethnicity;
      this.quote = quote;
      this.image_file_path = image_file_path;
   }

   // Getter methods
   public String getName() {
      return name;
   }

   public int getAge() {
      return age;
   }

   public String getEthnicity() {
      return ethnicity;
   }

   public String getQuote() {
      return quote;
   }

   public String getImageFilePath() {
      return image_file_path;
   }
}
