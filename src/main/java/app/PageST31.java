package app;

import java.util.ArrayList;

//import java.util.HashMap;
//import java.util.Map;

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
public class PageST31 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page5.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageST31.html");

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        
        // The model of data to provide to Thymeleaf.
       //Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        //model.put("title", new String("Gap Score Between Indigenous and Non-Indigenous"));

         // Look up some information from JDBC
        // First, create new connection to JDBC
        //JDBCConnection jdbc = new JDBCConnection();

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
       // context.render(TEMPLATE, model);
        
         
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 3.1</title>";

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
                <h1>Compare Gap Scores Between Indigenous and Non-indigenous over the Census Years</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

         // Add instructions on how to use the filters
         html = html + """
            <h2>Compare the scores for different Local Government Areas over different census years</h2>
            <p>Select a condition, filter by the area, sort the gap scores in ASC (ascending) or DESC (descending) order and or by the average proportional indigenous population.</p>
            <div class='info-L3-wrap'>
            <h4 class='info-L3'>A <i class='selectionBlue'>'+' gap score </i>indicates: portion of indigenous population is greater than the portion of nonindigenous population for a given LGA </h4>
            <h4 class='info-L3'>A <i class='selectionBlue'>'-' gap score </i>indicates: portion of indigenous population is less than the portion of nonindigenous population for a given LGA</h4>
            <h4 class='info-L3'>A <i class='selectionBlue'>'0' gap score </i> indicates: the gap between the communities is small for a given LGA</h4>
            <h4 class='info-L3'>A <i class='selectionBlue'>'+%' change score</i> indicates: portion of indigenous population increased in 2021 when comparing with 2016.</h4> 
            <h4 class='info-L3'>A <i class='selectionBlue'>'-%' change score</i> indicates: portion of indigenous population has decreased in 2021 when comparing with 2016.</h4>
            <h4 class='info-L3'>A <i class='selectionBlue'>'0%' change score</i> indicates: there was no change in the indigenous population in 2021 when comparing with 2016.</h4>
            </div>
        """;

        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        html = html + " <div class='filter-sidebar'>";
        html = html + "<form action='/page3.html' method='post'>";

        html = html + """
            <fieldset>
                <legend>Select one ore more</legend>
                        """;
            
            html = html + """
                <div class='form-group'> 
                    <label for='age_drop'>Select the Age Range:</label></br>
                        <select id='age_drop' name='age_drop'>
                        <option>select</option>
                        """;
                        ArrayList<String> ageNum = jdbc.getAge(); 
    
                        for (String anything : ageNum) {
                            html = html + "<option>" + anything + "</option>";
                        }
    
                        html = html + "      </select>";
                        html = html + "   </div>";
                    
            html = html + """
                <div class='form-group'> 
                    <label for='school_drop'>Select the Highest Year of Schooling:</label></br>
                        <select id='school_drop' name='school_drop'>
                        <option>select</option>
                        """;
                         ArrayList<String> schoolingYears = jdbc.getSchooling(); 
    
                        for (String schooled : schoolingYears) {
                            html = html + "<option>" + schooled + "</option>";
                        }
    
                        html = html + "      </select>";
                        html = html + "   </div>";
                    
            html = html + """        
                <div class='form-group'> 
                    <label for='income_drop'>Select the Weekly Household Income Bracket:</label></br>
                        <select id='income_drop' name='income_drop'>
                        <option>select</option>
                        """;
                        ArrayList<String> incomeBrackets = jdbc.getHousehold(); 
    
                        for (String income : incomeBrackets) {
                            html = html + "<option>" + income + "</option>";
                        }
                           
                            
                        html = html + "      </select>";
                        html = html + "   </div>";
            html = html + "</fieldset>";

            

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

}
