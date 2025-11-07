package com.movie.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Linking and Form Fields
    private Long userId;
    private Long movieId;

    // Booking Details
    private String movieTitle;
    private String showTime;
    private String seatNumber;

    // Field to satisfy database NOT NULL constraint
    private Integer seatsBooked;

    private Double price;

    // --- Constructors ---
    public Ticket() {}

    // --- Getters and Setters (REQUIRED for JPA and Thymeleaf) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }

    public String getShowTime() { return showTime; }
    public void setShowTime(String showTime) { this.showTime = showTime; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    // Getter/Setter for seatsBooked
    public Integer getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(Integer seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}