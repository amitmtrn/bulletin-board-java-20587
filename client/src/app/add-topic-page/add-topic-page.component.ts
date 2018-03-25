import { Component, OnInit } from '@angular/core';
import { Topic, Comment, TopicService } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-topic-page',
  templateUrl: './add-topic-page.component.html',
  styleUrls: ['./add-topic-page.component.css']
})
export class AddTopicPageComponent implements OnInit {
  topic: any = {
    title: '',
    body: ''
  };

  constructor(private topicService: TopicService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    if (this.route.snapshot.params.id) {
      this.topicService.getTopics().subscribe(topics => {
        this.topic = topics.find(topic => topic.id === parseInt(this.route.snapshot.params.id, 10));
        delete this.topic.user;
        delete this.topic.comments;
      });
    }
  }

  addTopic() {
    this.topicService.addTopic(this.topic).subscribe(() => {
      this.router.navigate(['']);
    });
  }
}
