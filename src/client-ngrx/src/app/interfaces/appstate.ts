import { ActionReducerMap } from '@ngrx/store';
import * as fromMovieList from '../movie/store/movie-list.reducer'

export interface AppState {
    movieList: fromMovieList.State;
}

export const appReducer: ActionReducerMap<AppState> = {
    //ts-ignore
    movieList: fromMovieList.movieListReducer
}