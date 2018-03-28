import { Component, OnInit } from '@angular/core';
import { ForumSubjectService, ForumSubject } from '../forum-subject.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {
  subjects: ForumSubject[] = [];

  constructor(private forumSubjectService: ForumSubjectService) { }

  ngOnInit() {
    this.forumSubjectService.getForumSubjects().subscribe(subjects => {
      this.subjects = subjects;
    });
  }

  addSubject(details) {
    this.forumSubjectService.addForumSubjects(details).subscribe(() => {
      this.ngOnInit();
    });
  }
}
