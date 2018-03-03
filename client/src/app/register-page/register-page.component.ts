import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  register(details: {username: string, password: string}) {
    this.auth.register(details)
    .subscribe(res => {
      this.router.navigate(['']);
    });
  }

  keys(obj) {
    return Object.keys(obj);
  }
}
