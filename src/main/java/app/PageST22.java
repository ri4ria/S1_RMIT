package app;

import java.util.ArrayList;

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
public class PageST22 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {
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

        String datasetRadio = context.formParam("datasetRadio");
        html = html + outputDataset(datasetRadio);
                
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
        
        /* // Look up some information from JDBC
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
        html = html + "</ul>"; */

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
    }

}
