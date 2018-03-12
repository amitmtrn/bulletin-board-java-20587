import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {WebStorageModule, LocalStorageService} from 'angular2-localstorage';

import { AuthService } from './auth.service';
import { TopicService } from './topic.service';

import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { Error404PageComponent } from './error-404-page/error-404-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AddTopicPageComponent } from './add-topic-page/add-topic-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'add-topic', component: AddTopicPageComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: '**', component: Error404PageComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    Error404PageComponent,
    HomePageComponent,
    AddTopicPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    WebStorageModule,
    RouterModule.forRoot(routes)
  ],
  providers: [AuthService, TopicService, LocalStorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
