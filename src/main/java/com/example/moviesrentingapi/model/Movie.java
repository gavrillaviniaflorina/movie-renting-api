package com.example.moviesrentingapi.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name="Movie")
@Table(name="movies")

@NoArgsConstructor
public class Movie implements Comparable{

    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Id
    Long id;
    private String title;
    private String director;
    private String gen;
    private int year;

    public Movie(String title, String director, String gen, int year) {
        this.title = title;
        this.director = director;
        this.gen = gen;
        this.year = year;
    }

    @Override
    public int compareTo(Object o) {

        Movie movie=(Movie) o;
        return this.title.compareTo(movie.title);
    }


}
