import { Component, OnInit } from '@angular/core';
import { TopicService } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.css']
})
export class AddCommentComponent implements OnInit {
  comment = {
    title: '',
    body: ''
  };

  constructor(private topicService: TopicService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
  }

  addComment(): void {
    this.topicService.addComment(this.route.snapshot.params.topicId, this.comment).subscribe(() => {
      this.router.navigate(['topic', this.route.snapshot.params.topicId]);
    });
  }

}
