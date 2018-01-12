import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThreadAnswerComponent } from './thread-answer.component';

describe('ThreadAnswerComponent', () => {
  let component: ThreadAnswerComponent;
  let fixture: ComponentFixture<ThreadAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThreadAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThreadAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
