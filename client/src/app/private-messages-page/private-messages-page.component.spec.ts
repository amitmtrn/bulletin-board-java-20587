import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivateMessagesPageComponent } from './private-messages-page.component';

describe('PrivateMessagesPageComponent', () => {
  let component: PrivateMessagesPageComponent;
  let fixture: ComponentFixture<PrivateMessagesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrivateMessagesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrivateMessagesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
