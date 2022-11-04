package app;

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

public class PageST32Education implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page6_Education.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageST32Education.html");

    @Override
    public void handle(Context context) throws Exception {
        // The model of data to provide to Thymeleaf
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        model.put("title", new String("Compare LGAs with Similar Characteristics"));

        // Create connection to JDBC class
        JDBCConnection jdbc = new JDBCConnection();

        // Retrieving LGA codes for dropdown list
        ArrayList<String> lgaCodes = jdbc.get2021LGAs();
        model.put("lgaCodes", lgaCodes);

        // Retrieving highest school year brackets for dropdown list
        ArrayList<String> years = jdbc.getHighSchoolYears();
        model.put("schoolYears", years);

        // Retrieving user's filter selections
        String location = context.formParam("location"); // location filter
        String indigenousStatus = context.formParam("indigenousStatus");
        String sex = context.formParam("sex");
        String schoolYear = context.formParam("highestSchoolYearCompleted");
        String limit = context.formParam("limit");

        // Inserting HTML 
       if (location == null && indigenousStatus == null && sex == null && schoolYear == null && limit == null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<div class = 'results-section'>";
            html = html + "<h1>No Results for Highest School Year Completed Data</h1>";
            html = html + "<p>Please make a selection for <b>each</b> filter option.</p>";
            html = html + "<a href = '/page6.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'results-section' closing tag
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);

       } else if (location == null || indigenousStatus == null & sex == null || schoolYear == null || limit == null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<div class = 'results-section'>";
            html = html + "<h1>No Results for Highest School Year Completed Data</h1>";
            html = html + "<p style = 'color: #CA3732'><b>Not all required filter selections were made.</b></p>";
            html = html + "<a href = '/page6.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'results-section' closing tag
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);

       } else if (location != null && indigenousStatus != null && sex != null && schoolYear != null && limit != null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<h1>Top " + limit + " Most Similar LGAs to " + location + "</h1>";
            html = html + "<h3>Filter Option Selections</h3>";
            html = html + "<div class = 'filter-selections-wrapper'>";
            html = html + "<div class = 'filter-selections'>";
            html = html + "<p><b>Location</b><br><i>"+ location +"</i></p>";
            html = html + "<p><b>Number of Results</b><br><i>"+ limit +"</i></p>";
            html = html + "<p><b>Indigenous Status</b><br><i>"+ indigenousStatus +"</i></p>";
            html = html + "<p><b>Sex</b><br><i>"+ sex +"</i></p>";
            html = html + "<p><b>Highest School Year Completed</b><br><i>"+ schoolYear +"</i></p>";
            html = html + "</div>"; // 'filter-selection' closing tag
            html = html + "</div>"; // 'filter-selection-wrapper' closing tag

            ArrayList<ST32Results> results = jdbc.getST32EducationResults(location, indigenousStatus, sex, schoolYear, String.valueOf(limit));

            html = html + "<div class = 'results-table'>";
            html = html + "<table>";
            html = html + "<tr>";
            html = html + "<th>LGA Code</th>";
            html = html + "<th>LGA Name</th>";
            html = html + "<th>Result</th>";
            html = html + "<th>Proportion</th>";
            html = html + "</tr>";

            for (ST32Results result : results) {
                int dataCode = result.getLGACode();
                if (dataCode == Integer.parseInt(location)) {
                    html = html + "<tr>";
                    html = html + "<td><u>" + dataCode + "</u></td>";
                    html = html + "<td><u>" + result.getLGAName() + "</u></td>";
                    html = html + "<td><u>" + result.getResult() + "</u></td>";
                    html = html + "<td><u>" + result.getProportion() + "</u></td>";
                    html = html + "</tr>";
                } else {
                    html = html + "<tr>";
                    html = html + "<td>" + dataCode + "</td>";
                    html = html + "<td>" + result.getLGAName() + "</td>";
                    html = html + "<td>" + result.getResult() + "</td>";
                    html = html + "<td>" + result.getProportion() + "</td>";
                    html = html + "</tr>";
                }    
            }
            
            html = html + "</table>";
            html = html + "</div>"; // 'results-table' closing tag
            html = html + "<a href = '/page6_Education.html'><button class = 'reset-button'>Reset Filters</button></a>";
            html = html + "<a href = '/page6.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);
       }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);
    }
}
