package com.example.moviesrentingapi.service;

import com.example.moviesrentingapi.dto.MovieDto;
import com.example.moviesrentingapi.exceptions.MovieExistsException;
import com.example.moviesrentingapi.exceptions.MovieNotFoundException;
import com.example.moviesrentingapi.exceptions.NoMovieException;
import com.example.moviesrentingapi.model.Movie;
import com.example.moviesrentingapi.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;

class MovieServiceTest {

    @Mock
    private MovieRepository  movieRepositoryMock;

    @Captor
    private ArgumentCaptor<Movie> movieArgumentCaptor;

    private MovieService underTest;

    private ModelMapper modelMapper=new ModelMapper();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        underTest=new MovieService(movieRepositoryMock);
    }

    @Test
    void itShouldGettAllMovies(){
        List<Movie> movies =new ArrayList<>();

        for(int i=0;i<10;i++){
            movies.add(new Movie());
        }

        doReturn(movies).when(movieRepositoryMock).findAll();
        assertThat(underTest.getAll()).isEqualTo(movies);

    }

    @Test
    void itShouldNotGetAllBooks(){
        List<Movie> movies=new ArrayList<>();
        doReturn(movies).when(movieRepositoryMock).findAll();

        assertThatThrownBy(()->underTest.getAll()).isInstanceOf(NoMovieException.class).hasMessageContaining("No movie found");
    }

    @Test
    void itShouldNotAddMovie(){
        MovieDto movieDto=new MovieDto("ana","are","mere",1);
        doReturn(Optional.of(movieDto)).when(movieRepositoryMock).titleExists(movieDto.getTitle());
        assertThatThrownBy(()->underTest.addMovie(movieDto)).isInstanceOf(MovieExistsException.class).hasMessageContaining( "The movie "+movieDto.getTitle()+" exists");
    }

    @Test
    void itShouldAddMovie(){



        MovieDto movieDto=new MovieDto("Ion","Liviu Rebreanu","drama",1920);

        doReturn(Optional.empty()).when(movieRepositoryMock).titleExists(movieDto.getTitle());

        underTest.addMovie(movieDto);

        then(movieRepositoryMock).should().save(movieArgumentCaptor.capture());
        Movie movie=movieArgumentCaptor.getValue();
        MovieDto movieDto1=new MovieDto(movie.getTitle(), movie.getDirector(), movie.getGen(),movie.getYear());
        Assertions.assertThat(movieDto1).isEqualTo(movieDto);

    }


    @Test
    void itShouldDeleteBook(){
        Movie movie=new Movie("w","e","q",12);
        movie.setId(337L);

        doReturn(Optional.of(movie)).when(movieRepositoryMock).findById(337L);
        underTest.deleteMovie(movie.getId());

        BDDMockito.then(movieRepositoryMock).should().deleteById(movie.getId());
    }

    @Test
    public void itShouldNotDeleteABook(){
       Movie movie=new Movie("Ion","Liviu Rebreanu","drama",1920);
        movie.setId(337L);
        doReturn(Optional.empty()).when(movieRepositoryMock).titleExists(movie.getTitle());

        Assertions.assertThatThrownBy(()->underTest.deleteMovie(movie.getId())).isInstanceOf(MovieNotFoundException.class).hasMessageContaining("Movie not found");


    }



    @Test
    public void itShouldNotUpdatePerson(){
        MovieDto movieDto=new MovieDto("w","e","q",12);


        doReturn(Optional.empty()).when(movieRepositoryMock).titleExists(movieDto.getTitle());

        Assertions.assertThatThrownBy(()->underTest.updateMovie(movieDto)).isInstanceOf(MovieNotFoundException.class).hasMessageContaining("The book does not exist");

    }
}