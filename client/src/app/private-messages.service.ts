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

    return this.http.post(`/api/private-messages`, privateMessage, { headers });
  }

  deletePrivateMessage(privateMessageId: number) {
    const headers = this.auth.getHeaders();

    return this.http.delete(`/api/private-messages/${privateMessageId}`, { headers });
  }

  getInboxMessages(): Observable<PrivateMessage[]> {
    const headers = this.auth.getHeaders();

    return this.http.get<PrivateMessage[]>('/api/private-messages/inbox', { headers });
  }

  getOutboxMessages(): Observable<PrivateMessage[]> {
    const headers = this.auth.getHeaders();

    return this.http.get<PrivateMessage[]>('/api/private-messages/outbox', { headers });
  }


}
