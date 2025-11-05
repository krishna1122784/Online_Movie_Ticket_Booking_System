package com.movie.demo.controller;

import com.movie.demo.entity.Ticket;
import com.movie.demo.repository.TicketRepository;
import com.movie.demo.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @GetMapping("/book-ticket")
    public String showBookingForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("moviesList", moviesRepository.findAll());
        return "book-ticket";
    }

    @PostMapping("/book-ticket")
    public String processBooking(@ModelAttribute Ticket ticket) {
        ticketRepository.save(ticket);
        return "redirect:/booking-success";
    }

    @GetMapping("/booking-success")
    public String bookingSuccess() {
        return "booking-success";
    }
}