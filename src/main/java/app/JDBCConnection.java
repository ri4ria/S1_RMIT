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
     * 
     * @return
     *         Returns an ArrayList of LGA objects
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
                int code = results.getInt("lga_code");
                int year = results.getInt("lga_year");
                String name = results.getString("lga_name");

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
                String name = results.getString("persona_name");
                int age = results.getInt("age");
                String ethnicity = results.getString("ethnicity");
                String quote = results.getString("quote");
                String image_file_path = results.getString("image_file_path");

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
            String query = "SELECT * FROM PersonaAttribute WHERE persona_name = '" + inputName
                    + "' AND attributeType = '" + inputAttributeType + "'";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String name = results.getString("persona_name");
                int id = results.getInt("id");
                String attributeType = results.getString("attributeType");
                String description = results.getString("descrip");

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
     * 
     * @return
     *         Returns an ArrayList of Outcome objects
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
                String OutcomeID = results.getString("outcome_ID");
                String Title = results.getString("title");
                String Descrip = results.getString("descrip");

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
                String OutcomeID = results.getString("outcome_ID");
                String Title = results.getString("title");
                String Descrip = results.getString("descrip");

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

    public ArrayList<String> getLGACodes() {
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

    public ArrayList<String> getTotalPopulation(int year) {
        ArrayList<String> totalPopulation = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT SUM(count) FROM PopulationStatistics WHERE lga_year = " + year + ";";

            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                int result;

                result = results.getInt("SUM(count)");

                totalPopulation.add(String.valueOf(result));
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

        return totalPopulation;
    }

    public ArrayList<String> getAgeGroups() {
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

    public ArrayList<String> getHighSchoolYears() {
        ArrayList<String> years = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT(highest_school_year) FROM EducationStatistics;";

            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String result = new String();

                result = results.getString("highest_school_year");

                years.add(result);
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
        return years;
    }

    public ArrayList<String> getIncomeBrackets() {
        ArrayList<String> incomeBrackets = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT(income_bracket) FROM HouseholdStatistics;";

            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String result = new String();

                result = results.getString("income_bracket");

                incomeBrackets.add(result);
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
        return incomeBrackets;
    }

    public ST22Results getST22PopulationResults(String locationType, String location, String valueType,
            String indigenousStatus, String sex, String age) {

        // locationType = LGA or state
        // location = LGA code or state name
        // valueType = raw or proportional
        // indigenousStatus = indig, non_indig, indig_ns
        // sex = f or m
        // age = _0_4, _10_14, _15_19, _20_24, _25_29, _30_34, _35_39, _40_44, _45_49,
        // _50_54, _55_59, _5_9, _60_64, _65_yrs_ov

        // Create PSST22Results object
        ST22Results ST22PopulationResults = new ST22Results();

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
            query += "(`2016 Indigenous Status` = '" + indigenousStatus + "' OR `2021 Indigenous Status` = '"
                    + indigenousStatus + "') AND ";
            query += "(`2016 Sex` = '" + sex + "' OR `2021 Sex` = '" + sex + "') AND ";
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

    public ST22Results getST22EducationResults(String locationType, String location, String valueType,
            String indigenousStatus, String sex, String highestSchoolYear) {

        // locationType = LGA or state
        // location = LGA code or state name
        // valueType = raw or proportional
        // indigenousStatus = indig, non_indig, indig_ns
        // sex = f or m
        // highestSchoolYear = did_not_go_to_school, y8_below, y9_equivalent,
        // y10_equivalent, y11_equivalent, y12_equivalent

        // Create PSST22Results object
        ST22Results ST22EducationResults = new ST22Results();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        ST22EducationResults.setLGACode(location);

        if (valueType == "raw") {

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                String query = "SELECT * ";
                query += "FROM ES2016VS2021 ";
                query += "WHERE (`2016 LGA Code` = " + location + " OR `2021 LGA Code` = " + location + ") AND ";
                query += "(`2016 Indigenous Status` = '" + indigenousStatus + "' OR `2021 Indigenous Status` = '"
                        + indigenousStatus + "') AND ";
                query += "(`2016 Sex` = '" + sex + "' OR `2021 Sex` = '" + sex + "') AND ";
                query += "(`2016 Highest School Year Completed` = '" + highestSchoolYear
                        + "' OR `2021 Highest School Year Completed` = '" + highestSchoolYear + "');";

                System.out.println(query);

                // Get Result
                ResultSet results = statement.executeQuery(query);

                // Process all of the results
                while (results.next()) {

                    ST22EducationResults.setLGAName2016(results.getString("2016 LGA Name"));
                    ST22EducationResults.setLGAState2016(results.getString("2016 LGA State"));
                    ST22EducationResults.setLGAType2016(results.getString("2016 LGA Type"));
                    ST22EducationResults.setLGAName2021(results.getString("2021 LGA Name"));
                    ST22EducationResults.setLGAState2021(results.getString("2021 LGA State"));
                    ST22EducationResults.setLGAType2021(results.getString("2021 LGA Type"));
                    ST22EducationResults.setResult2016(results.getInt("2016 Result"));
                    ST22EducationResults.setResult2021(results.getInt("2021 Result"));
                    ST22EducationResults.setRank2016(results.getInt("rank2016"));
                    ST22EducationResults.setRank2021(results.getInt("rank2021"));
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
        } else if (valueType == "proportional") {
            int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) ";
                    query1 += "FROM ES" + String.valueOf(years[y]) + " ";
                    query1 += "state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "';";

                    String query2 = "SELECT * ";
                    query2 += "FROM ES" + String.valueOf(years[y]) + " ";
                    query2 += "state = '" + location + "' AND ";
                    query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                    query2 += "sex = '" + sex + "' AND ";
                    query2 += "highest_school_year = '" + highestSchoolYear + "';";

                    System.out.println(query1);
                    System.out.println(query2);

                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);
                    ResultSet results2 = statement.executeQuery(query2);

                    // Process all of the results
                    while (results2.next()) {

                        // Create connection to JDBC class
                        JDBCConnection jdbc = new JDBCConnection();

                        ArrayList<String> states = jdbc.getStates();
                        int[] statesResults = new int[states.size()];
                        int[] statesDenominator = new int[states.size()];
                        float[] statesProportion = new float[states.size()];

                        int denominator = results1.getInt("SUM(count)");
                        int numerator = results2.getInt("count");
                        float proportion = numerator / denominator;

                        statesProportion[0] = proportion;

                        for (int i = 1; i < states.size(); i++) {

                            if (states.get(i) != location) {

                                String query3 = "SELECT SUM(count) FROM ES" + String.valueOf(years[y]) + "state = '"
                                        + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                        + "' AND sex = '" + sex + "';";

                                ResultSet results3 = statement.executeQuery(query3);

                                while (results3.next()) {
                                    statesDenominator[i] = results3.getInt("SUM(count)");
                                }

                                String query4 = "SELECT * FROM ES" + String.valueOf(years[y]) + "state = '"
                                        + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                        + "' AND sex = '" + sex + "' AND highest_school_year = '" + highestSchoolYear
                                        + "';";

                                ResultSet results4 = statement.executeQuery(query4);

                                while (results4.next()) {
                                    statesResults[i] = results4.getInt("count");
                                }

                                statesProportion[i] = statesResults[i] / statesDenominator[i];
                            }
                        }

                        for (int i = 0; i < states.size(); i++) {
                            for (int j = i + 1; j < states.size(); j++) {
                                float temp = 0;
                                if (statesProportion[i] > statesProportion[j]) {
                                    temp = statesProportion[i];
                                    statesProportion[i] = statesProportion[j];
                                    statesProportion[j] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < states.size(); i++) {
                                if (statesProportion[i] == proportion) {
                                    ST22EducationResults.setRank2016(i);
                                }
                            }

                            ST22EducationResults.setLGAName2016(results2.getString("lga_name"));
                            ST22EducationResults.setLGAState2016(results2.getString("state"));
                            ST22EducationResults.setLGAType2016(results2.getString("lga_type"));
                            ST22EducationResults.setResult2016(proportion);
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < states.size(); i++) {
                                if (statesProportion[i] == proportion) {
                                    ST22EducationResults.setRank2021(i);
                                }
                            }

                            ST22EducationResults.setLGAName2021(results2.getString("lga_name"));
                            ST22EducationResults.setLGAState2021(results2.getString("state"));
                            ST22EducationResults.setLGAType2021(results2.getString("lga_type"));
                            ST22EducationResults.setResult2016(proportion);
                        }

                    }

                    // Close the statement because we are done with it
                    statement.close();
                }
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
        }

        return ST22EducationResults;
    }

    public ArrayList<String> getStates() {
        ArrayList<String> states = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT (state) FROM LGA_State;";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                states.add(results.getString("state"));
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

        return states;
    }

    // Generate a list of all the health conditions for the drop down
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
                    // "SELECT DISTINCT upper(substr(condition, 1, 1)) || substr(condition, 2) as
                    // condition "; //needs space and needs an alias!!
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

    // Aim to show the raw values, proportional values and difference
    // healthconditions
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
            String query = "SELECT health.LGA_CODE AS code, LGAnum.LGA_name AS name, SUM(health.count) AS RAW ";
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

    // Generate a list of all the health conditions for the drop down
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
                    // trim the addtional underscore and rearrange the order
                    // "SELECT DISTINCT trim(age,'_') AS age ";
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

    // healthconditions
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
            String query = "SELECT lth.LGA_CODE AS 'LGA', age FROM PopulationStatistics AS lth WHERE lth.indigenous_status = 'indig' AND lth.age = '"
                    + selectedAge + "'";
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

    // Generate a list for the drop down
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
            String query = "SELECT DISTINCT highest_school_year ";
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

    // healthconditions
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
            String query = "SELECT EducationStatistics.lga_code, SUM(EducationStatistics.Count) AS 'raw values'";
            query += "FROM EducationStatistics";
            query += "WHERE EducationStatistics.lga_year = '2021'AND EducationStatistics.indigenous_status = 'indig'";
            query += "AND EducationStatistics.highest_school_year = " + selectedSchool
                    + "AND EducationStatistics.count > 0";
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

    // Generate a list for the drop down
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
            String query = "SELECT DISTINCT income_bracket ";
            query += "FROM HouseholdStatistics; ";
            // query += "ORDER BY SUBSTRING(income_bracket,-1) AND
            // SUBSTRING(income_bracket,-2) AND SUBSTRING(income_bracket,-4);";
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

    // healthconditions
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
            String query = "SELECT incomedata.code AS 'cod', LGA.lga_name AS 'nam', incomedata.indig AS 'indi', incomedata.nonindig AS 'nonindi', incomedata.gap AS 'gap', incomedata.total AS 'total', incomedata.proportional AS 'prop' ";
            query += "FROM LGA ";
            query += "JOIN (SELECT H1.lga_code AS code, H1.count AS 'indig', HIgap.nonindig AS 'nonindig', H2.count AS 'total', HIgap.gap AS 'gap', printf('%d%%', H1.count*100/H2.count) AS 'proportional' ";
            query += "FROM HouseholdStatistics H1 ";
            query += "OUTER LEFT JOIN HouseholdStatistics H2 ";
            query += "JOIN (SELECT *, SUM(H1.count), SUM(H2.count) AS 'nonindig', (H1.count - H2.count) AS gap ";
            query += "FROM HouseholdStatistics H1 ";
            query += "OUTER LEFT JOIN HouseholdStatistics H2 ";
            query += "WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' ";
            query += "AND H1.income_bracket = '" + selectedIncome + "' AND H2.income_bracket ='" + selectedIncome
                    + "' ";
            query += "AND H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%other%' ";
            query += "AND H1.lga_code = H2.lga_code ";
            query += "GROUP BY H1.lga_Code) AS HIgap ";
            query += "ON HIgap.lga_code = H2.lga_code ";
            query += "WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' ";
            query += "AND H1.income_bracket = '" + selectedIncome + "' AND H2.income_bracket ='" + selectedIncome
                    + "' ";
            query += "AND H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%total%' ";
            query += "AND H1.lga_code = H2.lga_code ";
            query += "GROUP BY H1.lga_Code) AS incomedata ";
            query += "WHERE incomedata.code = LGA.lga_code ";
            query += "AND LGA.lga_year = '2021' ";
            query += "AND incomedata.indig > 0 ";
            query += "AND incomedata.indig <= incomedata.total;";
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
                result = result + results.getString("gap") + " ";
                result = result + results.getString("total") + " ";
                result = result + results.getString("prop") + " ";

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
