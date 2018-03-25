import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PrivateMessagesService } from '../private-messages.service';

@Component({
  selector: 'app-private-messages-page',
  templateUrl: './private-messages-page.component.html',
  styleUrls: ['./private-messages-page.component.css']
})
export class PrivateMessagesPageComponent implements OnInit {
  data = this.route.snapshot.data;
  privateMessages = [];

  constructor(private route: ActivatedRoute, private pmService: PrivateMessagesService) { }

  ngOnInit() {
    switch (this.data.type) {
      case 'inbox':
        this.pmService.getInboxMessages().subscribe(inboxMessages => {
          this.privateMessages = inboxMessages;
        });
        break;
      case 'outbox':
        this.pmService.getOutboxMessages().subscribe(outboxMessages => {
          this.privateMessages = outboxMessages;
        });
        break;
    }

  }

  deletePrivateMessage(privateMessageId) {
    this.pmService.deletePrivateMessage(privateMessageId).subscribe(() => {
      this.ngOnInit();
    });
  }

}
