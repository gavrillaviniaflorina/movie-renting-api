import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Movie } from 'src/app/interfaces/movie';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  public movies:Movie[]=[];
  constructor(private movieService:BookService, private router:Router) { }

  ngOnInit(): void {

    this.movieService.moviesChanged.subscribe(response=>{
      this.movies=[...response];

     // console.log(response);
      
    })
  }

  public goToNewMovie():void{
    this.router.navigate(['/movies/new']);
  }

}
