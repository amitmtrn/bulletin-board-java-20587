import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

export interface PrivateMessage {
  id: number;
  to: string;
  from: string;
  body: string;
}

@Injectable()
export class PrivateMessagesService {

  constructor(private auth: AuthService, private http: HttpClient) { }

  sendPrivateMessage(privateMessage): Observable<any> {
    const headers = this.auth.getHeaders();

    return this.http.post(`/api/new-message`, privateMessage, { headers });
  }

  // deletePrivateMessage(commentId: string) {
  //   const headers = this.auth.getHeaders();

  //   return this.http.delete(`/api/comments/${commentId}`, { headers });
  // }

  getInboxMessages(): Observable<PrivateMessage[]> {
    const headers = this.auth.getHeaders();

    return this.http.get<PrivateMessage[]>('/api/private-messages/inbox', { headers });
  }

  getOutboxMessages(): Observable<PrivateMessage[]> {
    const headers = this.auth.getHeaders();

    return this.http.get<PrivateMessage[]>('/api/private-messages/outbox', { headers });
  }


}
