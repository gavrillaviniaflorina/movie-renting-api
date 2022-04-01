package com.example.moviesrentingapi;
import com.example.moviesrentingapi.model.Movie;
import com.github.javafaker.Faker;
import com.example.moviesrentingapi.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
       };

    }

}
