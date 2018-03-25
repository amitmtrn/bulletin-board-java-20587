import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SendMessagePageComponent } from './send-message-page.component';

describe('SendMessagePageComponent', () => {
  let component: SendMessagePageComponent;
  let fixture: ComponentFixture<SendMessagePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SendMessagePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SendMessagePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
