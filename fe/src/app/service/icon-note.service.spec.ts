import { TestBed } from '@angular/core/testing';

import { IconNoteService } from './icon-note.service';

describe('IconNoteService', () => {
  let service: IconNoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IconNoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
