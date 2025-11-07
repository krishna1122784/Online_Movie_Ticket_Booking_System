package com.movie.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_review") // Use a suitable table name
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The movie the review is for (e.g., "Pulp Fiction")
    private String movieTitle;

    // The ID of the user who submitted the review (link to your User table)
    private Long userId;

    // The rating (1-10)
    private Integer rating;

    // The comment/text
    @Column(columnDefinition = "TEXT")
    private String comment;

    // --- Constructors ---
    public Review() {}

    // --- Getters and Setters (REQUIRED) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}