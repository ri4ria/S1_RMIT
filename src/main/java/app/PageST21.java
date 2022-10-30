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

        html = html + " <div class='filter-sidebar'>";
        html = html + "<form action='/page3.html' method='post'>";
        
        /* 
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
        """;
        */

        html = html + """
        <!-- Dropdown that will only be available if the relevant dataset is selected-->
        <fieldset>
            <legend>Select and filter one dataset</legend>
            <div class='form-group'> 
                <label for='condition_drop'>Select the Health Condition:</label></br>
                    <select id='condition_drop' name='condition_drop'>
                    <option>select</option>
                    """;

                    ArrayList<String> healthCond = jdbc.getHealthConditions(); 

                    for (String health : healthCond) {
                        html = html + "<option>" + health + "</option>";
                    }

                    html = html + "      </select>";
                    html = html + "   </div>";
        
        
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
        
        html = html + """ 
            <fieldset>
            <legend>Sort the dataset</legend>
            <div class='form-group'> 
                <label for='sort_drop'>Per table heading:</label></br>
                    <select id='sort_drop' name='sort_drop'>
                        <option>sort.code ASC</option>
                        <option>sort.code DESC</option>
                        <option>sort.name ASC</option>
                        <option>sort.name DESC</option>
                        <option>sort.indig ASC</option>
                        <option>sort.indig DESC</option>
                        <option>sort.nonindig ASC</option>
                        <option>sort.nonindig DESC</option>
                        <option>sort.total ASC</option>
                        <option>sort.total DESC</option>
                        <option>sort.propIndig ASC</option>
                        <option>sort.propIndig DESC</option>
                        <option>sort.propNon ASC</option>
                        <option>sort.propNon DESC</option>
                        <option>sort.gap ASC</option>
                        <option>sort.gap DESC</option>
                    </select>
            </div>
            </fieldset> 
                    """;

        html = html + """  
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
                """;

        /* 
        html = html + """         
        <!--Select LGA Order-->
        <!--https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio-->
        <div class='form-group'> 
            <fieldset>
                <legend>Select LGA Order</legend>
                    <input type='radio' name='SortOrder' id='AscendingLGA' value='Ascending' checked>
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
                """;
        */

        html = html + """  
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
                    String age_drop = context.formParam("age_drop");
                    String school_drop = context.formParam("school_drop");
                    String income_drop = context.formParam("income_drop");

                    String sort_drop = context.formParam("sort_drop");

                    if (condition_drop != null) {
                        html = html + outputDataByHealthCond(condition_drop, sort_drop);
                        } else {
                        html = html + "<h2><i>No results to show for Outcome 1: Health conditions</i></h2>";
                        }
                    
                        if (age_drop != null) {
                            html = html + outputDataByAge(age_drop, sort_drop);
                            } else {
                            html = html + "<h2><i>No results to show for Outcome 1: Population by age</i></h2>";
                            }

                            if (school_drop != null) {
                                html = html + outputDataBySchool(school_drop, sort_drop);
                                } else {
                                html = html + "<h2><i>No results to show for Outcome 5: Highest year of shcooling</i></h2>";
                                }

                                if (income_drop != null) {
                                    html = html + outputDataByIncome(income_drop, sort_drop) ;
                                    } else {
                                    html = html + "<h2><i>No results to show for Outcome 8: Weekely household income</i></h2>";
                                    }


                        /* 
                    // String movietype_drop = context.queryParam("movietype_drop");
                    if (condition_drop == null) {
                    // If NULL, nothing to show, therefore we make some "no results" HTML
                    html = html + "<h2><i>No Results to show for Outcome 1: Health Conditions</i></h2>";
                    } else {
                    // If NOT NULL, then lookup the movie by type!
                    html = html + outputDataByHealthCond(condition_drop, sort_drop);
                    }

                    //age_drop
                    //String age_drop = context.formParam("age_drop");
                    // String movietype_drop = context.queryParam("movietype_drop");
                    if (age_drop == null) {
                    // If NULL, nothing to show, therefore we make some "no results" HTML
                    html = html + "<h2><i>No Results to show for Outcome 1: Population By Age</i></h2>";
                    } else {
                    // If NOT NULL, then lookup the movie by type!
                    html = html + outputDataByAge(age_drop, sort_drop);
                    }

                     //school_drop
                     //String school_drop = context.formParam("school_drop");
                     //String sort_drop = context.formParam("sort_drop");
                     // String movietype_drop = context.queryParam("movietype_drop");
                     if (school_drop == null) {
                     // If NULL, nothing to show, therefore we make some "no results" HTML
                     html = html + "<h2><i>No Results to show for Outcome 5: Highest year of shcooling</i></h2>";
                     } else {
                     // If NOT NULL, then lookup the movie by type!
                     html = html + outputDataBySchool(school_drop, sort_drop);
                     }

                     //income_drop
                     //String income_drop = context.formParam("income_drop");
                     // String movietype_drop = context.queryParam("movietype_drop");
                     if (income_drop == null) {
                     // If NULL, nothing to show, therefore we make some "no results" HTML
                     html = html + "<h2><i>No Results to show for Outcome 8: Weekely household income</i></h2>";
                     } else {
                     // If NOT NULL, then lookup the movie by type!
                     html = html + outputDataByIncome(income_drop, sort_drop) ;
                     }
                     */

                     html = html + " </div>";

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

    
    //get data for first query
    public String outputDataByHealthCond(String selectedCondition, String sort) {
        String html = "";
        if (selectedCondition == null) {
            html = html + "<h2><i>Please select from dropbox</i></h2>";
            } else {
            html = html + "<h2> Population of people with " + selectedCondition + "</h2>";
            html = html + "<h2> Sort type used: " + sort + "</h2>";
        }

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Table> healthConditions21 = jdbc.getDataByHealthCondition(selectedCondition, sort);
        
        // Add HTML for the health conditions list
        html = html + "<div class='results-table2'>";
        html = html + "<table>";
        html = html + "<tr>";
                html = html + "<th>Code </th>";
                html = html + "<th>Name </th>";
                html = html + "<th>Indigenous (I) </th>";
                html = html + "<th>Non-indigenous (N) </th>";
                html = html + "<th>Total (including non-stated) </th>";
                html = html + "<th>Proportion of total indigenous </th>";
                html = html + "<th>Proportion of total non-indigenous </th>";
                html = html + "<th>Gap (I - N) </th>";
            html = html + "</tr>";
        for (Table table : healthConditions21) {
            html = html + "<tr>";
            html = html + "<td>" + table.getCode() + "</td>";
            html = html + "<td>" + table.getName() + "</td>";
            html = html + "<td>" + table.getIndig() + "</td>";
            html = html + "<td>" + table.getNonindig() + "</td>";
            html = html + "<td>" + table.getTotal() + "</td>";
            html = html + "<td>" + table.getPropIndig() + "</td>";
            html = html + "<td>" + table.getPropNon() + "</td>";
            html = html + "<td>" + table.getGap() + "</td>";
            html = html + "</tr>";
        }

        html = html + "</table>";
        html = html + "</div>";
        return html;
    }

    //get data for first query
    public String outputDataByAge(String selectedAge, String sort) {
        String html = "";
        html = html + "<h2> Population of people that are " + selectedAge + " years old</h2>";
        html = html + "<h2> Sort type used: " + sort + "</h2>";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Table> age21 = jdbc.getDataByAge(selectedAge, sort);
        
        // Add HTML for the health conditions list
        /* 
        html = html + "<ul>";
        for (Table table : age21) {
            html = html + "<li>" + result + "</li>";
        }
        html = html + "</ul>";
        */
        html = html + "<div class='results-table2'>";
        html = html + "<table>";
            html = html + "<tr>";
                html = html + "<th>Code </th>";
                html = html + "<th>Name </th>";
                html = html + "<th>Indigenous (I) </th>";
                html = html + "<th>Non-indigenous (N) </th>";
                html = html + "<th>Total (including non-stated) </th>";
                html = html + "<th>Proportion of total indigenous </th>";
                html = html + "<th>Proportion of total non-indigenous </th>";
                html = html + "<th>Gap (I - N) </th>";
            html = html + "</tr>";
            //html = html + "<tr>";
        for (Table table : age21) {
            html = html + "<tr>";
            html = html + "<td>" + table.getCode() + "</td>";
            html = html + "<td>" + table.getName() + "</td>";
            html = html + "<td>" + table.getIndig() + "</td>";
            html = html + "<td>" + table.getNonindig() + "</td>";
            html = html + "<td>" + table.getTotal() + "</td>";
            html = html + "<td>" + table.getPropIndig() + "</td>";
            html = html + "<td>" + table.getPropNon() + "</td>";
            html = html + "<td>" + table.getGap() + "</td>";
            html = html + "</tr>";
        }
        html = html + "</table>";
        html = html + "</div>";

        return html;
    }

     //get data for first query
     public String outputDataBySchool(String selectedSchool, String sort) {
        String html = "";
        html = html + "<h2> Population of people with highest schooling being: " + selectedSchool + "</h2>";
        html = html + "<h2> Sort type used: " + sort + "</h2>";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Table> school21 = jdbc.getDataBySchool(selectedSchool, sort);
        
        /*
        // Add HTML for the health conditions list
        html = html + "<ul>";
        for (Table result : school21) {
            html = html + "<li>" + result + "</li>";
        }
        html = html + "</ul>";
        */

        // Add HTML for the health conditions list
        html = html + "<div class='results-table2'>";
        html = html + "<table>";
            html = html + "<tr>";
                html = html + "<th>Code </th>";
                html = html + "<th>Name </th>";
                html = html + "<th>Indigenous (I) </th>";
                html = html + "<th>Non-indigenous (N) </th>";
                html = html + "<th>Total (including non-stated) </th>";
                html = html + "<th>Proportion of total indigenous </th>";
                html = html + "<th>Proportion of total non-indigenous </th>";
                html = html + "<th>Gap (I - N) </th>";
            html = html + "</tr>";
            //html = html + "<tr>";
        for (Table table : school21) {
            html = html + "<tr>";
            html = html + "<td>" + table.getCode() + "</td>";
            html = html + "<td>" + table.getName() + "</td>";
            html = html + "<td>" + table.getIndig() + "</td>";
            html = html + "<td>" + table.getNonindig() + "</td>";
            html = html + "<td>" + table.getTotal() + "</td>";
            html = html + "<td>" + table.getPropIndig() + "</td>";
            html = html + "<td>" + table.getPropNon() + "</td>";
            html = html + "<td>" + table.getGap() + "</td>";
            html = html + "</tr>";
        }
        /* 
        for (Table table : school21) {
            html = html + "<td>" + table.getName() + "</td>";
        }
        for (Table table : school21) {
            html = html + "<td>" + table.getIndig() + "</td>";
        }
        for (Table table : school21) {
            html = html + "<td>" + table.getNonindig() + "</td>";
        }
        for (Table table : school21) {
            html = html + "<td>" + table.getNonstated() + "</td>";
        }
        for (Table table : school21) {
            html = html + "<td>" + table.getTotal() + "</td>";
        }
        for (Table table : school21) {
            html = html + "<td>" + table.getGap() + "</td>";
        }
        for (Table table : school21) {
            html = html + "<td>" + table.getProportional() + "</td>";
        }
        */
            //html = html + "</tr";
        html = html + "</table>";
        html = html + "</div>";

        return html;
    }

    //get data for first query
    public String outputDataByIncome(String selectedIncome, String sort) {
        String html = "";
        html = html + "<h2>Population of households with a weekly household income of: $" + selectedIncome + "</h2>";
        html = html + "<h2> Sort type used: " + sort + "</h2>";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Table> income21 = jdbc.getDataByHouse(selectedIncome, sort);
        html = html + "<div class='results-table2'>";
        html = html + "<table>";
            html = html + "<tr>";
                html = html + "<th>Code </th>";
                html = html + "<th>Name </th>";
                html = html + "<th>Indigenous (I) </th>";
                html = html + "<th>Non-indigenous (N) </th>";
                html = html + "<th>Total (including non-stated) </th>";
                html = html + "<th>Proportion of total indigenous </th>";
                html = html + "<th>Proportion of total non-indigenous </th>";
                html = html + "<th>Gap (I - N) </th>";
            html = html + "</tr>";
            //html = html + "<tr>";
        for (Table table : income21) {
            html = html + "<tr>";
            html = html + "<td>" + table.getCode() + "</td>";
            html = html + "<td>" + table.getName() + "</td>";
            html = html + "<td>" + table.getIndig() + "</td>";
            html = html + "<td>" + table.getNonindig() + "</td>";
            html = html + "<td>" + table.getTotal() + "</td>";
            html = html + "<td>" + table.getPropIndig() + "</td>";
            html = html + "<td>" + table.getPropNon() + "</td>";
            html = html + "<td>" + table.getGap() + "</td>";
            html = html + "</tr>";
        }
        html = html + "</table>";
        html = html + "</div>";

        return html;
    }

}
