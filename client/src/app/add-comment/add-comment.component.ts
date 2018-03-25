import { Component, OnInit } from '@angular/core';
import { TopicService } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {
  comment: any = {
    title: '',
    body: ''
  };

  constructor(private topicService: TopicService, private router: Router, private route: ActivatedRoute) {
    if (route.snapshot.params.commentId) {
      this.topicService.getTopics().subscribe(topics => {
        const topic = topics.find(tp => tp.id === parseInt(this.route.snapshot.params.topicId, 10));
        this.comment = topic.comments.find(comment => comment.id === parseInt(this.route.snapshot.params.commentId, 10));
        delete this.comment.user;
      });
    }
  }

  ngOnInit() {
  }

  addComment(): void {
    this.topicService.addComment(this.route.snapshot.params.topicId, this.comment).subscribe(() => {
      this.router.navigate(['topic', this.route.snapshot.params.topicId]);
    });
  }

}
