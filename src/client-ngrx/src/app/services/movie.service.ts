import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomHttpResponse } from '../interfaces/custom-http-response';
import { Movie } from '../movie/movie.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private api=environment.api+"/api/v1/movies";

  //@ts-ignore
  moviesChanged= new BehaviorSubject<Movie[]>(null);
  constructor(private http: HttpClient) { 
   // this.getMovies().subscribe();
  }

  getMovies():Observable<CustomHttpResponse>{
    console.log('bau')
    return this.http.get<CustomHttpResponse>(this.api).pipe(
      tap(response=>{
        console.log("aici");
        this.moviesChanged.next(response.movies);
      }),
      catchError(this.handleError)
    );
  }
  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    let errorMessage: string;
    if (error.error instanceof ErrorEvent) {
      errorMessage = `A client error occurred - ${error.error.message}`;
    } else {
      if (error.error.reason) {
        errorMessage = `${error.error.reason} - Error code ${error.status}`;
      } else {
        errorMessage = `An error occurred - Error code ${error.status}`;
      }
    }
    return throwError(errorMessage);
  }

}
