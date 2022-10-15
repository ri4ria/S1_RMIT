package app;

public class Persona {

   private String name;
   private String quote;
   private String image_file_path;
   
   // Constructor
   public Persona(String name, String quote, String image_file_path) {
      this.name = name;
      this.quote = quote;
      this.image_file_path = image_file_path;
   }

   // Getter methods
   public String getName() {
      return name;
   }

   public String getQuote() {
      return quote;
   }

   public String getImageFilePath() {
      return image_file_path;
   }
}
