import { Component, OnInit } from '@angular/core';
import { TopicService, Topic, Comment } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-forum-page',
  templateUrl: './forum-page.component.html',
  styleUrls: ['./forum-page.component.css']
})
export class ForumPageComponent implements OnInit {
  topics: Topic[] = [];
  subjectId: number;

  constructor(private topicService: TopicService, private router: Router, private route: ActivatedRoute) {
    this.subjectId = route.snapshot.params.subjectId;
  }

  ngOnInit() {
    this.updateTopics();
  }

  updateTopics() {
    this.topicService.getTopics(this.subjectId).subscribe(topics => {
      this.topics = topics;
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
