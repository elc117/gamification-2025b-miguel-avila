package com.mubification;

import com.mubification.models.Movie;
import com.mubification.repositories.MovieRepository;
import com.mubification.util.Database;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String dbUrl = System.getenv("DATABASE_URL");

        if (dbUrl == null) {
            System.err.println("ERRO: A variável DATABASE_URL não está definida!");
            System.exit(1);
        }

        Database.connect(dbUrl);

        MovieRepository movieRepository = new MovieRepository();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.directory = "/public";
                staticFiles.location = Location.CLASSPATH;
            });
        }).start(7070);

        app.get("/", ctx -> ctx.redirect("index.html"));

        // GET /movies
        app.get("/movies", ctx -> {
            List<Movie> movies = movieRepository.findAll();
            ctx.json(movies);
        });

        // POST /movies
        app.post("/movies", ctx -> {
            Movie movie = ctx.bodyAsClass(Movie.class);
            movieRepository.save(movie);
            ctx.status(201);
        });
    }
}
