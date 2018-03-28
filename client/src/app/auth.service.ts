import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import { LocalStorage, LocalStorageService } from 'angular2-localstorage';

import 'rxjs/add/operator/map';

export interface User {
  id?: number;
  username: string;
  password?: string;
  role?: string;
}

@Injectable()
export class AuthService {
  @LocalStorage() private auth: string = null;
  private userDetails: BehaviorSubject<User> = new BehaviorSubject({id: null, username: null, role: null});

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

  getUserDetails(): Observable<User> {
    return this.userDetails;
  }

  setUserDetails(userDetails: User) {
    this.userDetails.next(userDetails);
    this.localstorage.set('userInfo', userDetails);
  }

  register(details: User): Observable<boolean> {
    return this.http.post('/api/users', details)
    .map((serverUserDetails: User) => {
      this.auth = 'Basic ' + btoa(`${details.username}:${details.password}`);
      this.setUserDetails({
        id: serverUserDetails.id,
        username: details.username,
        role: serverUserDetails.role
      });

      return true;
    });
  }

  login(userDetails: User): Observable<boolean> {
    const auth = 'Basic ' + btoa(`${userDetails.username}:${userDetails.password}`);
    const headers = new HttpHeaders({
      Authorization: auth
    });

    return this.http.get('/api/users/me', { headers })
    .map((serverUserDetails: User) => {
      this.setUserDetails({
        id: serverUserDetails.id,
        username: userDetails.username,
        role: serverUserDetails.role
      });
      this.auth = auth;

      return true;
    });
  }

  logout(): void {
    this.auth = null;
    this.setUserDetails({id: null, username: null, role: null});
  }
}
