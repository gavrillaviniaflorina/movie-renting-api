package com.example.moviesrentingapi.controller;

import com.example.moviesrentingapi.MoviesRentingApiApplication;
import com.example.moviesrentingapi.dto.MovieDto;
import com.example.moviesrentingapi.model.Movie;
import com.example.moviesrentingapi.repository.MovieRepository;
import com.example.moviesrentingapi.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesRentingApiApplication.class)
@AutoConfigureMockMvc
class MovieControllerTest {

    @MockBean
    private MovieRepository movieRepositoryMock;

    @MockBean
    private MovieService movieServiceMock;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMovies() throws Exception {

        List<Movie> movies=new ArrayList<>();
        Movie movie=new Movie("test1","autor1","gen1",2022);
        movies.add(movie);
        when(movieServiceMock.getAll()).thenReturn(movies);

        ObjectMapper mapper=new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(movies)));

    }

    @Test
    void addMovie() throws Exception {

        ObjectMapper mapper=new ObjectMapper();
        Movie movie=new Movie("test2","autor2","gen2",2022);
        movie.setId(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movies/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(mapper.writeValueAsString(movie))))
                .andExpect(status().isOk());

    }

    @Test
    void deleteMoive() throws Exception {


        Movie movie=new Movie("test5","autor","gen",2019);
        movie.setId(1L);
        when(movieServiceMock.findMovie(1L)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/v1/movies/delete/%d",1L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void findMoiveById() throws Exception {

        ObjectMapper mapper=new ObjectMapper();
        Movie movie=new Movie("test4","autor4","gen",2021);
        movie.setId(1L);
        when(movieServiceMock.findMovie(1L)).thenReturn(movie);

        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/v1/movies/findMovie/%d",1L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(mapper.writeValueAsString(movie)));
    }


//    @Test
//    void updateMovie() throws Exception {
//        ObjectMapper mapper=new ObjectMapper();
//
//        MovieDto movieDto=new MovieDto("test2","autor3","gen2",2022);
//        Movie movie=new Movie(movieDto.getTitle(),movieDto.getDirector(),movieDto.getGen(),movieDto.getYear());
//        movie.setId(1L);
//        when(movieServiceMock.findMovie(1L)).thenReturn(movie);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movies/update/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(String.valueOf(mapper.writeValueAsString(movieDto))))
//                .andExpect(content().string(mapper.writeValueAsString(movie)))
//                .andExpect(status().isOk());
//
//
//    }

}