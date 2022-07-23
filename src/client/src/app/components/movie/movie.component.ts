import { Component, Input, OnInit } from '@angular/core';
import { Movie } from 'src/app/interfaces/movie';

@Component({
  selector: '.movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  @Input()movie:Movie={
    id:0,
    title : "",
    director: "",
    gen: "",
    year: 0
  }
  constructor() { }

  ngOnInit(): void {
  }

}
