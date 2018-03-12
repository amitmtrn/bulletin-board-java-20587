import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

export interface Topic {
  title: string;
  body: string;
}

@Injectable()
export class TopicService {

  constructor(private auth: AuthService, private http: HttpClient) { }

  addTopic(topic): Observable<Topic> {
    const headers = this.auth.getHeaders();

    return this.http.post<Topic>('/api/topics', topic, { headers });
  }

}
