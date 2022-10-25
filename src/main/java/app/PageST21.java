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
public class PageST21 implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page3.html";

     // Name of the Thymeleaf HTML template page in the resources folder
     //private static final String TEMPLATE = ("PageST21.html");


    @Override
    public void handle(Context context) throws Exception {

        /* 
        // The model of data to provide to Thymeleaf.
        // In this example the model will remain empty
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        model.put("title", new String("2021 Census Results"));

         // Look up some information from JDBC
        // First, create new connection to JDBC
        JDBCConnection jdbc = new JDBCConnection();

        ArrayList<String> healthCond = jdbc.getHealthConditions(); 
        for (String health : healthCond) {
            System.out.print(health.toString());
        }

         // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);*/

        
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.1</title>";

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
                <h1>2021 Census Results</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add instructions on how to use the filters
        html = html + """
                <h2>Compare Local Government Area performance using the latest results from the 2021 census</h2>
        """;
        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        html = html + "<form action='/page3.html' method='post'>";
        
        html = html + """
            <div class='form-group'>
            <fieldset>
            <legend>Select a dataset:</legend>
            <!--Allow user to only select one Dataset-->
            <div class='target-outcome-wrap'>
                <div class='target-outcome'>
                    <label for='DatasetRadio1' onchange='enableSet(this)'>
                        <input type='radio' name='DatasetRadio' id='DatasetRadio1' value='1'>
                        <img src='outcomeA.png' alt='Option 1'>
                        <p><b>Data on Health Conditions</b></p>
                        <p>(Outcome 1)</p>
                    </label>
                </div> 

                <div class='target-outcome'>
                    <label for='DatasetRadio2' class='rotate'>
                        <input type='radio' name='DatasetRadio' id='DatasetRadio2' value='2'>
                        <img src='outcomeA.png' alt='Option 2'>
                        <p><b>Data on Population by Age</b></p>
                        <p>(Outcome 1)</p>
                    </label>
                </div> 
                
                <div class='target-outcome'>
                    <label class='dataset' for='DatasetRadio3'>
                        <input type='radio' name='DatasetRadio' id='DatasetRadio3' value='3'>
                        <img src='outcomeB.png' alt='Option 3'>
                        <p><b>Data on Highest Year of Schooling</b></p>
                        <p>(Outcome 5)</p>
                    </label>
                </div> 

                <div class='target-outcome'>
                    <label for='DatasetRadio4'>
                        <input type='radio' name='DatasetRadio' id='DatasetRadio4' value='4'>
                        <img src='outcomeC.png' alt='Option 4'>
                        <p><b>Data on Weekly Household Income</b></p>
                        <p>(Outcome 8)</p>
                    </label>
                </div> 
            </div>
            </fieldset>
        </div>

        <!-- Dropdown that will only be available if the relevant dataset is selected-->
        <fieldset>
            <legend>Filter the dataset</legend>
            <div class='form-group'> 
                <label for='condition_drop'>Select the Health Condition:</label></br>
                    <select id='condition_drop' name='condition_drop'>
                    """;

                    ArrayList<String> healthCond = jdbc.getHealthConditions(); 

                    for (String health : healthCond) {
                        switch (health) {
                            case "heartdisease":
                                html = html + "<option>heart disease</option>";
                                break;
                            case "kidneydisease":
                                html = html + "<option>kidney disease</option>";
                                break;
  			                case "lungcondition":
                                html = html + "<option>lung condition</option>";
                                break;
  			                case "mentalhealth":
                                html = html + "<option>mental health</option>";
                                break;
                        }
                        html = html + "<option>" + health + "</option>";
                    }

                    html = html + "      </select>";
                    html = html + "   </div>";
                
        html = html + """
            <div class='form-group'> 
                <label for='age_drop'>Select the Age Range:</label></br>
                    <select id='age_drop' name='age_drop'>
                        <option value='0to4'>0 to 4 years old</option> 
                        <option value='5to10'>5 to 10 years old</option>
                        <option value='10to15'>10 to 15 years old </option>
                        <option value='15to19'>15 to 19 years old</option> 
                        <option value='20to24'>20 to 24 years old</option> 
                        <option value='25to29'>25 to 29 years old</option> 
                        <option value='30to34'>30 to 34 years old</option> 
                        <option value='35to39'>35 to 39 years old</option>
                        <option value='40to44'>40 to 44 years old</option> 
                        <option value='45to49'>45 to 49 years old</option> 
                        <option value='50to54'>50 to 54 years old</option> 
                        <option value='55to59'>55 to 59 years old</option> 
                        <option value='60to64'>60 to 64 years old</option> 
                        <option value='65+'>65+ years old</option> 
                    </select>
            </div>
            <div class='form-group'> 
                <label for='school_drop'>Select the Highest Year of Schooling:</label></br>
                    <select id='school_drop' name='direct_drop'>

                    </select>
            </div>
            <div class='form-group'> 
                <label for='income_drop'>Select the Weekly Household Income Bracket:</label></br>
                    <select id='income_drop' name='direct_drop'>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        <option value='65+'></option>
                        

                    </select>
            </div>
        </fieldset>

        <script>
            function enableSet(answer) {
                if(answer.value == '1') {
                    document.getElementById('condition_drop').classList.remove('d-none');
                } else if (answer.value == '2') {
                    document.getElementById('age_drop').classList.remove('d-none');
                }
                else if (answer.value == '3') {
                    document.getElementById('school_drop').classList.remove('d-none');
                }
                else if (answer.value == '4') {
                    document.getElementById('income_drop').classList.remove('d-none');
                }
            }; 
        </script>

        <!--Select LGA Order-->
        <!--https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio-->
        <div class='form-group'> 
            <fieldset>
                <legend>Select LGA Order</legend>
                    <input type='radio' name='SortOrder' id='AscendingLGA' value='Ascending'>
                    <label for='AscendingLGA'>View LGA in Ascending Order</label> 
                    </br>
                    <input type='radio' name='SortOrder' id='DescendingLGA' value='Descending'>
                    <lable for='DescendingLGA'>View LGA in Descending Order</lable>
                    </br>
            </fieldset>
            <fieldset>
                <legend>Select Raw Data Order</legend>
                    <input type='radio' name='SortOrder' id='AscendingRaw' value='Ascending'>
                    <label for='AscendingRaw'>View Raw Data in Ascending Order</label> 
                    </br>
                    <input type='radio' name='SortOrder' id='DescendingRaw' value='Descending'>
                    <lable for='DescendingLGA'>View Raw Data in Descending Order</lable>
                    </br>
            </fieldset>
            <fieldset>
                <legend>Select Proportional Data Order</legend>
                    <input type='radio' name='SortOrder' id='AscendingProportional' value='Ascending'>
                    <label for='AscendingProportional'>View Proportional Data in Ascending Order</label> 
                    </br>
                    <input type='radio' name='SortOrder' id='DescendingProportional' value='Descending'>
                    <lable for='DescendingProportional'>View Proportional Data in Descending Order</lable>
                    </br>
            </fieldset>
            <fieldset>
                <legend>Select The Gap Order</legend>
                    <input type='radio' name='SortOrder' id='AscendingGap' value='Ascending'>
                    <label for='AscendingGap'>View The Gap in Ascending Order</label> 
                    </br>
                    <input type='radio' name='SortOrder' id='DescendingGap' value='Descending'>
                    <lable for='DescendingGap'>View The Gap in Descending Order</lable>
            </fieldset>
        </div>

        <!-- Submit Button-->
            <button type='submit' class='btn'>Compare LGAs</button>
        </form>
    </div>
                """;

        /* 
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
        */

          /* Get the Form Data
                    *  from the drop down list
                    * Need to be Careful!!
                    *  If the form is not filled in, then the form will return null!
                    */
                    String condition_drop = context.formParam("condition_drop");
                    // String movietype_drop = context.queryParam("movietype_drop");
                    if (condition_drop == null) {
                    // If NULL, nothing to show, therefore we make some "no results" HTML
                    html = html + "<h2><i>No Results to show for dropbox</i></h2>";
                    } else {
                    // If NOT NULL, then lookup the movie by type!
                    html = html + outputDataByHealthCond(condition_drop);
                    }

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

    
    
    public String outputDataByHealthCond(String selectedCondition) {
        String html = "";
        html = html + "<h2> Population of indigenous people with " + selectedCondition + "</h2>";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> healthConditions21 = jdbc.getDataByHealthCondition(selectedCondition);
        
        // Add HTML for the movies list
        html = html + "<ul>";
        for (String result : healthConditions21) {
            html = html + "<li>" + result + "</li>";
        }
        html = html + "</ul>";

        return html;
    }

}
