import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import { LocalStorage, LocalStorageService } from 'angular2-localstorage';

import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  @LocalStorage() private auth: string = null;
  private userDetails: BehaviorSubject<{id: number, username: string}> = new BehaviorSubject({id: null, username: null});

  constructor(private http: HttpClient, private localstorage: LocalStorageService) {
    const userInfo = localstorage.get('userInfo');

    if (userInfo) {
      this.userDetails.next(userInfo);
    }
  }

  public getHeaders(): HttpHeaders {
    return new HttpHeaders({
      Authorization: this.auth
    });
  }

  getUserDetails(): Observable<{id: number, username: string}> {
    return this.userDetails;
  }

  setUserDetails(userDetails: {id: number, username: string}) {
    this.userDetails.next(userDetails);
    this.localstorage.set('userInfo', userDetails);
  }

  register(details: {username: string, password: string}): Observable<boolean> {
    return this.http.post('/api/users', details)
    .map((serverUserDetails: {id: number, password: string}) => {
      this.auth = 'Basic ' + btoa(`${details.username}:${details.password}`);
      this.setUserDetails({id: serverUserDetails.id, username: details.username});

      return true;
    });
  }

  login(userDetails: {username: string, password: string}): Observable<boolean> {
    const auth = 'Basic ' + btoa(`${userDetails.username}:${userDetails.password}`);
    const headers = new HttpHeaders({
      Authorization: auth
    });

    return this.http.get('/api/users/me', { headers })
    .map((serverUserDetails: {id: number, username: string}) => {
      this.setUserDetails({id: serverUserDetails.id, username: userDetails.username});
      this.auth = auth;

      return true;
    });
  }

  logout(): void {
    this.auth = null;
    this.setUserDetails({id: null, username: null});
  }
}
