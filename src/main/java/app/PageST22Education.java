package app;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */

public class PageST22Education implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page4_Education.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageST22Education.html");

    @Override
    public void handle(Context context) throws Exception {
        // The model of data to provide to Thymeleaf
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        model.put("title", new String("Changes between 2016 & 2021 Censuses"));

        // Create connection to JDBC class
        JDBCConnection jdbc = new JDBCConnection();

        // Retrieving LGA codes for dropdown list
        ArrayList<String> lgaCodes = jdbc.getLGACodes();
        model.put("lgaCodes", lgaCodes);

        // Retrieving states for dropdown list
        ArrayList<String> states = jdbc.getStates();
        model.put("states", states);

        // Retrieving age groups for dropdown list
        ArrayList<String> ageGroups = jdbc.getAgeGroups();
        model.put("ageGroups", ageGroups);

        // Retrieving highest school year brackets for dropdown list
        ArrayList<String> years = jdbc.getHighSchoolYears();
        model.put("schoolYears", years);

        // Retrieving income brackets for dropdown list
        ArrayList<String> brackets = jdbc.getIncomeBrackets();
        model.put("incomeBrackets", brackets);

        // Retrieving household indigenous statuses for dropdown list
        ArrayList<String> householdStatuses = jdbc.getHouseholdIndigenousStatuses();
        model.put("householdStatuses", householdStatuses);

        String dataset = context.formParam("dataset"); // dataset
        String locationType = context.formParam("locationType"); // LGA or state
        String location = context.formParam("location"); // location filter
        String valueType = context.formParam("valueType"); // raw or proportional
        String indigenousStatus = context.formParam("indigenousStatus");
        String sex = context.formParam("sex");
        String age = context.formParam("age");
        String highestSchoolYearCompleted = context.formParam("highestSchoolYearCompleted");
        String incomeBracket = context.formParam("incomeBracket");
        String householdIndigenousStatus = context.formParam("householdStatus");

        // System.out.println(dataset);

        model.put("dataset", dataset);
        model.put("locationType", locationType);
        model.put("location", location);
        model.put("valueType", valueType);
        model.put("indigenousStatus", indigenousStatus);
        model.put("sex", sex);
        model.put("age", age);
        model.put("highestSchoolYearCompleted", highestSchoolYearCompleted);
        model.put("incomeBracket", incomeBracket);
        model.put("householdIndigenousStatus", householdIndigenousStatus);

        // TODO: add another if-else statement to add 'Error' messages for missing input

        
        if (locationType != null & location != null & valueType != null & indigenousStatus != null & sex != null & age != null & highestSchoolYearCompleted != null) {
            model.put("titleEducationResults", new String("2016 vs. 2021 Highest Year of School Completed Data for " + location));
            model.put("titleFilterSelections", new String("Filter Selections"));

            ST22Results results = jdbc.getST22EducationResults(locationType, location, valueType, indigenousStatus, sex, highestSchoolYearCompleted);
            model.put("lgaCodeEducation", results.getLGACode());
            model.put("lgaName2016Education", results.getLGAName2016());
            model.put("lgaState2016Education", results.getLGAState2016());
            model.put("lgaType2016Education", results.getLGAType2016());
            model.put("lgaName2021Education", results.getLGAName2021());
            model.put("lgaState2021Education", results.getLGAState2021());
            model.put("lgaType2021Education", results.getLGAType2021());

            DecimalFormat df = new DecimalFormat("#.##");
            float result2016 = results.getResult2016();
            float result2021 = results.getResult2021();
            if (result2016 > 1.0) {
                model.put("results2016Education", results.getResult2016());
                model.put("results2021Education", results.getResult2021());
            } else if (result2016 < 1.0) {
                result2016 = result2016 * 100;
                result2021 = result2021 * 100;
                String result2016P = df.format(result2016);
                String result2021P = df.format(result2021);
                model.put("results2016Education", result2016P + "%");
                model.put("results2021Education", result2021P + "%");
            }
                
            model.put("ranking2016Education", results.getRank2016());
            model.put("ranking2021Education", results.getRank2021());
        } else {
            model.put("titleEducationResults", new String("No Results for Highest Year of School Completed Data"));
            model.put("titleFilterSelections", new String("No Filter Options Selected"));
        }

        if (locationType != null & location != null & valueType != null & indigenousStatus != null & sex != null & age != null) {
            model.put("titlePopulationResults", new String("2016 vs. 2021 Indigenous Status Data for " + location));
            model.put("titleFilterSelections", new String("Filter Selections"));

            ST22Results results = jdbc.getST22PopulationResults(locationType, location, valueType, indigenousStatus, sex, age);
            model.put("lgaCodePopulation", results.getLGACode());
            model.put("lgaName2016Population", results.getLGAName2016());
            model.put("lgaState2016Population", results.getLGAState2016());
            model.put("lgaType2016Population", results.getLGAType2016());
            model.put("lgaName2021Population", results.getLGAName2021());
            model.put("lgaState2021Population", results.getLGAState2021());
            model.put("lgaType2021Population", results.getLGAType2021());

            DecimalFormat df = new DecimalFormat("#.##");
            float result2016 = results.getResult2016();
            float result2021 = results.getResult2021();
            if (result2016 > 1.0) {
                model.put("results2016Population", results.getResult2016());
                model.put("results2021Population", results.getResult2021());
            } else if (result2016 < 1.0) {
                result2016 = result2016 * 100;
                result2021 = result2021 * 100;
                String result2016P = df.format(result2016);
                String result2021P = df.format(result2021);
                model.put("results2016Population", result2016P + "%");
                model.put("results2021Population", result2021P + "%");
            }

            model.put("ranking2016Population", results.getRank2016());
            model.put("ranking2021Population", results.getRank2021());
        } else {
            model.put("titlePopulationResults", new String("No Results for Indigenous Status Data"));
            model.put("titleFilterSelections", new String("No Filter Options Selected"));
        }

        if (locationType != null & location != null & valueType != null & householdIndigenousStatus != null & incomeBracket != null) {
            model.put("titleIncomeResults", new String("2016 vs. 2021 Total Weekly Household Income Data for " + location));
            model.put("titleFilterSelections", new String("Filter Selections"));
            ST22Results results = jdbc.getST22IncomeResults(locationType, location, valueType, householdIndigenousStatus, incomeBracket);
            model.put("lgaCode", results.getLGACode());
            model.put("lgaName2016Income", results.getLGAName2016());
            model.put("lgaState2016Income", results.getLGAState2016());
            model.put("lgaType2016Income", results.getLGAType2016());
            model.put("lgaName2021Income", results.getLGAName2021());
            model.put("lgaState2021Income", results.getLGAState2021());
            model.put("lgaType2021Income", results.getLGAType2021());

            DecimalFormat df = new DecimalFormat("#.##");
            float result2016 = results.getResult2016();
            float result2021 = results.getResult2021();
            if (result2016 > 1.0) {
                model.put("results2016Income", results.getResult2016());
                model.put("results2021Income", results.getResult2021());
            } else if (result2016 < 1.0) {
                result2016 = result2016 * 100;
                result2021 = result2021 * 100;
                String result2016P = df.format(result2016);
                String result2021P = df.format(result2021);
                model.put("results2016Income", result2016P + "%");
                model.put("results2021Income", result2021P + "%");
            }

            model.put("ranking2016Income", results.getRank2016());
            model.put("ranking2021Income", results.getRank2021());
        } else {
            model.put("titleIncomeResults", new String("No Results for Total Weekly Household Income Data"));
            model.put("titleFilterSelections", new String("No Filter Options Selected"));
        }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);

        /*
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.2</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                    <img class='topnav-logo' src='newlogo white.png' alt='logo' height='50'>
                    <ul>
                        <a href='/'>Home</a>
                        <a href='mission.html'>Mission Statement</a>
                        <div class ='dropdown'>
                            <button class='dropbtn'>Data Analyses
                                <i class='fa fa-caret-down'></i>
                            </button>
                            <div class='dropdown-content'>
                                <a href='page3.html'>2021 Census Results</a>
                                <a href='page4.html'>Changes between 2016 and 2021 Censuses</a>
                                <a href='page5.html'>Gap Score between Indigenous and Non-Indigenous</a>
                                <a href='page6.html'>Compare LGAs with Similar Characteristics</a>
                            </div>
                        </div>
                        <a href='page7.html'>Resources</a>
                    </ul>
                    <input type='text' placeholder='Search...'>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 2.2</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";        

        // Adding a filter sidebar
        // https://stackoverflow.com/questions/36712967/single-dropdown-with-search-box-in-it

        // Action below specifies the URL for POST
        html = html + "<form action = '/page4.html' method = 'post'>";

        html = html + """
                <div class = 'form-group'>
                <fieldset>

                    <legend>Dataset</legend>

                    <input id = 'datasetRadio' name = 'datasetRadio'
                        type = 'radio' value = 'highestSchoolYear'>
                    <label for = 'datasetRadio'>Highest Year of High School Completed</label>

                    <input id = 'datasetRadio' name = 'datasetRadio'
                        type = 'radio' value = 'indigenousStatus'>
                    <label for = 'datasetRadio'>Indigenous Status</label>

                    <input id = 'datasetRadio' name = 'datasetRadio'
                        type = 'radio' value = 'householdIncome'>
                    <label for = 'datasetRadio'>Total Weekly Household Income</label>
                
                </fieldset>
                </div>

                <button type = 'submit' class = 'btn btn-primary'>Apply Filters</button>

                </form>

                """;
        
        // TODO: add method for finding corresponding dataset

        // String datasetRadio = context.formParam("datasetRadio");
        // html = html + outputDataset(datasetRadio);
                
        html = html + """
                
                <div class = 'form-group'>
                <fieldset>

                    <legend>LGA/State</legend>

                    <input id = 'LGARadio' name = 'LGAStateList'
                           type = 'radio' value = 'LGA'>
                    <label for = 'LGARadio'>LGA</label>

                    <input id = 'stateRadio' name = 'LGAStateList'
                           type = 'radio' value = 'State'>
                    <label for = 'stateRadio'>State</label>

                    <br>
                    <br>

                    <datalist id = 'suggestions'>
                        <option>First option</option>
                        <option>Second option</option>
                    </datalist>
                    <input autoComplete = 'on' list = 'suggestions'/>

                </fieldset>
                </div>

                <div class = 'form-group'>
                <fieldset>

                    <legend>Value Type</legend>

                    <input id = 'rawRadio' name = 'valueTypeList'
                           type = 'radio' value = 'Raw'>
                    <label for = 'rawRadio'>Raw</label>

                    <input id = 'proportionalRadio' name = 'valueTypeList'
                           type = 'radio' value = 'Proportional'>
                    <label for = 'proportionalRadio'>Proportional</label>

                </fieldset>
                </div>

                """;

        // Section 1
        html = html + "<h1>Changes between 2016 and 2021 Censuses</h1>";

        html = html + "<p>On this page, you can find the difference between 2016 and 2021 data on local government areas (LGAs) or states.</p>";

        html = html + "<p>Just apply your filter options in the sidebar and click 'Apply Filters'.</p>";
        
        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the LGAs
        ArrayList<LGA> lgas = jdbc.getLGAs();

        // Add HTML for the LGA list
        html = html + "<h1>All LGAs</h1>" + "<ul>";

        // Finally we can print out all of the LGAs
        for (LGA lga : lgas) {
            html = html + "<li>" + lga.getCode() 
                        + " - " + lga.getYear()
                        + " - " + lga.getName() + "</li>";
        }

        // Finish the List HTML
        html = html + "</ul>";

        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class = 'footer'>
                <h3>
                    Acknowledgement of Country
                </h3>
                <p id = 'acknowledgementText'>
                    Group 11 acknowledge and pay respect to Elders, both past and present, and all 
                    generations of Aboriginal and Torres Strait <br> Islander People now and into the 
                    future as the Traditional Owners of this land.
                </p>
                <p id = 'teamMemberCredit'>
                    COSC2803 - Milestone 2 - This website was made by Ria Kapoor (s3973093) and Marielle Louisse Lopez (s3922406).
                </p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        
    }

    public String outputDataset(String type) {
        String html = "";
        html = html + "<h2>2016 vs. 2021 " + type + " Datasets</h2>";

        // Look up datasets from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> ST22Results = jdbc.getST22Results(); // at the moment, this will only output results for EducationStatistics

        // Add HTML for the data list
        // TODO: code this to be a table
        html = html + "<ul>";
        for (String result : ST22Results) {
            html = html + "<li>" + result.toString() + "</li>";
        }
        html = html + "</ul>";

        return html;
        */
    }

}
