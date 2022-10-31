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

public class PageST22Income implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page4_Income.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageST22Income.html");

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

        // Retrieving income brackets for dropdown list
        ArrayList<String> brackets = jdbc.getIncomeBrackets();
        model.put("incomeBrackets", brackets);

        // Retrieving household indigenous statuses for dropdown list
        ArrayList<String> householdStatuses = jdbc.getHouseholdIndigenousStatuses();
        model.put("householdStatuses", householdStatuses);

        String locationType = context.formParam("locationType"); // LGA or state
        String location = context.formParam("location"); // location filter
        String valueType = context.formParam("valueType"); // raw or proportional
        String incomeBracket = context.formParam("incomeBracket");
        String householdIndigenousStatus = context.formParam("householdStatus");

        model.put("locationType", locationType);
        model.put("location", location);
        model.put("valueType", valueType);
        model.put("incomeBracket", incomeBracket);
        model.put("householdIndigenousStatus", householdIndigenousStatus);

        // TODO: add another if-else statement to add 'Error' messages for missing input


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

    }

}
