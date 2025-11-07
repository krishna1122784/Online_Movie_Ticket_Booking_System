package com.movie.demo.controller;

import com.movie.demo.entity.User;
import com.movie.demo.entity.Ticket; // Needed for fetching tickets
import com.movie.demo.repository.UserRepository;
import com.movie.demo.repository.TicketRepository; // Needed for fetching tickets

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository; // Injected for dashboard ticket fetching

    // --- 1. GET Mapping: Show the Signup Form ---
    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    // --- 2. POST Mapping: Process Registration and Redirect to Booking ---
    @PostMapping("/register-user")
    public String registerUser(@ModelAttribute("user") User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        userRepository.save(user);

        return "redirect:/booking";
    }

    // --- 3. Interactive User Dashboard Mapping ---
    @GetMapping("/user")
    public String showUserDashboard(Model model) {

        // --- TEMPORARY LOGIC FOR DEMONSTRATION (REPLACE WITH SPRING SECURITY LATER) ---
        Long currentUserId = 1L;

        // 1. Fetch User Details, using .orElseGet to resolve the constructor error
        User currentUser = userRepository.findById(currentUserId)
                .orElseGet(() -> {
                    User fallbackUser = new User(); // Uses the supported no-argument constructor
                    fallbackUser.setName("Guest User");
                    fallbackUser.setEmail("N/A");
                    fallbackUser.setRole("GUEST");
                    return fallbackUser;
                });

        model.addAttribute("user", currentUser);

        // 2. Fetch Tickets for that User
        List<Ticket> userTickets = List.of();
        // Only fetch tickets if we have a real ID (i.e., not the fallback user)
        if (currentUser.getId() != null) {
            userTickets = ticketRepository.findByUserId(currentUserId);
        }
        model.addAttribute("tickets", userTickets);

        return "user-dashboard";
    }
}