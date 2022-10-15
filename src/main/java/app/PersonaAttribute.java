package app;

public class PersonaAttribute {

   private String name;
   private int id;
   private String attributeType;
   private String description;
   
   // Constructor
   public PersonaAttribute(String name, int id, String attributeType, String description) {
      this.name = name;
      this.id = id;
      this.attributeType = attributeType;
      this.description = description;
   }

   // Getter methods
   public String getName() {
      return name;
   }

   public int getId() {
      return id;
   }

   public String getAttributeType() {
      return attributeType;
   }

   public String description() {
      return description;
   }
}
