package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageST32Income implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page6_Income.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageST32Income.html");

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

        // Retrieving income brackets for dropdown list
        ArrayList<String> minimums = jdbc.getMinimumIncomes();
        model.put("minimumIncomes", minimums);

        ArrayList<String> maximums = jdbc.getMaximumIncomes();
        model.put("maximumIncomes", maximums);

        // Retrieving household indigenous statuses for dropdown list
        ArrayList<String> householdStatuses = jdbc.getHouseholdIndigenousStatuses();
        model.put("householdStatuses", householdStatuses);

        // Retrieving user's filter selections
        String location = context.formParam("location"); // location filter
        String minIncome = context.formParam("minIncome");
        String maxIncome = context.formParam("maxIncome");
        String householdStatus = context.formParam("householdStatus");
        String limit = context.formParam("limit");

        // Inserting HTML
        if (location == null && householdStatus == null && minIncome == null && maxIncome == null && limit == null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<div class = 'results-section'>";
            html = html + "<h1>No Results for Total Weekly Household Income Data</h1>";
            html = html + "<p>Please make a selection for <b>each</b> filter option.</p>";
            html = html
                    + "<a href = '/page6.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'results-section' closing tag
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);

        } else if (location == null || householdStatus == null || minIncome == null || maxIncome == null || limit == null || 
                   location.equalsIgnoreCase("") || householdStatus.equalsIgnoreCase("") || 
                   minIncome.equalsIgnoreCase("") || maxIncome.equalsIgnoreCase("") || limit.equalsIgnoreCase("")) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<div class = 'results-section'>";
            html = html + "<h1>No Results for Total Weekly Household Income Data</h1>";
            html = html + "<p style = 'color: #CA3732'><b>Not all required filter selections were made.</b></p>";
            html = html
                    + "<a href = '/page6.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'results-section' closing tag
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);

        } else if (location != null && householdStatus != null && minIncome != null && maxIncome != null && limit != null) {
            String html = "<div class = 'introduction-results-wrapper'>";
            html = html + "<h1>Top " + limit + " Most Similar LGAs to " + location + " in Total Weekly Household Income Dataset</h1>";
            html = html + "<h3>Filter Option Selections</h3>";
            html = html + "<div class = 'filter-selections-wrapper'>";
            html = html + "<div class = 'filter-selections'>";
            html = html + "<p><b>Location</b><br><i>" + location + "</i></p>";
            html = html + "<p><b>Number of Results</b><br><i>" + limit + "</i></p>";
            html = html + "<p><b>Household Indigenous Status</b><br><i>" + householdStatus + "</i></p>";
            html = html + "<p><b>Minimum Income</b><br><i>$" + minIncome + "</i></p>";
            html = html + "<p><b>Maximum Income</b><br><i>$" + maxIncome + "</i></p>";
            html = html + "</div>"; // 'filter-selection' closing tag
            html = html + "</div>"; // 'filter-selection-wrapper' closing tag

            ArrayList<ST32Results> results = jdbc.getST32IncomeResults(location, householdStatus, minIncome, maxIncome, String.valueOf(limit));

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
            html = html + "<a href = '/page6_Income.html'><button class = 'reset-button'>Reset Filters</button></a>";
            html = html
                    + "<a href = '/page6.html'><button class = 'go-back-button'>Go Back to Dataset List</button></a>";
            html = html + "</div>"; // 'introduction-results-wrapper' closing tag

            model.put("htmlToInject", html);
        }
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);

    }

}
