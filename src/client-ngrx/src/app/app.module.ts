import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { StoreRouterConnectingModule } from '@ngrx/router-store';
import { environment } from 'src/environments/environment';
import { MovieComponent } from './movie/movie.component';
import {HttpClientModule} from "@angular/common/http";
import  {appReducer} from './store/app.reducer';
import { HomeComponent } from './home/home.component';
import { EffectsModule } from '@ngrx/effects';
import { MovieEffect } from './movie/store/movie-list.effect';
import {StoreModule} from "@ngrx/store";

@NgModule({
  declarations: [
    AppComponent,
    MovieComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    StoreDevtoolsModule.instrument({ logOnly: environment.production }),
    StoreModule.forRoot(appReducer),
    StoreRouterConnectingModule.forRoot(),
    EffectsModule.forRoot([MovieEffect]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
