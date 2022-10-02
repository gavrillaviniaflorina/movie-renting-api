package com.example.moviesrentingapi.dto;


import lombok.Data;

@Data
public class MovieDto {

    private String title;
    private String director;
    private String gen;
    private int year;

    public MovieDto(String title, String director, String gen, int year) {
        this.title = title;
        this.director = director;
        this.gen = gen;
        this.year = year;

    }
}
