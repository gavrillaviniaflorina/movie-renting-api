import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Movie } from 'src/app/interfaces/movie';
import { NotificationService } from 'src/app/services/notification.service';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-new-movie',
  templateUrl: './new-movie.component.html',
  styleUrls: ['./new-movie.component.css']
})
export class NewMovieComponent implements OnInit {

  constructor(private notificationService:NotificationService,private movieService:BookService, private router:Router) { }


//@ts-ignore
  movieForm:FormGroup;
  
  movie:Movie={
    title:"",
    director: "",
    gen:"",
    year:0
  };

  ngOnInit(): void {

    this.initForm();
  }
  
  private initForm():void{

    this.movieForm=new FormGroup({

      'title':new FormControl("",Validators.required),
      'director':new FormControl("",Validators.required),
      'gen':new FormControl("",Validators.required),
      'year':new FormControl("",Validators.required)
    })
  }
  


  public onClick(event:Event){
  
    if(this.movieForm.valid==true){

      this.movie.title=this.movieForm.value['title'];
      this.movie.director=this.movieForm.value['director'];
      this.movie.gen=this.movieForm.value['gen'];
      this.movie.year=this.movieForm.value['year'];


      this.movieService.addMovie(this.movie).subscribe(response=>{       
        this.success();
        this.router.navigate(['/movies']);
      });
    }
    else{
      this.validare();
    }
    
  }

 

  public validare(){

    for(let e in this.movieForm.value){

      if(this.movieForm.value[e]==""){

        this.notificationService.onError(e+ " is required");
      }
    }

  }

  public OnCancel(event:Event){

    this.router.navigate(['/movies']);
  }

  public success(){
    this.notificationService.onSuccess("The movie was created");
  }
  
}
