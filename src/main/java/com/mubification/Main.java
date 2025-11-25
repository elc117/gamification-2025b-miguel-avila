package com.mubification;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
// Controllers
import com.mubification.controllers.MovieController;
import com.mubification.controllers.ReviewController;
// Repositories
// Utils
import com.mubification.util.Database;

public class Main {

    public static void main(String[] args) {

        String dbUrl = System.getenv("DATABASE_URL");
        if (dbUrl == null) {
            System.err.println("ERRO: A variável DATABASE_URL não está definida!");
            System.exit(1);
        }
        Database.connect(dbUrl);

        ReviewController reviewController = new ReviewController();
        MovieController movieController   = new MovieController();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.directory = "/public"; 
                staticFiles.location = Location.CLASSPATH;
            });
        });
        app.get("/", ctx -> ctx.redirect("/app.html"));

        reviewController.registerRoutes(app);  
        movieController.registerRoutes(app);

        app.start(7070);
    }
}
