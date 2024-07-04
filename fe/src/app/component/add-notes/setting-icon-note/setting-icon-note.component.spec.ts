import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SettingIconNoteComponent } from './setting-icon-note.component';

describe('SettingIconNoteComponent', () => {
  let component: SettingIconNoteComponent;
  let fixture: ComponentFixture<SettingIconNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SettingIconNoteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SettingIconNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
