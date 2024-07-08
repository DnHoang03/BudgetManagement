import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconNoteInputComponent } from './icon-note-input.component';

describe('IconNoteInputComponent', () => {
  let component: IconNoteInputComponent;
  let fixture: ComponentFixture<IconNoteInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IconNoteInputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IconNoteInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
