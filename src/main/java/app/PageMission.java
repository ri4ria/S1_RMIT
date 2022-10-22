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
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    // Name of the Thymeleaf HTML template page in the resources folder
    private static final String TEMPLATE = ("PageMission.html");

    @Override
    public void handle(Context context) throws Exception {
        // The model of data to provide to Thymeleaf
        Map<String, Object> model = new HashMap<String, Object>();

        // Add in title for the h1 tag to the model
        model.put("title", new String("Mission Statement"));

        // Look up some information from JDBC
        // First, create new connection to JDBC
        JDBCConnection jdbc = new JDBCConnection();

        // Ask the JDBC class for the personas
        ArrayList<Persona> persona1 = jdbc.getPersonas("Joanna Tilden the GP");
        ArrayList<Persona> persona2 = jdbc.getPersonas("Lara King the Lawyer");

        String persona1name; String persona2name;
        String persona1imagepath; String persona2imagepath;
        int persona1age; int persona2age;
        String persona1ethnicity; String persona2ethnicity;
        String persona1quote; String persona2quote;
        
        // Put persona1 details into the model
        for (Persona persona : persona1) {
            model.put("persona1name", persona1name = persona.getName());
            model.put("persona1imagepath", persona1imagepath = persona.getImageFilePath());
            model.put("persona1age", persona1age = persona.getAge());
            model.put("persona1ethnicity", persona1ethnicity = persona.getEthnicity());
            model.put("persona1quote", persona1quote = persona.getQuote());
        }

        // Put persona2 details into the model
        for (Persona persona : persona2) {
            model.put("persona2name", persona2name = persona.getName());
            model.put("persona2imagepath", persona2imagepath = persona.getImageFilePath());
            model.put("persona2age", persona2age = persona.getAge());
            model.put("persona2ethnicity", persona2ethnicity = persona.getEthnicity());
            model.put("persona2quote", persona2quote = persona.getQuote());
        }

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);

        /*
         * // Create a simple HTML webpage in a String
         * String html = "<html>";
         * 
         * // Add some Head information
         * html = html + "<head>" +
         * "<title>Our Mission</title>";
         * 
         * // Add some CSS (external file)
         * html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
         * html = html + "</head>";
         * 
         * // Add the body
         * html = html + "<body>";
         * 
         * // Add the topnav
         * // This uses a Java v15+ Text Block
         * html = html + """
         * <div class='topnav'>
         * <img class='topnav-logo' src='newlogo white.png' alt='logo' height='50'>
         * <ul>
         * <a href='/'>Home</a>
         * <a href='mission.html'>Mission Statement</a>
         * <div class ='dropdown'>
         * <button class='dropbtn'>Data Analyses
         * <i class='fa fa-caret-down'></i>
         * </button>
         * <div class='dropdown-content'>
         * <a href='page3.html'>2021 Census Results</a>
         * <a href='page4.html'>Changes between 2016 and 2021 Censuses</a>
         * <a href='page5.html'>Gap Score between Indigenous and Non-Indigenous</a>
         * <a href='page6.html'>Compare LGAs with Similar Characteristics</a>
         * </div>
         * </div>
         * <a href='page7.html'>Resources</a>
         * </ul>
         * <input type='text' placeholder='Search...'>
         * </div>
         * """;
         * 
         * // Add header content block
         * html = html + """
         * <div class='header'>
         * <h1>Mission Statement</h1>
         * </div>
         * """;
         * 
         * // Add Div for page Content
         * html = html + "<div class='content'>";
         * 
         * // Look up some information from JDBC
         * // First we need to use your JDBCConnection class
         * // JDBCConnection jdbc = new JDBCConnection();
         * 
         * // Next we will ask this *class* for the LGAs
         * // ArrayList<LGA> lgas = jdbc.getLGAs();
         * 
         * // Addressing "Closing the Gap"
         * html = html + "<h1>Addressing 'Closing the Gap'</h1>";
         * // Addressing "Closing the Gap" content
         * html = html + """
         * <p>
         * Our approach to addressing this social challenge includes raising awareness
         * of all 17 target outcomes, however we will be particularly focusing on three:
         * </p>
         * <ul>
         * <li>
         * <b>Outcome 1: </b>Aboriginal and Torres Strait Islander people enjoy long
         * and healthly lives
         * </li>
         * <li>
         * <b>Outcome 5: </b>Aboriginal and Torres Strait Islander students achieve
         * their full learning potential
         * </li>
         * <li>
         * <b>Outcome 8: </b> Strong economic participation and development of
         * Indigenous
         * people and communities
         * </li>
         * </ul>
         * <p>
         * This website provides data on these three target outcomes, which you can use
         * to
         * compare between two years - 2016 and 2021.
         * </p>
         * <p>
         * We're also going to provide additional resources, but also insights on how
         * progress
         * is tracking on the targets of the 'Closing the Gap' campaign.
         * </p>
         * """;
         * 
         * // How to Use Our Website
         * html = html + """
         * <h1>
         * How to Use Our Website
         * </h1>
         * <p>
         * A majority of site activity will be present in the 'Data Analyses' tab. The
         * results
         * that appear depend on what target outcome and type of data you are interested
         * in.
         * </p>
         * <p>
         * Generally, you must choose your filter options in the sidebar.
         * </p>
         * <p>
         * More detailed instructions appear on each 'Data Analyses' page.
         * </p>
         * """;
         * 
         * // Personas
         * 
         * html = html + """
         * <div class='personas'>
         * <h1>
         * Personas
         * </h1>
         * """;
         * 
         * // Look up some information from JDBC
         * // First we need to use your JDBCConnection class
         * JDBCConnection jdbc = new JDBCConnection();
         * 
         * // Next we will ask this *class* for the Joanna Tilden persona
         * ArrayList<Persona> persona1 = jdbc.getPersonas("Joanna Tilden the GP");
         * 
         * // Print the Joanna Tilden persona
         * for (Persona persona : persona1) {
         * html = html + "<h2>" + persona.getName() + "</h2> <ul>"
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * + "<img src = '" + persona.getImageFilePath() +
         * "' alt = 'Persona Image' height = '100' style = 'transform:rotate(0deg)'>"
         * + "<li>" + persona.getAge() + "</li>"
         * + "<li>" + persona.getEthnicity() + "</li>"
         * + "<li>\"" + persona.getQuote() + "\"</li> </ul>";
         * }
         * 
         * html = html + "<h4>Background</h4> <ul>";
         * ArrayList<PersonaAttribute> persona1background =
         * jdbc.getPersonaAttribute("Joanna Tilden the GP", "Background");
         * for (PersonaAttribute background : persona1background) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + background.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * html = html + "<h4>Needs</h4> <ul>";
         * ArrayList<PersonaAttribute> persona1needs =
         * jdbc.getPersonaAttribute("Joanna Tilden the GP", "Needs");
         * for (PersonaAttribute needs : persona1needs) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + needs.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * html = html + "<h4>Goals</h4> <ul>";
         * ArrayList<PersonaAttribute> persona1goals =
         * jdbc.getPersonaAttribute("Joanna Tilden the GP", "Goals");
         * for (PersonaAttribute goals : persona1goals) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + goals.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * html = html + "<h4>Skills</h4> <ul>";
         * ArrayList<PersonaAttribute> persona1skills =
         * jdbc.getPersonaAttribute("Joanna Tilden the GP", "Skills");
         * for (PersonaAttribute skills : persona1skills) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + skills.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * // Next we will ask this *class* for the Lara King persona
         * ArrayList<Persona> persona2 = jdbc.getPersonas("Lara King the Lawyer");
         * 
         * // Print the Lara King persona
         * for (Persona persona : persona2) {
         * html = html + "<h2>" + persona.getName() + "</h2> <ul>"
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * + "<img src = '" + persona.getImageFilePath() +
         * "' alt = 'Persona Image' height = '100' style = 'transform:rotate(0deg)'>"
         * + "<li>" + persona.getAge() + "</li>"
         * + "<li>" + persona.getEthnicity() + "</li>"
         * + "<li>\"" + persona.getQuote() + "\"</li> </ul>";
         * }
         * 
         * html = html + "<h4>Background</h4> <ul>";
         * ArrayList<PersonaAttribute> persona2background =
         * jdbc.getPersonaAttribute("Lara King the Lawyer", "Background");
         * for (PersonaAttribute background : persona2background) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + background.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * html = html + "<h4>Needs</h4> <ul>";
         * ArrayList<PersonaAttribute> persona2needs =
         * jdbc.getPersonaAttribute("Lara King the Lawyer", "Needs");
         * for (PersonaAttribute needs : persona2needs) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + needs.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * html = html + "<h4>Goals</h4> <ul>";
         * ArrayList<PersonaAttribute> persona2goals =
         * jdbc.getPersonaAttribute("Lara King the Lawyer", "Goals");
         * for (PersonaAttribute goals : persona2goals) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + goals.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * html = html + "<h4>Skills</h4> <ul>";
         * ArrayList<PersonaAttribute> persona2skills =
         * jdbc.getPersonaAttribute("Lara King the Lawyer", "Skills");
         * for (PersonaAttribute skills : persona2skills) {
         * // TODO: remove inline CSS and create alternative in CSS stylesheet
         * html = html + "<li>" + skills.getDescription() + "</li>";
         * }
         * 
         * html = html + "</ul>";
         * 
         * // Closing Personas div
         * html = html + "</div>";
         * 
         * // Close Content div
         * html = html + "</div>";
         * 
         * // Footer
         * html = html + """
         * <div class = 'footer'>
         * <h3>
         * Acknowledgement of Country
         * </h3>
         * <p id = 'acknowledgementText'>
         * Group 11 acknowledge and pay respect to Elders, both past and present, and
         * all
         * generations of Aboriginal and Torres Strait <br> Islander People now and into
         * the
         * future as the Traditional Owners of this land.
         * </p>
         * <p id = 'teamMemberCredit'>
         * COSC2803 - Milestone 2 - This website was made by Ria Kapoor (s3973093) and
         * Marielle Louisse Lopez (s3922406).
         * </p>
         * </div>
         * """;
         * 
         * // Finish the HTML webpage
         * html = html + "</body>" + "</html>";
         * 
         * // DO NOT MODIFY THIS
         * // Makes Javalin render the webpage
         * context.html(html);
         */
    }
}
