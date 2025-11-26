package com.mubification.services;

import com.mubification.models.Review;
import com.mubification.models.ReviewDTO;
import com.mubification.repositories.ReviewRepository;

import java.util.List;

public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService() {
        this.reviewRepository = new ReviewRepository();
    }

    public Review addReview(Review review) {
        return reviewRepository.addReview(review);
    }

    public List<ReviewDTO> getTopReviews(int limit) {
        return reviewRepository.getTopReviews(limit);
    }


}
