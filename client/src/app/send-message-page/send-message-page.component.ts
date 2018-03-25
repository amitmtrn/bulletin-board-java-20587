import { Component, OnInit } from '@angular/core';
import { PrivateMessagesService } from '../private-messages.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-send-message-page',
  templateUrl: './send-message-page.component.html',
  styleUrls: ['./send-message-page.component.css']
})
export class SendMessagePageComponent implements OnInit {

  privateMessage: any = {
    to: '',
    topic: '',
    body: ''
  };

  constructor(private pmService: PrivateMessagesService, private router: Router) { }

  ngOnInit() {
  }

  sendPrivateMessage() {
    this.pmService.sendPrivateMessage(this.privateMessage).subscribe(res => {
      this.router.navigate(['inbox']);
    });
  }
}
