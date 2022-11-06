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

public class PageST32 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page6.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageST32.html");

    @Override
    public void handle(Context context) throws Exception {
        // The model of data to provide to Thymeleaf
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        model.put("title", new String("Compare LGAs with Similar Characteristics"));

        // Look up some information from JDBC
        // First, create new connection to JDBC
        JDBCConnection jdbc = new JDBCConnection();

        // Ask JDBC class for target outcomes
        ArrayList<Outcome> outcome1 = jdbc.getSpecificOutcome(1);
        ArrayList<Outcome> outcome5 = jdbc.getSpecificOutcome(5);
        ArrayList<Outcome> outcome8 = jdbc.getSpecificOutcome(8);

        String outcome1description;
        String outcome5description;
        String outcome8description;

        // Put target outcomes into the model
        for (Outcome outcome : outcome1) {
            model.put("outcome1description", outcome1description = outcome.getDescription());
        }
        for (Outcome outcome : outcome5) {
            model.put("outcome5description", outcome5description = outcome.getDescription());
        }
        for (Outcome outcome : outcome8) {
            model.put("outcome8description", outcome8description = outcome.getDescription());
        }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);

    }

}
