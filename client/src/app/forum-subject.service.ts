import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';

export interface ForumSubject {
  id?: number;
  title: string;
}

@Injectable()
export class ForumSubjectService {

  constructor(private auth: AuthService, private http: HttpClient) { }

  getForumSubjects() {
    return this.http.get<ForumSubject[]>('/api/subjects');
  }

  addForumSubjects(subject: ForumSubject) {
    const headers = this.auth.getHeaders();

    return this.http.post<ForumSubject[]>('/api/subjects', subject, { headers });
  }

}
