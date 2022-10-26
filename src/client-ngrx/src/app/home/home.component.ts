import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable, Subscription } from 'rxjs';
import { Movie } from '../movie/movie.model';
import { MovieService } from '../services/movie.service';
import * as fromApp from '../store/app.reducer';

@Component({
  selector: 'app-home',
  templateUrl:  './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  movies: Movie[] = [];
  moviesLoaded= false;


  moviesSubscription:Subscription= new Subscription();

    // @ts-ignore
  public movies$: Observable< {movies: Movie[];}>


  constructor(private store: Store<fromApp.AppState>, private router:  Router) { 


    
  }
  ngOnDestroy(): void {
    this.moviesSubscription.unsubscribe();
  }

  ngOnInit(): void {

    this.movies$=this.store.select('movieList')
    this.moviesSubscription=this.movies$.subscribe(e=>{
      console.log(e);
      this.movies=e.movies;
      this.moviesLoaded=true;
    });

  }

  onClick(){
    this.router.navigate(['/movies/new']);
  }

}
