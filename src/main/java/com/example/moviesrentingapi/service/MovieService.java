package com.example.moviesrentingapi.service;

import com.example.moviesrentingapi.dto.MovieDto;
import com.example.moviesrentingapi.exceptions.MovieExistsException;
import com.example.moviesrentingapi.exceptions.MovieNotFoundException;
import com.example.moviesrentingapi.exceptions.NoMovieException;
import com.example.moviesrentingapi.model.Movie;
import com.example.moviesrentingapi.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public List<Movie>getAll(){
        List<Movie> movies=this.movieRepository.findAll();

        if(movies.size()==0){
            throw new NoMovieException("No movie found");
        }


        return movies;
    }

    public void addMovie(MovieDto movie){

        Boolean exist=this.movieRepository.titleExists(movie.getTitle()).isPresent();

        if(exist){
            throw new MovieExistsException(

                    "The movie "+movie.getTitle()+" exists"
            );
        }

        movieRepository.save(new Movie(movie.getTitle(),movie.getDirector(),movie.getGen(),movie.getYear()));
    }


    public void updateMovie(MovieDto updateMovie){
        Boolean movieExists=this.movieRepository.titleExists(updateMovie.getTitle()).isPresent();

        if(!movieExists){
            throw new MovieNotFoundException(
                    "The book does not exist"
            );
        }

        Movie movieHelper=this.movieRepository.titleExists(updateMovie.getTitle()).get();
        this.movieRepository.findById(movieHelper.getId()).map(book -> {

            book.setTitle(updateMovie.getTitle());
            book.setDirector(updateMovie.getDirector());
            book.setGen(updateMovie.getGen());
            book.setYear(updateMovie.getYear());


            return movieRepository.save(book);
        });
    }

    public Movie findMovie(Long id){
        Boolean bookExists=movieRepository.findById(id).isPresent();

        if(!bookExists){
            throw new MovieNotFoundException(

                    "The movie does not exist"
            );

        }else{

            return movieRepository.findById(id).get();
        }

    }


    public void deleteMovie(Long id){

        Boolean movieExists=this.movieRepository.findById(id).isPresent();

        if(!movieExists){
            throw  new MovieNotFoundException("Movie not found");
        }

        movieRepository.deleteById(id);
    }
}
