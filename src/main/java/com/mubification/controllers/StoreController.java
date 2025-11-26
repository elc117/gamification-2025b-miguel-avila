package com.mubification.controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;

import com.mubification.models.Store;
import com.mubification.services.StoreService;

import java.util.List;

public class StoreController {

    private StoreService storeService;

    public StoreController() {
        this.storeService = new StoreService();
    }

    // lista todos itens da loja
    public void getItems(Context ctx) {
        List<Store> items = storeService.getStoreItems();
        ctx.json(items);
    }

    // compra um item
    public void buyItem(Context ctx) {
        int userId = Integer.parseInt(ctx.queryParam("userid"));
        int itemId = Integer.parseInt(ctx.queryParam("itemid"));

        String result = storeService.buyItem(userId, itemId);

        ctx.json(result);
    }

    public void registerRoutes(Javalin app) {
        app.get("/api/store/items", this::getItems);
        app.post("/api/store/buy",  this::buyItem);
    }
}
