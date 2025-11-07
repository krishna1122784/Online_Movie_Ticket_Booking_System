package com.movie.demo.repository;

import com.movie.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // --- ADD THIS METHOD ---
    /**
     * Finds all Ticket entities associated with a specific user ID.
     */
    List<Ticket> findByUserId(Long userId);
}