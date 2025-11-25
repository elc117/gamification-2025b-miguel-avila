package com.mubification.controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.mubification.models.Review;
import com.mubification.services.ReviewService;

public class ReviewController {

    private ReviewService reviewService;

    public ReviewController() {
        this.reviewService = new ReviewService();
    }

    public void addReview(Context ctx) {
        Review newReview   = ctx.bodyAsClass(Review.class);
        Review addedReview = reviewService.addReview(newReview);
        ctx.status(201).json(addedReview);
    }

    public void registerRoutes(Javalin app) {
        app.post("/api/reviews", this::addReview);
    }
}
