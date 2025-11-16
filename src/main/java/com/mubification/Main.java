package com.mubification;

import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;


public class Main {
    // cria a lista de filmes provisoria para testes
    private static List<Movie> movies = new ArrayList<>(); 

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(7070);

    app.get("/", ctx -> ctx.redirect("index.html"));

    app.get("/movies", ctx -> {
        ctx.json(movies);
    });

    app.post("/movies", ctx -> {
        Movie movie = ctx.bodyAsClass(Movie.class);
        movies.add(movie);
        ctx.status(201); // status 201 = created
    });

    }
}