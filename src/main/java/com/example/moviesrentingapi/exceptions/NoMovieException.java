package com.example.moviesrentingapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoMovieException extends RuntimeException{

    public NoMovieException(String msg){
        super(msg);
    }
}
