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
                int[] years = { 2016, 2021 };

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                    /*
                    String query = "SELECT * ";
                    query += "FROM ES2016VS2021 ";
                    query += "WHERE (`2016 LGA Code` = " + location + " OR `2021 LGA Code` = " + location + ") AND ";
                    query += "(`2016 Indigenous Status` = '" + indigenousStatus + "' OR `2021 Indigenous Status` = '"
                            + indigenousStatus + "') AND ";
                    query += "(`2016 Sex` = '" + sex + "' OR `2021 Sex` = '" + sex + "') AND ";
                    query += "(`2016 Highest School Year Completed` = '" + highestSchoolYear
                            + "' OR `2021 Highest School Year Completed` = '" + highestSchoolYear + "');";
                    */

                    // Create connection to JDBC class
                    JDBCConnection jdbc = new JDBCConnection();

                    ArrayList<String> lgaCodes = jdbc.getLGACodes();
                    float[] lgaProportions = new float[lgaCodes.size()];
                    float proportionTracker;

                    for (int y = 0; y < years.length; y++) {

                        // Query to find selected LGA results
                        String query1 = "SELECT *, ";
                        query1 += "(SELECT SUM(count) ";
                        query1 += "FROM ES" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "') AS 'SUM(count)', ";
                        query1 += "SUM(1.0 * count / (SELECT SUM(count) ";
                        query1 += "FROM ES" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "')) AS 'proportion' ";
                        query1 += "FROM ES" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "' AND ";
                        query1 += "highest_school_year = '" + highestSchoolYear + "';";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            if (years[y] == 2016) {
                                ST22EducationResults.setLGAName2016(results.getString("lga_name"));
                                ST22EducationResults.setLGAState2016(results.getString("state"));
                                ST22EducationResults.setLGAType2016(results.getString("lga_type"));
                                ST22EducationResults.setResult2016(results.getInt("count"));
                                lgaProportions[0] = results.getFloat("proportion");
                            } else if (years[y] == 2021) {
                                ST22EducationResults.setLGAName2021(results.getString("lga_name"));
                                ST22EducationResults.setLGAState2021(results.getString("state"));
                                ST22EducationResults.setLGAType2021(results.getString("lga_type"));
                                ST22EducationResults.setResult2021(results.getInt("count"));
                                lgaProportions[0] = results.getFloat("proportion");
                            }
                        }

                        proportionTracker = lgaProportions[0];

                        for (int i = 1; i < lgaCodes.size(); i++) {
                            String query2 = "SELECT *, ";
                            query2 += "(SELECT SUM(count) ";
                            query2 += "FROM ES" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "') AS 'SUM(count)', ";
                            query2 += "SUM(1.0 * count / (SELECT SUM(count) ";
                            query2 += "FROM ES" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "')) AS 'proportion' ";
                            query2 += "FROM ES" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "' AND ";
                            query2 += "highest_school_year = '" + highestSchoolYear + "';";

                            System.out.println(query2);

                            // Get Result
                            ResultSet results2 = statement.executeQuery(query2);

                            // Adding proportion result of lgaCode
                            while (results2.next()) {
                                lgaProportions[i] = results2.getFloat("proportion");
                            }
                        }

                        // Sorting proportion results in descending order
                        for (int i = 0; i < lgaProportions.length; i++) {
                            for (int j = i + 1; j < lgaProportions.length; j++) {
                                float temp = 0;
                                if (lgaProportions[j] > lgaProportions[i]) {
                                    temp = lgaProportions[j];
                                    lgaProportions[j] = lgaProportions[i];
                                    lgaProportions[i] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22EducationResults.setRank2016(i);
                                }
                            }
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22EducationResults.setRank2021(i);
                                }
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
            int[] years = { 2016, 2021 };

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                    /*
                    String query = "SELECT * ";
                    query += "FROM ES2016VS2021 ";
                    query += "WHERE (`2016 LGA Code` = " + location + " OR `2021 LGA Code` = " + location + ") AND ";
                    query += "(`2016 Indigenous Status` = '" + indigenousStatus + "' OR `2021 Indigenous Status` = '"
                            + indigenousStatus + "') AND ";
                    query += "(`2016 Sex` = '" + sex + "' OR `2021 Sex` = '" + sex + "') AND ";
                    query += "(`2016 Highest School Year Completed` = '" + highestSchoolYear
                            + "' OR `2021 Highest School Year Completed` = '" + highestSchoolYear + "');";
                    */

                    // Create connection to JDBC class
                    JDBCConnection jdbc = new JDBCConnection();

                    ArrayList<String> lgaCodes = jdbc.getLGACodes();
                    float[] lgaProportions = new float[lgaCodes.size()];
                    float proportionTracker;

                    for (int y = 0; y < years.length; y++) {

                        // Query to find selected LGA results
                        String query1 = "SELECT *, ";
                        query1 += "(SELECT SUM(count) ";
                        query1 += "FROM ES" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "') AS 'SUM(count)', ";
                        query1 += "SUM(1.0 * count / (SELECT SUM(count) ";
                        query1 += "FROM ES" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "')) AS 'proportion' ";
                        query1 += "FROM ES" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "' AND ";
                        query1 += "highest_school_year = '" + highestSchoolYear + "';";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            if (years[y] == 2016) {
                                ST22EducationResults.setLGAName2016(results.getString("lga_name"));
                                ST22EducationResults.setLGAState2016(results.getString("state"));
                                ST22EducationResults.setLGAType2016(results.getString("lga_type"));
                                ST22EducationResults.setResult2016(results.getFloat("proportion"));
                                lgaProportions[0] = results.getFloat("proportion");
                            } else if (years[y] == 2021) {
                                ST22EducationResults.setLGAName2021(results.getString("lga_name"));
                                ST22EducationResults.setLGAState2021(results.getString("state"));
                                ST22EducationResults.setLGAType2021(results.getString("lga_type"));
                                ST22EducationResults.setResult2021(results.getFloat("proportion"));
                                lgaProportions[0] = results.getFloat("proportion");
                            }
                        }

                        proportionTracker = lgaProportions[0];

                        for (int i = 1; i < lgaCodes.size(); i++) {
                            String query2 = "SELECT *, ";
                            query2 += "(SELECT SUM(count) ";
                            query2 += "FROM ES" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "') AS 'SUM(count)', ";
                            query2 += "SUM(1.0 * count / (SELECT SUM(count) ";
                            query2 += "FROM ES" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "')) AS 'proportion' ";
                            query2 += "FROM ES" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "' AND ";
                            query2 += "highest_school_year = '" + highestSchoolYear + "';";

                            System.out.println(query2);

                            // Get Result
                            ResultSet results2 = statement.executeQuery(query2);

                            // Adding proportion result of lgaCode
                            while (results2.next()) {
                                lgaProportions[i] = results2.getFloat("proportion");
                            }
                        }

                        // Sorting proportion results in descending order
                        for (int i = 0; i < lgaProportions.length; i++) {
                            for (int j = i + 1; j < lgaProportions.length; j++) {
                                float temp = 0;
                                if (lgaProportions[j] > lgaProportions[i]) {
                                    temp = lgaProportions[j];
                                    lgaProportions[j] = lgaProportions[i];
                                    lgaProportions[i] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22EducationResults.setRank2016(i);
                                }
                            }
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22EducationResults.setRank2021(i);
                                }
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
                int[] years = { 2016, 2021 };

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                    // Create connection to JDBC class
                    JDBCConnection jdbc = new JDBCConnection();

                    ArrayList<String> lgaCodes = jdbc.getLGACodes();
                    float[] lgaProportions = new float[lgaCodes.size()];
                    float proportionTracker;

                    for (int y = 0; y < years.length; y++) {

                        // Query to find selected LGA results
                        String query1 = "SELECT *, ";
                        query1 += "(SELECT SUM(count) ";
                        query1 += "FROM PS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "') AS 'SUM(count)', ";
                        query1 += "SUM(1.0 * count / (SELECT SUM(count) ";
                        query1 += "FROM PS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "')) AS 'proportion' ";
                        query1 += "FROM PS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "' AND ";
                        query1 += "age = '" + age + "';";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            if (years[y] == 2016) {
                                ST22PopulationResults.setLGAName2016(results.getString("lga_name"));
                                ST22PopulationResults.setLGAState2016(results.getString("state"));
                                ST22PopulationResults.setLGAType2016(results.getString("lga_type"));
                                ST22PopulationResults.setResult2016(results.getInt("count"));
                                lgaProportions[0] = results.getFloat("proportion");
                            } else if (years[y] == 2021) {
                                ST22PopulationResults.setLGAName2021(results.getString("lga_name"));
                                ST22PopulationResults.setLGAState2021(results.getString("state"));
                                ST22PopulationResults.setLGAType2021(results.getString("lga_type"));
                                ST22PopulationResults.setResult2021(results.getInt("count"));
                                lgaProportions[0] = results.getFloat("proportion");
                            }
                        }

                        proportionTracker = lgaProportions[0];

                        for (int i = 1; i < lgaCodes.size(); i++) {
                            String query2 = "SELECT *, ";
                            query2 += "(SELECT SUM(count) ";
                            query2 += "FROM PS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "') AS 'SUM(count)', ";
                            query2 += "SUM(1.0 * count / (SELECT SUM(count) ";
                            query2 += "FROM PS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "')) AS 'proportion' ";
                            query2 += "FROM PS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "' AND ";
                            query2 += "age = '" + age + "';";

                            System.out.println(query2);

                            // Get Result
                            ResultSet results2 = statement.executeQuery(query2);

                            // Adding proportion result of lgaCode
                            while (results2.next()) {
                                lgaProportions[i] = results2.getFloat("proportion");
                            }
                        }

                        // Sorting proportion results in descending order
                        for (int i = 0; i < lgaProportions.length; i++) {
                            for (int j = i + 1; j < lgaProportions.length; j++) {
                                float temp = 0;
                                if (lgaProportions[j] > lgaProportions[i]) {
                                    temp = lgaProportions[j];
                                    lgaProportions[j] = lgaProportions[i];
                                    lgaProportions[i] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22PopulationResults.setRank2016(i);
                                }
                            }
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22PopulationResults.setRank2021(i);
                                }
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
            int[] years = { 2016, 2021 };

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                    // Create connection to JDBC class
                    JDBCConnection jdbc = new JDBCConnection();

                    ArrayList<String> lgaCodes = jdbc.getLGACodes();
                    float[] lgaProportions = new float[lgaCodes.size()];
                    float proportionTracker;

                    for (int y = 0; y < years.length; y++) {

                        // Query to find selected LGA results
                        String query1 = "SELECT *, ";
                        query1 += "(SELECT SUM(count) ";
                        query1 += "FROM PS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "') AS 'SUM(count)', ";
                        query1 += "SUM(1.0 * count / (SELECT SUM(count) ";
                        query1 += "FROM PS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "')) AS 'proportion' ";
                        query1 += "FROM PS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + indigenousStatus + "' AND ";
                        query1 += "sex = '" + sex + "' AND ";
                        query1 += "age = '" + age + "';";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            if (years[y] == 2016) {
                                ST22PopulationResults.setLGAName2016(results.getString("lga_name"));
                                ST22PopulationResults.setLGAState2016(results.getString("state"));
                                ST22PopulationResults.setLGAType2016(results.getString("lga_type"));
                                ST22PopulationResults.setResult2016(results.getFloat("proportion"));
                                lgaProportions[0] = results.getFloat("proportion");
                            } else if (years[y] == 2021) {
                                ST22PopulationResults.setLGAName2021(results.getString("lga_name"));
                                ST22PopulationResults.setLGAState2021(results.getString("state"));
                                ST22PopulationResults.setLGAType2021(results.getString("lga_type"));
                                ST22PopulationResults.setResult2021(results.getFloat("proportion"));
                                lgaProportions[0] = results.getFloat("proportion");
                            }
                        }

                        proportionTracker = lgaProportions[0];

                        for (int i = 1; i < lgaCodes.size(); i++) {
                            String query2 = "SELECT *, ";
                            query2 += "(SELECT SUM(count) ";
                            query2 += "FROM PS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "') AS 'SUM(count)', ";
                            query2 += "SUM(1.0 * count / (SELECT SUM(count) ";
                            query2 += "FROM PS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "')) AS 'proportion' ";
                            query2 += "FROM PS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + indigenousStatus + "' AND ";
                            query2 += "sex = '" + sex + "' AND ";
                            query2 += "age = '" + age + "';";

                            System.out.println(query2);

                            // Get Result
                            ResultSet results2 = statement.executeQuery(query2);

                            // Adding proportion result of lgaCode
                            while (results2.next()) {
                                lgaProportions[i] = results2.getFloat("proportion");
                            }
                        }

                        // Sorting proportion results in descending order
                        for (int i = 0; i < lgaProportions.length; i++) {
                            for (int j = i + 1; j < lgaProportions.length; j++) {
                                float temp = 0;
                                if (lgaProportions[j] > lgaProportions[i]) {
                                    temp = lgaProportions[j];
                                    lgaProportions[j] = lgaProportions[i];
                                    lgaProportions[i] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22PopulationResults.setRank2016(i);
                                }
                            }
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22PopulationResults.setRank2021(i);
                                }
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
                int[] years = { 2016, 2021 };

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                    // Create connection to JDBC class
                    JDBCConnection jdbc = new JDBCConnection();

                    ArrayList<String> lgaCodes = jdbc.getLGACodes();
                    float[] lgaProportions = new float[lgaCodes.size()];
                    float proportionTracker;

                    for (int y = 0; y < years.length; y++) {

                        // Query to find selected LGA results
                        String query1 = "SELECT *, ";
                        query1 += "(SELECT SUM(count) ";
                        query1 += "FROM HS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + householdIndigenousStatus + "') AS 'SUM(count)', ";
                        query1 += "SUM(1.0 * count / (SELECT SUM(count) ";
                        query1 += "FROM HS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + householdIndigenousStatus + "')) AS 'proportion' ";
                        query1 += "FROM HS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + householdIndigenousStatus + "' AND ";
                        query1 += "income_bracket = '" + incomeBracket + "'";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            if (years[y] == 2016) {
                                ST22IncomeResults.setLGAName2016(results.getString("lga_name"));
                                ST22IncomeResults.setLGAState2016(results.getString("state"));
                                ST22IncomeResults.setLGAType2016(results.getString("lga_type"));
                                ST22IncomeResults.setResult2016(results.getInt("count"));
                                lgaProportions[0] = results.getFloat("proportion");
                            } else if (years[y] == 2021) {
                                ST22IncomeResults.setLGAName2021(results.getString("lga_name"));
                                ST22IncomeResults.setLGAState2021(results.getString("state"));
                                ST22IncomeResults.setLGAType2021(results.getString("lga_type"));
                                ST22IncomeResults.setResult2021(results.getInt("count"));
                                lgaProportions[0] = results.getFloat("proportion");
                            }
                        }

                        proportionTracker = lgaProportions[0];

                        for (int i = 1; i < lgaCodes.size(); i++) {
                            String query2 = "SELECT *, ";
                            query2 += "(SELECT SUM(count) ";
                            query2 += "FROM HS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + householdIndigenousStatus + "') AS 'SUM(count)', ";
                            query2 += "SUM(1.0 * count / (SELECT SUM(count) ";
                            query2 += "FROM HS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + householdIndigenousStatus + "')) AS 'proportion' ";
                            query2 += "FROM HS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + householdIndigenousStatus + "' AND ";
                            query2 += "income_bracket = '" + incomeBracket + "'";

                            System.out.println(query2);

                            // Get Result
                            ResultSet results2 = statement.executeQuery(query2);

                            // Adding proportion result of lgaCode
                            while (results2.next()) {
                                lgaProportions[i] = results2.getFloat("proportion");
                            }
                        }

                        // Sorting proportion results in descending order
                        for (int i = 0; i < lgaProportions.length; i++) {
                            for (int j = i + 1; j < lgaProportions.length; j++) {
                                float temp = 0;
                                if (lgaProportions[j] > lgaProportions[i]) {
                                    temp = lgaProportions[j];
                                    lgaProportions[j] = lgaProportions[i];
                                    lgaProportions[i] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22IncomeResults.setRank2016(i);
                                }
                            }
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22IncomeResults.setRank2021(i);
                                }
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
            int[] years = { 2016, 2021 };

                try {
                    // Connect to JDBC data base
                    connection = DriverManager.getConnection(DATABASE);

                    // Prepare a new SQL Query & Set a timeout
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(30);

                    // Create connection to JDBC class
                    JDBCConnection jdbc = new JDBCConnection();

                    ArrayList<String> lgaCodes = jdbc.getLGACodes();
                    float[] lgaProportions = new float[lgaCodes.size()];
                    float proportionTracker;

                    for (int y = 0; y < years.length; y++) {

                        // Query to find selected LGA results
                        String query1 = "SELECT *, ";
                        query1 += "(SELECT SUM(count) ";
                        query1 += "FROM HS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + householdIndigenousStatus + "') AS 'SUM(count)', ";
                        query1 += "SUM(1.0 * count / (SELECT SUM(count) ";
                        query1 += "FROM HS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + householdIndigenousStatus + "')) AS 'proportion' ";
                        query1 += "FROM HS" + years[y] + " ";
                        query1 += "WHERE lga_code = " + location + " AND ";
                        query1 += "indigenous_status = '" + householdIndigenousStatus + "' AND ";
                        query1 += "income_bracket = '" + incomeBracket + "'";

                        System.out.println(query1);

                        // Get Result
                        ResultSet results = statement.executeQuery(query1);

                        // Process and store results
                        while (results.next()) {
                            if (years[y] == 2016) {
                                ST22IncomeResults.setLGAName2016(results.getString("lga_name"));
                                ST22IncomeResults.setLGAState2016(results.getString("state"));
                                ST22IncomeResults.setLGAType2016(results.getString("lga_type"));
                                ST22IncomeResults.setResult2016(results.getFloat("proportion"));
                                lgaProportions[0] = results.getFloat("proportion");
                            } else if (years[y] == 2021) {
                                ST22IncomeResults.setLGAName2021(results.getString("lga_name"));
                                ST22IncomeResults.setLGAState2021(results.getString("state"));
                                ST22IncomeResults.setLGAType2021(results.getString("lga_type"));
                                ST22IncomeResults.setResult2021(results.getFloat("proportion"));
                                lgaProportions[0] = results.getFloat("proportion");
                            }
                        }

                        proportionTracker = lgaProportions[0];

                        for (int i = 1; i < lgaCodes.size(); i++) {
                            String query2 = "SELECT *, ";
                            query2 += "(SELECT SUM(count) ";
                            query2 += "FROM HS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + householdIndigenousStatus + "') AS 'SUM(count)', ";
                            query2 += "SUM(1.0 * count / (SELECT SUM(count) ";
                            query2 += "FROM HS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + householdIndigenousStatus + "')) AS 'proportion' ";
                            query2 += "FROM HS" + years[y] + " ";
                            query2 += "WHERE lga_code = " + lgaCodes.get(i) + " AND ";
                            query2 += "indigenous_status = '" + householdIndigenousStatus + "' AND ";
                            query2 += "income_bracket = '" + incomeBracket + "'";

                            System.out.println(query2);

                            // Get Result
                            ResultSet results2 = statement.executeQuery(query2);

                            // Adding proportion result of lgaCode
                            while (results2.next()) {
                                lgaProportions[i] = results2.getFloat("proportion");
                            }
                        }

                        // Sorting proportion results in descending order
                        for (int i = 0; i < lgaProportions.length; i++) {
                            for (int j = i + 1; j < lgaProportions.length; j++) {
                                float temp = 0;
                                if (lgaProportions[j] > lgaProportions[i]) {
                                    temp = lgaProportions[j];
                                    lgaProportions[j] = lgaProportions[i];
                                    lgaProportions[i] = temp;
                                }
                            }
                        }

                        if (years[y] == 2016) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22IncomeResults.setRank2016(i);
                                }
                            }
                        } else if (years[y] == 2021) {
                            for (int i = 0; i < lgaProportions.length; i++) {
                                if (lgaProportions[i] == proportionTracker) {
                                    ST22IncomeResults.setRank2021(i);
                                }
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
    public ArrayList<Table> getDataByHealthCondition(String selectedCondition, String sort) {
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
    public ArrayList<Table> getDataByAge(String selectedAge, String sort) {
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
            query += "ORDER BY " + sort + ";";

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
                query += "ORDER BY " + sort + ";";

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
    public ArrayList<Table> getDataByHouse(String selectedIncome, String sort) {
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
            query += "ORDER BY " + sort + ";";

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

} // Keep as last bracket
