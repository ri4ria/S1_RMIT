package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/ctg.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getLGAs() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code     = results.getInt("lga_code");
                int year     = results.getInt("lga_year");
                String name  = results.getString("lga_name");

                // Create a LGA Object
                LGA lga = new LGA(code, year, name);

                // Add the lga object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    // TODO: Add your required methods here

    public ArrayList<Persona> getPersonas(String inputName) {
        // Create the ArrayList of LGA objects to return
        ArrayList<Persona> requested_persona = new ArrayList<Persona>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Persona WHERE persona_name = '" + inputName + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name     = results.getString("persona_name");
                int age = results.getInt("age");
                String ethnicity = results.getString("ethnicity");
                String quote     = results.getString("quote");
                String image_file_path  = results.getString("image_file_path");

                // Create a LGA Object
                Persona persona = new Persona(name, age, ethnicity, quote, image_file_path);

                // Add the lga object to the array
                requested_persona.add(persona);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return requested_persona;
    }

    public ArrayList<PersonaAttribute> getPersonaAttribute(String inputName, String inputAttributeType) {
        // Create the ArrayList of LGA objects to return
        ArrayList<PersonaAttribute> requested_persona_attributes = new ArrayList<PersonaAttribute>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM PersonaAttribute WHERE persona_name = '" + inputName +"' AND attributeType = '" + inputAttributeType + "'";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name     = results.getString("persona_name");
                int id = results.getInt("id");
                String attributeType = results.getString("attributeType");
                String description     = results.getString("descrip");

                // Create a LGA Object
                PersonaAttribute personaAttribute = new PersonaAttribute(name, id, attributeType, description);

                // Add the lga object to the array
                requested_persona_attributes.add(personaAttribute);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return requested_persona_attributes;
    }    

     /**
     * Get all of the Outcomes in the database.
     * @return
     *    Returns an ArrayList of Outcome objects
     */
    public ArrayList<Outcome> getOutcomes() {
        // Create the ArrayList of LGA objects to return
        ArrayList<Outcome> outcomes = new ArrayList<Outcome>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Outcomes";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String OutcomeID     = results.getString("outcome_ID");
                String Title     = results.getString("title");
                String Descrip  = results.getString("descrip");

                // Create a Outcome Object
                Outcome target = new Outcome(OutcomeID, Title, Descrip);

                // Add the target object to the array
                outcomes.add(target);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return outcomes;
    }

    public ArrayList<Outcome> getSpecificOutcome(int outcomeID) {
        // Create the ArrayList of specific outcome to return
        ArrayList<Outcome> outcome = new ArrayList<Outcome>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM Outcomes WHERE outcome_ID = " + String.valueOf(outcomeID);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String OutcomeID     = results.getString("outcome_ID");
                String Title     = results.getString("title");
                String Descrip  = results.getString("descrip");

                // Create a LGA Object
                Outcome target = new Outcome(OutcomeID, Title, Descrip);

                // Add the lga object to the array
                outcome.add(target);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return outcome;
    }

    public ArrayList<String> getLGACodes () {
        ArrayList<String> lgaCodes = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * FROM LGAS";

            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String result = new String();

                result = String.valueOf(results.getInt("lga_code"));

                lgaCodes.add(result);
            }
            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return lgaCodes;
    }

    public ArrayList<String> getAgeGroups () {
        ArrayList<String> ageGroups = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT age FROM PopulationStatistics GROUP BY age";

            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String result = new String();

                result = results.getString("age");

                ageGroups.add(result);
            }
            statement.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return ageGroups;
    }

    /*

    public ArrayList<String> getST22PopulationResults(String locationType, String location, String valueType, String indigenousStatus, String sex, String age) {

        // locationType = LGA or state
        // location = LGA code or state name
        // valueType = raw or proportional
        // indigenousStatus = indig, non_indig, indig_ns
        // sex = f or m
        // age = _0_4, _10_14, _15_19, _20_24, _25_29, _30_34, _35_39, _40_44, _45_49, _50_54, _55_59, _5_9, _60_64, _65_yrs_ov

        // Creating ArrayList for results
        ArrayList<String> ST22PopulationResults = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * ";
            query += "FROM PS2016VS2021 ";
            query += "WHERE (`2016 LGA Code` = " + location + " OR `2021 LGA Code` = " + location + ") AND ";
            query += "(`2016 Indigenous Status` = '" + indigenousStatus + "' OR `2021 Indigenous Status` = '" + indigenousStatus + "') AND ";
            query += "(`2016 Sex` = '" + sex + "' OR `2021 Sex` = '"+ sex + "') AND ";
            query += "(`2016 Age` = '" + age + "' OR `2021 Age` = '" + age + "');";
            
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a result object
                String result = new String();

                result += location + " ";
                result += results.getString("2016 LGA Name") + " ";
                result += results.getString("2016 LGA State") + " ";
                result += results.getString("2016 LGA Type") + " ";
                result += results.getString("2021 LGA Name") + " ";
                result += results.getString("2021 LGA State") + " ";
                result += results.getString("2021 LGA Type") + " ";
                result += String.valueOf(results.getInt("2016 Result")) + " ";
                result += String.valueOf(results.getInt("2021 Result"));

                ST22PopulationResults.add(result);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the results
        return ST22PopulationResults;
    }

    */

    public PSST22Results getST22PopulationResults(String locationType, String location, String valueType, String indigenousStatus, String sex, String age) {

        // locationType = LGA or state
        // location = LGA code or state name
        // valueType = raw or proportional
        // indigenousStatus = indig, non_indig, indig_ns
        // sex = f or m
        // age = _0_4, _10_14, _15_19, _20_24, _25_29, _30_34, _35_39, _40_44, _45_49, _50_54, _55_59, _5_9, _60_64, _65_yrs_ov

        // Creating ArrayList for results
        // PSST22Results ST22Results = new PSST22Results();

        // Create PSST22Results object
        PSST22Results ST22PopulationResults = new PSST22Results();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT * ";
            query += "FROM PS2016VS2021 ";
            query += "WHERE (`2016 LGA Code` = " + location + " OR `2021 LGA Code` = " + location + ") AND ";
            query += "(`2016 Indigenous Status` = '" + indigenousStatus + "' OR `2021 Indigenous Status` = '" + indigenousStatus + "') AND ";
            query += "(`2016 Sex` = '" + sex + "' OR `2021 Sex` = '"+ sex + "') AND ";
            query += "(`2016 Age` = '" + age + "' OR `2021 Age` = '" + age + "');";
            
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {

                ST22PopulationResults.setLGACode(location);
                ST22PopulationResults.setLGAName2016(results.getString("2016 LGA Name"));
                ST22PopulationResults.setLGAState2016(results.getString("2016 LGA State"));
                ST22PopulationResults.setLGAType2016(results.getString("2016 LGA Type"));
                ST22PopulationResults.setLGAName2021(results.getString("2021 LGA Name"));
                ST22PopulationResults.setLGAState2021(results.getString("2021 LGA State"));
                ST22PopulationResults.setLGAType2021(results.getString("2021 LGA Type"));
                ST22PopulationResults.setResult2016(results.getInt("2016 Result"));
                ST22PopulationResults.setResult2021(results.getInt("2021 Result"));
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        return ST22PopulationResults;
    }

    //Generate a list of all the health conditions for the drop down
    public ArrayList<String> getHealthConditions() {
        ArrayList<String> healthCond = new ArrayList<String>();
    
        // Setup the variable for the JDBC connection
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
    
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
    
            // The Query
            String query = "SELECT DISTINCT condition FROM LTHCStatistics";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
    
            // Process all of the results
            while (results.next()) {
                healthCond.add(results.getString("condition"));
            }
    
            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    
        // Finally we return all of the movies
        return healthCond;
    }

    //Aim to show the raw values, proportional values and difference 
    //healthconditions 
    public ArrayList<String> getDataByHealthCondition(String selectedCondition) {
        ArrayList<String> healthCondData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT lth.LGA_CODE AS 'LGA', condition FROM LTHCStatistics AS lth WHERE lth.indigenous_status = 'indig' AND lth.condition = '" + selectedCondition + "'"; 
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("LGA")) + " ";
                result = result + results.getString("condition");

                healthCondData.add(result);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return healthCondData;
    }

     //Generate a list of all the health conditions for the drop down
     public ArrayList<String> getAge() {
        ArrayList<String> ageNum = new ArrayList<String>();
    
        // Setup the variable for the JDBC connection
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
    
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
    
            // The Query
            String query = "SELECT DISTINCT age FROM PopulationStatistics";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
    
            // Process all of the results
            while (results.next()) {
                ageNum.add(results.getString("age"));
            }
    
            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    
        // Finally we return all of the age brackets
        return ageNum;
    }

    //healthconditions 
    public ArrayList<String> getDataByAge(String selectedAge) {
        ArrayList<String> selectedAgeData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT lth.LGA_CODE AS 'LGA', age FROM PopulationStatistics AS lth WHERE lth.indigenous_status = 'indig' AND lth.age = '" + selectedAge + "'"; 
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("LGA")) + " ";
                result = result + results.getString("age");

                selectedAgeData.add(result);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return selectedAgeData;
    }


     //Generate a list for the drop down
     public ArrayList<String> getSchooling() {
        ArrayList<String> schoolingYears = new ArrayList<String>();
    
        // Setup the variable for the JDBC connection
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
    
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
    
            // The Query
            String query = "SELECT DISTINCT highest_school_year FROM EducationStatistics WHERE lga_year = '2021'";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
    
            // Process all of the results
            while (results.next()) {
                schoolingYears.add(results.getString("highest_school_year"));
            }
    
            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    
        // Finally we return a list for the dropdown field
        return schoolingYears;
    }

      //healthconditions 
      public ArrayList<String> getDataBySchool(String selectedSchool) {
        ArrayList<String> schoolData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = 
                "SELECT EducationStatistics.lga_code, SUM(EducationStatistics.Count) AS 'raw values'";
                query += "FROM EducationStatistics"; 
                query += "WHERE EducationStatistics.lga_year = '2021'AND EducationStatistics.indigenous_status = 'indig'";
                query += "AND EducationStatistics.highest_school_year = " + selectedSchool + "AND EducationStatistics.count > 0";
                query += "GROUP BY EducationStatistics.lga_code;";
                 
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("LGA")) + " ";
                result = result + results.getString("highest_school_year");

                schoolData.add(result);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return schoolData;
    }

     //Generate a list for the drop down
     public ArrayList<String> getHousehold() {
        ArrayList<String> incomeBracket = new ArrayList<String>();
    
        // Setup the variable for the JDBC connection
        Connection connection = null;
    
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
    
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
    
            // The Query
            String query = "SELECT DISTINCT income_bracket FROM HouseholdStatistics WHERE lga_year = '2021'";
            ;
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
    
            // Process all of the results
            while (results.next()) {
                incomeBracket.add(results.getString("income_bracket"));
            }
    
            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    
        // Finally we return a list for the dropdown field
        return incomeBracket;
    }

     //healthconditions 
     public ArrayList<String> getDataByHouse(String selectedIncome) {
        ArrayList<String> incomeData = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                SELECT EducationStatistics.lga_code, SUM(EducationStatistics.Count) AS 'raw values'
                FROM EducationStatistics 
                WHERE EducationStatistics.lga_year = '2021' 
                AND EducationStatistics.indigenous_status = 'indig'
                AND EducationStatistics.highest_school_year = 'did_not_go_to_school'
                AND EducationStatistics.count > 0
                GROUP BY EducationStatistics.lga_code;
                    """; 
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("LGA")) + " ";
                result = result + results.getString("income_bracket");

                incomeData.add(result);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return incomeData;
    }

} // Keep as last bracket
