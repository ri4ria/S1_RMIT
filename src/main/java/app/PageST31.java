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
        //private static final String TEMPLATE = ("PageST31.html");
    
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
                <div class='topnav-logo-wrapper'>
                    <img class='topnav-logo' src='whitelogo-cropped.png' alt='logo'>
                </div>
                <div class = 'topnav-tabs'>
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
                        <a href='page8.html'>References</a>
                    </ul>
                </div>
            </div>
        """;
    
            // Add header content block
            html = html + """
                <div class='header'>
                    <h1>Compare Gap Scores over the Census Years</h1>
                </div>
            """;
    
            // Add Div for page Content
            html = html + "<div class='content'>";
    
             // Add instructions on how to use the filters
             html = html + """
                <h2>Compare indigenous population with non-indigenous population in different Local Government Areas using census results from 2021 and 2016</h2>
                <p>Select and filter the conditions, then filter LGAs by size, then sort table columns in ASC (ascending) or DESC (descending) order.</p>
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
            html = html + "<form action='/page5.html' method='post'>";
    
            html = html + """
                <fieldset>
                    <legend>Select and filter</legend>
                            """;
                
                html = html + """
                    <div class='form-group'> 
                    <label for='age_tick'></lable>
                            <input type='checkbox' class='largerTick' id='age_tick' name='age_tick' value='age_tick'>
                        <label for='age_drop2'>Select the Age Range:</label></br>
                            <select id='age_drop2' name='age_drop2'>
                            """;
                            ArrayList<String> ageNum = jdbc.getAge(); 
        
                            for (String anything : ageNum) {
                                html = html + "<option>" + anything + "</option>";
                            }
        
                            html = html + "      </select>";
                            
                            html = html + "   </div>";
                        
                html = html + """
                    <div class='form-group'> 
                    <label for='school_tick'></lable>
                            <input type='checkbox' class='largerTick' id='school_tick' name='school_tick' value='school_tick'>
                        <label for='school_drop2'>Select the Highest Year of Schooling:</label></br>
                            <select id='school_drop2' name='school_drop2'>
                            """;
                             ArrayList<String> schoolingYears = jdbc.getSchooling(); 
        
                            for (String schooled : schoolingYears) {
                                html = html + "<option>" + schooled + "</option>";
                            }
        
                            html = html + "      </select>";
                            html = html + "   </div>";
                        
                html = html + """        
                    <div class='form-group'> 
                    <label for='income_tick'></lable>
                            <input type='checkbox' class='largerTick' id='income_tick' name='income_tick' value='income_tick'>
                        <label for='income_drop2'>Select the Weekly Household Income Bracket:</label></br>
                            <select id='income_drop2' name='income_drop2'>
                            """;
                            ArrayList<String> incomeBrackets = jdbc.getHousehold(); 
        
                            for (String income : incomeBrackets) {
                                html = html + "<option>" + income + "</option>";
                            }
                               
                                
                            html = html + "      </select>";
                            html = html + "   </div>";
                html = html + "</fieldset>";
    
                html = html + "<fieldset>";
                html = html + " <legend>Filter LGAs by area (digits only)</legend>";
                html = html + "   <div class='form-group'>";
                html = html + "      <label for='sqkmMin_textbox'> min (0 km)</label> </br>";
                html = html + "      <input class='form-control' id='sqkmMin_textbox' name='sqkmMin_textbox' value='0'>";
                html = html + "   </div>";
                html = html + "   <div class='form-group'>";
                html = html + "      <label for='sqkmMax_textbox'> max (1,000,000 km)</label>";
                html = html + "      <input class='form-control' id='sqkmMax_textbox' name='sqkmMax_textbox' value='1000000'>";
                html = html + "   </div>";
                html = html + "</fieldset>";
    
                html = html + """ 
                    <fieldset>
                    <legend>Sort the dataset</legend>
                    <div class='form-group'> 
                        <label for='sort_drop2'>Per table heading</label></br>
                            <select id='sort_drop2' name='sort_drop2'>
                                <option>sort.code ASC</option>
                                <option>sort.code DESC</option>
                                <option>sort.name ASC</option>
                                <option>sort.name DESC</option>
                                <option>sort.area ASC</option>
                                <option>sort.area DESC</option>
                            </select>
                    </div>
                    </fieldset> 
                            """;
    
                html = html + """  
                    <!-- Submit Button-->
                    <button type='submit' class='filter-button'>Compare LGAs</button>
                    </form>
                    </div>
                    """;
    
                    String age_drop2 = context.formParam("age_drop2");
                    String school_drop2 = context.formParam("school_drop2");
                    String income_drop2 = context.formParam("income_drop2");
        
                    String age_tick = context.formParam("age_tick");
                    String school_tick = context.formParam("school_tick");
                    String income_tick = context.formParam("income_tick");
    
                    String sqkmMin_textbox = context.formParam("sqkmMin_textbox");
                    String sqkmMax_textbox = context.formParam("sqkmMax_textbox");
    
                    String sort_drop2 = context.formParam("sort_drop2");
    
                    if (income_tick == null && school_tick == null && age_tick == null) {
                        html = html + "<h3><i class='selectionBrown'>No selection made</i></h3>";
                        } else {
                            html = html + outputData31(income_drop2, school_drop2, age_drop2, income_tick, school_tick, age_tick, sqkmMin_textbox, sqkmMax_textbox, sort_drop2);
                        }
    
                        html = html + " </div>";
    
        
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
    
        //get data 
        public String outputData31(String selectedIncome2, String selectedSchool2, String selectedAge2, String incomeTick, String schoolTick, String ageTick, String sqkmMin, String sqkmMax, String sort) {
            String html = "";
    
                JDBCConnection jdbc = new JDBCConnection();
                ArrayList<Table2> Level3 = jdbc.getData31(selectedIncome2, selectedSchool2, selectedAge2, sqkmMin, sqkmMax, sort);
    
            if (incomeTick != null && schoolTick != null && ageTick != null) {
    
                            // propIndig_ISA
                            html = html + "<div class='results-table2'>";
                                html = html + "<table>";
                                html = html + "<tr>";
                                html = html + "<th>Code </th>";
                                html = html + "<th>Name </th>";
                                html = html + "<th>Area </th>";
                                html = html + "<th>Average proportional <br> indigenous population</th>";
                                html = html + "<th>Income Gap 2021</th>";
                                html = html + "<th>Income Gap 2016</th>";
                                html = html + "<th>&#8710 Income</th>";
                                html = html + "<th>School Gap 2021</th>";
                                html = html + "<th>School Gap 2016</th>";
                                html = html + "<th>&#8710 School</th>";
                                html = html + "<th>Age Gap 2021</th>";
                                html = html + "<th>Age Gap 2016</th>";
                                html = html + "<th>&#8710 <br> Age</th>";
                                html = html + "</tr>";
                            for (Table2 table2 : Level3) {
                                html = html + "<tr>";
                                html = html + "<td>" + table2.getCode() + "</td>";
                                html = html + "<td>" + table2.getName() + "</td>";
                                html = html + "<td>" + table2.getArea() + "</td>";
                                html = html + "<td>" + table2.getPropIndig_ISA() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc21() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc16() + "</td>";
                                html = html + "<td>" + table2.getChange_Inc() + "</td>";
                                html = html + "<td>" + table2.getGap_Sch21() + "</td>";
                                html = html + "<td>" + table2.getGap_Sch16() + "</td>";
                                html = html + "<td>" + table2.getChange_Sch() + "</td>";
                                html = html + "<td>" + table2.getGap_Age21() + "</td>";
                                html = html + "<td>" + table2.getGap_Age16() + "</td>";
                                html = html + "<td>" + table2.getChange_Age() + "</td>";
                                html = html + "</tr>";
                            }
                                html = html + "</table>";
                                html = html + "</div>";
                                return html;
    
                } else if (incomeTick != null && schoolTick != null && ageTick == null) {
    
                            // propIndig_IS
                            html = html + "<div class='results-table2'>";
                                html = html + "<table>";
                                html = html + "<tr>";
                                html = html + "<th>Code </th>";
                                html = html + "<th>Name </th>";
                                html = html + "<th>Area </th>";
                                html = html + "<th>Average proportional <br> indigenous population</th>";
                                html = html + "<th>Income Gap 2021</th>";
                                html = html + "<th>Income Gap 2016</th>";
                                html = html + "<th>&#8710 Income</th>";
                                html = html + "<th>School Gap 2021</th>";
                                html = html + "<th>School Gap 2016</th>";
                                html = html + "<th>&#8710 School</th>";
                                html = html + "</tr>";
                            for (Table2 table2 : Level3) {
                                html = html + "<tr>";
                                html = html + "<td>" + table2.getCode() + "</td>";
                                html = html + "<td>" + table2.getName() + "</td>";
                                html = html + "<td>" + table2.getArea() + "</td>";
                                html = html + "<td>" + table2.getPropIndig_IS() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc21() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc16() + "</td>";
                                html = html + "<td>" + table2.getChange_Inc() + "</td>";
                                html = html + "<td>" + table2.getGap_Sch21() + "</td>";
                                html = html + "<td>" + table2.getGap_Sch16() + "</td>";
                                html = html + "<td>" + table2.getChange_Sch() + "</td>";
                                html = html + "</tr>";
                            }
                                html = html + "</table>";
                                html = html + "</div>";
                                return html;

                } else if (incomeTick != null && schoolTick == null && ageTick != null) {

                            // propIndig_IA
                            html = html + "<div class='results-table2'>";
                                html = html + "<table>";
                                html = html + "<tr>";
                                html = html + "<th>Code </th>";
                                html = html + "<th>Name </th>";
                                html = html + "<th>Area </th>";
                                html = html + "<th>Average proportional <br> indigenous population</th>";
                                html = html + "<th>Income Gap 2021</th>";
                                html = html + "<th>Income Gap 2016</th>";
                                html = html + "<th>&#8710 Income</th>";
                                html = html + "<th>Age Gap 2021</th>";
                                html = html + "<th>Age Gap 2016</th>";
                                html = html + "<th>&#8710 <br> Age</th>";
                                html = html + "</tr>";
                            for (Table2 table2 : Level3) {
                                html = html + "<tr>";
                                html = html + "<td>" + table2.getCode() + "</td>";
                                html = html + "<td>" + table2.getName() + "</td>";
                                html = html + "<td>" + table2.getArea() + "</td>";
                                html = html + "<td>" + table2.getPropIndig_IA() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc21() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc16() + "</td>";
                                html = html + "<td>" + table2.getChange_Inc() + "</td>";
                                html = html + "<td>" + table2.getGap_Age21() + "</td>";
                                html = html + "<td>" + table2.getGap_Age16() + "</td>";
                                html = html + "<td>" + table2.getChange_Age() + "</td>";
                                html = html + "</tr>";
                            }
                                html = html + "</table>";
                                html = html + "</div>";
                                return html;

                } else if (incomeTick != null && schoolTick == null && ageTick != null)  {
                            //propIndig_SA
                            html = html + "<div class='results-table2'>";
                            html = html + "<table>";
                            html = html + "<tr>";
                            html = html + "<th>Code </th>";
                            html = html + "<th>Name </th>";
                            html = html + "<th>Area </th>";
                            html = html + "<th>Average proportional <br> indigenous population</th>";
                            html = html + "<th>School Gap 2021</th>";
                            html = html + "<th>School Gap 2016</th>";
                            html = html + "<th>&#8710 School</th>";
                            html = html + "<th>Age Gap 2021</th>";
                            html = html + "<th>Age Gap 2016</th>";
                            html = html + "<th>&#8710 <br> Age</th>";
                            html = html + "</tr>";
                        for (Table2 table2 : Level3) {
                            html = html + "<tr>";
                            html = html + "<td>" + table2.getCode() + "</td>";
                            html = html + "<td>" + table2.getName() + "</td>";
                            html = html + "<td>" + table2.getArea() + "</td>";
                            html = html + "<td>" + table2.getPropIndig_SA() + "</td>";
                            html = html + "<td>" + table2.getGap_Sch21() + "</td>";
                            html = html + "<td>" + table2.getGap_Sch16() + "</td>";
                            html = html + "<td>" + table2.getChange_Sch() + "</td>";
                            html = html + "<td>" + table2.getGap_Age21() + "</td>";
                            html = html + "<td>" + table2.getGap_Age16() + "</td>";
                            html = html + "<td>" + table2.getChange_Age() + "</td>";
                            html = html + "</tr>";
                        }
                            html = html + "</table>";
                            html = html + "</div>";
                            return html;


                } else if (incomeTick != null && schoolTick == null && ageTick == null) {
                    
                            // propIndig_I
                            html = html + "<div class='results-table2'>";
                                html = html + "<table>";
                                html = html + "<tr>";
                                html = html + "<th>Code </th>";
                                html = html + "<th>Name </th>";
                                html = html + "<th>Area </th>";
                                html = html + "<th>Average proportional <br> indigenous population</th>";
                                html = html + "<th>Income Gap 2021</th>";
                                html = html + "<th>Income Gap 2016</th>";
                                html = html + "<th>&#8710 Income</th>";
                                html = html + "</tr>";
                            for (Table2 table2 : Level3) {
                                html = html + "<tr>";
                                html = html + "<td>" + table2.getCode() + "</td>";
                                html = html + "<td>" + table2.getName() + "</td>";
                                html = html + "<td>" + table2.getArea() + "</td>";
                                html = html + "<td>" + table2.getPropIndig_I() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc21() + "</td>";
                                html = html + "<td>" + table2.getGap_Inc16() + "</td>";
                                html = html + "<td>" + table2.getChange_Inc() + "</td>";
                                html = html + "</tr>";
                        }
                                html = html + "</table>";
                                html = html + "</div>";
                                return html;

                } else if (incomeTick == null && schoolTick != null && ageTick == null) {
                                    
                            // propIndig_S
                            html = html + "<div class='results-table2'>";
                                html = html + "<table>";
                                html = html + "<tr>";
                                html = html + "<th>Code </th>";
                                html = html + "<th>Name </th>";
                                html = html + "<th>Area </th>";
                                html = html + "<th>Average proportional <br> indigenous population</th>";
                                html = html + "<th>School Gap 2021</th>";
                                html = html + "<th>School Gap 2016</th>";
                                html = html + "<th>&#8710 School</th>";
                               
                                html = html + "</tr>";
                            for (Table2 table2 : Level3) {
                                html = html + "<tr>";
                                html = html + "<td>" + table2.getCode() + "</td>";
                                html = html + "<td>" + table2.getName() + "</td>";
                                html = html + "<td>" + table2.getArea() + "</td>";
                                html = html + "<td>" + table2.getPropIndig_S() + "</td>";
                                html = html + "<td>" + table2.getGap_Sch21() + "</td>";
                                html = html + "<td>" + table2.getGap_Sch16() + "</td>";
                                html = html + "<td>" + table2.getChange_Sch() + "</td>";
                                html = html + "</tr>";
                            }
                                html = html + "</table>";
                                html = html + "</div>";
                                return html;


                } else if (incomeTick == null && schoolTick == null && ageTick != null) {

                                // propIndig_A
                            html = html + "<div class='results-table2'>";
                                html = html + "<table>";
                                html = html + "<tr>";
                                html = html + "<th>Code </th>";
                                html = html + "<th>Name </th>";
                                html = html + "<th>Area </th>";
                                html = html + "<th>Average proportional <br> indigenous population</th>";
                                html = html + "<th>Age Gap 2021</th>";
                                html = html + "<th>Age Gap 2016</th>";
                                html = html + "<th>&#8710 <br> Age</th>";
                                html = html + "</tr>";
                            for (Table2 table2 : Level3) {
                                html = html + "<tr>";
                                html = html + "<td>" + table2.getCode() + "</td>";
                                html = html + "<td>" + table2.getName() + "</td>";
                                html = html + "<td>" + table2.getArea() + "</td>";
                                html = html + "<td>" + table2.getPropIndig_A() + "</td>";
                                html = html + "<td>" + table2.getGap_Age21() + "</td>";
                                html = html + "<td>" + table2.getGap_Age16() + "</td>";
                                html = html + "<td>" + table2.getChange_Age() + "</td>";
                                html = html + "</tr>";
                            }
                                html = html + "</table>";
                                html = html + "</div>";
                                return html;

                }

            
            return html; // added RETURN STATEMENT 
            
           
        } // get data
    
    }

    