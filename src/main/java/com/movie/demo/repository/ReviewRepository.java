package com.movie.demo.repository;

import com.movie.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // You can add custom finder methods here later, if needed.

}