package com.mubification.services;

import com.mubification.models.Review;
import com.mubification.repositories.ReviewRepository;

public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService() {
        this.reviewRepository = new ReviewRepository();
    }

    public Review addReview(Review review) {
        return reviewRepository.addReview(review);
    }

}
