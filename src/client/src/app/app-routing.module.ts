import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from '../app/components/home/home.component';
import { NewMovieComponent } from '../app/components/new-movie/new-movie.component';
import { UpdateMovieComponent } from '../app/components/update-movie/update-movie.component';

const routes: Routes = [

{path:'', redirectTo:'/movies',pathMatch:'full'},
{path:'movies',component:HomeComponent},
{path:'movies/new',component:NewMovieComponent},
{path:'movies/update/:id',component:UpdateMovieComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
