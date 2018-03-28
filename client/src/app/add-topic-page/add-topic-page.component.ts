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
    // edit
    if (this.route.snapshot.params.topicId) {
      this.topicService.getTopics(this.route.snapshot.params.subjectId).subscribe(topics => {
        this.topic = topics.find(topic => topic.id === parseInt(this.route.snapshot.params.topicId, 10));
        delete this.topic.user;
        delete this.topic.comments;
      });
    }
  }

  addTopic() {
    this.topicService.addTopic(this.route.snapshot.params.subjectId, this.topic).subscribe(() => {
      this.router.navigate(['subject', this.route.snapshot.params.subjectId]);
    });
  }
}
