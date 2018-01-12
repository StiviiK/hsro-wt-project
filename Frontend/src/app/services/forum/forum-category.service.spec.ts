import { TestBed, inject } from '@angular/core/testing';

import { ForumCategoryService } from './forum-category.service';

describe('ForumCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ForumCategoryService]
    });
  });

  it('should be created', inject([ForumCategoryService], (service: ForumCategoryService) => {
    expect(service).toBeTruthy();
  }));
});
