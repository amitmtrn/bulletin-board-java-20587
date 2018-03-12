import { Component, OnInit } from '@angular/core';
import { Topic, TopicService } from '../topic.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-topic-page',
  templateUrl: './add-topic-page.component.html',
  styleUrls: ['./add-topic-page.component.css']
})
export class AddTopicPageComponent implements OnInit {

  constructor(private topicService: TopicService, private router: Router) { }

  ngOnInit() {
  }

  addTopic(topic: Topic) {
    this.topicService.addTopic(topic).subscribe(() => {
      this.router.navigate(['']);
    });
  }
}
