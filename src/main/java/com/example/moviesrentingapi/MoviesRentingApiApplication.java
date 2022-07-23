package com.example.moviesrentingapi;
import com.example.moviesrentingapi.model.Movie;
import com.github.javafaker.Faker;
import com.example.moviesrentingapi.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class MoviesRentingApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoviesRentingApiApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(
            MovieRepository movieRepository){
        return args -> {
//            Faker faker=new Faker();
//
//
//            for(int i=0;i<=100;i++){
//                Movie movie=new Movie(faker.book().title(),faker.book().author(),faker.book().genre(),(int)(Math.random()*2000+1000));
//                movieRepository.save(movie);
//            }


          //  log.warn(movieRepository.getTheMoviesThatContainTheSearch("Lavinia").toString());
           // log.warn(movieRepository.filterMoviesByDirectorAndGenre("Clelia Conn","Textbook").toString());



            System.out.println(movieRepository.filterMoviesByDirectorAndGenre("Una Blanda","Short story"));
       };

    }

}
