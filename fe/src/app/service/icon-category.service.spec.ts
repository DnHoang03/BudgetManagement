import { TestBed } from '@angular/core/testing';

import { IconCategoryService } from './icon-category.service';

describe('IconCategoryService', () => {
  let service: IconCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IconCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
