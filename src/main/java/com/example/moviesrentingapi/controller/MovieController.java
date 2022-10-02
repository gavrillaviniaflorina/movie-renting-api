package com.example.moviesrentingapi.controller;


import com.example.moviesrentingapi.dto.MovieDto;
import com.example.moviesrentingapi.model.Movie;
import com.example.moviesrentingapi.repository.MovieRepository;
import com.example.moviesrentingapi.service.MovieService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/movies")
public class MovieController {

    private MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<List<Movie>>(movieService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie){
        this.movieService.addMovie(movie);

        return new ResponseEntity<>(movie,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody MovieDto updateMovie,@PathVariable Long id){
        this.movieService.updateMovie(updateMovie);

        return new ResponseEntity<Movie>(this.movieService.findMovie(id),HttpStatus.OK);
    }


    @GetMapping("/findMovie/{id}")
    public ResponseEntity<Movie> findMoiveById(@PathVariable Long id){
        return new ResponseEntity<>(this.movieService.findMovie(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id){

        Movie movie=this.movieService.findMovie(id);
        this.movieService.deleteMovie(id);
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);

    }

//
//
//    @PutMapping("/update/{id}")
//    public void updateMovie(@RequestBody Movie updatedMovie, @PathVariable Long id) {
//
//        this.movieRepository.findById(id).map(movie -> {
//
//            movie.setTitle(updatedMovie.getTitle());
//            movie.setDirector(updatedMovie.getDirector());
//            movie.setGen(updatedMovie.getGen());
//            movie.setYear(updatedMovie.getYear());
//
//
//            return movieRepository.save(movie);
//        });
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteMoive(@PathVariable Long id) {
//        this.movieRepository.deleteById(id);
//
//        return new ResponseEntity<>("Sters", HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/sortByTitle")
//    public ResponseEntity<List<Movie>> sortMoives() {
//        List<Movie> movies = movieRepository.findAll().stream().sorted().collect(Collectors.toList());
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/sortByDirector")
//    public ResponseEntity<List<Movie>> sortDirecors() {
//        List<Movie> movies = movieRepository.findAll().stream().sorted(Comparator.comparing(Movie::getDirector)).collect(Collectors.toList());
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/sortByYear")
//    public ResponseEntity<List<Movie>> sortMoivesByYear() {
//        List<Movie> movies = movieRepository.findAll().stream().sorted(Comparator.comparingInt(Movie::getYear)).collect(Collectors.toList());
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/sortByYearDescending")
//    public ResponseEntity<List<Movie>> sortMoivesByYearDescending() {
//        List<Movie> movies = movieRepository.findAll().stream().sorted(Comparator.comparingInt(Movie::getYear).reversed()).collect(Collectors.toList());
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/sortByGenre/{gen}")
//    public ResponseEntity<List<Movie>> sortMoivesByGen(@PathVariable String gen) {
//        List<Movie> movies = movieRepository.findAll().stream().filter(movie -> movie.getGen().equals(gen)).collect(Collectors.toList());
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/getGenres")
//    public ResponseEntity<List<String>> getGenre() {
//        List<String> genres = this.movieRepository.findAll().stream().map(Movie::getGen).distinct().collect(Collectors.toList());
//        return new ResponseEntity<>(genres, HttpStatus.OK);
//    }
//
//    @GetMapping("/getDirectors")
//    public ResponseEntity<List<String>> getDirectors(){
//        List<String> directors=this.movieRepository.findAll().stream().map(Movie::getDirector).distinct().collect(Collectors.toList());
//        return new ResponseEntity<>(directors,HttpStatus.OK);
//    }
//
//    @GetMapping("/getSearched/{searched}")
//    public ResponseEntity<List<Movie>> getSearched(@PathVariable String searched){
//      List<Movie> movies=movieRepository.getTheMoviesThatContainTheSearch(searched);
//      return new ResponseEntity<>(movies, HttpStatus.OK);
//    }
//
//    @GetMapping("/filterMoviesByDirectorAndGenre")
//    public ResponseEntity<List<Movie>> filterByDirectorAndMovie(@RequestParam String director, @RequestParam String genre){
//        List<Movie> movies=movieRepository.filterMoviesByDirectorAndGenre(director,genre);
//        return new ResponseEntity<>(movies,HttpStatus.OK);
//    }

}
