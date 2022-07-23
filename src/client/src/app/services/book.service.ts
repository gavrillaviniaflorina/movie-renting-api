import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movie } from '../interfaces/movie';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private api=environment.api+"/api/v1/movies";
  public moviesChanged=new BehaviorSubject<Movie[]>([]);

  constructor(private http:HttpClient) {

    this.getMovies().subscribe((response)=>{
    //  console.log(response);
    })

   }

   getMovies():Observable<Movie[]>{

    return this.http.get<Movie[]>(this.api).pipe(
      tap((response:Movie[])=>{

        this.moviesChanged.next(response)
      })
    )
   }

   addMovie(movie:Movie):Observable<Movie>{
    this.moviesChanged.next([...this.moviesChanged.value,movie]);
    return this.http.post<Movie>(this.api+"/add",movie);
   }


   updateMovie(movie:Movie,id:number):Observable<Movie>{
    this.moviesChanged.next([...this.moviesChanged.value.filter(e=>e.id!=id),movie]);
    return this.http.put<Movie>(this.api+`/update/${id}`,movie);
   }

   findMoiveById(id:number):Observable<Movie>{
    return this.http.get<Movie>(this.api+`/findMovie/${id}`);
  }


  deleteMovie(id:number):Observable<Movie>{
    this.moviesChanged.next([...this.moviesChanged.value.filter(e=>e.id!=id)]);
   return this.http.delete<Movie>(this.api+`/delete/${id}`).pipe(tap(console.log),catchError(this.handleError));
 }

 private handleError(error:HttpErrorResponse):Observable<never>{
  console.log(error);
  let errorMessage:string;

  if(error.error instanceof ErrorEvent){
    errorMessage=`A client error ocurred -${error.error.message}`;
  }else{

    if(error.error.reason){
      errorMessage=`${error.error.reason} - Error code ${error.status}`;
    }else{
      errorMessage=` An error ocurred -Error code ${error.status}`
    }
  }

  return throwError(errorMessage);

}

}
