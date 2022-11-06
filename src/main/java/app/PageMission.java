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

        // Ask the JDBC class for the personas
        ArrayList<Persona> persona1 = jdbc.getPersonas("Joanna Tilden the GP");
        ArrayList<PersonaAttribute> persona1background = jdbc.getPersonaAttribute("Joanna Tilden the GP", "Background");
        ArrayList<PersonaAttribute> persona1needs = jdbc.getPersonaAttribute("Joanna Tilden the GP", "Needs");
        ArrayList<PersonaAttribute> persona1goals = jdbc.getPersonaAttribute("Joanna Tilden the GP", "Goals");
        ArrayList<PersonaAttribute> persona1skills = jdbc.getPersonaAttribute("Joanna Tilden the GP", "Skills");

        ArrayList<Persona> persona2 = jdbc.getPersonas("Lara King the Lawyer");
        ArrayList<PersonaAttribute> persona2background = jdbc.getPersonaAttribute("Lara King the Lawyer", "Background");
        ArrayList<PersonaAttribute> persona2needs = jdbc.getPersonaAttribute("Lara King the Lawyer", "Needs");
        ArrayList<PersonaAttribute> persona2goals = jdbc.getPersonaAttribute("Lara King the Lawyer", "Goals");
        ArrayList<PersonaAttribute> persona2skills = jdbc.getPersonaAttribute("Lara King the Lawyer", "Skills");

        /*
        ArrayList<Persona> persona3 = jdbc.getPersonas("Oscar Smith the High Schooler");
        ArrayList<PersonaAttribute> persona3background = jdbc.getPersonaAttribute("Oscar Smith the High Schooler", "Background");
        ArrayList<PersonaAttribute> persona3needs = jdbc.getPersonaAttribute("Oscar Smith the High Schooler", "Needs");
        ArrayList<PersonaAttribute> persona3goals = jdbc.getPersonaAttribute("Oscar Smith the High Schooler", "Goals");
        ArrayList<PersonaAttribute> persona3skills = jdbc.getPersonaAttribute("Oscar Smith the High Schooler", "Skills");
        */

        String persona1name; String persona2name; // String persona3name; 
        String persona1imagepath; String persona2imagepath; // String persona3imagepath;
        int persona1age; int persona2age; // int persona3age;
        String persona1ethnicity; String persona2ethnicity; // String persona3ethnicity;
        String persona1quote; String persona2quote; // String persona3quote;
        
        // Put persona1 details into the model
        for (Persona persona : persona1) {
            model.put("persona1name", persona1name = persona.getName());
            model.put("persona1imagepath", persona1imagepath = persona.getImageFilePath());
            model.put("persona1age", persona1age = persona.getAge());
            model.put("persona1ethnicity", persona1ethnicity = persona.getEthnicity());
            model.put("persona1quote", persona1quote = persona.getQuote());
        }

        ArrayList<String> persona1backgroundlist = new ArrayList<String>();
        for (PersonaAttribute background : persona1background) {
            persona1backgroundlist.add(background.getDescription());
        }
        model.put("persona1backgroundlist", persona1backgroundlist);

        ArrayList<String> persona1needslist = new ArrayList<String>();
        for (PersonaAttribute need : persona1needs) {
            persona1needslist.add(need.getDescription());
        }
        model.put("persona1needslist", persona1needslist);

        ArrayList<String> persona1goalslist = new ArrayList<String>();
        for (PersonaAttribute goal : persona1goals) {
            persona1goalslist.add(goal.getDescription());
        }
        model.put("persona1goalslist", persona1goalslist);

        ArrayList<String> persona1skillslist = new ArrayList<String>();
        for (PersonaAttribute skill : persona1skills) {
            persona1skillslist.add(skill.getDescription());
        }
        model.put("persona1skillslist", persona1skillslist);

        // Put persona2 details into the model
        for (Persona persona : persona2) {
            model.put("persona2name", persona2name = persona.getName());
            model.put("persona2imagepath", persona2imagepath = persona.getImageFilePath());
            model.put("persona2age", persona2age = persona.getAge());
            model.put("persona2ethnicity", persona2ethnicity = persona.getEthnicity());
            model.put("persona2quote", persona2quote = persona.getQuote());
        }

        ArrayList<String> persona2backgroundlist = new ArrayList<String>();
        for (PersonaAttribute background : persona2background) {
            persona2backgroundlist.add(background.getDescription());
        }
        model.put("persona2backgroundlist", persona2backgroundlist);

        ArrayList<String> persona2needslist = new ArrayList<String>();
        for (PersonaAttribute need : persona2needs) {
            persona2needslist.add(need.getDescription());
        }
        model.put("persona2needslist", persona2needslist);

        ArrayList<String> persona2goalslist = new ArrayList<String>();
        for (PersonaAttribute goal : persona2goals) {
            persona2goalslist.add(goal.getDescription());
        }
        model.put("persona2goalslist", persona2goalslist);

        ArrayList<String> persona2skillslist = new ArrayList<String>();
        for (PersonaAttribute skill : persona2skills) {
            persona2skillslist.add(skill.getDescription());
        }
        model.put("persona2skillslist", persona2skillslist);

        /*

        // Put persona3 details into the model
        for (Persona persona : persona3) {
            model.put("persona3name", persona3name = persona.getName());
            model.put("persona3imagepath", persona3imagepath = persona.getImageFilePath());
            model.put("persona3age", persona3age = persona.getAge());
            model.put("persona3ethnicity", persona3ethnicity = persona.getEthnicity());
            model.put("persona3quote", persona3quote = persona.getQuote());
        }

        ArrayList<String> persona3backgroundlist = new ArrayList<String>();
        for (PersonaAttribute background : persona3background) {
            persona3backgroundlist.add(background.getDescription());
        }
        model.put("persona3backgroundlist", persona3backgroundlist);

        ArrayList<String> persona3needslist = new ArrayList<String>();
        for (PersonaAttribute need : persona3needs) {
            persona3needslist.add(need.getDescription());
        }
        model.put("persona3needslist", persona3needslist);

        ArrayList<String> persona3goalslist = new ArrayList<String>();
        for (PersonaAttribute goal : persona3goals) {
            persona3goalslist.add(goal.getDescription());
        }
        model.put("persona3goalslist", persona3goalslist);

        ArrayList<String> persona3skillslist = new ArrayList<String>();
        for (PersonaAttribute skill : persona3skills) {
            persona3skillslist.add(skill.getDescription());
        }
        model.put("persona3skillslist", persona3skillslist);

        */

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage using Thymeleaf
        context.render(TEMPLATE, model);
        
    }
}
