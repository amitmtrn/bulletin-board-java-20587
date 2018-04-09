import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ForumSubject, ForumSubjectService } from '../forum-subject.service';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  subjects: ForumSubject[] = [];

  constructor(private subjectService: ForumSubjectService, private router: Router) { }

  ngOnInit() {
    this.updateTopics();
  }

  updateTopics() {
    this.subjectService.getForumSubjects().subscribe(subjects => {
      this.subjects = subjects;
    });
  }
}
