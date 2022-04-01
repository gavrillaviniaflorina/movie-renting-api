package com.example.moviesrentingapi.repository;

import com.example.moviesrentingapi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
