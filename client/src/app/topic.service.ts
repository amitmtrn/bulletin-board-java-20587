import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

export interface Comment {
  id: number;
  title: string;
  body: string;
}

export interface Topic {
  id: number;
  title: string;
  body: string;
  comments: Comment[];
  date?: Date;
  user?: string;
}

@Injectable()
export class TopicService {

  constructor(private auth: AuthService, private http: HttpClient) { }

  addComment(topicId, comment): Observable<any> {
    const headers = this.auth.getHeaders();

    return this.http.post(`/api/topics/${topicId}/comments`, comment, { headers });
  }

  deleteComment(commentId: string) {
    const headers = this.auth.getHeaders();

    return this.http.delete(`/api/comments/${commentId}`, { headers });
  }

  addTopic(subjectId, topic): Observable<Topic> {
    const headers = this.auth.getHeaders();

    return this.http.post<Topic>(`/api/topics/${subjectId}`, topic, { headers });
  }

  getTopics(subjectId): Observable<Topic[]> {
    return this.http.get<Topic[]>(`/api/topics/${subjectId}`);
  }

  deleteTopic(topicId: number) {
    const headers = this.auth.getHeaders();

    return this.http.delete('/api/topics/' + topicId, { headers });
  }

}
