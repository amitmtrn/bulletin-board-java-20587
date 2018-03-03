import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private isLoggedIn = false;
  private username = '';

  constructor(private auth: AuthService) {

  }

  ngOnInit() {
    this.auth.userDetails.subscribe(userDetails => {
      if (userDetails.id !== null) {
        this.isLoggedIn = true;
      } else {
        this.isLoggedIn = false;
      }

      this.username = userDetails.username;
    });
  }

  logout() {
    this.auth.logout();
  }
}
