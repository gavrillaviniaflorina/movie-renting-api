import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from 'src/app/interfaces/movie';
import { BookService } from 'src/app/services/book.service';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-update-movie',
  templateUrl: './update-movie.component.html',
  styleUrls: ['./update-movie.component.css']
})
export class UpdateMovieComponent implements OnInit {
  id:string='ceva';
  constructor(private notificationService:NotificationService, private movieService:BookService, private route:ActivatedRoute,  private router: Router) { }

 //@ts-ignore
  movieForm:FormGroup;
  
  movie:Movie={
    title:"ana chiar aare mere",
    director: "a",
    gen:"a",
    year:0
  };

  ngOnInit(): void {

    this.initForm(this.movie);
    this.route.params.subscribe(params=>{
   
      this.id=params['id'];

      this.movieService.findMoiveById(+this.id).subscribe(response=>{

        
          this.initForm(response);
        
      })

    })
  }
  
  private initForm(movie:Movie):void{

    this.movieForm=new FormGroup({

      'title':new FormControl(movie.title,Validators.required),
      'director':new FormControl(movie.director,Validators.required),
      'gen':new FormControl(movie.gen,Validators.required),
      'year':new FormControl(movie.year,Validators.required)
    })
  }
  


  public onClick(event:Event){
    

    if(this.movieForm.valid==true){
      
      this.movieService.updateMovie(this.movieForm.value,+this.id).subscribe(response=>{
        this.success();
        this.router.navigate(['/movies']);
      });
    }else{
      this.validare();
    }
  
  }

  public success(){
    this.notificationService.onSuccess("The movie was updated");
  }

  public OnCancel(event:Event){

    this.router.navigate(['/movies']);
  }
  

 

  public validare(){

    for(let e in this.movieForm.value){

      if(this.movieForm.value[e]==""){

        this.notificationService.onError(e+ " is required");
      }
    }

  }

 
  public successDelete(){
    this.notificationService.onSuccess("The movie was deleted");
  
  }


  public OnDelete(){
   
    this.movieService.deleteMovie(+this.id).subscribe(response=>{
    
     this.successDelete();

     this.router.navigate(['/movies']);

    })
  }

}
