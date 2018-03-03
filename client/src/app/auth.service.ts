import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  private auth = null;

  public userDetails: BehaviorSubject<{id: number, username: string}> = new BehaviorSubject({id: null, username: null});

  constructor(private http: Http) { }

  register(details: {username: string, password: string}) {
    return this.http.post('/users', details)
    .map(res => res.json())
    .map(serverUserDetails => {
      this.userDetails.next({id: serverUserDetails, username: details.username});
      this.auth = 'Basic ' + btoa(`${details.username}:${details.password}`);

      return true;
    });
  }

  logout() {
    this.auth = null;
    this.userDetails.next({id: null, username: null});
  }
}
