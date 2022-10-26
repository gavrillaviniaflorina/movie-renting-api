export class Movie{

    public id?:number=0;
    public title:string="";
    public director:string="";
    public gen:string="";
    public year:number=0;

   constructor(id:number, title:string, director:string, gen:string, year:number){
    this.id=id;
    this.title=title;
    this.director=director;
    this.gen=gen;
    this.year=year;
   } 
}