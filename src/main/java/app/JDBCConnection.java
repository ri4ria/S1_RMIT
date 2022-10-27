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

    public ArrayList<String> getST22EducationResults(String dataset, String locationType, String location, String valueType) {
        // Creating ArrayList for results
        ArrayList<String> ST22Results = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = """
                SELECT data2016.lga_code AS '2016 LGA Code', 
                       data2021.lga_code AS '2021 LGA Code', 
                       data2016.indigenous_status AS 'Indigenous Status', 
                       data2016.sex AS 'Gender', 
                       data2016.highest_school_year AS 'Highest School Year Completed', 
                       data2016.count AS '2016 Result', 
                       data2021.count AS '2021 Result',
                       data2021.count - data2016.count AS 'Difference'
                    FROM EducationStatistics AS data2016 LEFT OUTER JOIN EducationStatistics AS data2021 ON data2016.lga_code = data2021.lga_code AND 
                                                                                                data2016.indigenous_status = data2021.indigenous_status AND
                                                                                                data2016.sex = data2021.sex AND
                                                                                                data2016.highest_school_year = data2021.highest_school_year
                    WHERE data2016.lga_year = 2016 AND data2021.lga_year = 2021
                    """;

            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a Movie Object
                String result = new String();

                result = String.valueOf(results.getInt("2016 LGA Code")) + " ";
                result = result + String.valueOf(results.getInt("2021 LGA Code")) + " ";
                result = result + results.getString("Indigenous Status") + " ";
                result = result + results.getString("Gender") + " ";
                result = result + results.getString("Highest School Year Completed") + " ";
                result = result + String.valueOf(results.getInt("2016 Result")) + " ";
                result = result + String.valueOf(results.getInt("2021 Result")) + " ";
                result = result + String.valueOf(results.getInt("Difference"));

                ST22Results.add(result);
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
        return ST22Results;
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
            String query = 
            //"SELECT DISTINCT upper(substr(condition, 1, 1)) || substr(condition, 2) as condition "; //needs space and needs an alias!! 
            "SELECT DISTINCT condition ";
            query += "FROM LTHCStatistics;";
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
    
        // Finally we return all of the conditions
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
            String query = 
            "SELECT health.LGA_CODE AS code, LGAnum.LGA_name AS name, SUM(health.count) AS RAW ";
            query += "FROM LTHCStatistics AS health ";
            query += "JOIN LGA AS LGAnum ";
            query += "ON health.LGA_CODE = LGAnum.LGA_CODE ";
            query += "WHERE health.indigenous_status = 'indig' ";
            query += "AND health.condition = '" + selectedCondition + "' ";
            query += "AND LGAnum.lga_year = '2021' "; 
            query += "GROUP BY health.LGA_CODE;";  

            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("code")) + " ";
                result += results.getString("name") + " ";
                result = result + results.getString("RAW");

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
            String query = 
            //trim the addtional underscore and rearrange the order
            //"SELECT DISTINCT trim(age,'_') AS age ";
            "SELECT DISTINCT age ";
            query += "FROM PopulationStatistics ";
            query += "ORDER BY SUBSTRING(age, 4);";
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
            String query = 
            "SELECT DISTINCT highest_school_year ";
            query += "FROM EducationStatistics ";
            query += " ORDER BY SUBSTRING(highest_school_year, -1) || SUBSTRING(highest_school_year, 3) DESC;";
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
            String query = 
            "SELECT DISTINCT income_bracket ";
            query += "FROM HouseholdStatistics ";
            query += "ORDER BY SUBSTRING(income_bracket,-1) AND SUBSTRING(income_bracket,-2) AND SUBSTRING(income_bracket,-4);";
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
            String query = 
                " "; 
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
