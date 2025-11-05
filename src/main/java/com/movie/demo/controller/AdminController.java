package com.movie.demo.controller;

import com.movie.demo.entity.Movies;
import com.movie.demo.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MoviesRepository moviesRepository;

    @GetMapping("")
    public String adminHome() {
        return "admin-home";
    }

    @GetMapping("/add-movie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movies", new Movies());
        return "add-movie";
    }

    @PostMapping("/add-movie")
    public String processAddMovie(@ModelAttribute Movies movies) {
        moviesRepository.save(movies);
        return "redirect:/admin/movies";
    }

    @GetMapping("/movies")
    public String viewAllMovies(Model model) {
        model.addAttribute("moviesList", moviesRepository.findAll());
        return "movie-list";
    }
}