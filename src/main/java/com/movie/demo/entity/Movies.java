package com.movie.demo.entity;

import jakarta.persistence.*;

@Entity
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String showDate;
    private String showTime;
    private int totalSeats;

    // Getters and setters
}