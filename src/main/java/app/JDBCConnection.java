package app;

import java.util.ArrayList;
import java.text.DecimalFormat;

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

    // Creates a JDBC Object so we can keep talking to the database
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    // Gets all LGAs from database
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

            String query = "SELECT DISTINCT (income_bracket) FROM IncomeBracket ORDER BY min_income;";

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

    public ArrayList<String> getHouseholdIndigenousStatuses() {
        ArrayList<String> incomeBrackets = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String query = "SELECT DISTINCT(indigenous_status) FROM HouseholdStatistics;";

            System.out.println(query);

            ResultSet results = statement.executeQuery(query);

            while (results.next()) {
                String result = new String();

                result = results.getString("indigenous_status");

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

        if (valueType.equalsIgnoreCase("raw")) {
            if (locationType.equalsIgnoreCase("LGA")) {
                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                        // Query to find selected LGA results
                        String query1 = "SELECT * ";
                        query1 += "FROM ESST22Comparison " ;
                        query1 += "WHERE (code_2016 = " + location + " OR code_2021 = " + location + ") AND ";
                        query1 += "(status_2016 = '" + indigenousStatus + "' OR status_2021 = '" + indigenousStatus + "') AND ";
                        query1 += "(sex_2016 = '" + sex + "' OR sex_2021 = '" + sex + "') AND ";
                        query1 += "(hs_year_completed_2016 = '" + highestSchoolYear + "' OR hs_year_completed_2021 = '" + highestSchoolYear + "');";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            
                            ST22EducationResults.setLGAName2016(results.getString("name_2016"));
                            ST22EducationResults.setLGAState2016(results.getString("state_2016"));
                            ST22EducationResults.setLGAType2016(results.getString("type_2016"));
                            ST22EducationResults.setResult2016(results.getInt("result_2016"));
                            ST22EducationResults.setRank2016(results.getInt("rank_2016"));
                           
                            ST22EducationResults.setLGAName2021(results.getString("name_2021"));
                            ST22EducationResults.setLGAState2021(results.getString("state_2021"));
                            ST22EducationResults.setLGAType2021(results.getString("type_2021"));
                            ST22EducationResults.setResult2021(results.getInt("result_2021"));
                            ST22EducationResults.setRank2021(results.getInt("rank_2021"));
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
            } else if (locationType.equalsIgnoreCase("state")) {
                int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // Create connection to JDBC class
                JDBCConnection jdbc = new JDBCConnection();

                ArrayList<String> states = jdbc.getStates();
                float[] statesResults = new float[states.size()];
                float[] statesDenominator = new float[states.size()];
                float[] statesProportion = new float[states.size()];

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) AS 'count', ";
                    query1 += "SUM(1.0 * count / (SELECT SUM(count) FROM ES" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "')) AS 'proportion' ";
                    query1 += "FROM ES" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "' AND highest_school_year = '" + highestSchoolYear + "';";

                    System.out.println(query1);


                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);

                    // Process all of the results -- this is correct
                    while (results1.next()) {
                        if (years[y] == 2016) {
                            ST22EducationResults.setResult2016(results1.getFloat("count"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                        else if (years[y] == 2021) {
                            ST22EducationResults.setResult2021(results1.getFloat("count"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                    }

                    float proportionTracker = statesProportion[0];

                    for (int i = 1; i < states.size(); i++) {

                        if (!states.get(i).equalsIgnoreCase(location)) {

                            String query3 = "SELECT SUM(count) FROM ES" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "';";

                            System.out.println(query3);

                            ResultSet results3 = statement.executeQuery(query3);

                            while (results3.next()) {
                                statesDenominator[i] = results3.getInt("SUM(count)");
                                System.out.println(statesDenominator[i] + " denominator for " + states.get(i));
                            }

                            String query4 = "SELECT SUM(count) FROM ES" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "' AND highest_school_year = '" + highestSchoolYear
                                    + "';";

                            System.out.println(query4);
                            
                            ResultSet results4 = statement.executeQuery(query4);

                            while (results4.next()) {
                                statesResults[i] = results4.getInt("SUM(count)");
                                System.out.println(statesResults[i] + " result for " + states.get(i));
                            }

                            if (statesDenominator[i] != 0) {
                            statesProportion[i] = statesResults[i] / statesDenominator[i];
                                System.out.println(statesProportion[i] + " proportion for " + states.get(i));
                            }
                            else if (statesDenominator[i] == 0) {
                                statesProportion[i] = 0;
                            }
                        }
                    }

                    for (int i = 0; i < states.size(); i++) {
                        for (int j = i + 1; j < states.size(); j++) {
                            float temp = 0;
                            if (statesProportion[j] > statesProportion[i]) {
                                temp = statesProportion[j];
                                statesProportion[j] = statesProportion[i];
                                statesProportion[i] = temp;
                            }
                        }
                    }

                    if (years[y] == 2016) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22EducationResults.setRank2016(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22EducationResults.setLGAName2016("N/A");
                        ST22EducationResults.setLGAState2016(location);
                        ST22EducationResults.setLGAType2016("N/A");

                    } else if (years[y] == 2021) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22EducationResults.setRank2021(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22EducationResults.setLGAName2021("N/A");
                        ST22EducationResults.setLGAState2021(location);
                        ST22EducationResults.setLGAType2021("N/A");
                    }

                    ST22EducationResults.setProportions(statesProportion);

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
        } else if (valueType.equalsIgnoreCase("proportional")) {
            if (locationType.equalsIgnoreCase("state")) {
            int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // Create connection to JDBC class
                JDBCConnection jdbc = new JDBCConnection();

                ArrayList<String> states = jdbc.getStates();
                float[] statesResults = new float[states.size()];
                float[] statesDenominator = new float[states.size()];
                float[] statesProportion = new float[states.size()];

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) AS 'count', ";
                    query1 += "SUM(1.0 * count / (SELECT SUM(count) FROM ES" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "')) AS 'proportion' ";
                    query1 += "FROM ES" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "' AND highest_school_year = '" + highestSchoolYear + "';";

                    System.out.println(query1);


                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);

                    // Process all of the results -- this is correct
                    while (results1.next()) {
                        if (years[y] == 2016) {
                            ST22EducationResults.setResult2016(results1.getFloat("proportion"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                        else if (years[y] == 2021) {
                            ST22EducationResults.setResult2021(results1.getFloat("proportion"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                    }

                    float proportionTracker = statesProportion[0];

                    for (int i = 1; i < states.size(); i++) {

                        if (!states.get(i).equalsIgnoreCase(location)) {

                            String query3 = "SELECT SUM(count) FROM ES" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "';";

                            System.out.println(query3);

                            ResultSet results3 = statement.executeQuery(query3);

                            while (results3.next()) {
                                statesDenominator[i] = results3.getInt("SUM(count)");
                                System.out.println(statesDenominator[i] + " denominator for " + states.get(i));
                            }

                            String query4 = "SELECT SUM(count) FROM ES" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "' AND highest_school_year = '" + highestSchoolYear
                                    + "';";

                            System.out.println(query4);
                            
                            ResultSet results4 = statement.executeQuery(query4);

                            while (results4.next()) {
                                statesResults[i] = results4.getInt("SUM(count)");
                                System.out.println(statesResults[i] + " result for " + states.get(i));
                            }

                            if (statesDenominator[i] != 0) {
                            statesProportion[i] = statesResults[i] / statesDenominator[i];
                                System.out.println(statesProportion[i] + " proportion for " + states.get(i));
                            }
                            else if (statesDenominator[i] == 0) {
                                statesProportion[i] = 0;
                            }
                        }
                    }

                    for (int i = 0; i < states.size(); i++) {
                        for (int j = i + 1; j < states.size(); j++) {
                            float temp = 0;
                            if (statesProportion[j] > statesProportion[i]) {
                                temp = statesProportion[j];
                                statesProportion[j] = statesProportion[i];
                                statesProportion[i] = temp;
                            }
                        }
                    }

                    if (years[y] == 2016) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22EducationResults.setRank2016(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22EducationResults.setLGAName2016("N/A");
                        ST22EducationResults.setLGAState2016(location);
                        ST22EducationResults.setLGAType2016("N/A");

                    } else if (years[y] == 2021) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22EducationResults.setRank2021(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22EducationResults.setLGAName2021("N/A");
                        ST22EducationResults.setLGAState2021(location);
                        ST22EducationResults.setLGAType2021("N/A");
                    }

                    ST22EducationResults.setProportions(statesProportion);

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
        } else if (locationType.equalsIgnoreCase("LGA")) {

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                        // Query to find selected LGA results
                        String query1 = "SELECT * ";
                        query1 += "FROM ESST22Comparison " ;
                        query1 += "WHERE (code_2016 = " + location + " OR code_2021 = " + location + ") AND ";
                        query1 += "(status_2016 = '" + indigenousStatus + "' OR status_2021 = '" + indigenousStatus + "') AND ";
                        query1 += "(sex_2016 = '" + sex + "' OR sex_2021 = '" + sex + "') AND ";
                        query1 += "(hs_year_completed_2016 = '" + highestSchoolYear + "' OR hs_year_completed_2021 = '" + highestSchoolYear + "');";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {

                                ST22EducationResults.setLGAName2016(results.getString("name_2016"));
                                ST22EducationResults.setLGAState2016(results.getString("state_2016"));
                                ST22EducationResults.setLGAType2016(results.getString("type_2016"));
                                ST22EducationResults.setResult2016(results.getFloat("proportion_2016"));
                                ST22EducationResults.setRank2016(results.getInt("rank_2016"));

                                ST22EducationResults.setLGAName2021(results.getString("name_2021"));
                                ST22EducationResults.setLGAState2021(results.getString("state_2021"));
                                ST22EducationResults.setLGAType2021(results.getString("type_2021"));
                                ST22EducationResults.setResult2021(results.getFloat("proportion_2021"));
                                ST22EducationResults.setRank2021(results.getInt("rank_2021"));
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
        }
    }

        return ST22EducationResults;
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

        ST22PopulationResults.setLGACode(location);

        if (valueType.equalsIgnoreCase("raw")) {
            if (locationType.equalsIgnoreCase("LGA")) {
                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                        // Query to find selected LGA results
                        String query1 = "SELECT * ";
                        query1 += "FROM PSST22Comparison " ;
                        query1 += "WHERE (code_2016 = " + location + " OR code_2021 = " + location + ") AND ";
                        query1 += "(status_2016 = '" + indigenousStatus + "' OR status_2021 = '" + indigenousStatus + "') AND ";
                        query1 += "(sex_2016 = '" + sex + "' OR sex_2021 = '" + sex + "') AND ";
                        query1 += "(age_2016 = '" + age + "' OR age_2021 = '" + age + "');";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            
                            ST22PopulationResults.setLGAName2016(results.getString("name_2016"));
                            ST22PopulationResults.setLGAState2016(results.getString("state_2016"));
                            ST22PopulationResults.setLGAType2016(results.getString("type_2016"));
                            ST22PopulationResults.setResult2016(results.getInt("result_2016"));
                            ST22PopulationResults.setRank2016(results.getInt("rank_2016"));
                           
                            ST22PopulationResults.setLGAName2021(results.getString("name_2021"));
                            ST22PopulationResults.setLGAState2021(results.getString("state_2021"));
                            ST22PopulationResults.setLGAType2021(results.getString("type_2021"));
                            ST22PopulationResults.setResult2021(results.getInt("result_2021"));
                            ST22PopulationResults.setRank2021(results.getInt("rank_2021"));
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
            } else if (locationType.equalsIgnoreCase("state")) {
                int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // Create connection to JDBC class
                JDBCConnection jdbc = new JDBCConnection();

                ArrayList<String> states = jdbc.getStates();
                float[] statesResults = new float[states.size()];
                float[] statesDenominator = new float[states.size()];
                float[] statesProportion = new float[states.size()];

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) AS 'count', ";
                    query1 += "SUM(1.0 * count / (SELECT SUM(count) FROM PS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "')) AS 'proportion' ";
                    query1 += "FROM PS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "' AND age = '" + age + "';";

                    System.out.println(query1);


                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);

                    // Process all of the results -- this is correct
                    while (results1.next()) {
                        if (years[y] == 2016) {
                            ST22PopulationResults.setResult2016(results1.getFloat("count"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                        else if (years[y] == 2021) {
                            ST22PopulationResults.setResult2021(results1.getFloat("count"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                    }

                    float proportionTracker = statesProportion[0];

                    for (int i = 1; i < states.size(); i++) {

                        if (!states.get(i).equalsIgnoreCase(location)) {

                            String query3 = "SELECT SUM(count) FROM PS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "';";

                            System.out.println(query3);

                            ResultSet results3 = statement.executeQuery(query3);

                            while (results3.next()) {
                                statesDenominator[i] = results3.getInt("SUM(count)");
                                System.out.println(statesDenominator[i] + " denominator for " + states.get(i));
                            }

                            String query4 = "SELECT SUM(count) FROM PS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "' AND age = '" + age
                                    + "';";

                            System.out.println(query4);
                            
                            ResultSet results4 = statement.executeQuery(query4);

                            while (results4.next()) {
                                statesResults[i] = results4.getInt("SUM(count)");
                                System.out.println(statesResults[i] + " result for " + states.get(i));
                            }

                            if (statesDenominator[i] != 0) {
                            statesProportion[i] = statesResults[i] / statesDenominator[i];
                                System.out.println(statesProportion[i] + " proportion for " + states.get(i));
                            }
                            else if (statesDenominator[i] == 0) {
                                statesProportion[i] = 0;
                            }
                        }
                    }

                    for (int i = 0; i < states.size(); i++) {
                        for (int j = i + 1; j < states.size(); j++) {
                            float temp = 0;
                            if (statesProportion[j] > statesProportion[i]) {
                                temp = statesProportion[j];
                                statesProportion[j] = statesProportion[i];
                                statesProportion[i] = temp;
                            }
                        }
                    }

                    if (years[y] == 2016) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22PopulationResults.setRank2016(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22PopulationResults.setLGAName2016("N/A");
                        ST22PopulationResults.setLGAState2016(location);
                        ST22PopulationResults.setLGAType2016("N/A");

                    } else if (years[y] == 2021) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22PopulationResults.setRank2021(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22PopulationResults.setLGAName2021("N/A");
                        ST22PopulationResults.setLGAState2021(location);
                        ST22PopulationResults.setLGAType2021("N/A");
                    }

                    ST22PopulationResults.setProportions(statesProportion);

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
        } else if (valueType.equalsIgnoreCase("proportional")) {
            if (locationType.equalsIgnoreCase("state")) {
            int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // Create connection to JDBC class
                JDBCConnection jdbc = new JDBCConnection();

                ArrayList<String> states = jdbc.getStates();
                float[] statesResults = new float[states.size()];
                float[] statesDenominator = new float[states.size()];
                float[] statesProportion = new float[states.size()];

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) AS 'count', ";
                    query1 += "SUM(1.0 * count / (SELECT SUM(count) FROM PS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "')) AS 'proportion' ";
                    query1 += "FROM PS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                    query1 += "sex = '" + sex + "' AND age = '" + age + "';";

                    System.out.println(query1);


                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);

                    // Process all of the results -- this is correct
                    while (results1.next()) {
                        if (years[y] == 2016) {
                            ST22PopulationResults.setResult2016(results1.getFloat("proportion"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                        else if (years[y] == 2021) {
                            ST22PopulationResults.setResult2021(results1.getFloat("proportion"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                    }

                    float proportionTracker = statesProportion[0];

                    for (int i = 1; i < states.size(); i++) {

                        if (!states.get(i).equalsIgnoreCase(location)) {

                            String query3 = "SELECT SUM(count) FROM PS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "';";

                            System.out.println(query3);

                            ResultSet results3 = statement.executeQuery(query3);

                            while (results3.next()) {
                                statesDenominator[i] = results3.getInt("SUM(count)");
                                System.out.println(statesDenominator[i] + " denominator for " + states.get(i));
                            }

                            String query4 = "SELECT SUM(count) FROM PS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + indigenousStatus
                                    + "' AND sex = '" + sex + "' AND age = '" + age
                                    + "';";

                            System.out.println(query4);
                            
                            ResultSet results4 = statement.executeQuery(query4);

                            while (results4.next()) {
                                statesResults[i] = results4.getInt("SUM(count)");
                                System.out.println(statesResults[i] + " result for " + states.get(i));
                            }

                            if (statesDenominator[i] != 0) {
                            statesProportion[i] = statesResults[i] / statesDenominator[i];
                                System.out.println(statesProportion[i] + " proportion for " + states.get(i));
                            }
                            else if (statesDenominator[i] == 0) {
                                statesProportion[i] = 0;
                            }
                        }
                    }

                    for (int i = 0; i < states.size(); i++) {
                        for (int j = i + 1; j < states.size(); j++) {
                            float temp = 0;
                            if (statesProportion[j] > statesProportion[i]) {
                                temp = statesProportion[j];
                                statesProportion[j] = statesProportion[i];
                                statesProportion[i] = temp;
                            }
                        }
                    }

                    if (years[y] == 2016) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22PopulationResults.setRank2016(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22PopulationResults.setLGAName2016("N/A");
                        ST22PopulationResults.setLGAState2016(location);
                        ST22PopulationResults.setLGAType2016("N/A");

                    } else if (years[y] == 2021) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22PopulationResults.setRank2021(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22PopulationResults.setLGAName2021("N/A");
                        ST22PopulationResults.setLGAState2021(location);
                        ST22PopulationResults.setLGAType2021("N/A");
                    }

                    ST22PopulationResults.setProportions(statesProportion);

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
        } else if (locationType.equalsIgnoreCase("LGA")) {
            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                    // Query to find selected LGA results
                    String query1 = "SELECT * ";
                    query1 += "FROM PSST22Comparison " ;
                    query1 += "WHERE (code_2016 = " + location + " OR code_2021 = " + location + ") AND ";
                    query1 += "(status_2016 = '" + indigenousStatus + "' OR status_2021 = '" + indigenousStatus + "') AND ";
                    query1 += "(sex_2016 = '" + sex + "' OR sex_2021 = '" + sex + "') AND ";
                    query1 += "(age_2016 = '" + age + "' OR age_2021 = '" + age + "');";

                    System.out.println(query1);

                    // Get Result
                    ResultSet results = statement.executeQuery(query1);

                    // Process and store results
                    while (results.next()) {
                        
                        ST22PopulationResults.setLGAName2016(results.getString("name_2016"));
                        ST22PopulationResults.setLGAState2016(results.getString("state_2016"));
                        ST22PopulationResults.setLGAType2016(results.getString("type_2016"));
                        ST22PopulationResults.setResult2016(results.getFloat("proportion_2016"));
                        ST22PopulationResults.setRank2016(results.getInt("rank_2016"));
                       
                        ST22PopulationResults.setLGAName2021(results.getString("name_2021"));
                        ST22PopulationResults.setLGAState2021(results.getString("state_2021"));
                        ST22PopulationResults.setLGAType2021(results.getString("type_2021"));
                        ST22PopulationResults.setResult2021(results.getFloat("proportion_2021"));
                        ST22PopulationResults.setRank2021(results.getInt("rank_2021"));
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
        }
    }

        return ST22PopulationResults;
    }
    
    public ST22Results getST22IncomeResults(String locationType, String location, String valueType, 
            String householdIndigenousStatus, String incomeBracket) {

        // locationType = LGA or state
        // location = LGA code or state name
        // valueType = raw or proportional
        // indigenousStatus = indig, non_indig, indig_ns
        // sex = f or m
        // age = _0_4, _10_14, _15_19, _20_24, _25_29, _30_34, _35_39, _40_44, _45_49,
        // _50_54, _55_59, _5_9, _60_64, _65_yrs_ov

        // Create PSST22Results object
        ST22Results ST22IncomeResults = new ST22Results();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        ST22IncomeResults.setLGACode(location);

        if (valueType.equalsIgnoreCase("raw")) {
            if (locationType.equalsIgnoreCase("LGA")) {
                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);
    
                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);
    
                        // Query to find selected LGA results
                        String query1 = "SELECT * ";
                        query1 += "FROM HSST22Comparison " ;
                        query1 += "WHERE (code_2016 = " + location + " OR code_2021 = " + location + ") AND ";
                        query1 += "(status_2016 = '" + householdIndigenousStatus + "' OR status_2021 = '" + householdIndigenousStatus + "') AND ";
                        query1 += "(income_2016 = '" + incomeBracket + "' OR income_2021 = '" + incomeBracket + "');";
    
                        System.out.println(query1);
    
                        // Get Result
                        ResultSet results = statement.executeQuery(query1);
    
                        // Process and store results
                        while (results.next()) {
                            
                            ST22IncomeResults.setLGAName2016(results.getString("name_2016"));
                            ST22IncomeResults.setLGAState2016(results.getString("state_2016"));
                            ST22IncomeResults.setLGAType2016(results.getString("type_2016"));
                            ST22IncomeResults.setResult2016(results.getInt("result_2016"));
                            ST22IncomeResults.setRank2016(results.getInt("rank_2016"));
                           
                            ST22IncomeResults.setLGAName2021(results.getString("name_2021"));
                            ST22IncomeResults.setLGAState2021(results.getString("state_2021"));
                            ST22IncomeResults.setLGAType2021(results.getString("type_2021"));
                            ST22IncomeResults.setResult2021(results.getInt("result_2021"));
                            ST22IncomeResults.setRank2021(results.getInt("rank_2021"));
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
            } else if (locationType.equalsIgnoreCase("state")) {
                int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // Create connection to JDBC class
                JDBCConnection jdbc = new JDBCConnection();

                ArrayList<String> states = jdbc.getStates();
                float[] statesResults = new float[states.size()];
                float[] statesDenominator = new float[states.size()];
                float[] statesProportion = new float[states.size()];

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) AS 'count', ";
                    query1 += "SUM(1.0 * count / (SELECT SUM(count) FROM HS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND indigenous_status = '" + householdIndigenousStatus + "')) AS 'proportion' ";
                    query1 += "FROM HS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + householdIndigenousStatus + "' AND ";
                    query1 += "income_bracket = '" + incomeBracket + "';";

                    System.out.println(query1);


                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);

                    // Process all of the results -- this is correct
                    while (results1.next()) {
                        if (years[y] == 2016) {
                            ST22IncomeResults.setResult2016(results1.getFloat("count"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                        else if (years[y] == 2021) {
                            ST22IncomeResults.setResult2021(results1.getFloat("count"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                    }

                    float proportionTracker = statesProportion[0];

                    for (int i = 1; i < states.size(); i++) {

                        if (!states.get(i).equalsIgnoreCase(location)) {

                            String query3 = "SELECT SUM(count) FROM HS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + householdIndigenousStatus + "';";

                            System.out.println(query3);

                            ResultSet results3 = statement.executeQuery(query3);

                            while (results3.next()) {
                                statesDenominator[i] = results3.getInt("SUM(count)");
                                System.out.println(statesDenominator[i] + " denominator for " + states.get(i));
                            }

                            String query4 = "SELECT SUM(count) FROM HS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + householdIndigenousStatus
                                    + "' AND income_bracket = '" + incomeBracket + "';";

                            System.out.println(query4);
                            
                            ResultSet results4 = statement.executeQuery(query4);

                            while (results4.next()) {
                                statesResults[i] = results4.getInt("SUM(count)");
                                System.out.println(statesResults[i] + " result for " + states.get(i));
                            }

                            if (statesDenominator[i] != 0) {
                            statesProportion[i] = statesResults[i] / statesDenominator[i];
                                System.out.println(statesProportion[i] + " proportion for " + states.get(i));
                            }
                            else if (statesDenominator[i] == 0) {
                                statesProportion[i] = 0;
                            }
                        }
                    }

                    for (int i = 0; i < states.size(); i++) {
                        for (int j = i + 1; j < states.size(); j++) {
                            float temp = 0;
                            if (statesProportion[j] > statesProportion[i]) {
                                temp = statesProportion[j];
                                statesProportion[j] = statesProportion[i];
                                statesProportion[i] = temp;
                            }
                        }
                    }

                    if (years[y] == 2016) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22IncomeResults.setRank2016(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22IncomeResults.setLGAName2016("N/A");
                        ST22IncomeResults.setLGAState2016(location);
                        ST22IncomeResults.setLGAType2016("N/A");

                    } else if (years[y] == 2021) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22IncomeResults.setRank2021(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22IncomeResults.setLGAName2021("N/A");
                        ST22IncomeResults.setLGAState2021(location);
                        ST22IncomeResults.setLGAType2021("N/A");
                    }

                    ST22IncomeResults.setProportions(statesProportion);

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
        } else if (valueType.equalsIgnoreCase("proportional")) {
            if (locationType.equalsIgnoreCase("state")) {
            int[] years = { 2016, 2021 };

            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                // Create connection to JDBC class
                JDBCConnection jdbc = new JDBCConnection();

                ArrayList<String> states = jdbc.getStates();
                float[] statesResults = new float[states.size()];
                float[] statesDenominator = new float[states.size()];
                float[] statesProportion = new float[states.size()];

                for (int y = 0; y < years.length; y++) {

                    String query1 = "SELECT SUM(count) AS 'count', ";
                    query1 += "SUM(1.0 * count / (SELECT SUM(count) FROM HS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND indigenous_status = '" + householdIndigenousStatus + "')) AS 'proportion' ";
                    query1 += "FROM HS" + years[y] + " ";
                    query1 += "WHERE state = '" + location + "' AND ";
                    query1 += "indigenous_status = '" + householdIndigenousStatus + "' AND ";
                    query1 += "income_bracket = '" + incomeBracket + "';";


                    System.out.println(query1);


                    // Get Result
                    ResultSet results1 = statement.executeQuery(query1);

                    // Process all of the results -- this is correct
                    while (results1.next()) {
                        if (years[y] == 2016) {
                            ST22IncomeResults.setResult2016(results1.getFloat("proportion"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                        else if (years[y] == 2021) {
                            ST22IncomeResults.setResult2021(results1.getFloat("proportion"));
                            statesProportion[0] = results1.getFloat("proportion");
                        }
                    }

                    float proportionTracker = statesProportion[0];

                    for (int i = 1; i < states.size(); i++) {

                        if (!states.get(i).equalsIgnoreCase(location)) {

                            String query3 = "SELECT SUM(count) FROM HS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + householdIndigenousStatus + "';";

                            System.out.println(query3);

                            ResultSet results3 = statement.executeQuery(query3);

                            while (results3.next()) {
                                statesDenominator[i] = results3.getInt("SUM(count)");
                                System.out.println(statesDenominator[i] + " denominator for " + states.get(i));
                            }

                            String query4 = "SELECT SUM(count) FROM HS" + years[y] + " WHERE state = '"
                                    + states.get(i) + "' AND indigenous_status = '" + householdIndigenousStatus
                                    + "' AND income_bracket = '" + incomeBracket + "';";

                            System.out.println(query4);
                            
                            ResultSet results4 = statement.executeQuery(query4);

                            while (results4.next()) {
                                statesResults[i] = results4.getInt("SUM(count)");
                                System.out.println(statesResults[i] + " result for " + states.get(i));
                            }

                            if (statesDenominator[i] != 0) {
                            statesProportion[i] = statesResults[i] / statesDenominator[i];
                                System.out.println(statesProportion[i] + " proportion for " + states.get(i));
                            }
                            else if (statesDenominator[i] == 0) {
                                statesProportion[i] = 0;
                            }
                        }
                    }

                    for (int i = 0; i < states.size(); i++) {
                        for (int j = i + 1; j < states.size(); j++) {
                            float temp = 0;
                            if (statesProportion[j] > statesProportion[i]) {
                                temp = statesProportion[j];
                                statesProportion[j] = statesProportion[i];
                                statesProportion[i] = temp;
                            }
                        }
                    }

                    if (years[y] == 2016) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22IncomeResults.setRank2016(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22IncomeResults.setLGAName2016("N/A");
                        ST22IncomeResults.setLGAState2016(location);
                        ST22IncomeResults.setLGAType2016("N/A");

                    } else if (years[y] == 2021) {
                        for (int i = 0; i < states.size(); i++) {
                            if (statesProportion[i] == proportionTracker) {
                                ST22IncomeResults.setRank2021(i + 1);
                                System.out.println(statesProportion[i]);
                                System.out.println(proportionTracker);
                                System.out.print(i);
                            }
                        }

                        ST22IncomeResults.setLGAName2021("N/A");
                        ST22IncomeResults.setLGAState2021(location);
                        ST22IncomeResults.setLGAType2021("N/A");
                    }

                    ST22IncomeResults.setProportions(statesProportion);

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
        } else if (locationType.equalsIgnoreCase("LGA")) {
            try {
                // Connect to JDBC data base
                connection = DriverManager.getConnection(DATABASE);

                // Prepare a new SQL Query & Set a timeout
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(30);

                    // Query to find selected LGA results
                    String query1 = "SELECT * ";
                    query1 += "FROM HSST22Comparison " ;
                    query1 += "WHERE (code_2016 = " + location + " OR code_2021 = " + location + ") AND ";
                    query1 += "(status_2016 = '" + householdIndigenousStatus + "' OR status_2021 = '" + householdIndigenousStatus + "') AND ";
                    query1 += "(income_2016 = '" + incomeBracket + "' OR income_2021 = '" + incomeBracket + "');";

                    System.out.println(query1);

                    // Get Result
                    ResultSet results = statement.executeQuery(query1);

                    // Process and store results
                    while (results.next()) {
                        
                        ST22IncomeResults.setLGAName2016(results.getString("name_2016"));
                        ST22IncomeResults.setLGAState2016(results.getString("state_2016"));
                        ST22IncomeResults.setLGAType2016(results.getString("type_2016"));
                        ST22IncomeResults.setResult2016(results.getFloat("proportion_2016"));
                        ST22IncomeResults.setRank2016(results.getInt("rank_2016"));
                       
                        ST22IncomeResults.setLGAName2021(results.getString("name_2021"));
                        ST22IncomeResults.setLGAState2021(results.getString("state_2021"));
                        ST22IncomeResults.setLGAType2021(results.getString("type_2021"));
                        ST22IncomeResults.setResult2021(results.getFloat("proportion_2021"));
                        ST22IncomeResults.setRank2021(results.getInt("rank_2021"));
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
        }
    }

        return ST22IncomeResults;
    }
    
    public ArrayList<ST32Results> getST32HealthResults (String code, String status, String sex, String condition, String limit) {

        // Creating an ArrayList to store results
        ArrayList<ST32Results> similarLGAs = new ArrayList<ST32Results>();

        // Variable to store proportion result of specified LGA for later comparison
        ArrayList<String> specifiedLGAProportion = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Query to find results from specified LGA
            String query1 = "SELECT * ";
            query1 += "FROM LTHCST32 l JOIN LGA_State ls ON l.lga_code = ls.lga_code AND l.lga_year = ls.lga_year ";
            query1 += "WHERE l.lga_code = " + code + " AND ";
            query1 += "l.indigenous_status = '" + status + "' AND ";
            query1 += "l.sex = '" + sex + "' AND ";
            query1 += "l.condition = '" + condition + "';";

            // Printing out the query
            System.out.println(query1);

            // Get Result
            ResultSet results1 = statement.executeQuery(query1);

            // Process all of the results
            while (results1.next()) {

                // Creating variable to store specified LGA results
                ST32Results specifiedLGA = new ST32Results();

                specifiedLGA.setLGACode(Integer.parseInt(code));
                specifiedLGA.setLGAName(results1.getString("lga_name"));
                specifiedLGA.setResult(results1.getInt("count"));

                // Processing proportional value
                float lgaProportion = results1.getFloat("proportion");
                specifiedLGAProportion.add(String.valueOf(results1.getFloat("proportion")));
                DecimalFormat df = new DecimalFormat("#.###");
                String lgaProportionString = String.valueOf(lgaProportion);
                lgaProportionString = df.format(lgaProportion) + "%";

                specifiedLGA.setProportion(lgaProportionString);
                
                similarLGAs.add(specifiedLGA);
            }

            // Query to find the other LGAs
            if (specifiedLGAProportion.size() > 0) {
                String query2 = "SELECT l.lga_code, ls.lga_name, l.indigenous_status, l.sex, l.condition, ";
                query2 += "l.count, l.population_count, l.proportion ";
                query2 += "FROM LTHCST32 l JOIN LGA_State ls ON l.lga_code = ls.lga_code AND l.lga_year = ls.lga_year ";
                query2 += "WHERE l.indigenous_status = '" + status + "' AND ";
                query2 += "l.sex = '" + sex + "' AND ";
                query2 += "l.condition = '" + condition + "' AND ";
                query2 += "l.proportion <= " + specifiedLGAProportion.get(0) + " ";
                query2 += "ORDER BY l.proportion DESC ";
                query2 += "LIMIT " + limit + ";";
                // Printing out the query
            System.out.println(query2);

            // Get Result
            ResultSet results2 = statement.executeQuery(query2);

            // Process all of the results
            while (results1.next()) {

                int lgaCode = results2.getInt("lga_code");
                String lgaName = results2.getString("lga_name");
                int lgaResult = results2.getInt("count");
                float lgaProportion = results2.getFloat("proportion");

                if (lgaCode == Integer.parseInt(code)) {
                    continue;
                } else {

                    // Creating variable to store specified LGA results
                    ST32Results specifiedLGA = new ST32Results();

                    specifiedLGA.setLGACode(lgaCode);
                    specifiedLGA.setLGAName(lgaName);
                    specifiedLGA.setResult(lgaResult);
                
                    // Processing proportional value
                    DecimalFormat df = new DecimalFormat("#.###");
                    String lgaProportionString = String.valueOf(lgaProportion);
                    lgaProportionString = df.format(lgaProportion) + "%";

                    specifiedLGA.setProportion(lgaProportionString);

                    similarLGAs.add(specifiedLGA);
                }
            }

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
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Returning ArrayList of results
        return similarLGAs;
    }

    public ArrayList<ST32Results> getST32EducationResults (String code, String status, String sex, String highestSchoolYear, String limit) {

        // Creating an ArrayList to store results
        ArrayList<ST32Results> similarLGAs = new ArrayList<ST32Results>();

        // Variable to store proportion result of specified LGA for later comparison
        ArrayList<String> specifiedLGAProportion = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Query to find results from specified LGA
            String query1 = "SELECT * ";
            query1 += "FROM ESST32 e JOIN LGA_State ls ON e.lga_code = ls.lga_code AND e.lga_year = ls.lga_year ";
            query1 += "WHERE e.lga_code = " + code + " AND ";
            query1 += "e.indigenous_status = '" + status + "' AND ";
            query1 += "e.sex = '" + sex + "' AND ";
            query1 += "e.highest_school_year = '" + highestSchoolYear + "';";

            // Printing out the query
            System.out.println(query1);

            // Get Result
            ResultSet results1 = statement.executeQuery(query1);

            // Process all of the results
            while (results1.next()) {

                // Creating variable to store specified LGA results
                ST32Results specifiedLGA = new ST32Results();

                specifiedLGA.setLGACode(Integer.parseInt(code));
                specifiedLGA.setLGAName(results1.getString("lga_name"));
                specifiedLGA.setResult(results1.getInt("count"));

                // Processing proportional value
                float lgaProportion = results1.getFloat("proportion");
                specifiedLGAProportion.add(String.valueOf(results1.getFloat("proportion")));
                DecimalFormat df = new DecimalFormat("#.###");
                String lgaProportionString = String.valueOf(lgaProportion);
                lgaProportionString = df.format(lgaProportion) + "%";

                specifiedLGA.setProportion(lgaProportionString);
                
                similarLGAs.add(specifiedLGA);
            }

            // Query to find the other LGAs
            if (specifiedLGAProportion.size() > 0) {
                String query2 = "SELECT e.lga_code, ls.lga_name, e.indigenous_status, e.sex, e.highest_school_year, ";
                query2 += "e.count, e.population_count, e.proportion ";
                query2 += "FROM ESST32 e JOIN LGA_State ls ON e.lga_code = ls.lga_code AND e.lga_year = ls.lga_year ";
                query2 += "WHERE e.indigenous_status = '" + status + "' AND ";
                query2 += "e.sex = '" + sex + "' AND ";
                query2 += "e.highest_school_year = '" + highestSchoolYear + "' AND ";
                query2 += "e.proportion <= " + specifiedLGAProportion.get(0) + " ";
                query2 += "ORDER BY e.proportion DESC ";
                query2 += "LIMIT " + limit + ";";
                // Printing out the query
            System.out.println(query2);

            // Get Result
            ResultSet results2 = statement.executeQuery(query2);

            // Process all of the results
            while (results1.next()) {

                int lgaCode = results2.getInt("lga_code");
                String lgaName = results2.getString("lga_name");
                int lgaResult = results2.getInt("count");
                float lgaProportion = results2.getFloat("proportion");

                if (lgaCode == Integer.parseInt(code)) {
                    continue;
                } else {

                    // Creating variable to store specified LGA results
                    ST32Results specifiedLGA = new ST32Results();

                    specifiedLGA.setLGACode(lgaCode);
                    specifiedLGA.setLGAName(lgaName);
                    specifiedLGA.setResult(lgaResult);
                
                    // Processing proportional value
                    DecimalFormat df = new DecimalFormat("#.###");
                    String lgaProportionString = String.valueOf(lgaProportion);
                    lgaProportionString = df.format(lgaProportion) + "%";

                    specifiedLGA.setProportion(lgaProportionString);

                    similarLGAs.add(specifiedLGA);
                }
            }

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
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Returning ArrayList of results
        return similarLGAs;
    }

    public ArrayList<ST32Results> getST32PopulationResults (String code, String status, String sex, String minAge, String maxAge, String limit) {

        // Creating an ArrayList to store results
        ArrayList<ST32Results> similarLGAs = new ArrayList<ST32Results>();

        // Variable to store proportion result of specified LGA for later comparison
        ArrayList<String> specifiedLGAProportion = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Query to find results from specified LGA
            String query1 = "SELECT *, SUM(count), SUM(population_count), SUM(proportion) ";
            query1 += "FROM PSST32 p JOIN LGA_State ls ON p.lga_code = ls.lga_code AND p.lga_year = ls.lga_year ";
            query1 += "WHERE p.lga_code = " + code + " AND ";
            query1 += "p.indigenous_status = '" + status + "' AND ";
            query1 += "p.sex = '" + sex + "' AND ";
            query1 += "p.min_age >= " + minAge + " AND ";
            query1 += "p.max_age <= " + maxAge + ";";

            // Printing out the query
            System.out.println(query1);

            // Get Result
            ResultSet results1 = statement.executeQuery(query1);

            // Process all of the results
            while (results1.next()) {

                // Creating variable to store specified LGA results
                ST32Results specifiedLGA = new ST32Results();

                specifiedLGA.setLGACode(Integer.parseInt(code));
                specifiedLGA.setLGAName(results1.getString("lga_name"));
                specifiedLGA.setResult(results1.getInt("SUM(count)"));

                // Processing proportional value
                int numerator = results1.getInt("SUM(count)");
                int denominator = results1.getInt("SUM(population_count)");
                float lgaProportion = ((float)numerator / denominator) * 100;
                specifiedLGAProportion.add(String.valueOf(results1.getFloat("SUM(proportion)")));
                DecimalFormat df = new DecimalFormat("#.###");
                String lgaProportionString = String.valueOf(lgaProportion);
                lgaProportionString = df.format(lgaProportion) + "%";

                specifiedLGA.setProportion(lgaProportionString);
                
                similarLGAs.add(specifiedLGA);
            }

            // Query to find the other LGAs
            if (specifiedLGAProportion.size() > 0) {
                String query2 = "SELECT *, p.lga_code, SUM(count), SUM(population_count), SUM(proportion) ";
                query2 += "FROM PSST32 p JOIN LGA_State ls ON p.lga_code = ls.lga_code AND p.lga_year = ls.lga_year ";
                query2 += "WHERE p.indigenous_status = '" + status + "' AND ";
                query2 += "p.sex = '" + sex + "' AND ";
                query2 += "p.min_age >= " + minAge + " AND ";
                query2 += "p.max_age <= " + maxAge + " ";
                query2 += "GROUP BY p.lga_code ";
                query2 += "HAVING SUM(proportion) < " + specifiedLGAProportion.get(0) + " ";
                query2 += "ORDER BY SUM(proportion) DESC ";
                query2 += "LIMIT " + limit + ";";
                // Printing out the query
            System.out.println(query2);

            // Get Result
            ResultSet results2 = statement.executeQuery(query2);

            // Process all of the results
            while (results1.next()) {

                int lgaCode = results2.getInt("lga_code");
                String lgaName = results2.getString("lga_name");
                int lgaResult = results2.getInt("SUM(count)");

                int numerator = results2.getInt("SUM(count)");
                int denominator = results2.getInt("SUM(population_count)");
                float lgaProportion = ((float)numerator / denominator) * 100;

                if (lgaCode == Integer.parseInt(code)) {
                    continue;
                } else {

                    // Creating variable to store specified LGA results
                    ST32Results specifiedLGA = new ST32Results();

                    specifiedLGA.setLGACode(lgaCode);
                    specifiedLGA.setLGAName(lgaName);
                    specifiedLGA.setResult(lgaResult);
                
                    // Processing proportional value
                    DecimalFormat df = new DecimalFormat("#.###");
                    String lgaProportionString = String.valueOf(lgaProportion);
                    lgaProportionString = df.format(lgaProportion) + "%";

                    specifiedLGA.setProportion(lgaProportionString);

                    similarLGAs.add(specifiedLGA);
                }
            }

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
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Returning ArrayList of results
        return similarLGAs;
    }

    public ArrayList<ST32Results> getST32IncomeResults (String code, String status, String minIncome, String maxIncome, String limit) {

        // Creating an ArrayList to store results
        ArrayList<ST32Results> similarLGAs = new ArrayList<ST32Results>();

        // Variable to store proportion result of specified LGA for later comparison
        ArrayList<String> specifiedLGAProportion = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Query to find results from specified LGA
            String query1 = "SELECT *, SUM(count), SUM(population_count), SUM(proportion) ";
            query1 += "FROM HSST32 h JOIN LGA_State ls ON h.lga_code = ls.lga_code AND h.lga_year = ls.lga_year ";
            query1 += "WHERE h.lga_code = " + code + " AND ";
            query1 += "h.indigenous_status = '" + status + "' AND ";
            query1 += "h.min_income >= " + minIncome + " AND ";
            query1 += "h.max_income <= " + maxIncome + ";";

            // Printing out the query
            System.out.println(query1);

            // Get Result
            ResultSet results1 = statement.executeQuery(query1);

            // Process all of the results
            while (results1.next()) {

                // Creating variable to store specified LGA results
                ST32Results specifiedLGA = new ST32Results();

                specifiedLGA.setLGACode(Integer.parseInt(code));
                specifiedLGA.setLGAName(results1.getString("lga_name"));
                specifiedLGA.setResult(results1.getInt("SUM(count)"));

                // Processing proportional value
                int numerator = results1.getInt("SUM(count)");
                int denominator = results1.getInt("SUM(population_count)");
                float lgaProportion = ((float)numerator / denominator) * 100;
                specifiedLGAProportion.add(String.valueOf(results1.getFloat("SUM(proportion)")));
                DecimalFormat df = new DecimalFormat("#.###");
                String lgaProportionString = String.valueOf(lgaProportion);
                lgaProportionString = df.format(lgaProportion) + "%";

                specifiedLGA.setProportion(lgaProportionString);
                
                similarLGAs.add(specifiedLGA);
            }

            // Query to find the other LGAs
            if (specifiedLGAProportion.size() > 0) {
                String query2 = "SELECT *, SUM(count), SUM(population_count), SUM(proportion) ";
                query2 += "FROM HSST32 h JOIN LGA_State ls ON h.lga_code = ls.lga_code AND h.lga_year = ls.lga_year ";
                query2 += "WHERE h.indigenous_status = '" + status + "' AND ";
                query2 += "h.min_income >= " + minIncome + " AND ";
                query2 += "h.max_income <= " + maxIncome + " ";
                query2 += "GROUP BY h.lga_code ";
                query2 += "HAVING SUM(proportion) < " + specifiedLGAProportion.get(0) + " ";
                query2 += "ORDER BY SUM(proportion) DESC ";
                query2 += "LIMIT " + limit + ";";
                // Printing out the query
            System.out.println(query2);

            // Get Result
            ResultSet results2 = statement.executeQuery(query2);

            // Process all of the results
            while (results1.next()) {

                int lgaCode = results2.getInt("lga_code");
                String lgaName = results2.getString("lga_name");
                int lgaResult = results2.getInt("SUM(count)");

                int numerator = results2.getInt("SUM(count)");
                int denominator = results2.getInt("SUM(population_count)");
                float lgaProportion = ((float)numerator / denominator) * 100;

                if (lgaCode == Integer.parseInt(code)) {
                    continue;
                } else {

                    // Creating variable to store specified LGA results
                    ST32Results specifiedLGA = new ST32Results();

                    specifiedLGA.setLGACode(lgaCode);
                    specifiedLGA.setLGAName(lgaName);
                    specifiedLGA.setResult(lgaResult);
                
                    // Processing proportional value
                    DecimalFormat df = new DecimalFormat("#.###");
                    String lgaProportionString = String.valueOf(lgaProportion);
                    lgaProportionString = df.format(lgaProportion) + "%";

                    specifiedLGA.setProportion(lgaProportionString);

                    similarLGAs.add(specifiedLGA);
                }
            }

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
                // Connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Returning ArrayList of results
        return similarLGAs;
    }

    public ArrayList<String> getMinimumIncomes () {
        ArrayList<String> minimums = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT min_income FROM IncomeBracket";

            // Printing out the query
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                minimums.add(results.getString("min_income"));
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

        return minimums;
    }

    public ArrayList<String> getMaximumIncomes () {
        ArrayList<String> maximums = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT(max_income), IFNULL(max_income, 99999) FROM IncomeBracket ORDER BY IFNULL(max_income, 99999) ASC";

            // Printing out the query
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                maximums.add(results.getString("IFNULL(max_income, 99999)"));
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

        return maximums;
    }

    public ArrayList<String> getMinimumAges () {
        ArrayList<String> minimums = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT(min_age) FROM AgeBracket;";

            // Printing out the query
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                minimums.add(results.getString("min_age"));
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

        return minimums;
    }

    public ArrayList<String> getMaximumAges () {
        ArrayList<String> maximums = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT(max_age), IFNULL(max_age, 99999) FROM AgeBracket ORDER BY IFNULL(max_age, 99999) ASC";

            // Printing out the query
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                maximums.add(results.getString("IFNULL(max_age, 99999)"));
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

        return maximums;
    }

    public ArrayList<String> get2021LGAs() {
        ArrayList<String> codes = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT lga_code FROM LGA WHERE lga_year = 2021;";

            // Printing out the query
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                codes.add(results.getString("lga_code"));
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

        return codes;
    }

    public ArrayList<String> getIndigenousStatuses() {
        ArrayList<String> statuses = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT DISTINCT(indigenous_status) FROM LTHCStatistics;";

            // Printing out the query
            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                statuses.add(results.getString("indigenous_status"));
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

        return statuses;
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
    public ArrayList<Table> getDataByHealthCondition(String selectedCondition, String sort, String order) {
        ArrayList<Table> healthCondData = new ArrayList<Table>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = /*
                            * "SELECT health.LGA_CODE AS code, LGAnum.LGA_name AS name, SUM(health.count) AS RAW "
                            * ;
                            * query += "FROM LTHCStatistics AS health ";
                            * query += "JOIN LGA AS LGAnum ";
                            * query += "ON health.LGA_CODE = LGAnum.LGA_CODE ";
                            * query += "WHERE health.indigenous_status = 'indig' ";
                            * query += "AND health.condition = '" + selectedCondition + "' ";
                            * query += "AND LGAnum.lga_year = '2021' ";
                            * query += "GROUP BY health.LGA_CODE;";
                            * "SELECT hc.lga_code AS 'code', lga.lga_name AS 'name', printf('%,d', hc.indig) AS 'indig', printf('%,d', hc.nonindig) AS 'nonindig', "
                            * ;
                            * query +=
                            * "printf('%,d', hc.nonstated) AS 'nonstated', printf('%,d', hc.total) AS 'total', printf('%,d', hc.gap) as 'gap', printf('%d%%', hc.pro) AS 'pro', printf('%d%%', hc.proInd) AS 'proInd' "
                            * ;
                            * query +=
                            * "FROM LGA JOIN (SELECT health.lga_code, cond.indig AS indig, cond.nonindig AS nonindig, cond.nonstated, cond.total, cond.gap AS gap, cond.proportional AS pro, (cond.indig * 100 /SUM(health.count)) AS proInd "
                            * ;
                            * query +=
                            * "FROM LTHCStatistics AS health JOIN (SELECT heal.code, heal.indig, heal.nonindig, heal.nonstated, heal.total, heal.gap, heal.proportional "
                            * ;
                            * query +=
                            * "FROM (SELECT C1.lga_code AS code, hcon.indig AS indig, hcon.nonindig AS nonindig, (C1.count + C2.count) AS nonstated, "
                            * ;
                            * query +=
                            * "(hcon.indig + hcon.nonindig + C1.count + C2.count) AS total, (hcon.indig - hcon.nonindig) AS 'gap', (hcon.indig * 100/ (hcon.indig + hcon.nonindig + C1.count + C2.count)) AS proportional "
                            * ;
                            * query +=
                            * "FROM LTHCStatistics AS C1 OUTER LEFT JOIN LTHCStatistics AS C2 JOIN (SELECT Con1.lga_code, ConIndig.indig, (Con1.count + Con2.count) AS 'nonindig' "
                            * ;
                            * query +=
                            * "FROM LTHCStatistics AS Con1 OUTER LEFT JOIN LTHCStatistics AS Con2 JOIN (SELECT *, C1.lga_code, (C1.count + C2.count) AS indig "
                            * ;
                            * query +=
                            * "FROM LTHCStatistics AS C1 OUTER LEFT JOIN LTHCStatistics AS C2 WHERE C1.condition = '"
                            * + selectedCondition + "' AND C2.condition = '" + selectedCondition
                            * + "' AND C1.indigenous_status = 'indig' AND C2.indigenous_status = 'indig' ";
                            * query +=
                            * "AND C1.sex = 'f' AND C2.sex = 'm' AND C1.lga_code = C2.lga_code) AS ConIndig ON ConIndig.lga_code = Con1.lga_code WHERE Con1.condition = '"
                            * + selectedCondition + "' AND Con2.condition = '" + selectedCondition + "' ";
                            * query +=
                            * "AND Con1.indigenous_status = 'non_indig' AND Con2.indigenous_status = 'non_indig' AND Con1.sex = 'f' AND Con2.sex = 'm' "
                            * ;
                            * query +=
                            * "AND Con1.lga_code = Con2.lga_code) AS hcon ON hcon.lga_code = C1.lga_code ";
                            * query += "WHERE C1.condition = '" + selectedCondition +
                            * "' AND C2.condition = '" + selectedCondition
                            * +
                            * "' AND C1.indigenous_status = 'indig_stat_notstated' AND C2.indigenous_status = 'indig_stat_notstated' "
                            * ;
                            * query +=
                            * "AND C1.sex = 'f' AND C2.sex = 'm' AND C1.lga_code = C2.lga_code) AS heal) AS cond "
                            * ;
                            * query +=
                            * "ON cond.code = health.lga_code WHERE health.indigenous_status = 'indig' GROUP BY health.lGA_Code) AS hc ON hc.lga_code = lga.lga_code GROUP BY hc.lga_code;"
                            * ;
                            */
                    "SELECT sort.code AS 'code', sort.name AS 'name', printf('%,d', sort.indig) AS 'indig', printf('%,d', sort.nonindig) AS 'nonindig', printf('%,d', sort.total) AS 'total', printf('%d%%', sort.propIndig) AS 'propIndig', printf('%d%%', sort.propNon) AS 'propNon', (sort.gap*100) AS 'gap' ";
            query += "FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, (Ef1.count + Ef2.count) AS indig, ed.tindig AS tindig, s.nonindig AS nonindig, s.tnon AS tnon, ss.notstated AS nonstated, ";
            query += "(Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, ";
            query += "(((Ef1.count + Ef2.count)*100)/ed.tindig) AS propIndig, (s.nonindig*100/s.tnon) AS propNon,((((Ef1.count + Ef2.count)*100)/ed.tindig)-(s.nonindig*100/s.tnon)) AS gap ";
            query += "FROM LTHCStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name FROM LGA ";
            query += "WHERE LGA.lga_year = '2021') AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ed.tnotstated ";
            query += "FROM LTHCStatistics AS Ef1 OUTER LEFT JOIN LTHCStatistics AS Ef2 JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tnotstated ";
            query += "FROM LTHCStatistics AS E WHERE E.indigenous_status = 'indig_stat_notstated' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ";
            query += "ON ed.code =  EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.condition = '"
                    + selectedCondition + "' AND Ef2.condition = '" + selectedCondition + "' ";
            query += "AND Ef1.indigenous_status = 'indig_stat_notstated' AND Ef2.indigenous_status = 'indig_stat_notstated' AND Ef1.sex = 'f' AND Ef2.sex = 'm' ";
            query += "AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code ";
            query += "OUTER LEFT JOIN LTHCStatistics AS Ef2 JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon ";
            query += "FROM LTHCStatistics AS Ef1 OUTER LEFT JOIN LTHCStatistics AS Ef2 JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tnon ";
            query += "FROM LTHCStatistics AS E WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2021' ";
            query += "GROUP BY E.lga_code) AS ed ON ed.code =  EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' ";
            query += "AND Ef1.condition = '" + selectedCondition + "' AND Ef2.condition = '" + selectedCondition
                    + "' AND Ef1.indigenous_status = 'non_indig' AND Ef2.indigenous_status = 'non_indig' ";
            query += "AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code ";
            query += "JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tindig FROM LTHCStatistics AS E ";
            query += "WHERE E.indigenous_status = 'indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code =  EF1.lga_code ";
            query += "WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.condition = '" + selectedCondition
                    + "' AND Ef2.condition = '" + selectedCondition + "' ";
            query += "AND Ef1.indigenous_status = 'indig' AND Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort ";
            query += "ORDER BY " + sort + ";";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                /*
                 * String result = new String();
                 * result = String.valueOf(results.getString("code")) + " ";
                 * result += results.getString("name") + " ";
                 * result = result + results.getString("indig") + " ";
                 * result += results.getString("nonindig") + " ";
                 * result += results.getString("nonstated") + " ";
                 * result += results.getString("total") + " ";
                 * result += results.getString("gap") + " ";
                 * result += results.getString("pro") + " ";
                 * result += results.getString("proInd") + " ";
                 */
                String code = String.valueOf(results.getString("code"));
                String name = results.getString("name");
                String indig = results.getString("indig");
                String nonindig = results.getString("nonindig");
                String total = results.getString("total");
                String propIndig = results.getString("propIndig");
                String propNon = results.getString("propNon");
                String gap = String.valueOf(results.getString("gap"));

                Table tableCondition = new Table(code, name, indig, nonindig, total, propIndig, propNon, gap);

                healthCondData.add(tableCondition);
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
    public ArrayList<Table> getDataByAge(String selectedAge, String sort, String order) {
        ArrayList<Table> selectedAgeData = new ArrayList<Table>();

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
                     * "SELECT lth.LGA_CODE AS 'LGA', age FROM PopulationStatistics AS lth WHERE lth.indigenous_status = 'indig' AND lth.age = '"
                     * + selectedAge + "'";
                     */

                    "SELECT sort.code AS 'code', sort.name AS 'name', printf('%,d', sort.indig) AS 'indig', printf('%,d', sort.nonindig) AS 'nonindig', printf('%,d', sort.total) AS 'total', printf('%d%%', sort.propIndig) AS 'propIndig', printf('%d%%', sort.propNon) AS 'propNon', (sort.gap *100) AS 'gap' ";
            query += "FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, (Ef1.count + Ef2.count) AS indig, ed.tindig AS tindig, s.nonindig AS nonindig, s.tnon AS tnon, ss.notstated AS nonstated, ";
            query += "(Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, ";
            query += "(((Ef1.count + Ef2.count)*100)/ed.tindig) AS propIndig, (s.nonindig*100/s.tnon) AS propNon,((((Ef1.count + Ef2.count)*100)/ed.tindig)-(s.nonindig*100/s.tnon)) AS gap ";
            query += "FROM PopulationStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name FROM LGA ";
            query += "WHERE LGA.lga_year = '2021') AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ed.tnotstated ";
            query += "FROM PopulationStatistics AS Ef1 OUTER LEFT JOIN PopulationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tnotstated ";
            query += "FROM PopulationStatistics AS E WHERE E.indigenous_status = 'indig_ns' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ";
            query += "ON ed.code =  EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.age = '"
                    + selectedAge + "' AND Ef2.age = '" + selectedAge + "' ";
            query += "AND Ef1.indigenous_status = 'indig_ns' AND Ef2.indigenous_status = 'indig_ns' AND Ef1.sex = 'f' AND Ef2.sex = 'm' ";
            query += "AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code ";
            query += "OUTER LEFT JOIN PopulationStatistics AS Ef2 JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon ";
            query += "FROM PopulationStatistics AS Ef1 OUTER LEFT JOIN PopulationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tnon ";
            query += "FROM PopulationStatistics AS E WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2021' ";
            query += "GROUP BY E.lga_code) AS ed ON ed.code =  EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' ";
            query += "AND Ef1.age = '" + selectedAge + "' AND Ef2.age = '" + selectedAge
                    + "' AND Ef1.indigenous_status = 'non_indig' AND Ef2.indigenous_status = 'non_indig' ";
            query += "AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code ";
            query += "JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tindig FROM PopulationStatistics AS E ";
            query += "WHERE E.indigenous_status = 'indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code =  EF1.lga_code ";
            query += "WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.age = '" + selectedAge
                    + "' AND Ef2.age = '" + selectedAge + "' ";
            query += "AND Ef1.indigenous_status = 'indig' AND Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort ";
            query += "ORDER BY " + sort + " " + order +  ";";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                /*
                 * String result = new String();
                 * 
                 * result = String.valueOf(results.getString("code")) + " ";
                 * result = result + results.getString("name") + " ";
                 * result = result + results.getString("indig") + " ";
                 * result = result + results.getString("nonindig") + " ";
                 * result = result + results.getString("nonstated") + " ";
                 * result = result + results.getString("total") + " ";
                 * result = result + results.getString("gap") + " ";
                 * result = result + results.getString("proportional");
                 */
                // QUERY FOR TABLE - look up the columns we need
                String code = String.valueOf(results.getString("code"));
                String name = results.getString("name");
                String indig = results.getString("indig");
                String nonindig = results.getString("nonindig");
                String total = results.getString("total");
                String propIndig = results.getString("propIndig");
                String propNon = results.getString("propNon");
                String gap = String.valueOf(results.getString("gap"));

                // create object for table class
                Table tableSex = new Table(code, name, indig, nonindig, total, propIndig, propNon, gap);

                selectedAgeData.add(tableSex);
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
    public ArrayList<Table> getDataBySchool(String selectedSchool, String sort, String order) {
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
                */

                "SELECT sort.code AS 'code', sort.name AS 'name', printf('%,d', sort.indig) AS 'indig', printf('%,d', sort.nonindig) AS 'nonindig', printf('%,d', sort.total) AS 'total', printf('%d%%', sort.propIndig) AS 'propIndig', printf('%d%%', sort.propNon) AS 'propNon', (sort.gap *100) AS 'gap' ";
                query += "FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, (Ef1.count + Ef2.count) AS indig, ed.tindig AS tindig, s.nonindig AS nonindig, s.tnon AS tnon, ss.notstated AS nonstated, ";
                query += "(Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, ";
                query += "(((Ef1.count + Ef2.count)*100)/ed.tindig) AS propIndig, (s.nonindig*100/s.tnon) AS propNon,((((Ef1.count + Ef2.count)*100)/ed.tindig)-(s.nonindig*100/s.tnon)) AS gap ";
                query += "FROM EducationStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name FROM LGA ";
                query += "WHERE LGA.lga_year = '2021') AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ed.tnotstated ";
                query += "FROM EducationStatistics AS Ef1 OUTER LEFT JOIN EducationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tnotstated ";
                query += "FROM EducationStatistics AS E WHERE E.indigenous_status = 'indig_stat_notstated' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ";
                query += "ON ed.code =  EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.highest_school_year = '" + selectedSchool + "' AND Ef2.highest_school_year = '" + selectedSchool + "' ";
                query += "AND Ef1.indigenous_status = 'indig_stat_notstated' AND Ef2.indigenous_status = 'indig_stat_notstated' AND Ef1.sex = 'f' AND Ef2.sex = 'm' ";
                query += "AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code ";
                query += "OUTER LEFT JOIN EducationStatistics AS Ef2 JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon ";
                query += "FROM EducationStatistics AS Ef1 OUTER LEFT JOIN EducationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tnon ";
                query += "FROM EducationStatistics AS E WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2021' ";
                query += "GROUP BY E.lga_code) AS ed ON ed.code =  EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' ";
                query += "AND Ef1.highest_school_year = '" + selectedSchool + "' AND Ef2.highest_school_year = '" + selectedSchool + "' AND Ef1.indigenous_status = 'non_indig' AND Ef2.indigenous_status = 'non_indig' ";
                query += "AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code ";
                query += "JOIN (SELECT E.lga_code AS code,  SUM(E.count) AS tindig FROM EducationStatistics AS E ";
                query += "WHERE E.indigenous_status = 'indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code =  EF1.lga_code ";
                query += "WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.highest_school_year = '" + selectedSchool + "' AND Ef2.highest_school_year = '" + selectedSchool +"' ";
                query += "AND Ef1.indigenous_status = 'indig' AND Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort ";
                query += "ORDER BY " + sort + " " + order +  ";";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                /*
                 * // this works
                 * String result = new String();
                 * 
                 * result = String.valueOf(results.getString("code")) + " ";
                 * result = result + results.getString("name") + " ";
                 * result = result + results.getString("indig") + " ";
                 * result = result + results.getString("nonindig") + " ";
                 * result = result + results.getString("nonstated") + " ";
                 * result = result + results.getString("total") + " ";
                 * result = result + results.getString("gap") + " ";
                 * result = result + results.getString("proportional");
                 * schoolData.add(result);
                 */

                  //QUERY FOR TABLE - look up the columns we need 
                  String code =  String.valueOf(results.getString("code"));
                  String name =  results.getString("name");
                  String indig =  results.getString("indig");
                  String nonindig =  results.getString("nonindig");
                  String total =  results.getString("total");
                  String propIndig =  results.getString("propIndig");
                  String propNon =  results.getString("propNon");
                  String gap =  results.getString("gap");
                  

                  //create object for table class
                  Table tableSchool = new Table(code, name, indig, nonindig, total, propIndig, propNon, gap);
                  
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
    public ArrayList<Table> getDataByHouse(String selectedIncome, String sort, String order) {
        ArrayList<Table> incomeData = new ArrayList<Table>();

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
            /* "SELECT incomedata.code AS 'cod', LGA.lga_name AS 'nam', incomedata.indig AS 'indi', incomedata.nonindig AS 'nonindi', incomedata.gap AS 'gap', incomedata.total AS 'total', incomedata.proportional AS 'prop' ";
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
            */
            "SELECT sort.code AS 'code', sort.name AS 'name', sort.indig AS 'indig', sort.nonindig AS 'nonindig', sort.total AS 'total', ";
            query += "printf('%.2f%', sort.propIndig) AS 'propIndig', printf('%.2f%', sort.propNonindig) AS 'propNon', (printf('%.0f', sort.gap)* 100) AS 'gap' ";
            query += "FROM (SELECT H1.lga_code AS code, L.name AS name, H1.count AS indig, ho.unstatedTotal AS total, hh.nonindig AS nonindig, ";
            query += "SUM(H2.count) AS tindig, hh.tnonLGA AS tnon, ((H1.count * 1.0/ SUM(H2.count))*100) AS propIndig, hh.proNon AS propNonindig, ";
            query += "(((H1.count * 1.0/ SUM(H2.count))*100)-hh.proNon) AS gap FROM HouseholdStatistics H1 JOIN (SELECT LGA.lga_code, LGA.lga_name AS name ";
            query += "FROM LGA WHERE LGA.lga_year = '2021') AS L ON L.lga_code = H1.lga_code JOIN (SELECT H1.lga_code AS code, H1.count AS unstatedTotal, ";
            query += "SUM(H2.count) AS tunstatedLGA, (H1.count * 100/ H2.count) AS proUn FROM HouseholdStatistics H1 LEFT OUTER JOIN HouseholdStatistics H2 ";
            query += "WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' AND H1.income_bracket = '" + selectedIncome
                    + "' AND H1.indigenous_status LIKE '%total%' AND ";
            query += "H2.indigenous_status LIKE '%total%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) AS ho ON ho.code = H1.lga_code ";
            query += "JOIN (SELECT H1.lga_code, H1.count AS nonindig, SUM(H2.count) AS tnonLGA, ((H1.count * 1.0/ SUM(H2.count))*100) AS proNon FROM HouseholdStatistics H1 ";
            query += "LEFT OUTER JOIN HouseholdStatistics H2 WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' AND H1.income_bracket = '" + selectedIncome + "' AND H1.indigenous_status LIKE '%other%' AND ";
            query += "H2.indigenous_status LIKE '%other%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) AS hh ON hh.lga_code = H1.lga_code ";
            query += "LEFT OUTER JOIN HouseholdStatistics H2 WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' AND H1.income_bracket = '"
                    + selectedIncome + "' AND ";
            query += "H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%indig%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) AS sort ";
            query += "ORDER BY " + sort + " " + order +  ";";

            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Create a HealthCondition Object
                /*
                 * String result = new String();
                 * 
                 * result = String.valueOf(results.getString("cod")) + " ";
                 * result = result + results.getString("nam") + " ";
                 * result = result + results.getString("indi") + " ";
                 * result = result + results.getString("nonindi") + " ";
                 * result = result + results.getString("total") + " ";
                 * result = result + results.getString("gap") + " ";
                 * // result = result + results.getString("total") + " ";
                 * result = result + results.getString("prop") + " ";
                 */

                String code = String.valueOf(results.getString("code"));
                String name = results.getString("name");
                String indig = results.getString("indig");
                String nonindig = results.getString("nonindig");
                String total = results.getString("total");
                String propIndig = results.getString("propIndig");
                String propNon = results.getString("propNon");
                String gap = String.valueOf(results.getString("gap"));

                // create object for table class
                Table tableSchool = new Table(code, name, indig, nonindig, total, propIndig, propNon, gap);

                incomeData.add(tableSchool);
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

    // level 3 page 5 JDBC ----------------------------------------------------------------------------------------------------------------------------------
    public ArrayList<Table2> getData31(String selectedIncome2, String selectedSchool2, String selectedAge2, String sqkmMin, String sqkmMax, String sort2, String order) {
        ArrayList<Table2> data31 = new ArrayList<Table2>();

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
            "SELECT sort.code AS 'code', sort.name AS 'name', printf('%,d km', sort.area) AS 'area', sort.propIndig_ISA AS 'propIndig_ISA', sort.propIndig_IS AS 'propIndig_IS', ";
            query += "sort.propIndig_IA AS 'propIndig_IA', sort.propIndig_SA AS 'propIndig_SA', sort.propIndig_I AS 'propIndig_I', sort.propIndig_S AS 'propIndig_S', sort.propIndig_A AS 'propIndig_A', ";
            query += "sort.gap_inc21 AS 'gap_inc21', sort.gap_inc16 AS 'gap_inc16', printf('%d%%', sort.change_inc) AS 'change_inc', sort.gap_sch21 AS 'gap_sch21', sort.gap_sch16 AS 'gap_sch16', ";
            query += "printf('%d%%', sort.change_sch) AS 'change_sch', sort.gap_age21 AS 'gap_age21', sort.gap_age16 AS 'gap_age16', printf('%d%%', sort.change_age) AS 'change_age' ";
            query += "FROM (Select A.code AS code, A.name AS name, A.area AS area, printf('%.2f%', (A.propIndig_inc21 + A.propIndig_sch21 + B.propIndig_age21)/3) AS propIndig_ISA, printf('%.2f%', (A.propIndig_inc21 + A.propIndig_sch21)/2) AS propIndig_IS, ";
            query += "printf('%.2f%', (A.propIndig_inc21 + B.propIndig_age21)/2) AS propIndig_IA, printf('%.2f%', (A.propIndig_sch21 + B.propIndig_age21)/2) AS propIndig_SA, printf('%.2f%', (A.propIndig_inc21)) AS propIndig_I, ";
            query += "printf('%.2f%', (A.propIndig_sch21)) AS propIndig_S, printf('%.2f%', (B.propIndig_age21)) AS propIndig_A, ";
            query += "A.gap_inc21 AS gap_inc21, A.gap_inc16 AS gap_inc16, A.change_inc, A.gap_sch21, A.gap_sch16, A.change_sch, B.gap_age21, B.gap_age16, B.change_age AS change_age ";
            query += "FROM (SELECT A.code, A.name, A.area, A.propIndig_inc21, A.propNon_inc21, A.gap_inc21, A.propIndig_inc16, A.propNon_inc16, A.gap_inc16, A.change_inc, B.propIndig_sch21, B.propNon_sch21, B.gap_sch21, B.propIndig_sch16, B.propNon_sch16, B.gap_sch16, B.change_sch ";
            query += "FROM (Select A.code_inc21 AS code, A.name_inc21 AS name, A.area_inc21 AS area, A.propIndig_inc21, A.propNon_inc21, A.gap_inc21, B.propIndig_inc16, B.propNon_inc16, B.gap_inc16, ((A.gap_Inc21 - B.gap_Inc16)/B.gap_Inc16 *100) AS change_inc ";
            query += "FROM (SELECT sort.code AS code_inc21, sort.name AS name_inc21, sort.area AS area_inc21, sort.indig AS indig_inc21, sort.nonindig AS nonindig_inc21, sort.total AS total_inc21, sort.propIndig AS propIndig_inc21, sort.propNonindig AS propNon_inc21, ";
            query += "((printf('%.0f', sort.gap)) *100) AS gap_inc21 FROM (SELECT H1.lga_code AS code, L.name AS name, L.area AS area, H1.count AS indig, ho.unstatedTotal AS total, hh.nonindig AS nonindig, SUM(H2.count) AS tindig, hh.tnonLGA AS tnon, ((H1.count * 1.0 / SUM(H2.count))*100) AS propIndig, ";
            query += "hh.proNon AS propNonindig, ((H1.count * 1.0 / SUM(H2.count)*100) - hh.proNon) AS gap FROM HouseholdStatistics H1 JOIN (SELECT LGA.lga_code, LGA.lga_name AS name, LGA.area_sqkm AS area FROM LGA WHERE LGA.lga_year = '2021') ";
            query += "AS L ON L.lga_code = H1.lga_code JOIN (SELECT H1.lga_code AS code, H1.count AS unstatedTotal, SUM(H2.count) AS tunstatedLGA, (H1.count * 100 / H2.count) AS proUn FROM HouseholdStatistics H1 LEFT OUTER JOIN ";
            query += "HouseholdStatistics H2 WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' AND H1.income_bracket = '" + selectedIncome2 + "' AND H1.indigenous_status LIKE '%total%' AND H2.indigenous_status LIKE '%total%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) ";
            query += "AS ho ON ho.code = H1.lga_code JOIN (SELECT H1.lga_code, H1.count AS nonindig, SUM(H2.count) AS tnonLGA, ((H1.count * 1.0 / SUM(H2.count))*100) AS proNon FROM HouseholdStatistics H1 LEFT OUTER JOIN HouseholdStatistics H2 ";
            query += "WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' AND H1.income_bracket = '" + selectedIncome2 + "' AND H1.indigenous_status LIKE '%other%' AND H2.indigenous_status LIKE '%other%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) ";
            query += "AS hh ON hh.lga_code = H1.lga_code LEFT OUTER JOIN HouseholdStatistics H2 WHERE H1.LGA_year = '2021' AND H2.LGA_year = '2021' AND H1.income_bracket = '" + selectedIncome2 + "' AND H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%indig%' AND H1.lga_code = H2.lga_code ";
            query += "GROUP BY H1.lga_Code) AS sort) AS A JOIN (SELECT sort.code AS code_inc16, sort.name AS name_inc16, sort.area AS area_inc16, sort.indig AS indig_inc16, sort.nonindig AS nonindig_inc16, sort.total AS total_inc16, sort.propIndig AS propIndig_inc16, sort.propNonindig AS propNon_inc16, ";
            query += "(printf('%.0f', sort.gap) *100) AS gap_inc16 FROM (SELECT H1.lga_code AS code, L.name AS name, L.area AS area, H1.count AS indig, ho.unstatedTotal AS total, hh.nonindig AS nonindig, SUM(H2.count) AS tindig, hh.tnonLGA AS tnon, ((H1.count * 1.0 / SUM(H2.count))*100) AS propIndig, ";
            query += "hh.proNon AS propNonindig, ( (H1.count * 1.0 / SUM(H2.count)*100) - hh.proNon) AS gap FROM HouseholdStatistics H1 JOIN (SELECT LGA.lga_code, LGA.lga_name AS name, LGA.area_sqkm AS area FROM LGA WHERE LGA.lga_year = '2016') ";
            query += "AS L ON L.lga_code = H1.lga_code JOIN (SELECT H1.lga_code AS code, H1.count AS unstatedTotal, SUM(H2.count) AS tunstatedLGA, (H1.count * 100 / H2.count) AS proUn FROM HouseholdStatistics H1 LEFT OUTER JOIN HouseholdStatistics H2 ";
            query += "WHERE H1.LGA_year = '2016' AND H2.LGA_year = '2016' AND H1.income_bracket = '" + selectedIncome2 + "' AND H1.indigenous_status LIKE '%total%' AND H2.indigenous_status LIKE '%total%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) ";
            query += "AS ho ON ho.code = H1.lga_code JOIN (SELECT H1.lga_code, H1.count AS nonindig, SUM(H2.count) AS tnonLGA, ((H1.count * 1.0 / SUM(H2.count))*100) AS proNon FROM HouseholdStatistics H1 LEFT OUTER JOIN HouseholdStatistics H2 WHERE H1.LGA_year = '2016' AND ";
            query += "H2.LGA_year = '2016' AND H1.income_bracket = '" + selectedIncome2 + "' AND H1.indigenous_status LIKE '%other%' AND H2.indigenous_status LIKE '%other%' AND H1.lga_code = H2.lga_code GROUP BY H1.lga_Code) ";
            query += "AS hh ON hh.lga_code = H1.lga_code LEFT OUTER JOIN HouseholdStatistics H2 WHERE H1.LGA_year = '2016' AND H2.LGA_year = '2016' AND H1.income_bracket = '" + selectedIncome2 + "' AND H1.indigenous_status LIKE '%indig%' AND H2.indigenous_status LIKE '%indig%' AND H1.lga_code = H2.lga_code ";
            query += "GROUP BY H1.lga_Code) AS sort) AS B ON B.code_inc16 = A.code_inc21) AS A JOIN (SELECT A.code, A.name, A.area, A.propIndig_sch21, A.propNon_sch21, A.gap_sch21, B.propIndig_sch16, B.propNon_sch16, B.gap_sch16, ";
            query += "printf('%.0f', ((A.gap_sch21 - B.gap_sch16)*1.0/B.gap_sch16)*100) AS change_sch FROM (SELECT sort.code AS code, sort.name AS name, sort.area AS area, sort.indig AS indig_sch21, sort.nonindig AS nonindig_sch21, sort.total AS total_sch21, sort.propIndig AS propIndig_sch21, ";
            query += "sort.propNon AS propNon_sch21, (printf('%.0f', sort.gap) *100) AS gap_sch21 FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, L.area_sqkm AS area, (Ef1.count + Ef2.count) AS indig, ed.tindig AS tindig, s.nonindig AS nonindig, ";
            query += "s.tnon AS tnon, ss.notstated AS nonstated, (Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, ( ( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100 AS propIndig, ((s.nonindig * 1.0 / s.tnon)*100) AS propNon, ( ( ( (Ef1.count + Ef2.count) * 100) / ed.tindig) - (s.nonindig * 100 / s.tnon) ) AS gap ";
            query += "FROM EducationStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name, LGA.area_sqkm FROM LGA WHERE LGA.lga_year = '2021') AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ed.tnotstated FROM EducationStatistics AS Ef1 ";
            query += "LEFT OUTER JOIN EducationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnotstated FROM EducationStatistics AS E WHERE E.indigenous_status = 'indig_stat_notstated' AND E.lga_year = '2021' GROUP BY E.lga_code) ";
            query += "AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.highest_school_year = '" + selectedSchool2 + "' AND Ef2.highest_school_year = '" + selectedSchool2 + "' AND Ef1.indigenous_status = 'indig_stat_notstated' AND Ef2.indigenous_status = 'indig_stat_notstated' AND Ef1.sex = 'f' AND ";
            query += "Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code LEFT OUTER JOIN EducationStatistics AS Ef2 JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon FROM EducationStatistics AS Ef1 LEFT OUTER JOIN EducationStatistics AS Ef2 ";
            query += "JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnon FROM EducationStatistics AS E WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND ";
            query += "Ef1.highest_school_year = '" + selectedSchool2 + "' AND Ef2.highest_school_year = '" + selectedSchool2 + "' AND Ef1.indigenous_status = 'non_indig' AND Ef2.indigenous_status = 'non_indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code JOIN (SELECT E.lga_code AS code, ";
            query += "SUM(E.count) AS tindig FROM EducationStatistics AS E WHERE E.indigenous_status = 'indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.highest_school_year = '" + selectedSchool2 + "' AND Ef2.highest_school_year = '" + selectedSchool2 + "' AND ";
            query += "Ef1.indigenous_status = 'indig' AND Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort) AS A JOIN (SELECT sort.code AS code, sort.name AS name, sort.area AS area, ";
            query += "sort.indig AS indig_sch16, sort.nonindig AS nonindig_sch16, sort.total AS total_sch16, sort.propIndig AS propIndig_sch16, sort.propNon AS propNon_sch16, ((printf('%.0f', sort.gap) *100)) AS gap_sch16 FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, L.area_sqkm AS area, (Ef1.count + Ef2.count) AS indig, ";
            query += "ed.tindig AS tindig, s.nonindig AS nonindig, s.tnon AS tnon, ss.notstated AS nonstated, (Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, ( (( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100) AS propIndig, ((s.nonindig *1.0/ s.tnon)*100) AS propNon, ";
            query += "(( ( ( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100) - ((s.nonindig * 1.0 / s.tnon)*100 )) AS gap FROM EducationStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name, LGA.area_sqkm FROM LGA WHERE LGA.lga_year = '2016') AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ";
            query += "ed.tnotstated FROM EducationStatistics AS Ef1 LEFT OUTER JOIN EducationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnotstated FROM EducationStatistics AS E WHERE E.indigenous_status = 'indig_stat_notstated' AND E.lga_year = '2016' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code ";
            query += "WHERE Ef1.lga_year = '2016' AND Ef2.lga_year = '2016' AND Ef1.highest_school_year = '" + selectedSchool2 + "' AND Ef2.highest_school_year = '" + selectedSchool2 + "' AND Ef1.indigenous_status = 'indig_stat_notstated' AND Ef2.indigenous_status = 'indig_stat_notstated' AND ";
            query += "Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code LEFT OUTER JOIN EducationStatistics AS Ef2 JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon FROM EducationStatistics AS Ef1 LEFT OUTER JOIN ";
            query += "EducationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnon FROM EducationStatistics AS E WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2016' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2016' AND ";
            query += "Ef2.lga_year = '2016' AND Ef1.highest_school_year = '" + selectedSchool2 + "' AND Ef2.highest_school_year = '" + selectedSchool2 + "' AND Ef1.indigenous_status = 'non_indig' AND Ef2.indigenous_status = 'non_indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code ";
            query += "GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tindig FROM EducationStatistics AS E WHERE E.indigenous_status = 'indig' AND E.lga_year = '2016' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2016' AND ";
            query += "Ef2.lga_year = '2016' AND Ef1.highest_school_year = '" + selectedSchool2 + "' AND Ef2.highest_school_year = '" + selectedSchool2 + "' AND Ef1.indigenous_status = 'indig' AND Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) ";
            query += "AS sort) AS B ON B.code = A.code) AS B ON A.code = B.code) AS A JOIN (SELECT A.code, A.name, A.area, A.propIndig_age21, A.propNon_age21, A.gap_age21, B.propIndig_age16, B.propNon_age16, B.gap_age16, (printf('%.0f',(((A.gap_age21 - B.gap_age16 * 1.0)/B.gap_age16)*100))) AS change_age ";
            query += "FROM (SELECT sort.code AS code, sort.name AS name, sort.area AS area, sort.indig AS indig_Age21, sort.nonindig AS nonindig_Age21, sort.total AS total_Age21, sort.propIndig AS propIndig_Age21, sort.propNon AS propNon_Age21, (printf('%.0f', sort.gap)*100) AS gap_Age21 ";
            query += "FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, L.area_sqkm AS area, (Ef1.count + Ef2.count) AS indig, ed.tindig AS tindig, s.nonindig AS nonindig, s.tnon AS tnon, ss.notstated AS nonstated, (Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, (( ( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100) AS propIndig, ";
            query += "((s.nonindig * 1.0 / s.tnon)*100) AS propNon, ( (( ( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100) - (s.nonindig * 100 / s.tnon) ) AS gap FROM PopulationStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name, LGA.area_sqkm ";
            query += "FROM LGA WHERE LGA.lga_year = '2021') AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ed.tnotstated FROM PopulationStatistics AS Ef1 LEFT OUTER JOIN PopulationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnotstated ";
            query += "FROM PopulationStatistics AS E WHERE E.indigenous_status = 'indig_ns' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.age = '" + selectedAge2 + "' AND ";
            query += "Ef2.age = '" + selectedAge2 + "' AND Ef1.indigenous_status = 'indig_ns' AND Ef2.indigenous_status = 'indig_ns' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code LEFT OUTER JOIN ";
            query += "PopulationStatistics AS Ef2 JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon FROM PopulationStatistics AS Ef1 LEFT OUTER JOIN PopulationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnon FROM PopulationStatistics AS E ";
            query += "WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.age = '" + selectedAge2 + "' AND Ef2.age = '" + selectedAge2 + "' AND Ef1.indigenous_status = 'non_indig' AND ";
            query += "Ef2.indigenous_status = 'non_indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tindig FROM PopulationStatistics AS E ";
            query += "WHERE E.indigenous_status = 'indig' AND E.lga_year = '2021' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2021' AND Ef2.lga_year = '2021' AND Ef1.age = '" + selectedAge2 + "' AND Ef2.age = '" + selectedAge2 + "' AND Ef1.indigenous_status = 'indig' AND ";
            query += "Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort) AS A JOIN (SELECT sort.code AS code, sort.name AS name, sort.area AS area, sort.indig AS indig_Age16, sort.nonindig AS nonindig_Age16, ";
            query += "sort.total AS total_Age16, sort.propIndig AS propIndig_Age16, sort.propNon AS propNon_Age16, (printf('%.0f', sort.gap)*100) AS gap_Age16 FROM (SELECT Ef1.lga_code AS code, L.lga_name AS name, L.area_sqkm AS area, (Ef1.count + Ef2.count) AS indig, ed.tindig AS tindig, ";
            query += "s.nonindig AS nonindig, s.tnon AS tnon, ss.notstated AS nonstated, (Ef1.count + Ef2.count + s.nonindig + ss.notstated) AS total, (( ( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100) AS propIndig, ((s.nonindig * 1.0 / s.tnon)*100) AS propNon, ";
            query += "( (( ( (Ef1.count + Ef2.count) * 1.0) / ed.tindig)*100) - (s.nonindig * 100 / s.tnon) ) AS gap FROM PopulationStatistics AS Ef1 JOIN (SELECT LGA.lga_code, LGA.lga_name, LGA.area_sqkm FROM LGA WHERE LGA.lga_year = '2016') ";
            query += "AS L ON L.lga_code = Ef1.lga_code JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS notstated, ed.tnotstated FROM PopulationStatistics AS Ef1 LEFT OUTER JOIN PopulationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, ";
            query += "SUM(E.count) AS tnotstated FROM PopulationStatistics AS E WHERE E.indigenous_status = 'indig_ns' AND E.lga_year = '2016' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2016' AND Ef2.lga_year = '2016' AND Ef1.age = '" + selectedAge2 + "' AND ";
            query += "Ef2.age = '" + selectedAge2 + "' AND Ef1.indigenous_status = 'indig_ns' AND Ef2.indigenous_status = 'indig_ns' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS ss ON ss.code = Ef1.lga_code LEFT OUTER JOIN PopulationStatistics AS Ef2 ";
            query += "JOIN (SELECT Ef1.lga_code AS code, (Ef1.count + Ef2.count) AS nonindig, ed.tnon AS tnon FROM PopulationStatistics AS Ef1 LEFT OUTER JOIN PopulationStatistics AS Ef2 JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tnon FROM PopulationStatistics AS E ";
            query += "WHERE E.indigenous_status = 'non_indig' AND E.lga_year = '2016' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2016' AND Ef2.lga_year = '2016' AND Ef1.age = '" + selectedAge2 + "' AND Ef2.age = '" + selectedAge2 + "' AND Ef1.indigenous_status = 'non_indig' AND ";
            query += "Ef2.indigenous_status = 'non_indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS s ON s.code = Ef1.lga_code JOIN (SELECT E.lga_code AS code, SUM(E.count) AS tindig FROM PopulationStatistics AS E ";
            query += "WHERE E.indigenous_status = 'indig' AND E.lga_year = '2016' GROUP BY E.lga_code) AS ed ON ed.code = EF1.lga_code WHERE Ef1.lga_year = '2016' AND Ef2.lga_year = '2016' AND Ef1.age = '" + selectedAge2 + "' AND Ef2.age = '" + selectedAge2 + "' AND Ef1.indigenous_status = 'indig' AND ";
            query += "Ef2.indigenous_status = 'indig' AND Ef1.sex = 'f' AND Ef2.sex = 'm' AND Ef1.lga_code = Ef2.lga_code GROUP BY Ef1.lga_code) AS sort) AS B ON A.code = B.code) AS B ON A.code = B.code AND A.area >= " + sqkmMin + " AND A.area <= " + sqkmMax + ") AS sort ";
            query += "ORDER BY " + sort2 + " " + order + ";";



            System.out.println(query);

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                String code = String.valueOf(results.getString("code"));
                String name = results.getString("name");
                String area = results.getString("area");
                String propIndig_ISA = results.getString("propIndig_ISA");

                String propIndig_IS = results.getString("propIndig_IS");
                String propIndig_IA = results.getString("propIndig_IA");
                String propIndig_SA = results.getString("propIndig_SA");

                String propIndig_I = results.getString("propIndig_I");
                String propIndig_S = results.getString("propIndig_S");
                String propIndig_A = results.getString("propIndig_A");

                String gap_inc21 = results.getString("gap_inc21");
                String gap_inc16 = results.getString("gap_inc16");
                String change_inc = results.getString("change_inc");

                String gap_sch21 = results.getString("gap_sch21");
                String gap_sch16 = results.getString("gap_sch16");
                String change_sch = results.getString("change_sch");

                String gap_age21 = results.getString("gap_age21");
                String gap_age16 = results.getString("gap_age16");
                String change_age = results.getString("change_age");

                // create object for table class
                Table2 table31 = new Table2(code, name, area, propIndig_ISA, propIndig_IS, propIndig_IA, propIndig_SA, propIndig_I, propIndig_S, propIndig_A, gap_inc21, gap_inc16, change_inc, gap_sch21, gap_sch16, change_sch, gap_age21, gap_age16, change_age);

                data31.add(table31);
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
        return data31;
    }


} // Keep as last bracket
