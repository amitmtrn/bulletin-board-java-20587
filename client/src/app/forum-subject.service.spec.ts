import { TestBed, inject } from '@angular/core/testing';

import { ForumSubjectService } from './forum-subject.service';

describe('ForumSubjectService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ForumSubjectService]
    });
  });

  it('should be created', inject([ForumSubjectService], (service: ForumSubjectService) => {
    expect(service).toBeTruthy();
  }));
});
