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
            /* "SELECT health.LGA_CODE AS code, LGAnum.LGA_name AS name, SUM(health.count) AS RAW ";
            query += "FROM LTHCStatistics AS health ";
            query += "JOIN LGA AS LGAnum ";
            query += "ON health.LGA_CODE = LGAnum.LGA_CODE ";
            query += "WHERE health.indigenous_status = 'indig' ";
            query += "AND health.condition = '" + selectedCondition + "' ";
            query += "AND LGAnum.lga_year = '2021' "; 
            query += "GROUP BY health.LGA_CODE;"; */
            "SELECT hc.lga_code AS 'code', lga.lga_name AS 'name', printf('%,d', hc.indig) AS 'indig', printf('%,d', hc.nonindig) AS 'nonindig', "; 
            query += "printf('%,d', hc.nonstated) AS 'nonstated', printf('%,d', hc.total) AS 'total', printf('%,d', hc.gap) as 'gap', printf('%d%%', hc.pro) AS 'pro', printf('%d%%', hc.proInd) AS 'proInd' ";
            query += "FROM LGA JOIN (SELECT health.lga_code, cond.indig AS indig, cond.nonindig AS nonindig, cond.nonstated, cond.total, cond.gap AS gap, cond.proportional AS pro, (cond.indig * 100 /SUM(health.count)) AS proInd ";
            query += "FROM LTHCStatistics AS health JOIN (SELECT heal.code, heal.indig, heal.nonindig, heal.nonstated, heal.total, heal.gap, heal.proportional ";
            query += "FROM (SELECT C1.lga_code AS code, hcon.indig AS indig, hcon.nonindig AS nonindig, (C1.count + C2.count) AS nonstated, "; 
            query += "(hcon.indig + hcon.nonindig + C1.count + C2.count) AS total, (hcon.indig - hcon.nonindig) AS 'gap', (hcon.indig * 100/ (hcon.indig + hcon.nonindig + C1.count + C2.count)) AS proportional ";
            query += "FROM LTHCStatistics AS C1 OUTER LEFT JOIN LTHCStatistics AS C2 JOIN (SELECT Con1.lga_code, ConIndig.indig, (Con1.count + Con2.count) AS 'nonindig' ";
            query += "FROM LTHCStatistics AS Con1 OUTER LEFT JOIN LTHCStatistics AS Con2 JOIN (SELECT *, C1.lga_code, (C1.count + C2.count) AS indig ";
            query += "FROM LTHCStatistics AS C1 OUTER LEFT JOIN LTHCStatistics AS C2 WHERE C1.condition = '" + selectedCondition + "' AND C2.condition = '" + selectedCondition + "' AND C1.indigenous_status = 'indig' AND C2.indigenous_status = 'indig' ";
            query += "AND C1.sex = 'f' AND C2.sex = 'm' AND C1.lga_code = C2.lga_code) AS ConIndig ON ConIndig.lga_code = Con1.lga_code WHERE Con1.condition = '" + selectedCondition + "' AND Con2.condition = '" + selectedCondition + "' ";
            query += "AND Con1.indigenous_status = 'non_indig' AND Con2.indigenous_status = 'non_indig' AND Con1.sex = 'f' AND Con2.sex = 'm' ";
            query += "AND Con1.lga_code = Con2.lga_code) AS hcon ON hcon.lga_code = C1.lga_code ";
            query += "WHERE C1.condition = '" + selectedCondition + "' AND C2.condition = '" + selectedCondition + "' AND C1.indigenous_status = 'indig_stat_notstated' AND C2.indigenous_status = 'indig_stat_notstated' ";
            query += "AND C1.sex = 'f' AND C2.sex = 'm' AND C1.lga_code = C2.lga_code) AS heal) AS cond ";
            query += "ON cond.code = health.lga_code WHERE health.indigenous_status = 'indig' GROUP BY health.lGA_Code) AS hc ON hc.lga_code = lga.lga_code GROUP BY hc.lga_code;";

            //System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("code")) + " ";
                result += results.getString("name") + " ";
                result = result + results.getString("indig") + " ";
                result += results.getString("nonindig") + " ";
                result += results.getString("nonstated") + " ";
                result += results.getString("total") + " ";
                result += results.getString("gap") + " ";
                result += results.getString("pro") + " ";
                result += results.getString("proInd") + " ";


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
            String query = 
            //"SELECT lth.LGA_CODE AS 'LGA', age FROM PopulationStatistics AS lth WHERE lth.indigenous_status = 'indig' AND lth.age = '" + selectedAge + "'"; 
            "SELECT pop.code AS 'code', lga_name AS 'name', printf('%,d', pop.indig) AS 'indig', printf('%,d', pop.nonindig) AS 'nonindig', printf('%,d', pop.nonstated) AS 'nonstated', ";
            query += "printf('%,d', pop.total) AS total, printf('%,d', pop.gap) AS gap, printf('%d%%', pop.proportional) AS proportional ";
            query += "FROM LGA JOIN (SELECT P1.lga_code AS code,  popINNON.indig AS indig, popINNON.nonindig AS nonindig, (P1.count + P2.count) AS nonstated, ";
            query += "(popINNON.indig + popINNON.nonindig + P1.count + P2.count) AS total, ";
            query += "(popINNON.indig - popINNON.nonindig) AS gap, (popINNON.indig * 100 /(popINNON.indig + popINNON.nonindig + P1.count + P2.count)) AS proportional ";
            query += "FROM PopulationStatistics as P1 JOIN PopulationStatistics as P2 JOIN (SELECT P1.lga_code AS code, popIn.indig AS indig, (P1.count + P2.count) AS nonindig ";
            query += "FROM PopulationStatistics as P1 JOIN PopulationStatistics as P2 JOIN (SELECT P1.lga_code, (P1.count + P2.count) AS indig ";
            query += "FROM PopulationStatistics as P1 JOIN PopulationStatistics as P2 WHERE P1.lga_year = '2021' AND P2.lga_year = '2021' ";
            query += "AND P1.age = '" + selectedAge + "' AND P2.age = '" + selectedAge + "' AND P1.sex = 'f' AND P2.sex = 'm' AND P1.indigenous_status = 'indig' AND P2.indigenous_status = 'indig' ";
            query += "AND P1.lga_code = P2.lga_code) AS popIn ON popIn.lga_code = P1.lga_code ";
            query += "WHERE P1.lga_year = '2021' AND P2.lga_year = '2021' AND P1.age = '" + selectedAge + "' AND P2.age = '" + selectedAge + "' ";
            query += "AND P1.sex = 'f' AND P2.sex = 'm' AND P1.indigenous_status = 'non_indig' AND P2.indigenous_status = 'non_indig' ";
            query += "AND P1.lga_code = P2.lga_code) AS popINNON ON popINNON.code = P1.lga_code WHERE P1.lga_year = '2021' AND P2.lga_year = '2021' ";
            query += "AND P1.age = '" + selectedAge + "' AND P2.age = '" + selectedAge + "' AND P1.sex = 'f' AND P2.sex = 'm' ";
            query += "AND P1.indigenous_status = 'indig_ns' AND P2.indigenous_status = 'indig_ns' AND P1.lga_code = P2.lga_code) AS pop ";
            query += "ON pop.code = LGA.lga_code GROUP BY pop.code;";

            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("code")) + " ";
                result = result + results.getString("name") + " ";
                result = result + results.getString("indig") + " ";
                result = result + results.getString("nonindig") + " ";
                result = result + results.getString("nonstated") + " ";
                result = result + results.getString("total") + " ";
                result = result + results.getString("gap") + " ";
                result = result + results.getString("proportional");

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
      public ArrayList<Table> getDataBySchool(String selectedSchool, String sort) {
        ArrayList<Table> schoolData = new ArrayList<Table>();

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

            /* 
                "SELECT EducationStatistics.lga_code AS 'LGA', SUM(EducationStatistics.Count) AS 'raw values' ";
                query += "FROM EducationStatistics "; 
                query += "WHERE EducationStatistics.lga_year = '2021'AND EducationStatistics.indigenous_status = 'indig' ";
                query += "AND EducationStatistics.highest_school_year = '" + selectedSchool + "' AND EducationStatistics.count > 0 ";
                query += "GROUP BY EducationStatistics.lga_code;";
            */

                "SELECT sort.code AS 'code', sort.name AS 'name', sort.indig AS 'indig', sort.nonindig AS 'nonindig', sort.nonstated AS 'nonstated', sort.total AS 'total', sort.gap AS 'gap', sort.proportional AS 'proportional' ";
                query += "FROM (SELECT Ef1.lga_code AS 'code', L.name AS 'name',  (Ef1.count + Ef2.count) AS 'indig', sc.nonindig AS 'nonindig', sc1.nonstated AS 'nonstated', ";
                query += "(Ef1.count + Ef2.count + sc.nonindig + sc1.nonstated) AS 'total', ";
                query += "(Ef1.count + Ef2.count - sc.nonindig) AS 'gap', ";
                query += "((Ef1.count + Ef2.count) *100/(Ef1.count + Ef2.count + sc.nonindig + sc1.nonstated)) AS 'proportional' ";
                query += "FROM EducationStatistics AS Ef1 ";
                query += "OUTER LEFT JOIN EducationStatistics AS Ef2 ";
                query += "JOIN (SELECT lga.lga_code AS code, lga.lga_name AS name ";
                query += "FROM Lga WHERE LGA.lga_year = '2021') AS L ";
                query += "ON L.code = Ef1.lga_code ";
                query += "JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonstated ";
                query += "FROM EducationStatistics AS Ef1 ";
                query += "OUTER LEFT JOIN EducationStatistics AS Ef2 WHERE ";
                query += "Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' ";
                query += "AND Ef1.highest_school_year = '" + selectedSchool + "' AND Ef2.highest_school_year = '" + selectedSchool + "' ";
                query += "AND Ef1.indigenous_status = 'indig_stat_notstated' AND Ef2.indigenous_status = 'indig_stat_notstated' ";
                query += "AND Ef1.sex = 'f' AND Ef2.sex = 'm' ";
                query += "AND Ef1.lga_code = Ef2.lga_code ";
                query += "GROUP BY Ef1.lga_code) AS sc1 ON sc1.code = Ef1.lga_code ";
                query += "JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig ";
                query += "FROM EducationStatistics AS Ef1 OUTER LEFT JOIN EducationStatistics AS Ef2 WHERE ";
                query += "Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' ";
                query += "AND Ef1.highest_school_year = '" + selectedSchool + "' AND Ef2.highest_school_year = '" + selectedSchool + "' ";
                query += "AND Ef1.indigenous_status = 'non_indig' AND Ef2.indigenous_status = 'non_indig' ";
                query += "AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code ";
                query += "GROUP BY Ef1.lga_code) as sc on sc.code = Ef1.lga_code ";
                query += "WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' ";
                query += "AND Ef1.highest_school_year = '" + selectedSchool + "' AND Ef2.highest_school_year = '" + selectedSchool + "' ";
                query += "AND Ef1.indigenous_status = 'indig' AND Ef2.indigenous_status = 'indig' ";
                query += "AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort ";
                query += "ORDER BY " + sort + ";";

                 
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                /* 
                // this works
                String result = new String();

                result = String.valueOf(results.getString("code")) + " ";
                result = result + results.getString("name") + " ";
                result = result + results.getString("indig") + " ";
                result = result + results.getString("nonindig") + " ";
                result = result + results.getString("nonstated") + " ";
                result = result + results.getString("total") + " ";
                result = result + results.getString("gap") + " ";
                result = result + results.getString("proportional");
                schoolData.add(result);
                */

                  //QUERY FOR TABLE - look up the columns we need 
                  String code =  String.valueOf(results.getString("code"));
                  String name =  results.getString("name");
                  String indig =  results.getString("indig");
                  String nonindig =  results.getString("nonindig");
                  String nonstated =  results.getString("nonstated");
                  String total =  results.getString("total");
                  String gap =  results.getString("gap");
                  String proportional =  String.valueOf(results.getString("proportional"));

                  //create object for table class
                  Table tableSchool = new Table(code, name, indig, nonindig, nonstated, total, gap, proportional);
                  
                  //add object to the array 
                  schoolData.add(tableSchool);

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
            query += "FROM HouseholdStatistics; ";
            //query += "ORDER BY SUBSTRING(income_bracket,-1) AND SUBSTRING(income_bracket,-2) AND SUBSTRING(income_bracket,-4);";
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
                "SELECT incomedata.code AS 'cod', LGA.lga_name AS 'nam', printf('%,d', incomedata.indig) AS 'indi', printf('%,d', incomedata.nonindig) AS 'nonindi', printf('%,d', incomedata.gap) AS 'gap', printf('%,d', incomedata.total) AS 'total', printf('%d%%', incomedata.proportional) AS 'prop' ";
                query +="FROM LGA ";
                query +="JOIN (SELECT H1.lga_code AS code, H1.count AS 'indig', HIgap.nonindig AS 'nonindig', H2.count AS 'total', HIgap.gap AS 'gap', (H1.count*100/H2.count) AS 'proportional' "; 
                query +="FROM HouseholdStatistics H1 ";
                query +="OUTER LEFT JOIN HouseholdStatistics H2 ";
                query +="JOIN (SELECT *, SUM(H1.count), SUM(H2.count) AS 'nonindig', (H1.count - H2.count) AS gap ";
                query +="FROM HouseholdStatistics H1 ";
                query +="OUTER LEFT JOIN HouseholdStatistics H2 ";
                query +="WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' ";
                query +="AND H1.income_bracket = '" + selectedIncome + "' AND H2.income_bracket ='" + selectedIncome + "' ";
                query +="AND H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%other%' ";
                query +="AND H1.lga_code = H2.lga_code ";
                query +="GROUP BY H1.lga_Code) AS HIgap ";
                query +="ON HIgap.lga_code = H2.lga_code ";
                query +="WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' ";
                query +="AND H1.income_bracket = '" + selectedIncome + "' AND H2.income_bracket ='" + selectedIncome + "' ";
                query +="AND H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%total%' ";
                query +="AND H1.lga_code = H2.lga_code ";
                query +="GROUP BY H1.lga_Code) AS incomedata ";
                query +="WHERE incomedata.code = LGA.lga_code ";
                query +="AND LGA.lga_year = '2021' ";
                query +="AND incomedata.indig > 0 ";
                query +="AND incomedata.indig <= incomedata.total;";
            System.out.println(query);
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                String result = new String();

                result = String.valueOf(results.getString("cod")) + " ";
                result = result + results.getString("nam") + " ";
                result = result + results.getString("indi") + " ";
                result = result + results.getString("nonindi") + " ";
                result = result + results.getString("total") + " ";
                result = result + results.getString("gap") + " ";
                //result = result + results.getString("total") + " ";
                result = result + results.getString("prop")+ " ";

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
