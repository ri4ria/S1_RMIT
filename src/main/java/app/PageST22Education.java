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

        // Retrieving highest school year brackets for dropdown list
        ArrayList<String> years = jdbc.getHighSchoolYears();
        model.put("schoolYears", years);

        // Retrieving user's filter selections
        String locationType = context.formParam("locationType"); // LGA or state
        String location = context.formParam("location"); // location filter
        String valueType = context.formParam("valueType"); // raw or proportional
        String indigenousStatus = context.formParam("indigenousStatus");
        String sex = context.formParam("sex");
        String highestSchoolYearCompleted = context.formParam("highestSchoolYearCompleted");

        // Inserting HTML
        if (locationType == null && location == null && valueType == null && indigenousStatus == null && sex == null
                && highestSchoolYearCompleted == null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<div class = 'results-section'>";
            html = html + "<h1>No Results for Highest Year of School Completed Data</h1>";
            html = html + "<p>Please make a selection for <b>each</b> filter option.</p>";
            html = html + "<a href = '/page4.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'results-section' closing tag
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);

        } else if (locationType == null || location == null || valueType == null || indigenousStatus == null
                || sex == null || highestSchoolYearCompleted == null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<div class = 'results-section'>";
            html = html + "<h1>No Results for Highest Year of School Completed Data</h1>";
            html = html + "<p style = 'color: #CA3732'><b>Not all required filter selections were made.</b></p>";
            html = html + "<a href = '/page4.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'results-section' closing tag
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);

        } else if (locationType != null && location != null && valueType != null && indigenousStatus != null
                && sex != null && highestSchoolYearCompleted != null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<h1>2016 vs 2021 Data for Highest Year of School Completed Data</h1>";
            html = html + "<p>Ranks are based on proportional values. For example, an LGA with the highest proportion of 65 year old indigenous males will be ranked 1.</p>";
            html = html + "<h3>Filter Option Selections</h3>";
            html = html + "<div class = 'filter-selections-wrapper'>";
            html = html + "<div class = 'filter-selections'>";
            html = html + "<p><b>Location</b><br><i>" + location + "</i></p>";
            html = html + "<p><b>Value Type</b><br><i>" + valueType + "</i></p>";
            html = html + "<p><b>Indigenous Status</b><br><i>" + indigenousStatus + "</i></p>";
            html = html + "<p><b>Sex</b><br><i>" + sex + "</i></p>";
            html = html + "<p><b>Highest School Year Completed</b><br><i>" + highestSchoolYearCompleted + "</i></p>";
            html = html + "</div>"; // 'filter-selection' closing tag
            html = html + "</div>"; // 'filter-selection-wrapper' closing tag

            ST22Results results = jdbc.getST22EducationResults(locationType, location, valueType, indigenousStatus,
                    sex, highestSchoolYearCompleted);

            // Processing proportional values
            DecimalFormat df = new DecimalFormat("#.##");
            float result2016 = results.getResult2016();
            float result2021 = results.getResult2021();
            String result2016F = df.format(result2016);
            String result2021F = df.format(result2021);

            if (result2016 < 1.0) {
                result2016 = result2016 * 100;
                result2021 = result2021 * 100;
                result2016F = df.format(result2016) + "%";
                result2021F = df.format(result2021) + "%";
            } else if (result2016 > 1.0) {
                result2016F = String.valueOf(Math.round(result2016));
                result2021F = String.valueOf(Math.round(result2021));
            }

            html = html + "<div class = 'results-table'>";
            html = html + "<table>";
            html = html + "<tr>";
            html = html + "<th>Year</th>";
            html = html + "<th>LGA Name</th>";
            html = html + "<th>State</th>";
            html = html + "<th>LGA Type</th>";
            html = html + "<th>Result</th>";
            html = html + "<th>Rank</th>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<td>2016</td>";
            html = html + "<td>" + results.getLGAName2016() + "</td>";
            html = html + "<td>" + results.getLGAState2016() + "</td>";
            html = html + "<td>" + results.getLGAType2016() + "</td>";
            html = html + "<td>" + result2016F + "</td>";
            html = html + "<td>" + results.getRank2016() + "</td>";
            html = html + "</tr>";
            html = html + "<tr>";
            html = html + "<td>2021</td>";
            html = html + "<td>" + results.getLGAName2021() + "</td>";
            html = html + "<td>" + results.getLGAState2021() + "</td>";
            html = html + "<td>" + results.getLGAType2021() + "</td>";
            html = html + "<td>" + result2021F + "</td>";
            html = html + "<td>" + results.getRank2021() + "</td>";
            html = html + "</tr>";
            html = html + "</table>";
            html = html + "</div>"; // 'results-table' closing tag
            html = html + "<a href = '/page4_Education.html'><button class = 'reset-button'>Reset Filters</button></a>";
            html = html + "<a href = '/page4.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);
        }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);
    }
}
