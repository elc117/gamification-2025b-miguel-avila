package com.mubification.controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.mubification.models.Review;
import com.mubification.models.ReviewDTO;
import com.mubification.services.ReviewService;

import java.util.List;

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
    
    public void getTopReviews(Context ctx) {
        List<ReviewDTO> topReviews = reviewService.getTopReviews(3);
        ctx.json(topReviews);
    }

    public void getUserTopReviews(Context ctx) {
        String userIdParam = ctx.queryParam("userid");
        int user = Integer.parseInt(userIdParam);
        
        List<ReviewDTO> topReviews = reviewService.getUserTopReviews(3, user);
        ctx.json(topReviews);
    }

    public void registerRoutes(Javalin app) {
        app.post("/api/reviews",      this::addReview); 
        app.get ("/api/reviews",      this::getTopReviews);
        app.get("/api/reviews/top", this::getUserTopReviews);
    }
}
