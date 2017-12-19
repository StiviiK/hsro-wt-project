import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpageComponent } from './enterpage.component';

describe('EnterpageComponent', () => {
  let component: EnterpageComponent;
  let fixture: ComponentFixture<EnterpageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterpageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
