package com.example.moviesrentingapi.repository;

import com.example.moviesrentingapi.model.Movie;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query(value = "select * from movies where title like %?1% or director like %?1% or year like %?1% or gen like %?1%",nativeQuery = true)
    List<Movie> getTheMoviesThatContainTheSearch(String search);

    @Query(value = "select * from movies where director= ?1  and gen=?2",nativeQuery = true)
    List<Movie> filterMoviesByDirectorAndGenre(String director, String genre);


    @Query(value="select * from movies where title like  ?1", nativeQuery = true)
    Optional<Movie> titleExists(String title);
}
