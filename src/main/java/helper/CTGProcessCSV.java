package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Stand-alone Java file for processing the database CSV files.
 * <p>
 * You can run this file using the "Run" or "Debug" options
 * from within VSCode. This won't conflict with the web server.
 * <p>
 * This program opens a CSV file from the Closing-the-Gap data set
 * and uses JDBC to load up data into the database.
 * <p>
 * To use this program you will need to change:
 * 1. The input file location
 * 2. The output file location
 * <p>
 * This assumes that the CSV files are the the **database** folder.
 * <p>
 * WARNING: This code may take quite a while to run as there will be a lot
 * of SQL insert statments!
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au

 */
public class CTGProcessCSV {

   // MODIFY these to load/store to/from the correct locations
   
   private static final String DATABASE = "jdbc:sqlite:database/ctg.db";
   private static final String CSV_FILE_indigenousStatus_2016 = "database/indigenousStatus_2016.csv"; // original file name: lga_indigenous_status_by_age_by_sex_census_2016.csv
   private static final String CSV_FILE_indigenousStatus_2021 = "database/indigenousStatus_2021.csv"; // original file name: lga_indigenous_status_by_age_by_sex_census_2021.csv
   private static final String CSV_FILE_householdIncome_2016 = "database/householdIncome_2016.csv"; // original file name: lga_total_household_income_weekly_by_indigenous_status_of_household_2016
   private static final String CSV_FILE_householdIncome_2021 = "database/householdIncome_2021.csv"; // original file name: lga_total_household_income_weekly_by_indigenous_status_of_household_2021
   private static final String CSV_FILE_highestSchoolYear_2016 = "database/highestSchoolYear_2016.csv"; // original file name: lga_highest_year_of_school_completed_by_indigenous_status_by_sex_2016
   private static final String CSV_FILE_highestSchoolYear_2021 = "database/highestSchoolYear_2021.csv"; // original file name: lga_highest_year_of_school_completed_by_indigenous_status_by_sex_2021
   private static final String CSV_FILE_LTHC_2021 = "database/LTHC_2021.csv"; // original file name: lga_long_term_health_conditions_by_indigenous_status_by_sex_2021
   private static final String CSV_FILE_lgas_2016 = "database/lgas_2016.csv";
   private static final String CSV_FILE_lgas_2021 = "database/lgas_2021.csv";
   private static final String CSV_FILE_targetOutcomes = "database/targetOutcomes.csv";
   private static final String CSV_FILE_persona = "database/persona.csv";
   private static final String CSV_FILE_personaAttribute = "database/personaAttribute.csv";
   private static final String CSV_FILE_states = "database/state.csv";
   private static final String CSV_FILE_ageBracket = "database/ageBracket.csv";
   private static final String CSV_FILE_incomeBracket = "database/incomeBracket.csv";

   public static void main (String[] args) {

      // JDBC Database Object
      Connection connection = null;

      // Implementing the householdInc2021.csv file
      // Original CSV file name: lga_total_household_income_weekly_by_indigenous_status_of_household_2021
      
      String category_householdInc_2021[] = {
         "1_149",
         "150_299",
         "300_399",
         "400_499",
         "500_649",
         "650_799",
         "800_999",
         "1000_1249",
         "1250_1499",
         "1500_1749",
         "1750_1999",
         "2000_2499",
         "2500_2999",
         "3000_3499",
         "3500_more"
      };

      String status_householdInc_2021[] = {
         "hhds_with_indig_persons",
         "other_hhds",
         "total_hhds"
      };

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_householdIncome_2021));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_householdInc_2021.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into HouseholdStatistics VALUES ("
                              + lgaCode + ","
                              + "2021" + ","
                              + "'" + status_householdInc_2021[indexStatus] + "',"
                              + "'" + category_householdInc_2021[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next status
               indexStatus++;
               if (indexStatus >= status_householdInc_2021.length) {
                  // Go to next Category
                  indexStatus = 0;
                  indexCategory++;
               }
               row++;
         }
      }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing incomeBracket CSV file

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_incomeBracket));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               String bracket = rowScanner.next();
               int min = rowScanner.nextInt();
               int max = rowScanner.nextInt();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into IncomeBracket VALUES ("
                              + "'" + bracket + "',"
                              + min + ","
                              + max + ");";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing ageBracket CSV file

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_ageBracket));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               String bracket = rowScanner.next();
               int min = rowScanner.nextInt();
               int max = rowScanner.nextInt();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into AgeBracket VALUES ("
                              + "'" + bracket + "',"
                              + min + ","
                              + max + ");";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing LTHC_2021 CSV file

      String category_LTHC_2021[] = {
         "arthritis",
         "asthma",
         "cancer",
         "dementia",
         "diabetes",
         "heartdisease",
         "kidneydisease",
         "lungcondition",
         "mentalhealth",
         "stroke",
         "other"
      };
      String status_LTHC_2021[] = {
         "indig",
         "non_indig",
         "indig_stat_notstated"
      };
      String sex_LTHC_2021[] = {
         "f",
         "m"
      };

      // JDBC Database Object
      // Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_LTHC_2021));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexSex = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_LTHC_2021.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into LTHCStatistics VALUES ("
                              + lgaCode + ","
                              + "2021" + ","
                              + "'" + status_LTHC_2021[indexStatus] + "',"
                              + "'" + sex_LTHC_2021[indexSex] + "',"
                              + "'" + category_LTHC_2021[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next sex
               indexSex++;
               if (indexSex >= sex_LTHC_2021.length) {
                  // Go to next status
                  indexSex = 0;
                  indexStatus++;

                  if (indexStatus >= status_LTHC_2021.length) {
                     // Go to next Category
                     indexStatus = 0;
                     indexCategory++;
                  }
               }
               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // The following arrays define the order of columns in the INPUT CSV.
      // The order of each array MUST match the order of the CSV.
      // These are specific to the given file and should be changed for each file.
      // This is a *simple* way to help you get up and running quickly wihout being confusing
      String category_indigenousStatus_2016[] = {
         "_0_4",
         "_5_9",
         "_10_14",
         "_15_19",
         "_20_24",
         "_25_29",
         "_30_34",
         "_35_39",
         "_40_44",
         "_45_49",
         "_50_54",
         "_55_59",
         "_60_64",
         "_65_yrs_ov"
      };
      String status_indigenousStatus_2016[] = {
         "indig",
         "non_indig",
         "indig_ns"
      };
      String sex_indigenousStatus_2016[] = {
         "f",
         "m"
      };

      // JDBC Database Object
      // Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_indigenousStatus_2016));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexSex = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Skip lga_name
            // String lgaName = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_indigenousStatus_2016.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into PopulationStatistics VALUES ("
                              + lgaCode + ","
                              + "2016" + ","
                              + "'" + status_indigenousStatus_2016[indexStatus] + "',"
                              + "'" + sex_indigenousStatus_2016[indexSex] + "',"
                              + "'" + category_indigenousStatus_2016[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next sex
               indexSex++;
               if (indexSex >= sex_indigenousStatus_2016.length) {
                  // Go to next status
                  indexSex = 0;
                  indexStatus++;

                  if (indexStatus >= status_indigenousStatus_2016.length) {
                     // Go to next Category
                     indexStatus = 0;
                     indexCategory++;
                  }
               }
               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing indigenousStatus_2021 CSV file

      String category_indigenousStatus_2021[] = {
         "_0_4",
         "_5_9",
         "_10_14",
         "_15_19",
         "_20_24",
         "_25_29",
         "_30_34",
         "_35_39",
         "_40_44",
         "_45_49",
         "_50_54",
         "_55_59",
         "_60_64",
         "_65_yrs_ov"
      };
      String status_indigenousStatus_2021[] = {
         "indig",
         "non_indig",
         "indig_ns"
      };
      String sex_indigenousStatus_2021[] = {
         "f",
         "m"
      };

      // JDBC Database Object
      // Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_indigenousStatus_2021));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexSex = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Skip lga_name
            // String lgaName = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_indigenousStatus_2021.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into PopulationStatistics VALUES ("
                              + lgaCode + ","
                              + "2021" + ","
                              + "'" + status_indigenousStatus_2021[indexStatus] + "',"
                              + "'" + sex_indigenousStatus_2021[indexSex] + "',"
                              + "'" + category_indigenousStatus_2021[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next sex
               indexSex++;
               if (indexSex >= sex_indigenousStatus_2021.length) {
                  // Go to next status
                  indexSex = 0;
                  indexStatus++;

                  if (indexStatus >= status_indigenousStatus_2021.length) {
                     // Go to next Category
                     indexStatus = 0;
                     indexCategory++;
                  }
               }
               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing state CSV file

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_states));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               int lga_starting_number = rowScanner.nextInt();
               String state = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into State VALUES ("
                              + lga_starting_number + ","
                              + "'" + state + "')";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
      
      // Implementing persona CSV file

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_persona));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               String name = rowScanner.next();
               int age = rowScanner.nextInt();
               String ethnicity = rowScanner.next();
               String quote = rowScanner.next();
               String image_file_path = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into Persona VALUES ("
                              + "'" + name + "',"
                              + "'" + age + "',"
                              + "'" + ethnicity + "',"
                              + "'" + quote + "',"
                              + "'" + image_file_path + "')";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing personaAttributes CSV file

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_personaAttribute));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               String name = rowScanner.next();
               int id = rowScanner.nextInt();
               String attributeType = rowScanner.next();
               String description = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into PersonaAttribute VALUES ("
                              + "'" + name + "',"
                              + id + ","
                              + "'" + attributeType + "',"
                              + "'" + description + "')";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);
               
               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing lgas_2016 CSV files

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_lgas_2016));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               String lgaCode = rowScanner.next();
               String lgaName = rowScanner.next();
               String lgaType = rowScanner.next();
               String lgaArea = rowScanner.next();
               String lgaLatitude = rowScanner.next();
               String lgaLongitude = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into LGA VALUES ("
                              + lgaCode + ","
                              + "2016" + ","
                              + "'" + lgaName + "',"
                              + "'" + lgaType + "',"
                              + lgaArea + ","
                              + lgaLatitude + ","
                              + lgaLongitude + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing lgas_2021 CSV files

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_lgas_2021));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            
            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext()) {

               // Save the lga_code as we need it for the foreign key
               String lgaCode = rowScanner.next();
               String lgaName = rowScanner.next();
               String lgaType = rowScanner.next();
               String lgaArea = rowScanner.next();
               String lgaLatitude = rowScanner.next();
               String lgaLongitude = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into LGA VALUES ("
                              + lgaCode + ","
                              + "2021" + ","
                              + "'" + lgaName + "',"
                              + "'" + lgaType + "',"
                              + lgaArea + ","
                              + lgaLatitude + ","
                              + lgaLongitude + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing the highestSchoolYear_2016.csv file
      // Original CSV file name: lga_highest_year_of_school_completed_by_indigenous_status_by_sex_2016

      String category_highestSchoolYear_2016[] = {
         "did_not_go_to_school",
         "y8_below",
         "y9_equivalent",
         "y10_equivalent",
         "y11_equivalent",
         "y12_equivalent"
      };
      String status_highestSchoolYear_2016[] = {
         "indig",
         "non_indig",
         "indig_stat_notstated"
      };
      String sex_highestSchoolYear_2016[] = {
         "f",
         "m"
      };

      // JDBC Database Object
      // Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_highestSchoolYear_2016));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexSex = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Skip lga_name
            // String lgaName = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_highestSchoolYear_2016.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into EducationStatistics VALUES ("
                              + lgaCode + ","
                              + "2016" + ","
                              + "'" + status_highestSchoolYear_2016[indexStatus] + "',"
                              + "'" + sex_highestSchoolYear_2016[indexSex] + "',"
                              + "'" + category_highestSchoolYear_2016[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next sex
               indexSex++;
               if (indexSex >= sex_highestSchoolYear_2016.length) {
                  // Go to next status
                  indexSex = 0;
                  indexStatus++;

                  if (indexStatus >= status_highestSchoolYear_2016.length) {
                     // Go to next Category
                     indexStatus = 0;
                     indexCategory++;
                  }
               }
               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing the highestSchoolYear_2021.csv file
      // Original CSV file name: lga_highest_year_of_school_completed_by_indigenous_status_by_sex_2021

      String category_highestSchoolYear_2021[] = {
         "did_not_go_to_school",
         "y8_below",
         "y9_equivalent",
         "y10_equivalent",
         "y11_equivalent",
         "y12_equivalent"
      };
      String status_highestSchoolYear_2021[] = {
         "indig",
         "non_indig",
         "indig_stat_notstated"
      };
      String sex_highestSchoolYear_2021[] = {
         "f",
         "m"
      };

      // JDBC Database Object
      // Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_highestSchoolYear_2021));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexSex = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Skip lga_name
            // String lgaName = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_highestSchoolYear_2021.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into EducationStatistics VALUES ("
                              + lgaCode + ","
                              + "2021" + ","
                              + "'" + status_highestSchoolYear_2021[indexStatus] + "',"
                              + "'" + sex_highestSchoolYear_2021[indexSex] + "',"
                              + "'" + category_highestSchoolYear_2021[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next sex
               indexSex++;
               if (indexSex >= sex_highestSchoolYear_2021.length) {
                  // Go to next status
                  indexSex = 0;
                  indexStatus++;

                  if (indexStatus >= status_highestSchoolYear_2021.length) {
                     // Go to next Category
                     indexStatus = 0;
                     indexCategory++;
                  }
               }
               row++;
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }

      // Implementing the householdInc2016.csv file
      // Original CSV file name: lga_total_household_income_weekly_by_indigenous_status_of_household_2016
      
      String category_householdInc_2016[] = {
         "1_149",
         "150_299",
         "300_399",
         "400_499",
         "500_649",
         "650_799",
         "800_999",
         "1000_1249",
         "1250_1499",
         "1500_1999",
         "2000_2499",
         "2500_2999",
         "3000_more"
      };

      String status_householdInc_2016[] = {
         "hhds_with_indig_persons",
         "other_hhds",
         "total_hhds"
      };

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE_householdIncome_2016));

         // Read the first line of "headings"
         String header = lineScanner.nextLine();
         System.out.println("Heading row" + header + "\n");

         // Setup JDBC
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE);

         // Read each line of the CSV
         int row = 1;
         while (lineScanner.hasNext()) {
            // Always get scan by line
            String line = lineScanner.nextLine();
            
            // Create a new scanner for this line to delimit by commas (,)
            Scanner rowScanner = new Scanner(line);
            rowScanner.useDelimiter(",");

            // These indicies track which column we are up to
            int indexStatus = 0;
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_householdInc_2016.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into HouseholdStatistics VALUES ("
                              + lgaCode + ","
                              + "2016" + ","
                              + "'" + status_householdInc_2016[indexStatus] + "',"
                              + "'" + category_householdInc_2016[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next status
               indexStatus++;
               if (indexStatus >= status_householdInc_2016.length) {
                  // Go to next Category
                  indexStatus = 0;
                  indexCategory++;
               }
               row++;
         }
      }

      } catch (Exception e) {
         e.printStackTrace();
      }

   // OUTCOMES 

   // Like JDBCConnection, we need some error handling.
   try {
      // Open A CSV File to process, one line at a time
      // CHANGE THIS to process a different file
      Scanner lineScanner = new Scanner(new File(CSV_FILE_targetOutcomes));

      // Read the first line of "headings"
      String header = lineScanner.nextLine();
      System.out.println("Heading row" + header + "\n");

      // Setup JDBC
      // Connect to JDBC data base
      connection = DriverManager.getConnection(DATABASE);

      // Read each line of the CSV
      int row = 1;
      while (lineScanner.hasNext()) {
         // Always get scan by line
         String line = lineScanner.nextLine();
         
         // Create a new scanner for this line to delimit by commas (,)
         Scanner rowScanner = new Scanner(line);
         rowScanner.useDelimiter(",");

         // Go through the data for the row
         // If we run out of categories, stop for safety (so the code doesn't crash)
         while (rowScanner.hasNext()) {

            // no need for foreign keys
            String OutcomeID = rowScanner.next();
            String Title = rowScanner.next();
            String Descrip = rowScanner.next();

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();

            // Create Insert Statement
            String query = "INSERT into Outcomes VALUES ("
                           + OutcomeID + ","
                           + "'" + Title + "',"
                           + "'" + Descrip + "')";

            // Execute the INSERT
            System.out.println("Executing: " + query);
            statement.execute(query);

            row++;
         }
      }

   } catch (Exception e) {
      e.printStackTrace();
   }

   } //keep second to last
} //keep last
