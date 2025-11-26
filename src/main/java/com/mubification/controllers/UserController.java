package com.mubification.controllers;

import java.util.Map;

import com.mubification.models.User;
import com.mubification.services.UserService;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {
    
    private UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    // adicionar um novo usuario
    public void addUser(Context ctx) {
        User newUser   = ctx.bodyAsClass(User.class);
        User addedUser = userService.addUser(newUser);
        ctx.status(201).json(addedUser);
    }

    // verifica se ja existe um usuario com email/usuario igual
    public void userExists(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        Boolean existe = userService.userExists(user);
        ctx.json(existe);
    }

    // register
    public void register(Context ctx) {
        User newUser = ctx.bodyAsClass(User.class);

        // verifica se username ou email já existem
        if (userService.userExists(newUser)) {
            ctx.status(400).json("Usuário ou email já cadastrados.");
            return;
        }

        User addedUser = userService.addUser(newUser);
        ctx.status(201).json(addedUser);
    }

    // login
    public void login(Context ctx) {
        Map<String, String> body = ctx.bodyAsClass(Map.class);
        String email = body.get("email");
        String password = body.get("password");

        User user = userService.login(email, password);
        if (user != null) { ctx.status(200).json(user); } 
        else { ctx.status(401).json(Map.of("error", "Email ou senha inválidos")); }
    }

    public void getUserInfo(Context ctx) {
        int userId = Integer.parseInt(ctx.queryParam("userid"));
        User user = userService.getUserInfo(userId);
        ctx.json(user);
    }

    public void registerRoutes(Javalin app) {
        app.post("/api/users",       this::addUser);
        app.post("/api/users/exist", this::userExists);
        app.post("/api/auth/login",  this::login);
        app.get("/api/user/info",    this::getUserInfo);
    }

}
