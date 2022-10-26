import { EmptyExpr } from "@angular/compiler";
import { Injectable } from "@angular/core";
import { Actions, createEffect, Effect, ofType } from "@ngrx/effects";
import { catchError, EMPTY, map, mergeMap, tap } from "rxjs";
import { MovieService } from "src/app/services/movie.service";
import * as MovieActions from './movie-list.actions';

@Injectable()
export class MovieEffect{

    constructor(
        private actions$: Actions,
        private movieService: MovieService
    ){}

    @Effect()
    fetch=this.actions$.pipe(
        ofType('[Movies Page] Load Movies'),
        tap(()=> console.log('aici')),
        mergeMap(()=> this.movieService.getMovies().pipe(
            tap(console.log),
            map(response => new MovieActions.SetMovie(response.movie)),
            catchError(error =>{
                
                console.error(error);
                return EMPTY;
            })
        ))   
    )

    loadMovies$ = createEffect(()=>
        this.actions$.pipe(
            ofType('[Movies Page] Load Movies'),
            tap(()=> console.log('aici')),
            mergeMap(()=> this.movieService.getMovies().pipe(
                tap(console.log),
                map(response => new MovieActions.SetMovie(response.movie)),
                catchError(error =>{

                    console.error(error);
                    return EMPTY;
                })
            ))   
        )
    )
}