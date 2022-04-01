package com.example.moviesrentingapi.controller;


import com.example.moviesrentingapi.MoviesRentingApiApplication;
import com.example.moviesrentingapi.model.Movie;
import com.example.moviesrentingapi.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/movies")
public class MovieController {

    private MovieRepository movieRepository;


    public MovieController(MovieRepository movieRepository){
        this.movieRepository=movieRepository;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return  new ResponseEntity<List<Movie>>(movieRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(value = HttpStatus.OK)
    public void addMoive(@RequestBody Movie movie){
        this.movieRepository.save(movie);
    }


    @PutMapping("/update/{id}")
    public  void updateMovie(@RequestBody Movie updatedMovie,@PathVariable Long id){

        this.movieRepository.findById(id).map(movie -> {

            movie.setTitle(updatedMovie.getTitle());
            movie.setDirector(updatedMovie.getDirector());
            movie.setGen(updatedMovie.getGen());
            movie.setYear(updatedMovie.getYear());


            return movieRepository.save(movie);
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMoive(@PathVariable Long id){
        this.movieRepository.deleteById(id);

        return  new ResponseEntity<>("Ceva",HttpStatus.ACCEPTED);
    }
}
