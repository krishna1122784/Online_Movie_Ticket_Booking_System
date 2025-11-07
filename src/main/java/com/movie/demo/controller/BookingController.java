package com.movie.demo.controller;

import com.movie.demo.entity.Movies;
import com.movie.demo.entity.Ticket;
import com.movie.demo.repository.MoviesRepository;
import com.movie.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private TicketRepository ticketRepository;

    // --- 1. Show Booking Form (GET /booking) ---
    @GetMapping
    public String showBookingPage(Model model) {
        List<Movies> moviesList = moviesRepository.findAll();

        model.addAttribute("moviesList", moviesList);
        model.addAttribute("ticket", new Ticket());

        // TEMPORARY: Hardcode User ID 1 for testing
        model.addAttribute("currentUserId", 1L);

        return "book-ticket";
    }

    // --- 2. Process Booking (POST /booking) ---
    @PostMapping
    public String processBooking(@ModelAttribute("ticket") Ticket ticket) {

        // 1. Calculate seats_booked
        String seatNumbers = ticket.getSeatNumber();
        int bookedCount = 0;

        if (seatNumbers != null && !seatNumbers.trim().isEmpty()) {
            bookedCount = seatNumbers.split(",").length;
        }

        ticket.setSeatsBooked(bookedCount);

        // 2. Set the price (Example: $10 per seat)
        if (bookedCount > 0) {
            ticket.setPrice(bookedCount * 10.00);
        } else {
            ticket.setPrice(0.00);
        }

        // 3. Save the ticket to the database
        ticketRepository.save(ticket);

        // 4. Redirect to the review confirmation page with all details URL-encoded
        String encodedMovieTitle = URLEncoder.encode(ticket.getMovieTitle(), StandardCharsets.UTF_8);
        String encodedSeatNumber = URLEncoder.encode(ticket.getSeatNumber(), StandardCharsets.UTF_8);
        String encodedShowTime = URLEncoder.encode(ticket.getShowTime(), StandardCharsets.UTF_8);

        // Construct the full redirect URL
        String redirectUrl = "/review/confirm?movieTitle=" + encodedMovieTitle +
                "&seatNumber=" + encodedSeatNumber +
                "&showTime=" + encodedShowTime;

        return "redirect:" + redirectUrl;
    }
}