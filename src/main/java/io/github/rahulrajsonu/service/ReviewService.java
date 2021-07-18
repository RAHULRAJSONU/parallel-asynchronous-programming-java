package io.github.rahulrajsonu.service;


import io.github.rahulrajsonu.domain.Review;

import static io.github.rahulrajsonu.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
