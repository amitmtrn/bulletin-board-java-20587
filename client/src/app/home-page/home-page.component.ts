import { Component, OnInit } from '@angular/core';
import { TopicService, Topic } from '../topic.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  topics: Topic[] = [];

  constructor(private topicService: TopicService, private router: Router) { }

  ngOnInit() {
    this.updateTopics();
  }

  updateTopics() {
    this.topicService.getTopics().subscribe(topics => {
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

  addComment(topicId: number, comment: {title: string, body: string}): void {
    this.topicService.addComment(topicId, comment).subscribe(res => {
      this.updateTopics();
    });
  }

}
