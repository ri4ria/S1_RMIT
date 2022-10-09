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
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

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
                <h1>
                    Welcome
                </h1>
            </div>
        """;
        
        // Add Div for page Content 
        html = html + "<div class='content'>";

        html = html + """
            <h1>Addressing the Social Challenge</h1>
                <p>Entrenched inequalities are impacting the lives of Aboriginal and Torres Strait Islander people and communities.</p>
                <p>In accordance with the National Agreement on Closing the Gap, local Governing bodies, organizations, businesses, 
                    and others in the community are working towards achieving 17 different outcomes.</p>
                <p>This website provides data for the following three outcomes:</p>

            <h1>Our Focus</h1>
                <p><b>Outcome 1: </b>Aboriginal and Torres Strait Islander people enjoy long and healthy lives</p>
                <p><b>Ouctome 5: </b>Aboriginal and Torres Strait Islander students achieve their full learning potential</p>
                <p><b>Outcome 8: </b>Strong economic participation and development of Aboriginal and Torres Strait Islander people and communities</p>

            <h1>Page information</h1>
            <ul>
                <li><a href='/'>Homepage:</a> View all the current target outcomes and our focus</li>
                <li><a href='mission.html'>Our Mission:</a> View information about our website personas and our mission</li>
                <li>Data Analysis: Hover over to view the following websites:
                    <ul>
                        <li><a href='page3.html'>2021 Census Results:</a> Observe the lastest information available from the 2021 census 
                            on 'The Gap' between Indigenous and Non-Indigenous peoples</li>
                        <li><a href='page4.html'>Changes between 2016 and 2021 Censuses:</a> Compare changes to 'the Gap' between 2016 
                            and 2021 censuses for a single Local Government Area or State</li>
                        <li><a href='page5.html'>Gap Score between Indigenous and Non-Indigenous:</a> Compare all three target outcomes and performance between 2016 and 2021 censuses</li>
                        <li><a href='page6.html'>Compare LGAs with Similar Characteristics:</a> Compare similar Local Government Areas' performance on addressing all the three target outcomes</li>
                    </ul>
                </li>
                <li><a href='page7.html'>Resources:</a> View links to different websites</li> 
            </ul>
            """;

        // Close Content div
        html = html + "</div>";

        // Add Div for 17 targets section 
        html = html + "<div class='content'>";

        html = html + """
            <h1>17 Outcomes of the National Agreement on Closing the Gap</h1>
            <p>Insert an arraylist to access the database</p>
            """;

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
