import { Component, Input, OnInit } from '@angular/core';
import { Movie } from './movie.model';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {

  constructor() { }
  ngOnInit(): void {
    
  }
  @Input() movie: Movie=new Movie(0,"","","",0);

}
