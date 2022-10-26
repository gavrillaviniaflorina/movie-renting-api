import { Action } from "@ngrx/store";
import { Movie } from "../movie.model";

export const ADD_MOVIE ='ADD_MOVIE';
// export const DELETE_MOVIE='DELETE_MOVIE';
export const UPDATE_MOVIE='UPDATE_MOVIE';
export const SET_MOVIE='[Movies] Set Movies';
export const FETCH_MOVIES='[Movies] Fetch Movies';

export class SetMovie implements Action {
    readonly type: string = SET_MOVIE;
    constructor(public payload: Movie[]){ }   
}

export class FetchMovies implements Action {
    readonly type: string = FETCH_MOVIES; 
}

export class AddMovie implements Action {
    readonly type: string = ADD_MOVIE;
    constructor(public payload: Movie){}
}

export class UpdateMovie implements Action {
    readonly type: string = UPDATE_MOVIE;
    constructor(public payload: Movie){}
}

// export class DeleteMovie implements Action {
//     readonly type: string = DELETE_MOVIE;
// }

export type MovieListAction=
 AddMovie|UpdateMovie|SetMovie;

