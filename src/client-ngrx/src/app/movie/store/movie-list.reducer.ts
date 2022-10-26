import { Movie } from "../movie.model";
import * as Actions from './movie-list.actions';

export interface State {
    movies : Movie[];
}

const initialState: State = {
    movies: []
}

export function movieListReducer(
    state: State = initialState,
    action: Actions.MovieListAction
){
    switch (action.type){
        case Actions.SET_MOVIE:
            return {
                ...state,
                //@ts-ignore
                movies: [...action.payload]
            };
        default:
            console.log("ceva");
            return state;    
    }
}