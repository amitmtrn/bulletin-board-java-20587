import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  isLoggedIn = false;
  role = 'USER';
  username = '';

  constructor(private auth: AuthService) {

  }

  ngOnInit() {
    this.auth.getUserDetails().subscribe(userDetails => {
      if (userDetails.id !== null) {
        this.isLoggedIn = true;
      } else {
        this.isLoggedIn = false;
      }

      this.role = userDetails.role;
      this.username = userDetails.username;
    });
  }

  logout() {
    this.auth.logout();
  }
}
