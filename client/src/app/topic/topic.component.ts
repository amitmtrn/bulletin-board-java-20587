import { Component, OnInit } from '@angular/core';
import { Topic, TopicService } from '../topic.service';
import { Router, ActivatedRoute } from '@angular/router';

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

  constructor(private topicService: TopicService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.updateTopics();
  }

  updateTopics() {
    this.topicService.getTopics().subscribe(topics => {
      this.topic = topics.find(topic => topic.id === parseInt(this.route.snapshot.params.id, 10));
    });
  }

  deleteTopic(topicId: number) {
    const isDeleting = confirm('are you sure you want to delete this topic??');

    if (isDeleting) {
      this.topicService.deleteTopic(topicId).subscribe(res => {
        this.router.navigate(['']);
      });
    }
  }

  deleteComment(commentId: string) {
    this.topicService.deleteComment(commentId).subscribe(() => {
      this.updateTopics();
    });
  }

}
