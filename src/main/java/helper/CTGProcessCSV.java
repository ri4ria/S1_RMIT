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
   private static final String CSV_FILE = "database/lga_indigenous_status_by_age_by_sex_census_2016.csv";
   private static final String CSV_FILE_householdInc_2016 = "database/householdInc_2016.csv";
   private static final String CSV_FILE_highestSchoolYear_2016 = "database/highestSchoolYear_2016.csv";

   public static void main (String[] args) {

      // JDBC Database Object
      Connection connection = null;

      // Implementing the highestSchoolYear_2016.csv file
      // Original CSV file name: lga_highest_year_of_school_completed_by_indigenous_status_by_sex_2016

      String category_highestSchoolYear_2016[] = {
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

      String status_highestSchoolYear_2016[] = {
         "hhds_with_indig_persons",
         "other_hhds",
         "total_hhds"
      };

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
            int indexCategory = 0;

            // Save the lga_code as we need it for the foreign key
            String lgaCode = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_highestSchoolYear_2016.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into HouseholdStatistics VALUES ("
                              + lgaCode + ","
                              + "'" + status_highestSchoolYear_2016[indexStatus] + "',"
                              + "'" + category_highestSchoolYear_2016[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next status
               indexStatus++;
               if (indexStatus >= status_highestSchoolYear_2016.length) {
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


      // Implementing the householdInc2021.csv file
      // Original CSV file name: lga_total_household_income_weekly_by_indigenous_status_of_household_2021
      
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
         Scanner lineScanner = new Scanner(new File(CSV_FILE_householdInc_2016));

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
      
      // The following arrays define the order of columns in the INPUT CSV.
      // The order of each array MUST match the order of the CSV.
      // These are specific to the given file and should be changed for each file.
      // This is a *simple* way to help you get up and running quickly wihout being confusing
      String category_lga_indigenous_status_by_age_by_sex_census_2016[] = {
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
      String status_lga_indigenous_status_by_age_by_sex_census_2016[] = {
         "indig",
         "non_indig",
         "indig_ns"
      };
      String sex_lga_indigenous_status_by_age_by_sex_census_2016[] = {
         "f",
         "m"
      };

      // JDBC Database Object
      // Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         Scanner lineScanner = new Scanner(new File(CSV_FILE));

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
            String lgaName = rowScanner.next();

            // Go through the data for the row
            // If we run out of categories, stop for safety (so the code doesn't crash)
            while (rowScanner.hasNext() && indexCategory < category_lga_indigenous_status_by_age_by_sex_census_2016.length) {
               String count = rowScanner.next();

               // Prepare a new SQL Query & Set a timeout
               Statement statement = connection.createStatement();

               // Create Insert Statement
               String query = "INSERT into PopulationStatistics VALUES ("
                              + lgaCode + ","
                              + "'" + status_lga_indigenous_status_by_age_by_sex_census_2016[indexStatus] + "',"
                              + "'" + sex_lga_indigenous_status_by_age_by_sex_census_2016[indexSex] + "',"
                              + "'" + category_lga_indigenous_status_by_age_by_sex_census_2016[indexCategory] + "',"
                              + count + ")";

               // Execute the INSERT
               System.out.println("Executing: " + query);
               statement.execute(query);

               // Update indices - go to next sex
               indexSex++;
               if (indexSex >= sex_lga_indigenous_status_by_age_by_sex_census_2016.length) {
                  // Go to next status
                  indexSex = 0;
                  indexStatus++;

                  if (indexStatus >= status_lga_indigenous_status_by_age_by_sex_census_2016.length) {
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

      
   }
}
