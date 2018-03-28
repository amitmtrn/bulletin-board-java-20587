import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { WebStorageModule, LocalStorageService } from 'angular2-localstorage';
import { NgxEditorModule } from 'ngx-editor';

import { AuthService } from './auth.service';
import { TopicService } from './topic.service';
import { PrivateMessagesService } from './private-messages.service';
import { ForumSubjectService } from './forum-subject.service';

import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { Error404PageComponent } from './error-404-page/error-404-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AddTopicPageComponent } from './add-topic-page/add-topic-page.component';
import { TopicComponent } from './topic/topic.component';
import { AddCommentComponent } from './add-comment/add-comment.component';
import { PrivateMessagesPageComponent } from './private-messages-page/private-messages-page.component';
import { SendMessagePageComponent } from './send-message-page/send-message-page.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { ForumPageComponent } from './forum-page/forum-page.component';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'add-topic/:subjectId', component: AddTopicPageComponent },
  { path: 'edit-topic/:subjectId/:topicId', component: AddTopicPageComponent },
  { path: 'edit-comment/:topicId/:commentId', component: AddCommentComponent },
  { path: 'add-comment/:subjectId/:topicId', component: AddCommentComponent },
  { path: 'inbox', component: PrivateMessagesPageComponent, data: {type: 'inbox'} },
  { path: 'outbox', component: PrivateMessagesPageComponent, data: {type: 'outbox'} },
  { path: 'send-message', component: SendMessagePageComponent },
  { path: 'subject/:subjectId/topic/:topicId', component: TopicComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'subject/:subjectId', component: ForumPageComponent },
  { path: 'admin', component: AdminPageComponent },
  { path: '**', component: Error404PageComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    Error404PageComponent,
    HomePageComponent,
    AddTopicPageComponent,
    TopicComponent,
    AddCommentComponent,
    PrivateMessagesPageComponent,
    SendMessagePageComponent,
    AdminPageComponent,
    ForumPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    WebStorageModule,
    NgxEditorModule,
    NgbModule.forRoot(),
    RouterModule.forRoot(routes)
  ],
  providers: [
    AuthService,
    TopicService,
    LocalStorageService,
    PrivateMessagesService,
    ForumSubjectService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
