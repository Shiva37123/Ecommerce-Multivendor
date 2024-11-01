package com.ben.controller;

import com.ben.modal.Product;
import com.ben.modal.Review;
import com.ben.modal.User;
import com.ben.modal.Wishlist;
import com.ben.repository.ReviewRepository;
import com.ben.request.CreateReviewRequest;
import com.ben.response.ApiResponse;
import com.ben.service.ProductService;
import com.ben.service.ReviewService;
import com.ben.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/products/{productId}/review")
    public ResponseEntity<List<Review>> getReviewByProductId(@PathVariable Long productId
                                                             ) throws Exception {


        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/products/{productId}/review")
    public ResponseEntity<Review> writeReview(
            @RequestBody CreateReviewRequest req,
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        Product product = productService.findProductById(productId);
        User user = userService.findUserByJwtToken(jwt);

        Review review = reviewService.createReview(req, user, product);
        return ResponseEntity.ok(review);
    }

    @PatchMapping("/review/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @RequestBody CreateReviewRequest req,
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Review review = reviewService.updateReview(reviewId, req.getReviewText(), req.getReviewRating(), user.getId());
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse res = new ApiResponse();
        res.setMessage("Review deleted successfully");

        return ResponseEntity.ok(res);
    }





}
