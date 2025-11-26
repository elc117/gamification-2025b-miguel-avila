package com.mubification;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

// Controllers
import com.mubification.controllers.AchievementController;
import com.mubification.controllers.ReviewController;
import com.mubification.controllers.StoreController;
import com.mubification.controllers.MovieController;
import com.mubification.controllers.UserController;
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

        AchievementController achievementController = new AchievementController();
        ReviewController      reviewController      = new ReviewController();
        MovieController       movieController       = new MovieController();
        UserController        userController        = new UserController();
        StoreController       storeController       = new StoreController();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.directory = "/public"; 
                staticFiles.location = Location.CLASSPATH;
            });
        });
        app.get("/", ctx -> ctx.redirect("/auth.html"));

        reviewController.registerRoutes(app);  
        movieController.registerRoutes(app);
        achievementController.registerRoutes(app);
        userController.registerRoutes(app);
        storeController.registerRoutes(app);

        app.start(7070);
    }
}
