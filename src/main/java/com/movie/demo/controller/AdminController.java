package com.movie.demo.controller;

import com.movie.demo.entity.Movies; // Assuming your entity is named 'Movies'
import com.movie.demo.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MoviesRepository moviesRepository;

    // --- 1. Admin Home Page ---
    @GetMapping("")
    public String adminHome() {
        return "admin-home";
    }

    // --- 2. Show Add Movie Form (Used for both Add and Edit) ---
    @GetMapping("/add-movie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movies", new Movies());
        return "add-movie";
    }

    // --- 3. Process Add/Create Movie ---
    @PostMapping("/add-movie")
    public String processAddMovie(@ModelAttribute Movies movies) {
        moviesRepository.save(movies);
        return "redirect:/admin/movies";
    }

    // --- 4. View All Movies (Read) ---
    @GetMapping("/movies")
    public String viewAllMovies(Model model) {
        model.addAttribute("moviesList", moviesRepository.findAll());
        return "movie-list";
    }

    // --- 5. Show Edit Movie Form (Update - Step 1) ---
    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable Long id, Model model) {
        Movies movieToEdit = moviesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));

        model.addAttribute("movies", movieToEdit);
        return "add-movie";
    }

    // --- 6. Process Edit/Update Movie (Update - Step 2) ---
    @PostMapping("/update/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute Movies movies) {
        moviesRepository.save(movies);
        return "redirect:/admin/movies";
    }

    // --- 7. Delete Movie ---
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        Movies movieToDelete = moviesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));

        moviesRepository.delete(movieToDelete);
        return "redirect:/admin/movies";
    }
}