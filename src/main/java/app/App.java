package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the 
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2022. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    public static final int         JAVALIN_PORT    = 7001;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            
            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);
        }).start(JAVALIN_PORT);


        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        // All webpages are listed here as GET pages
        app.get(PageIndex.URL, new PageIndex());
        app.get(PageMission.URL, new PageMission());
        app.get(PageST21.URL, new PageST21());
        app.get(PageST22.URL, new PageST22());
        app.get(PageST22Education.URL, new PageST22Education());
        app.get(PageST22Status.URL, new PageST22Status());
        app.get(PageST22Income.URL, new PageST22Income());
        app.get(PageST31.URL, new PageST31());
        app.get(PageST32.URL, new PageST32());
        app.get(PageST32Education.URL, new PageST32Education());
        app.get(PageST32Health.URL, new PageST32Health());
        app.get(PageST32Income.URL, new PageST32Income());
        app.get(PageST32Status.URL, new PageST32Status());
        app.get(PageResources.URL, new PageResources());
        app.get(PageReferences.URL, new PageReferences());

        // Add / uncomment POST commands for any pages that need web form POSTS
        // app.post(PageIndex.URL, new PageIndex());
        // app.post(PageMission.URL, new PageMission());
        app.post(PageST21.URL, new PageST21());
        app.post(PageST22.URL, new PageST22());
        app.post(PageST22Education.URL, new PageST22Education());
        app.post(PageST22Status.URL, new PageST22Status());
        app.post(PageST22Income.URL, new PageST22Income());
        app.post(PageST31.URL, new PageST31());
        app.post(PageST32.URL, new PageST32());
        app.post(PageST32Education.URL, new PageST32Education());
        app.post(PageST32Health.URL, new PageST32Health());
        app.post(PageST32Income.URL, new PageST32Income());
        app.post(PageST32Status.URL, new PageST32Status());
        // app.post(PageResources.URL, new PageResources());
    }

}
