// Content for UserRepository.java
package com.movie.demo.repository;

import com.movie.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}