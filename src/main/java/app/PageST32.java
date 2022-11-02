package app;

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

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);

    }

}
