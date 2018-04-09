import { Component, OnInit } from '@angular/core';
import { TopicService, Topic, Comment } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService, User } from '../auth.service';

@Component({
  selector: 'app-forum-page',
  templateUrl: './forum-page.component.html',
  styleUrls: ['./forum-page.component.css']
})
export class ForumPageComponent implements OnInit {
  topics: Topic[] = [];
  subjectId: number;
  user: User;
  page = 1;

  constructor(
    private topicService: TopicService,
    private router: Router,
    private route: ActivatedRoute,
    private auth: AuthService
  ) {
    this.subjectId = route.snapshot.params.subjectId;
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
      this.topics = topics.reverse();
    });
  }

  deleteTopic(topicId: number) {
    const isDeleting = confirm('are you sure you want to delete this topic??');

    if (isDeleting) {
      this.topicService.deleteTopic(topicId).subscribe(res => {
        this.updateTopics();
      });
    }
  }

  addComment(topicId: number, comment: Comment): void {
    this.topicService.addComment(topicId, comment).subscribe(res => {
      this.updateTopics();
    });
  }

  deleteComment(commentId: string) {
    this.topicService.deleteComment(commentId).subscribe(() => {
      this.updateTopics();
    });
  }

}
