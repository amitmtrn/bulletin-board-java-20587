import { Component, OnInit } from '@angular/core';
import { Topic, TopicService } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService, User } from '../auth.service';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css']
})
export class TopicComponent implements OnInit {
  topic: Topic = {
    id: 0,
    title: '',
    body: '',
    comments: []
  };
  subjectId: string;
  user: User;
  page = 1;

  constructor(
    private topicService: TopicService,
    private router: Router,
    private route: ActivatedRoute,
    private auth: AuthService
  ) {
    this.subjectId = this.route.snapshot.params.subjectId;
  }

  next() {
    this.page ++;
  }

  previous() {
    this.page --;
  }

  ngOnInit() {
    this.auth.getUserDetails().subscribe(userDetails => {
      this.user = userDetails;
    });

    this.updateTopics();
  }

  updateTopics() {
    this.topicService.getTopics(this.subjectId).subscribe(topics => {
      this.topic = topics.find(topic => topic.id === parseInt(this.route.snapshot.params.topicId, 10));
    });
  }

  deleteTopic(topicId: number) {
    const isDeleting = confirm('are you sure you want to delete this topic??');

    if (isDeleting) {
      this.topicService.deleteTopic(topicId).subscribe(res => {
        this.router.navigate(['subject', this.subjectId]);
      });
    }
  }

  deleteComment(commentId: string) {
    this.topicService.deleteComment(commentId).subscribe(() => {
      this.updateTopics();
    });
  }

}
