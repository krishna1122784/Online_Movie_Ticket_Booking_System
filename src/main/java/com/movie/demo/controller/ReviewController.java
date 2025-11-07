package com.movie.demo.controller;

import com.movie.demo.entity.Review;
import com.movie.demo.entity.Ticket;
import com.movie.demo.repository.TicketRepository;
import com.movie.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    // --- 1. Booking Success & Review Prompt ---
    @GetMapping("/review/confirm")
    public String bookingConfirmation(@RequestParam("movieTitle") String encodedMovieTitle,
                                      @RequestParam("seatNumber") String encodedSeatNumber,
                                      @RequestParam("showTime") String encodedShowTime,
                                      Model model) {

        String movieTitle = URLDecoder.decode(encodedMovieTitle, StandardCharsets.UTF_8);
        String seatNumber = URLDecoder.decode(encodedSeatNumber, StandardCharsets.UTF_8);
        String showTime = URLDecoder.decode(encodedShowTime, StandardCharsets.UTF_8);

        model.addAttribute("movieTitle", movieTitle);
        model.addAttribute("seatNumber", seatNumber);
        model.addAttribute("showTime", showTime);
        model.addAttribute("message", "Lights, Camera, Action! Get ready for the show!");

        return "booking-success-review";
    }

    // --- 2. Movie Review Form (TEMPORARILY UNSECURED CHECK) ---
    @GetMapping("/review/form")
    public String showReviewForm(@RequestParam("movieTitle") String movieTitle, Model model) {

        // TEMPORARILY BYPASSED SECURITY CHECK
        boolean hasBooked = true;

        if (hasBooked) {
            model.addAttribute("movieTitle", movieTitle);
            model.addAttribute("review", new Review());
            return "review-form";
        } else {
            model.addAttribute("error", "Access Denied: You must book a ticket for " + movieTitle + " before leaving a review.");
            return "error-page";
        }
    }

    // --- 3. Review Submission POST Mapping ---
    @PostMapping("/review/submit")
    public String submitReview(@ModelAttribute("review") Review review) {

        // TEMPORARY: Set user ID
        review.setUserId(1L);

        // Save the review to the database
        reviewRepository.save(review);

        // Redirect to a success page
        return "redirect:/review/submitted";
    }

    // --- 4. Review Submission Success Page (MISSING MAPPING ADDED) ---
    @GetMapping("/review/submitted")
    public String reviewSubmitted() {
        return "review-submitted";
    }
}